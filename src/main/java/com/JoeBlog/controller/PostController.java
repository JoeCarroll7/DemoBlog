
package com.JoeBlog.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	/*
	 * call to service to create the post and adding attribute from model to be passed to the template, redirecting to base url after
	 */
	@RequestMapping("/createPost")
	public String createPost(@ModelAttribute("postData") Post postInfo, Model model) {
		/*
		if(!getUserForNavbar(model).equals(postInfo.getUserName())) {
			return "redirect:/posts/userPosts";
		}*/
		
		postService.createPost(postInfo);
		
		return "redirect:/posts/userPosts";
	}
	
	public String getUserForNavbar(Model model) {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = new User(currentUser);
		model.addAttribute("user", user);
		System.out.println(currentUser + user.getUsername());
		return currentUser;
	}

	@GetMapping( "/userPosts/{id}")
	public String deletePost(@PathVariable Integer id, Model model) {
		Post post = postService.getPostWithId(id);
		System.out.println(id + ' ' + postService.getPostWithId(id).toString());
		if(!getUserForNavbar(model).equals(post.getUserName())) {
			return "redirect:/posts/userPosts";
		}
	
		postService.deletePost(post);
		return "redirect:/posts/userPosts";
	}
	
	@GetMapping("/userPosts/editPost/{id}")
	public String getEditPost(Model model, @PathVariable Integer id) {
		Post post = postService.getPostWithId(id);
		model.addAttribute("post", post);
		getUserForNavbar(model);
		return "editPost";
	}
	
	@RequestMapping(value="/userPosts/editPost/{id}")
	public String editPost(@ModelAttribute Post post, @PathVariable Integer id, Model model) {
		
		System.out.println("ran");
		getUserForNavbar(model);		
		postService.editPost(post);
		return "redirect:/posts/userPosts";
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
