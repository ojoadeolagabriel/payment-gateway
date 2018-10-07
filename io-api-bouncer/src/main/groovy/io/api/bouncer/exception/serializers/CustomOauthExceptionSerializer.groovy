package io.api.bouncer.exception.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import io.api.bouncer.exception.CustomOauthException

class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

	CustomOauthExceptionSerializer() {
		this(null)
	}

	CustomOauthExceptionSerializer(Class<CustomOauthException> t) {
		super(t)
	}

	@Override
	void serialize(CustomOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject()
		jsonGenerator.writeStringField("code", e.getHttpErrorCode().toString())
		jsonGenerator.writeStringField("message", "${e.getMessage()}")
		jsonGenerator.writeStringField("data", "")
		jsonGenerator.writeStringField("path", "")
		jsonGenerator.writeStringField("errors", "${e.getOAuth2ErrorCode()} - ${e.getMessage()}")
		includeAdditionalInfo(jsonGenerator, e)
		jsonGenerator.writeEndObject()
	}

	static void includeAdditionalInfo(JsonGenerator jsonGenerator, CustomOauthException e) {
		if (e.getAdditionalInformation()?.size() > 0)
			jsonGenerator.writeStringField("more-info", e
					.getAdditionalInformation().forEach({ k, v -> jsonGenerator.writeStringField(k, v) }))
	}
}
