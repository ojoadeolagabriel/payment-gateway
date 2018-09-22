package io.synapse.tasks.valueobjects

enum TaskParam {
    NAME("name"),
    EVENT_BUS_NAME("event_bus_name"),
    DATASOURCE_URL("datasource_url"),
    USERNAME("username"),
    INSTANCE_COUNT("instance_count"),
    KAFKA_HOST_IP_PORT("kafka_host_ip_config"),
    KAFKA_MESSAGE_GROUP("kafka_message_group")

    String code
    TaskParam(String code){
        this.code = code
    }
}
