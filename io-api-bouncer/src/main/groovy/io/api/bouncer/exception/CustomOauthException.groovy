package io.api.bouncer.exception

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.api.bouncer.exception.serializers.CustomOauthExceptionSerializer
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
class CustomOauthException extends OAuth2Exception{
	CustomOauthException(String msg) {
		super(msg)
	}
}
