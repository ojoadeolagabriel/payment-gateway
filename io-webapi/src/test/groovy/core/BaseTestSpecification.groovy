package core

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.web.FilterChainProxy
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

abstract class BaseTestSpecification {
	@Autowired
	protected WebApplicationContext wac

	@Autowired
	private FilterChainProxy springSecurityFilterChain

	protected MockMvc mockMvc

	@Before
	void applySecurity() {
		this.mockMvc = webAppContextSetup(wac)
				.apply(springSecurity(springSecurityFilterChain))
				.build()
	}
}
