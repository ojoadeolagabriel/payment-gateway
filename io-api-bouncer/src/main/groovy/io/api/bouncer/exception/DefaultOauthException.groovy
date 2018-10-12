package io.api.bouncer.exception

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.api.bouncer.exception.serializers.GenericOauthExceptionSerializer

@JsonSerialize(using = GenericOauthExceptionSerializer.class)
class DefaultOauthException extends RuntimeException{
	DefaultOauthException(String msg) {
		super(msg)
	}
}
