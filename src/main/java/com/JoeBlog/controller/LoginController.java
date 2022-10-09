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
	
       return "login";
	}
	
	@GetMapping("/")
	public String loginRedirect() {

       return "redirect:/posts";
       
	}		
}
