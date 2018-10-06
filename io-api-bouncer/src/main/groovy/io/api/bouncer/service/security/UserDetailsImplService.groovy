package io.api.bouncer.service.security

import io.api.bouncer.service.security.data.User
import io.api.bouncer.service.security.repository.UserProfileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component("userDetailsService")
class UserDetailsImplService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepo")
	UserProfileRepository userRepository

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
		if (user != null) {
			return user
		}
		throw new UsernameNotFoundException(username)
	}
}
