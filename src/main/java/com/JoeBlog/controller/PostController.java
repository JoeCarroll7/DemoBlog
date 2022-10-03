package com.JoeBlog.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JoeBlog.model.Post;
import com.JoeBlog.model.User;
import com.JoeBlog.service.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	/*
	 * adding post service reference variable and setting it in the constructor
	 */
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	/*
	 * adding attribute to model to be used and returning template
	 */
	
	@GetMapping("/createPost")
	public String getCreatePost(Model model) {
		model.addAttribute("postData", new Post());
		getUserForNavbar(model);
		return "createPost";
	}
	
	public String getUserForNavbar(Model model) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = new User(currentUser);
		model.addAttribute("user", user);
		
		
		
		System.out.println(currentUser + user.getUsername());
		return currentUser;
	}
	
	
	/*
	 * call to service to create the post and adding attribute from model to be passed to the template, redirecting to base url after
	 */
	@RequestMapping("/createPost")
	public String createPost(@ModelAttribute("postData") Post postInfo) {
		postService.createPost(postInfo);
		return "redirect:/posts";
	}
	
	/*
	 * adding attribute and call to service to get all posts, returning template at the end, base url is used as none are explicitly declared.
	 */
	@GetMapping
	public String listAllPosts(Model model) {
		model.addAttribute("posts", postService.getPosts());
		String current = getUserForNavbar(model);
		List<Post> userPosts = postService.findAllByUserName(current);
		for(Post s: userPosts) {
			System.out.println(s.getUserName() + s.getId() + s.getBlogPost());
		}
		return "listPosts";
	}
	
	@GetMapping("/userPosts")
	public String listAllUserPosts(Model model) {
		String currentUser = getUserForNavbar(model);
		model.addAttribute("userPosts", postService.findAllByUserName(currentUser));
		return "userPosts";
	}
	
}
