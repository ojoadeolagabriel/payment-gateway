package io.utils.validators

import io.utils.validators.contract.FluentHandler
import io.utils.validators.contract.ResultContext
import io.utils.validators.contract.ValidationResult

class FluentValidator<T> {
	T data

	FluentValidator<T> checkAll(T data) {
		def instance = new FluentValidator<T>()
		instance.data = data
		return instance
	}

	List<FluentHandler<T>> handlers = new ArrayList<>()

	FluentValidator<T> on(FluentHandler<T> handler){
		handlers.add(handler)
		return this
	}

	ResultContext validate() {
		List<ValidationResult> resultList = new ArrayList<>()

		if (handlers?.size() == 0){
			def excResult = new ValidationResult()
			excResult.setErrorMessage("no handlers found!")
			resultList.add(excResult)
			return new ResultContext(resultList)
		}

		//process all handlers
		handlers.forEach({ c ->
			try {
				def info = c.handle(data)
				resultList.add(info)
			} catch (RuntimeException ex) {
				def excResult = new ValidationResult()
				excResult.setErrorMessage("error processing: ${c.class.name} -> ${ex.message}")
				ex.printStackTrace()
				resultList.add(excResult)
			}
		})

		return new ResultContext(resultList)
	}
}
