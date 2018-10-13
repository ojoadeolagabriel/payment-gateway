package io.api.bouncer.config

import io.api.bouncer.filters.DemoAuthenticationFilter
import io.api.bouncer.profile.AuthorizationServerProfileCondition
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
import org.springframework.security.crypto.password.PasswordEncoder
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

	PasswordEncoder userPasswordEncoder() {
		new BCryptPasswordEncoder(4)
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
	}
}
