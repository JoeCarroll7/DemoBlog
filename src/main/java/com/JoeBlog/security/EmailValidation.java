package com.JoeBlog.security;

import java.util.function.Predicate;

import org.springframework.context.annotation.Configuration;
@Configuration
public class EmailValidation implements Predicate<String> {

	@Override
	public boolean test(String t) {
		return true;
	}
	
}
