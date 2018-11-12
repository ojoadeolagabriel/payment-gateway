package io.webapi.data

import groovy.transform.builder.Builder

@Builder
class ProfileAvailablePaymentInstruments {
	String instrumentType
	Long instrumentId
	String instrumentDescription
	String instrumentLogo
	String instrumentShortDescription
	FormVisibility formVisibility
}
