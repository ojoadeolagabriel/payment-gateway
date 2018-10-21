package io.edge.service.config

import io.edge.service.filters.SimpleFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ZuulFilterConfig {
	@Bean
	SimpleFilter simpleFilter() {
		return new SimpleFilter()
	}
}
