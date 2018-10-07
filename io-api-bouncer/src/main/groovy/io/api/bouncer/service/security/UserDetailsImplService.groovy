package io.api.bouncer.service.security

import io.api.bouncer.data.dataobjects.User
import io.api.bouncer.data.repository.AuthorityRepository
import io.api.bouncer.data.repository.UserProfileRepository
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

	@Autowired
	AuthorityRepository authorityRepository

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
		if (user != null) {
			def roles = authorityRepository.findByUserId(user.id)
			user.authorities = roles
			return user
		}
		throw new UsernameNotFoundException(username)
	}
}
