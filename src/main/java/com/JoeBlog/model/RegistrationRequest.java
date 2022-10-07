package com.JoeBlog.model;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Configuration
@Setter
public class RegistrationRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public RegistrationRequest parameters(String firstName, String lastName, String email, String password) {
		return new RegistrationRequest(firstName, lastName, email, password);
	}
}
