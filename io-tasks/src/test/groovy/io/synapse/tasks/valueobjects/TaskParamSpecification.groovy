package io.synapse.tasks.valueobjects

import spock.lang.Specification
import spock.lang.Unroll

class TaskParamSpecification extends Specification {
    def setup() {

    }

    @Unroll
    def "confirm codes #testCode"() {
        expect:
        testCode != null
        where:
        testCode << TaskParam.values()
    }
}
