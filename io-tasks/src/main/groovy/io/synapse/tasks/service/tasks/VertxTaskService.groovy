package io.synapse.tasks.service.tasks

import io.synapse.tasks.config.KafkaConfig
import io.synapse.tasks.handler.TaskCompletionHandler
import io.synapse.tasks.handler.TaskProcessorHandler
import io.synapse.tasks.util.EventState
import io.synapse.tasks.util.MapperUtil
import io.synapse.tasks.util.TaskConventionUtil
import io.synapse.tasks.valueobjects.Event
import io.synapse.tasks.valueobjects.TaskParam
import io.vertx.kafka.client.consumer.KafkaConsumer
import io.vertx.kafka.client.producer.KafkaProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VertxTaskService implements TaskService {

    private final Logger log = LoggerFactory.getLogger(VertxTaskService)

    String name
    Map<TaskParam, String> configuration
    KafkaConfig kafkaConfig
    TaskCompletionHandler processCompletionHandler

    VertxTaskService(Map<TaskParam, String> configuration) {
        this.configuration = configuration
        log.info("starting ${VertxTaskService.getCanonicalName()}")
    }

    def buildKafkaConfig() {
        name = configuration.get(TaskParam.NAME)
        def bootStrapServerConfig = configuration.get(TaskParam.KAFKA_HOST_IP_PORT)
        def messageGroup = configuration.get(TaskParam.KAFKA_MESSAGE_GROUP)
        if (!kafkaConfig)
            kafkaConfig = new KafkaConfig(bootStrapServerConfig, messageGroup)
    }

    @Override
    void startTask(Event event, TaskCompletionHandler handler) {
        processCompletionHandler = handler
        buildKafkaConfig()
        processTask(event)
        log.info("sending message: ${event.message}, for topic ${event.topic}")
    }

    @Override
    void processTask(String topic, TaskProcessorHandler processorHandler) {
        buildKafkaConfig()

        kafkaConfig.kafkaConsumer().subscribe(topic, { handler ->
            if (handler.succeeded()) {
                log.info("subscription to topic: $topic successful")

                kafkaConfig.kafkaConsumer().handler({ record ->
                    Event event = MapperUtil.mapper.readValue(record.value(), Event)
                    EventState state = timedAction(event)
                    def responseTopic = TaskConventionUtil.getResponseTopicDescription(topic)
                    kafkaConfig.kafkaProducer().write(KafkaProducerRecord.create(responseTopic, UUID.randomUUID().toString(), state.toString()))
                })
            }else{
                log.info("subscription to topic: $topic failed with, ${handler.cause().getMessage()}")
            }
        })
    }

    private EventState timedAction(Event event) {
        kafkaConfig.vertx.setTimer(5000, {
            timedHandler ->
        })
        processorHandler.handle(event.message)
    }

    void processTask(Event event) {
        try {
            //build payload
            if(!event?.key)
                event?.key = UUID.randomUUID().toString()

            def payload = MapperUtil.mapper.writeValueAsString(event)

            //handle task completion
            def responseTopic = TaskConventionUtil.getResponseTopicDescription(event.getTopic())
            kafkaConfig.kafkaResponseConsumer().subscribe(responseTopic, { handler ->
                if (handler.succeeded()) {
                    log.info("subscription to topic: $responseTopic successful")
                    kafkaConfig.kafkaResponseConsumer().handler({ record ->
                        def responseBody = record.value()
                        def state = EventState.valueOf(responseBody)

                        log.info("trigger detected: $state")
                        processCompletionHandler.handle(state)
                    })
                } else {
                    log.info("failed to subscribe to topic: $event.topic with ${handler.cause().getMessage()}")
                }
            })

            //forward task
            def record = KafkaProducerRecord.create(event.getTopic(), UUID.randomUUID().toString(), payload)
            kafkaConfig.kafkaProducer().write(record, { handler ->
                if (handler.succeeded()) {
                    log.info("successfully forwarded ${event.message} to topic: $event.topic")
                } else {
                    log.info("failed to forwarded to topic: $event.topic with ${handler.cause().getMessage()}")
                }
            })
        } catch (Exception e) {
            log.error(e.getMessage())
        }
    }
}
