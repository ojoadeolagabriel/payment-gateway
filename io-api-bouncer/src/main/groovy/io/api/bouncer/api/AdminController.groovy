package io.api.bouncer.api

import groovy.json.JsonOutput
import io.api.bouncer.profile.ResourceServerProfileCondition
import io.api.bouncer.valueobjects.BouncerRole
import org.springframework.context.annotation.Conditional
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@Conditional(value = ResourceServerProfileCondition)
@RestController
@RequestMapping(path = ["/api/v1/admin"])
class AdminController {

	@PreAuthorize(BouncerRole.ADMIN)
	@RequestMapping(method = RequestMethod.GET, value = "testrun/{id}", produces = "application/json")
	String findById(@PathVariable long id) {
		JsonOutput.toJson(["status": "online", "request-id": id])
	}

	@PreAuthorize(BouncerRole.ADMIN)
	@RequestMapping(method = RequestMethod.GET, value = "/user/info", produces = "application/json")
	String currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
		JsonOutput.toJson(["username": authentication.name, "principal": authentication.credentials.toString()])
	}
}
