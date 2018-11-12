package core

import io.webapi.api.PaymentProfileController
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

@TestConfiguration
class TestSpringConfiguration {
	private DetachedMockFactory factory = new DetachedMockFactory()
	@Bean
	PaymentProfileController paymentProfileController(){
		factory.Mock(PaymentProfileController)
	}
}
