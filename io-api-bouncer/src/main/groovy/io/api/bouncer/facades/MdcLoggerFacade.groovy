package io.api.bouncer.facades

import io.api.bouncer.handlers.MdcHandler
import org.slf4j.MDC

class MdcLoggerFacade<T> {
	private static MdcLoggerFacade instance

	MdcLoggerFacade(){}

	static MdcLoggerFacade mdc() {
		if (instance == null) {
			synchronized (MdcLoggerFacade.class) {
				if (instance == null) {
					instance = new MdcLoggerFacade()
				}
			}
		}
		return instance
	}

	MdcLoggerFacade setName(String name) {
		MDC.put("app_name", name)
		this
	}
	MdcLoggerFacade setActivity(String label) {
		MDC.put("activity_name", label)
		this
	}

	T process(MdcHandler handler) {
		def result = handler.process()
		MDC.clear()
		return result
	}
}
