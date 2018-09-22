package io.synapse.tasks.util

import spock.lang.Specification
import spock.lang.Unroll

class TaskConventionUtilTest extends Specification {
    def "setup"() {

    }

    @Unroll
    def "confirm getResponseTopicDescription() returns valid descriptor for '#topicLabel'"() {
        expect:
        TaskConventionUtil.getResponseTopicDescription(topicLabel) == String.format("%s-%s", topicLabel, TaskConventionUtil.RESPONSE_TOPIC_POSTFIX)
        where:
        topicLabel       || underscoreCount
        "topicX"         || 1
        "test-topic"     || 1
        "morpheus-topic" || 1
    }

    @Unroll
    def "confirm getTaskBusDescription() returns event bus descriptor for '#bus'"() {
        expect:
        TaskConventionUtil.getTaskBusDescription(bus) == String.format("%s-%s", bus, TaskConventionUtil.EVENT_BUS_POSTFIX)
        where:
        bus              || underscoreCount
        "pipeline"       || 1
        "test-topic"     || 1
        "morpheus-topic" || 1
    }
}
