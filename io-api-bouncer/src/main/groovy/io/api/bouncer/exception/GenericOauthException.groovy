package io.api.bouncer.exception

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.api.bouncer.exception.serializers.GenericOauthExceptionSerializer

@JsonSerialize(using = GenericOauthExceptionSerializer.class)
class GenericOauthException extends RuntimeException{
	GenericOauthException(String msg) {
		super(msg)
	}
}
