package io.synapse.tasks.assets

import lombok.RequiredArgsConstructor
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@RequiredArgsConstructor
class MockRule implements TestRule {
	private final Object target

	@Override
	Statement apply(Statement statement, Description description) {
		return new Statement() {
			@Override
			void evaluate() throws Throwable {

				evaluate()
			}
		}
	}
}
