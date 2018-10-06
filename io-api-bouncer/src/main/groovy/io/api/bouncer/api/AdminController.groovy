package io.api.bouncer.api

import groovy.json.JsonOutput
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class AdminController {

	@RequestMapping(method = RequestMethod.GET, value = "testrun/{id}", produces = "application/json")
	String findById(@PathVariable long id) {
		JsonOutput.toJson(["status": "online", "request-id": id])
	}
}
