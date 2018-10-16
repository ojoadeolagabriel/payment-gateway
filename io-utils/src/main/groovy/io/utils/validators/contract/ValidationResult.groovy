package io.utils.validators.contract

class ValidationResult{
	ValidationResult(String errorMessage, State state){
		this.errorMessage = errorMessage
		this.state = state
	}

	ValidationResult(){}

	String errorMessage = ""

	State getState() {
		return state
	}

	void setState(State state) {
		this.state = state
	}
	State state = State.SUCCESS

	String getErrorMessage() {
		return errorMessage
	}

	void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage
	}

	enum State {
		SUCCESS, FAILED
	}
}
