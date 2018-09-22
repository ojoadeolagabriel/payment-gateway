package io.synapse.tasks.valueobjects

class Event {
    String key
    String topic
    String message
    String partition
    Map<String, String> headers = new HashMap<>()
}
