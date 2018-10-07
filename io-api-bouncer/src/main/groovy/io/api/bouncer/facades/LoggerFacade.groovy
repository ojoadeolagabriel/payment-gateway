package io.api.bouncer.facades

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class LoggerFacade {
	static Logger logger(){
		return LoggerFactory.getLogger(LoggerFacade.class)
	}
}
