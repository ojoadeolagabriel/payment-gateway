package io.api.bouncer.valueobjects

class BouncerRole {
	static final String ADMIN = "hasRole('ROLE_ADMIN')"
	static final String SUPER_ADMIN = "hasRole('ROLE_SUPER_ADMINISTRATOR')"
	static final String GUEST = "hasRole('ROLE_GUEST')"
}
