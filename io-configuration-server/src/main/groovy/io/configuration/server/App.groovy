package io.configuration.server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
class App {
	static void main(String[] args) {
		SpringApplication.run(App, args)
	}
}
