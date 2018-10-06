package io.synapse.tasks.service

import io.synapse.tasks.service.tasks.TaskService
import io.synapse.tasks.service.tasks.TaskServiceFactory
import io.synapse.tasks.valueobjects.TaskParam
import io.synapse.tasks.valueobjects.TaskType
import spock.lang.Specification
import spock.lang.Unroll

class TaskServiceFactorySpecification extends Specification {
	def "setup"() {

	}

	@Unroll
	@SuppressWarnings("GroovyAssignabilityCheck")
	def "confirm taskService types"() {
		given:
			def config = [(TaskParam.NAME): "testApp"]
		expect:
			TaskServiceFactory.create(taskType, config) instanceof TaskService
		where:
			taskType << TaskType.values()
	}
}
