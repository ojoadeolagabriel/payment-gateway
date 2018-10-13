package io.api.bouncer.data.dataobjects

import lombok.Builder
import org.springframework.security.core.GrantedAuthority

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "oauth_user_roles")
@Builder
class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id

	@Column(name = "user_id")
	private Long userId

	@Column(name = "tag")
	private String role

	@Override
	String getAuthority() {
		return role
	}
}