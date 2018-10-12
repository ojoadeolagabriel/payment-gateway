package io.api.bouncer.config


import io.api.bouncer.profile.ResourceServerProfileCondition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.RemoteTokenServices

@Conditional(value = ResourceServerProfileCondition)
@Configuration
@EnableResourceServer
class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "myResource"

	@Override
	void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	}

	@Override
	void configure(ResourceServerSecurityConfigurer resources) throws
			Exception {
		resources.tokenServices(tokenService()).resourceId(RESOURCE_ID).stateless(true)
	}

	@Primary
	@Bean
	RemoteTokenServices tokenService() {
		RemoteTokenServices tokenService = new RemoteTokenServices()
		tokenService.setCheckTokenEndpointUrl("http://localhost:7005/oauth/check_token/")
		tokenService.setClientId("client")
		tokenService.setClientSecret("password")
		return tokenService
	}

}
