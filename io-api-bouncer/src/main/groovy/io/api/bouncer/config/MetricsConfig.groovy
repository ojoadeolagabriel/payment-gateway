package io.api.bouncer.config

import com.codahale.metrics.MetricRegistry
import org.springframework.aop.framework.AopContext
import org.springframework.context.annotation.Configuration

@Configuration
class MetricsConfig {
	MetricRegistry metrics = new MetricRegistry()
	void init(){

	}
}
