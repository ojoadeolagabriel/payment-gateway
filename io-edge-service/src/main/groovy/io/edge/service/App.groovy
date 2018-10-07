package io.edge.service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso

@EnableOAuth2Sso
@SpringBootApplication
class App {
	static void main(String[] args) {
		SpringApplication.run(App, args)
	}
}
