package io.api.bouncer.data.repository

import io.api.bouncer.data.dataobjects.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository("userRepo")
interface UserProfileRepository extends JpaRepository<User, Long>{
	@Query(nativeQuery = true, value = "select * from oauth_users u where u.username = ?1")
	User findByUsername(String username);
}
