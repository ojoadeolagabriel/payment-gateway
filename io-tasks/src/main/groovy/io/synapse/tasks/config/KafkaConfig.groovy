package io.synapse.tasks.config

import io.vertx.core.Vertx
import io.vertx.kafka.client.consumer.KafkaConsumer
import io.vertx.kafka.client.producer.KafkaProducer

class KafkaConfig {
    static Vertx vertx
    private KafkaConsumer<String, String> consumer
    private KafkaConsumer<String, String> consumerResponse
    private KafkaProducer<String, String> producer
    String bootstrapServers
    String messageGroup

    Vertx getVertx() {
        if (vertx == null) {
            vertx = Vertx.vertx()
        }
        return vertx
    }

    KafkaConfig(String bootstrapServers, String messageGroup) {
        this.bootstrapServers = bootstrapServers
        this.messageGroup = messageGroup
    }

    KafkaConsumer<String, String> kafkaConsumer() {
        if (consumer == null) {
            Map<String, String> config = new HashMap<>()
            config.put("bootstrap.servers", bootstrapServers)
            config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
            config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
            config.put("group.id", messageGroup)
            config.put("auto.offset.reset", "earliest")
            config.put("enable.auto.commit", "true")
            consumer = KafkaConsumer.create(getVertx(), config)
        }
        return consumer
    }

    KafkaConsumer<String, String> kafkaResponseConsumer() {
        if (consumerResponse == null) {
            Map<String, String> config = new HashMap<>()
            config.put("bootstrap.servers", bootstrapServers)
            config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
            config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
            config.put("group.id", messageGroup)
            config.put("auto.offset.reset", "earliest")
            config.put("enable.auto.commit", "true")
            consumerResponse = KafkaConsumer.create(getVertx(), config)
        }
        return consumerResponse
    }

    KafkaProducer<String, String> kafkaProducer() {
        if (Objects.isNull(producer)) {
            Map<String, String> config = new HashMap<>()
            config.put("bootstrap.servers", bootstrapServers)
            config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
            config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
            config.put("acks", "1")
            producer = KafkaProducer.createShared(getVertx(), "shared-kafka-producer-" + UUID.randomUUID().toString(), config)
        }
        return producer
    }
}
