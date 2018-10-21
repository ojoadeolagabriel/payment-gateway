package io.api.bouncer.scheduler

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@EnableScheduling
@Configuration
class SchedulerActions {

	@Scheduled(fixedDelay = 5000L)
	void run(){

	}
}
