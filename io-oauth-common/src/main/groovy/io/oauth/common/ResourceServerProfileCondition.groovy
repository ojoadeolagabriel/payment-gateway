package io.oauth.common

import org.springframework.core.env.Environment

class ResourceServerProfileCondition extends ProfileCondition {
	@Override
	protected boolean matchProfiles(Environment environment) {
		Arrays.stream(environment.getActiveProfiles()).anyMatch(
				{
					profile -> (profile == "resource_server")
				})
	}
}