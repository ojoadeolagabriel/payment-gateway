package io.synapse.tasks.basic

import spock.lang.Specification
import spock.lang.Unroll


class DudSpec extends Specification {
	@Unroll
	def "confirm optionals #input now"() {
		expect:
			process(input?.length())
		where:
			input || output
			"12"  || true
			null  || false
	}

	boolean process(Long a) {
		if (a > 0)
			return true
		return false
	}

	boolean process(Object a){
		return false
	}
}
