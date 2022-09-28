package com.JoeBlog.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.JoeBlog.model.ConfirmationToken;
import com.JoeBlog.model.User;
import com.JoeBlog.model.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return userRepository.findByEmail(email).orElseThrow(()
				-> new UsernameNotFoundException("User doesn't exist"));
	}
	
	public String registerUser(User user) {
		boolean userExists = userRepository
				.findByEmail(user.getEmail())
				.isPresent();
		
		ConfirmationToken confirmationTester = new ConfirmationToken();
		
		if (userExists) {
			if(confirmationTester.getConfirmedAt() != null) {
				throw new IllegalStateException("Email already exists");
			}						
		}else{
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			
			user.setPassword(encodedPassword);
			
			userRepository.save(user);
		}
		
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				user
		);
		
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		return token;
	}
	
	public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }

}
