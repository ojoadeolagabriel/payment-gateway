package io.utils.validators.contract

class ResultContext {
	List<ValidationResult> resultList = new ArrayList<>()
	ResultContext(List<ValidationResult> resultList){
		this.resultList = resultList
	}

	boolean isSuccessful(){
		!resultList.stream().anyMatch({ c -> c.state == ValidationResult.State.FAILED})
	}
}
