package io.utils;

import io.utils.validators.fluent.FluentValidator;
import io.utils.validators.fluent.TestObject;
import io.utils.validators.contract.ResultContext;
import io.utils.validators.contract.ValidationResult;

import java.util.stream.Collectors;

import static java.lang.System.out;

public class FluentJavaTest {
	void test(){
		FluentValidator<TestObject> validator = new FluentValidator<>();
		ResultContext resultContext = validator.checkAll(new TestObject())
				.on(c -> {
					if (c.getAddress() == null)
						return new ValidationResult("invalid address", ValidationResult.State.FAILED);
					else
						return new ValidationResult();
				})
				.on(c -> {
					if (c.getEmail() == null)
						return new ValidationResult("invalid email", ValidationResult.State.FAILED);
					else
						return new ValidationResult();
				})
				.validate();

		if (resultContext.isSuccessful()) {
			out.println("concluded successfully!");
		} else {
			out.println("errors detected: " + resultContext
					.getResultList()
					.stream()
					.map(ValidationResult::getErrorMessage)
					.collect(Collectors.joining(", ")));
		}
	}
}
