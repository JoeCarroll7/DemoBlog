package com.JoeBlog.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.JoeBlog.model.Post;
import com.JoeBlog.model.User;

@Controller
public class LoginController {
	
	@GetMapping("/posts/login")
	public String login() {
	
	   
	   /*
	    * String username = SecurityContextHolder.getContext().getAuthentication().getName();
	   System.out.println(username);
       if (!username.equals("anonymousUser")) {
    	   isLoggedIn(true);
           return "redirect:/posts"; 
       } else {
           
       }
       public boolean isLoggedIn(boolean loggedIn) {
		return loggedIn;
	}*/
       return "login";
	}
	
	
	
	
	
	
}
