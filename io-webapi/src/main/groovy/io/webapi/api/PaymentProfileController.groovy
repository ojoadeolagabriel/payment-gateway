package io.webapi.api

import groovy.json.JsonOutput
import io.webapi.data.FormVisibility
import io.webapi.data.PaymentCaptureSummary
import io.webapi.data.PaymentProfilePayload
import io.webapi.data.ProfileAvailablePaymentInstruments
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/api/v1/payments"])
@RestController
class PaymentProfileController {

	@PreAuthorize("hasRole('ROLE_SUPER_ADMINISTRATOR')")
	@RequestMapping(path = "/profile", produces = "application/json")
	ResponseEntity profile() {
		HttpHeaders responseHeaders = new HttpHeaders()

		return ResponseEntity.ok()
				.headers(responseHeaders)
				.body(JsonOutput.toJson(buildPayload()))
	}

	@RequestMapping(path = "/process", produces = "application/text")
	String processPayment(){
		return "yello!"
	}

	static PaymentProfilePayload buildPayload() {
		def payload = PaymentProfilePayload.builder()
				.merchantLogo("../assets/quickteller.jpg")
				.maxPaymentWindow(6000)
				.paymentCaptureSummary(
				PaymentCaptureSummary
						.builder()
						.profileUserId("1")
						.profileUserName("Ojo, Adeola Gabriel")
						.profilePaymentCaptureAmount("122")
						.profilePaymentCaptureCurrency("₦")
						.profileUserInitials("OA")
						.build())
				.profileAvailablePaymentInstruments(
				[
						ProfileAvailablePaymentInstruments
								.builder()
								.instrumentType("eCash")
								.instrumentId(1)
								.instrumentDescription("Verve Card")
								.instrumentLogo("verve.jpg")
								.instrumentShortDescription("**0009")
								.formVisibility(
								FormVisibility
										.builder()
										.showPan(true)
										.showExpiryDate(true)
										.showPinPadInput(true)
										.showCvv(true)
										.allowPaymentCaptureAmountAdjustment(true)
										.maxPaymentCaptureAmount(0)
										.build())
								.build(),
						ProfileAvailablePaymentInstruments
								.builder()
								.instrumentType("card")
								.instrumentId(2)
								.instrumentDescription("Verve E-Cash")
								.instrumentLogo("verve.jpg")
								.instrumentShortDescription("**0765")
								.formVisibility(
								FormVisibility
										.builder()
										.showPan(true)
										.showExpiryDate(false)
										.showPinPadInput(true)
										.showCvv(false)
										.allowPaymentCaptureAmountAdjustment(false)
										.maxPaymentCaptureAmount(0)
										.build())
								.build(),
						ProfileAvailablePaymentInstruments
								.builder()
								.instrumentType("card")
								.instrumentId(3)
								.instrumentDescription("Zenith Mastercard")
								.instrumentLogo("mastercard.jpg")
								.instrumentShortDescription("**3345")
								.formVisibility(
								FormVisibility
										.builder()
										.showPan(true)
										.showExpiryDate(false)
										.showPinPadInput(true)
										.showCvv(true)
										.allowPaymentCaptureAmountAdjustment(false)
										.maxPaymentCaptureAmount(0)
										.build())
								.build(),
						ProfileAvailablePaymentInstruments
								.builder()
								.instrumentType("card")
								.instrumentId(4)
								.instrumentDescription("Skye Visa Card")
								.instrumentLogo("visa.png")
								.instrumentShortDescription("**1496")
								.formVisibility(
								FormVisibility
										.builder()
										.showPan(true)
										.showExpiryDate(true)
										.showPinPadInput(true)
										.showCvv(false)
										.allowPaymentCaptureAmountAdjustment(true)
										.maxPaymentCaptureAmount(0)
										.build())
								.build()
				]

		).build()

		return payload
	}
}
