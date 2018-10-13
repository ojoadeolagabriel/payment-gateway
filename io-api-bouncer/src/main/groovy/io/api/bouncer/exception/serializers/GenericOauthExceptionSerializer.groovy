package io.api.bouncer.exception.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import io.api.bouncer.exception.DefaultOauthException
import io.api.bouncer.exception.common.ExceptionCommons
import org.springframework.http.HttpStatus

class GenericOauthExceptionSerializer extends StdSerializer<DefaultOauthException> {

	protected GenericOauthExceptionSerializer(Class<DefaultOauthException> t) {
		super(t)
	}

	@Override
	void serialize(DefaultOauthException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject()
		jsonGenerator.writeStringField(ExceptionCommons.ResponseCode, HttpStatus.INTERNAL_SERVER_ERROR.toString())
		jsonGenerator.writeStringField(ExceptionCommons.ResponseMessage, "")
		jsonGenerator.writeStringField(ExceptionCommons.OtherErrors, e.message)
		includeStacktrace(jsonGenerator, e)
		jsonGenerator.writeEndObject()
	}

	static void includeStacktrace(JsonGenerator jsonGenerator, DefaultOauthException e) {
		if (e.stackTrace?.size() > 0) {
			def stackMessageList = e.getStackTrace().toList()
			jsonGenerator.writeStringField(ExceptionCommons.MoreInformation, stackMessageList.forEach(
					{
						index -> jsonGenerator.writeStringField("info_{$index}", stackMessageList.get(index).toString())
					}
			))
		}
	}
}
