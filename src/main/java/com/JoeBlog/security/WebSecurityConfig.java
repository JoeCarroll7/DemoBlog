package com.JoeBlog.security;

import com.JoeBlog.service.UserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{
	
		private final UserService userService;
		private final BCryptPasswordEncoder bCryptPasswordEncoder;
		
		@Bean
    	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .antMatchers("/api/v*/registration/**")
            .permitAll()
            .anyRequest()
            .authenticated().and()
            .formLogin();
        
        	return http.build();
	    }
	 	
	 	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
	 		authentication.authenticationProvider(daoAuthenticationProvider());
	 		
	 	}

		@Bean
		DaoAuthenticationProvider daoAuthenticationProvider() {
		    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		    daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		    daoAuthenticationProvider.setUserDetailsService(userService);
		    return daoAuthenticationProvider;
		}
}
