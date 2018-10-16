package io.utils.validators.contract

interface FluentHandler<T>{
	ValidationResult handle(T data)
}