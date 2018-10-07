package io.api.bouncer.data.repository

import io.api.bouncer.data.dataobjects.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository extends JpaRepository<Authority, Long> {
	List<Authority> findByUserId(Long id)
}
