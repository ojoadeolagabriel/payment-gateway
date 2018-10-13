package io.api.bouncer.facades

enum ResponseCode {
	Success("90000", "system.messages.response.codes.success"),

	UNKNOWN("10000", "system.messages.response.codes.unknown"),
	UNKNOWN_CONTACT_ADMINISTRATOR("10001", "system.messages.response.codes.unknown_contact_admin")

	private String code
	private String messageCode

	ResponseCode(String code, String messageCode) {
		this.messageCode = messageCode
		this.code = code
	}

	String getResponseCode() {
		return code
	}

	String getResponseMessage() {
		return messageCode
	}
}
