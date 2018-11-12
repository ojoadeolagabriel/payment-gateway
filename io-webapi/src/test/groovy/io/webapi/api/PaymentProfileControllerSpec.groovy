package io.webapi.api

import core.TestSpringConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import([TestSpringConfiguration])
class PaymentProfileControllerSpec extends Specification {

	@Autowired
	private WebApplicationContext context

	@Autowired
	PaymentProfileController paymentProfileController

	@Autowired
	MockMvc mvc

	def setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				//.apply(springSecurity())
				.build()
	}

	@WithMockUser(roles="SUPER_ADMINISTRATOR")
	def "Confirm payment profile is return valid result"() {
		given: "a payment profile controller"
			paymentProfileController != null
		and: "a url for testing of"
			String url = "/api/v1/payments/profile"
		when:
			MvcResult mvcResult = mvc.perform(get(url))
					.andExpect(status().is2xxSuccessful())
					.andReturn()
		then:
			url
	}
}
