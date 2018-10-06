package io.api.bouncer.service.security.repository

import io.api.bouncer.service.security.data.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository("userRepo")
interface UserProfileRepository extends JpaRepository<User, Long>{
	@Query(nativeQuery = true, value = "select * from oauth_users u where u.username = ?1")
	User findByUsername(String username);
}
