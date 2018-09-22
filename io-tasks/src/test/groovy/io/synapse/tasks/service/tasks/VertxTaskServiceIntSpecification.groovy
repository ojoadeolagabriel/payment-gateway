package io.synapse.tasks.service.tasks

import io.synapse.tasks.domain.TestPayload
import io.synapse.tasks.util.EventState
import io.synapse.tasks.util.MapperUtil
import io.synapse.tasks.valueobjects.Event
import io.synapse.tasks.valueobjects.TaskParam
import io.synapse.tasks.valueobjects.TaskType
import org.junit.Rule
import org.testcontainers.containers.KafkaContainer
import spock.lang.Specification
import spock.lang.Unroll
import spock.util.concurrent.AsyncConditions

class VertxTaskServiceIntSpecification extends Specification {
    @Rule
    public KafkaContainer kafka = new KafkaContainer("4.1.2")
    def asyncCondition = new AsyncConditions(1)

    def setup() {

    }

    @Unroll
    def "do nothing"() {
        given:
        String bootStrapServers = kafka.getBootstrapServers()
        def service = TaskServiceFactory.create(TaskType.VERTX, [
                (TaskParam.NAME)               : "appName",
                (TaskParam.KAFKA_MESSAGE_GROUP): "defaultGroup",
                (TaskParam.INSTANCE_COUNT)     : "1",
                (TaskParam.KAFKA_HOST_IP_PORT) : bootStrapServers,
        ])

        and: "building event"
        Event event = buildEvent()

        and: "starting task pipeline"
        service.startTask(event, {
            c -> handleTaskCompletion(c)
        })

        when: "processing work"
        service.processTask(event.getTopic(), {
            payload ->
                try {
                    TestPayload load = MapperUtil.mapper.readValue(payload, TestPayload)
                    return EventState.SUCCESS
                } catch (Exception e) {
                    return EventState.FAILED
                }
        })
        then:
        asyncCondition.await(60)
    }

    private static Event buildEvent() {
        Event event = new Event()
        event.setTopic("onTestPayload")
        TestPayload testPayload = new TestPayload()
        testPayload.setName("adeola.ojo")
        testPayload.setAmount(1200)
        event.setMessage(MapperUtil.mapper.writeValueAsString(testPayload))
    }

    def handleTaskCompletion(EventState eventState) {
        switch (eventState) {
            case EventState.SUCCESS:
                break
            case EventState.FAILED:
                break
            case EventState.RETRY:
                break
        }
        asyncCondition.evaluate({

        })
    }
}
