package io.api.bouncer.encoder

import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class BasicEncoders {

	PasswordEncoder userPasswordEncoder(){
		new BCryptPasswordEncoder(4)
	}


	PasswordEncoder oauth2ClientPasswordEncoder(){
		new BCryptPasswordEncoder(8)
	}
}
