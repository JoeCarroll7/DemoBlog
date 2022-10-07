package com.JoeBlog.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class User implements UserDetails {
	
	public User(String firstName, String userName, String email, String password,
			UserRole userRole, Boolean accountLocked,
			Boolean accountEnabled) {
		super();
		this.firstName = firstName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.accountLocked = accountLocked;
		this.accountEnabled = accountEnabled;
	}
	
	public User(String email) {
		this.email = email;
	}

	public User(Integer id, String firstName, String userName, String email,
			String password, UserRole userRole,
			Boolean accountLocked, Boolean accountEnabled) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.accountLocked = accountLocked;
		this.accountEnabled = accountEnabled;
	}

	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy=GenerationType.IDENTITY,
			generator="user_sequence"
	)
	private Integer id;
	private String firstName;
	private String userName;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	private Boolean accountLocked = false;
	private Boolean accountEnabled = false;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return accountEnabled;
	}

	public User(String firstName, String userName, String email, String password, UserRole userRole) {
		super();
		this.firstName = firstName;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	

}
