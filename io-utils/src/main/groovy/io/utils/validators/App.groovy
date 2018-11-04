package io.utils.validators

class App {

	static void process(String m){

	}
	static void main(String[] args) {
		Person p = new Person()
		String[] items = Collections.emptyList()

		Person.metaClass.constructor << {
			String par ->
				Person de = new Person()
				de.metaClass.par = "dex"
		}

		Person dd = new Person("ddd")

		use(PersonCategory) {
			p.employPerson("")
		}
	}
}

class PersonCategory {
	static void employPerson(Person person, String state) {
		person.metaClass.@"stateOfStaff" = state
	}
}

class Person {
	void sackPerson() {

	}
}