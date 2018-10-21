package io.api.bouncer.config

import io.api.bouncer.filters.DemoAuthenticationFilter
import io.oauth.common.AuthorizationServerProfileCondition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.crypto.password.StandardPasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Conditional(value = AuthorizationServerProfileCondition)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	void configure(HttpSecurity http) throws Exception {

		http.
				addFilterBefore(new DemoAuthenticationFilter(), BasicAuthenticationFilter)
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/api/v1/**").authenticated()
	}

	PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt"
		Map encoders = new HashMap<>()
		encoders.put(idForEncode, new BCryptPasswordEncoder(10))
		encoders.put("noop", NoOpPasswordEncoder.getInstance())
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder())
		encoders.put("scrypt", new SCryptPasswordEncoder())
		encoders.put("sha256", new StandardPasswordEncoder())

		return new DelegatingPasswordEncoder(idForEncode, encoders)
	}

	@Bean
	@Override
	AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean()
	}

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService

	@Autowired
	void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
		builder
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
	}
}
