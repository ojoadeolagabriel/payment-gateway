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
    def "confirm VertxTaskService processes requests correctly"() {
        given:
        TestPayload load
        String bootStrapServers = kafka.getBootstrapServers()
        def service = TaskServiceFactory.create(TaskType.VERTX, [
                (TaskParam.NAME)               : "appName",
                (TaskParam.KAFKA_MESSAGE_GROUP): "defaultGroup",
                (TaskParam.INSTANCE_COUNT)     : "1",
                (TaskParam.KAFKA_HOST_IP_PORT) : bootStrapServers,
        ])

        and: "building test event"
        Event event = buildEvent()

        and: "starting task and completion handler"
        service.startTask(event, {
            c -> handleTaskCompletion(c)
        })

        when: "handle incoming work"
        service.processTask(event.getTopic(), {
            payload ->
                try {
                    load = MapperUtil.mapper.readValue(payload, TestPayload)
                    return EventState.SUCCESS
                } catch (Exception e) {
                    return EventState.FAILED
                }
        })
        then:
        asyncCondition.await(60)
        load
        load.name == "adeola.ojo"
        load.amount == 1200
    }

    private static Event buildEvent() {
        Event event = new Event()
        event.setTopic("onTestPayload")
        TestPayload testPayload = new TestPayload()
        testPayload.setName("adeola.ojo")
        testPayload.setAmount(1200)
        event.setMessage(MapperUtil.mapper.writeValueAsString(testPayload))
        return event
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
