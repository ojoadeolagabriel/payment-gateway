package io.utils

import io.utils.validators.contract.ValidationResult
import io.utils.validators.fluent.FluentValidator
import io.utils.validators.fluent.TestObject
import spock.lang.Specification

import static io.utils.validators.contract.ValidationResult.State.FAILED
import static io.utils.validators.contract.ValidationResult.State.SUCCESS
import static java.lang.System.out

class FluentGroovyTest extends Specification {
	def "given new validator confirm that FluentValidator returns successful response"() {
		given: "new validator"
			def validator = new FluentValidator<TestObject>()
		when: "when validator is initializer on TestObject"
			def resultContext = validator
					.checkAll(new TestObject())
					.on(
					{ c ->
						if (c.email == null) {
							return new ValidationResult("failed email validation", FAILED)
						} else {
							return new ValidationResult("success", SUCCESS)
						}
					})
					.on(
					{ c ->
						if (c.address == null) {
							return new ValidationResult("failed address validation", FAILED)
						} else {
							return new ValidationResult("success", SUCCESS)
						}
					})
					.validate()
		then: "confirm that result context is successful"
			if (resultContext.isSuccessful()) {
				out.println("concluded successfully!")
			} else {
				out.println "errors detected: " + resultContext.getResultList()?.errorMessage?.join(",")
			}
	}
}
