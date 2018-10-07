package io.api.bouncer.config

import io.api.bouncer.profile.ResourceServerProfileCondition
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher

@Configuration
@EnableResourceServer
@Conditional(value = ResourceServerProfileCondition)
class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Override
	void configure(HttpSecurity http) throws Exception {
		http
				.requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
				.authorizeRequests()
				.antMatchers("/**").authenticated()
	}
}
