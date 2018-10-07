package io.api.bouncer.service.security

import io.api.bouncer.data.dataobjects.User
import io.api.bouncer.data.repository.AuthorityRepository
import io.api.bouncer.data.repository.UserProfileRepository
import io.api.bouncer.facades.MdcLoggerFacade
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Slf4j
@RequiredArgsConstructor
@Component("userDetailsService")
class UserDetailsImplService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepo")
	UserProfileRepository userRepository

	@Autowired
	AuthorityRepository authorityRepository

	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MdcLoggerFacade.mdc().setActivity("get_user_detail").setName("dennis").process({
			User user = userRepository.findByUsername(username)
			if (user != null) {
				def roles = authorityRepository.findByUserId(user.getId())
				user.setProperties(roles)
				return user
			}
			throw new UsernameNotFoundException(username)
		})
	}
}
