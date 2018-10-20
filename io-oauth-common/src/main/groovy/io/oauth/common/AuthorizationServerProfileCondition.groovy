package io.oauth.common

import org.springframework.core.env.Environment

class AuthorizationServerProfileCondition extends ProfileCondition {
	@Override
	protected boolean matchProfiles(Environment environment) {
		Arrays.stream(environment.getActiveProfiles()).anyMatch(
				{
					profile -> (profile == "authorization_server")
				})
	}
}