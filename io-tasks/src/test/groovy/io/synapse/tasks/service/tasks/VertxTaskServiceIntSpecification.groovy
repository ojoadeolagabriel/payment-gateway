package io.synapse.tasks.service.tasks

import io.synapse.tasks.domain.TestPayload
import io.synapse.tasks.util.EventState
import io.synapse.tasks.util.MapperUtil
import io.synapse.tasks.valueobjects.Event
import io.synapse.tasks.valueobjects.TaskParam
import io.synapse.tasks.valueobjects.TaskType
import org.junit.Rule
import org.testcontainers.containers.KafkaContainer
import spock.lang.See
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll
import spock.util.concurrent.AsyncConditions

@Title("A Vertx task service integration specification")
@See("http://localhost:8700/docs/vertx/int/specs")
class VertxTaskServiceIntSpecification extends Specification {

    @Rule
    public KafkaContainer kafka = new KafkaContainer("4.1.2")
    def asyncCondition = new AsyncConditions(1)

    def setup() {

    }

    @Unroll
    def "confirm VertxTaskService processes requests correctly"() {
        given:
        TestPayload load = null
        def service = TaskServiceFactory.create(TaskType.VERTX, [
                (TaskParam.NAME)               : "appName",
                (TaskParam.KAFKA_MESSAGE_GROUP): "defaultGroup",
                (TaskParam.INSTANCE_COUNT)     : "1",
                (TaskParam.KAFKA_HOST_IP_PORT) : kafka.getBootstrapServers(),
        ])

        and: "building test event"
        Event event = buildEvent(name, amount, topic)

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
        asyncCondition.await(30)
        load.name == name
        load.amount == amount
        where:
        name         | amount | topic
        "adeola.ojo" | 12001  | "topic-1"
        "apple.ojo"  | 12002  | "topic-2"
    }

    private static Event buildEvent(String name, Integer amount, String topic) {
        Event event = new Event()
        event.setTopic(topic)
        TestPayload testPayload = new TestPayload()
        testPayload.setName(name)
        testPayload.setAmount(amount)
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
