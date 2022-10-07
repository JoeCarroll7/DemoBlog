package com.JoeBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.JoeBlog.model.ConfirmationToken;
import com.JoeBlog.model.RegistrationRequest;
import com.JoeBlog.model.User;
import com.JoeBlog.service.RegistrationService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@SessionAttributes("requestData")
public class RegistrationController {
	
	private RegistrationService registrationService;
	
	@GetMapping("/posts/registration")
	public String getAndCreateUser(Model model) {
		RegistrationRequest registrationRequest = new RegistrationRequest();
		model.addAttribute("requestData", registrationRequest);
		
		return "registration";
	}
	
	@PostMapping("posts/registration")
	public String register(@ModelAttribute("requestData") RegistrationRequest parameters, Model model) {
		
		registrationService.register(parameters);
		
		return "redirect:/login";
		
	}
	                                                              
	@GetMapping(path = "posts/registration/confirm")
	public String confirm(@RequestParam("token") String token) {
		registrationService.confirmToken(token);
		System.out.println("confirm ran");
		return "registered";
	}
}
