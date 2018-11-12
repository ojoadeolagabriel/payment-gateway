package io.webapi.data

import groovy.transform.builder.Builder

@Builder
class PaymentProfilePayload {
	String merchantLogo
	Long maxPaymentWindow
	PaymentCaptureSummary paymentCaptureSummary
	List<ProfileAvailablePaymentInstruments> profileAvailablePaymentInstruments
}
