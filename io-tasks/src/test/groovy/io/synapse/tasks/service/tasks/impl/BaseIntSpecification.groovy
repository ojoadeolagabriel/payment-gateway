package io.synapse.tasks.service.tasks.impl

import lombok.extern.slf4j.Slf4j
import org.junit.ClassRule
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.KafkaContainer
import spock.lang.Specification

@Slf4j
class BaseIntSpecification extends Specification {
	@Rule
	public KafkaContainer kafka = new KafkaContainer("4.1.2")
	static String MAGIC_NUMBER = 1


	@ClassRule
	public static GenericContainer alpine =
			new GenericContainer("alpine:3.2")
					.withExposedPorts(80)
					.withEnv("MAGIC_NUMBER", "42")
					.withCommand("/bin/sh", "-c",
					"while true; do echo '$MAGIC_NUMBER' | nc -l -p 80; done")

	@ClassRule
	public static GenericContainer simpleWebServer= new GenericContainer("alpine:3.2")
			.withExposedPorts(8080)
			.withCommand("/bin/sh", "-c", "while true; do echo "
			+ "\"HTTP/1.1 200 OK\n\nHello World!\" | nc -l -p 80; done")

	@Rule
	TemporaryFolder temporaryFolder = new TemporaryFolder()
}
