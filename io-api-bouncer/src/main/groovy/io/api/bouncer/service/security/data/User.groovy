package io.api.bouncer.service.security.data

import lombok.Getter
import lombok.Setter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Getter
@Setter
@Table(name = "oauth_users", uniqueConstraints = @UniqueConstraint(columnNames = ["username"]))
class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id

	@Column(name = "username")
	private String username

	@Column(name = "password")
	private String password

	@OneToMany
	@JoinTable(name = "oauth_user_roles", joinColumns = @JoinColumn(name = "user_id"))
	private Collection<Authority> authorities

	@Override
	Collection<? extends GrantedAuthority> getAuthorities() {
		[
				new Authority("ADMIN")
		]
	}

	@Override
	String getPassword() {
		return password
	}

	@Override
	String getUsername() {
		return username
	}

	@Override
	boolean isAccountNonExpired() {
		return true
	}

	@Override
	boolean isAccountNonLocked() {
		return true
	}

	@Override
	boolean isCredentialsNonExpired() {
		return true
	}

	@Override
	boolean isEnabled() {
		return true
	}
}
