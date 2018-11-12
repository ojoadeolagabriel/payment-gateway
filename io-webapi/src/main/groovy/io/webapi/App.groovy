package io.webapi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

@SpringBootApplication(exclude = [ DataSourceAutoConfiguration.class ])
class App {
	static void main(String[] args) {
		SpringApplication.run(App, args)
	}
}
