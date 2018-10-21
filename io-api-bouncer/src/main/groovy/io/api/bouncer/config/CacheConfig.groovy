package io.api.bouncer.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct

@Configuration
class CacheConfig {
	@Value('${message}')
	String message

	@PostConstruct
	void init(){
		System.out.println("found [xxxx]: ${message}")
	}
}
