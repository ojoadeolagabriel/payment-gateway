package io.api.bouncer.config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.access.AccessDeniedHandlerImpl

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/api/v1/**").authenticated()
		.and()
		.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl(){
			@Override
			void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
				super.handle(request, response, accessDeniedException)
				response.setStatus(401)
				response.getWriter().append("Access denied")
			}
		})
	}

	PasswordEncoder userPasswordEncoder(){
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
