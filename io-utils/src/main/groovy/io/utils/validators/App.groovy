package io.utils.validators

import groovy.transform.builder.Builder

class App {
	static void main(String[] args) {
		List<Person> list = [
		        Person.builder().age(100).build(),
		        Person.builder().age(200).build(),
		        Person.builder().age(300).build(),
		]

		List<Person> list2 = null
		println list2?.age?.join(",")
	}
}

@Builder
class Person {
	int age
}