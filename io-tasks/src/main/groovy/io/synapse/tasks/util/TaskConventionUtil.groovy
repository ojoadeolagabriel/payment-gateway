package io.synapse.tasks.util

class TaskConventionUtil {
    static final String RESPONSE_TOPIC_POSTFIX = "response-topic"
    static final String EVENT_BUS_POSTFIX = "task::event::bus-processor::"

    static String getResponseTopicDescription(String originalRequestTopicDescription) {
        "$originalRequestTopicDescription-$RESPONSE_TOPIC_POSTFIX"
    }

    static String getTaskBusDescription(String messageBusDescription) {
        "${messageBusDescription?.toLowerCase()}-$EVENT_BUS_POSTFIX"
    }
}
