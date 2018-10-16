package io.utils

import io.utils.validators.FluentValidator
import io.utils.validators.TestObject
import io.utils.validators.contract.ValidationResult

import java.util.stream.Collector
import java.util.stream.Collectors

import static io.utils.validators.contract.ValidationResult.State.FAILED
import static io.utils.validators.contract.ValidationResult.State.SUCCESS
import static java.lang.System.out

class App {
	static void main(String[] args) {
		def validator = new FluentValidator<TestObject>()
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

		if (resultContext.isSuccessful()) {
			out.println("concluded successfully!");
		} else {
			out.println "errors detected: " + resultContext
					.getResultList()
					.stream()
					.map({ c -> c.errorMessage }).collect(Collectors.joining(", ") as Collector<? super String, ?, String>)
		}

	}
}
