package io.api.bouncer.exception.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import io.api.bouncer.exception.GenericOauthException

class GenericOauthExceptionSerializer extends StdSerializer<GenericOauthException> {

	protected GenericOauthExceptionSerializer(Class<GenericOauthException> t) {
		super(t)
	}

	@Override
	void serialize(GenericOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject()
		jsonGenerator.writeStringField("code", "500")
		jsonGenerator.writeStringField("message", "")
		jsonGenerator.writeStringField("errors", e.message)
		includeStacktrace(jsonGenerator, e)
		jsonGenerator.writeEndObject()
	}

	static void includeStacktrace(JsonGenerator jsonGenerator, GenericOauthException e) {
		if (e.stackTrace?.size() > 0) {
			def stackMessageList = e.getStackTrace().toList()
			jsonGenerator.writeStringField("more_info", stackMessageList.forEach(
					{
						index -> jsonGenerator.writeStringField("info_{$index}", stackMessageList.get(index).toString())
					}
			))
		}
	}
}
