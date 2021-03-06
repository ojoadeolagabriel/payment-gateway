package io.api.bouncer.config

import io.oauth.common.ResourceServerProfileCondition
import org.springframework.beans.factory.annotation.Value
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
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Conditional(value = ResourceServerProfileCondition)
@Configuration
@EnableResourceServer
class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value('${io.security.checkTokenEndpointUrl:http://localhost:7005/oauth/check_token/}')
	String checkTokenUrl

	private static final String RESOURCE_ID = "myResource",
	                            clientId = "webpay",
	                            secret = "password"

	@Override
	void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	}

	@Override
	void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenServices(tokenService()).resourceId(RESOURCE_ID).stateless(true)
	}

	@Bean
	CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration()
		config.setAllowCredentials(true)
		config.addAllowedOrigin("http://localhost:8901")
		config.addAllowedHeader("*")
		config.addAllowedMethod("GET")
		config.addAllowedMethod("PUT")
		source.registerCorsConfiguration("/**", config)
		return new CorsFilter(source)
	}

	@Primary
	@Bean
	RemoteTokenServices tokenService() {
		RemoteTokenServices tokenService = new RemoteTokenServices()
		tokenService.setCheckTokenEndpointUrl(checkTokenUrl)
		tokenService.setClientId(clientId)
		tokenService.setClientSecret(secret)
		return tokenService
	}

}
