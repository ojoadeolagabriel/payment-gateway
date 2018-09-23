package io.api.bouncer.service.security

import io.api.bouncer.service.security.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class UserDetailsImplService implements UserDetailsService {

	@Autowired
	UserRepository userRepository

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User("ojoadeolagabriel@gmail.com","dexter", [])
		return user
	}
}
