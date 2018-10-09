package io.edge.service

import io.edge.service.filters.SimpleFilter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.Bean

@EnableZuulProxy
@SpringBootApplication
class App {
	static void main(String[] args) {
		SpringApplication.run(App, args)
	}

	@Bean
	SimpleFilter simpleFilter() {
		return new SimpleFilter()
	}
}
