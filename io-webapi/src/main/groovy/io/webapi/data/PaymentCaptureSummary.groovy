package io.webapi.data

import groovy.transform.builder.Builder

@Builder
class PaymentCaptureSummary {
	String profileUserId
	String profileUserName
	String profilePaymentCaptureAmount
	String profilePaymentCaptureCurrency
	String profileUserInitials
}
