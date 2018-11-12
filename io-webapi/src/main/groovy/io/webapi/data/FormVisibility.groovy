package io.webapi.data

import groovy.transform.builder.Builder

@Builder
class FormVisibility {
	boolean showPan
	boolean showExpiryDate
	boolean showPinPadInput
	boolean showCvv
	boolean allowPaymentCaptureAmountAdjustment
	long maxPaymentCaptureAmount
}
