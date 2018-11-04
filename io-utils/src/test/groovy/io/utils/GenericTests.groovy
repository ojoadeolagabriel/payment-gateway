package io.utils

import groovy.transform.Canonical
import groovy.transform.ToString
import spock.lang.Specification

class GenericTests extends Specification {
	def setup() {

	}

	def "person test"() {
		given:
			def testPerson = new Person("dex", "1")
			Person[] persons =
					[
							["name_1", "id_1"],
							["name_2", "id_2"],
					]
		expect:
			persons.size() == 2
	}
}

@Canonical
@ToString(excludes = "idx")
class Person {
	String name, id
}
