package io.synapse.tasks.service.tasks

import io.synapse.tasks.service.tasks.impl.BaseIntSpecification
import spock.lang.See
import spock.lang.Title
import spock.lang.Unroll

@Title("ignite task services integration feature suite")
@See("")
class IgniteTaskServiceIntegrationSpecification extends BaseIntSpecification {
	def setup(){

	}

	@Unroll("processing a: #a, b: #b, c: #c")
	def process(){
		where:
			a << [3, 7, 0]
			b << [2, 6, -1]
			c << [1, 5, 9]
	}
}
