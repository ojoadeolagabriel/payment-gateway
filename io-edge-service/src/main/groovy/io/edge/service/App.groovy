package io.edge.service

import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@EnableZuulProxy
@SpringBootApplication
class App {
	static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class)
		app.setBannerMode(Banner.Mode.OFF)
		app.run(args)
	}
}
