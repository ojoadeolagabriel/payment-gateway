package io.api.bouncer.encoder


import io.api.bouncer.data.repository.UserProfileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BasicEncoders {

	@Autowired
	UserProfileRepository repository

	@Bean(name = "userPasswordEncoder")
	PasswordEncoder userPasswordEncoder() {
		new BCryptPasswordEncoder(4)
	}

	@Bean(name = "oauth2ClientPasswordEncoder")
	PasswordEncoder oauth2ClientPasswordEncoder() {
		new BCryptPasswordEncoder(8)
	}
}
