package io.api.bouncer.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.api.bouncer.exception.CustomOauthException
import io.api.bouncer.exception.DefaultOauthException
import io.oauth.common.AuthorizationServerProfileCondition
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore

import javax.sql.DataSource

@Conditional(value = AuthorizationServerProfileCondition)
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	def mapper = new ObjectMapper()

	@Autowired
	WebSecurityConfig webSecurityConfig

	@Autowired
	private DataSource dataSource

	@Bean
	TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource)
	}

	@Override
	void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
				.tokenKeyAccess("permitAll()")
				.checkTokenAccess("permitAll()")
	}

	@Override
	void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource)
	}

	@Override
	void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.authenticationManager(webSecurityConfig.authenticationManagerBean())
				.tokenStore(tokenStore())
				.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.exceptionTranslator(
				{ exception ->
					if (exception instanceof OAuth2Exception) {
						OAuth2Exception oAuth2Exception = (OAuth2Exception) exception

						return ResponseEntity
								.status(oAuth2Exception.getHttpErrorCode())
								.body(new CustomOauthException(oAuth2Exception.getMessage()))
					} else {
						return ResponseEntity
								.status(500)
								.body(mapper.writeValueAsString(new DefaultOauthException(exception.getMessage())))
					}
				})
	}
}
