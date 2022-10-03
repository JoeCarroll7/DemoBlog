package com.JoeBlog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.JoeBlog.model.Post;
import com.JoeBlog.model.PostRepository;

import lombok.NoArgsConstructor;

@Service
public class PostServiceImplementation implements PostService {
	private PostRepository postRepository;
	
	public PostServiceImplementation(PostRepository postRepository) {
		this.postRepository = postRepository;
	}	
	
	@Override
	public Post createPost(Post post) {
		String currentUser = getCurrentUser();
		Post postFinal = new Post(currentUser, post.getBlogPost());
		return postRepository.save(postFinal);
	}

	@Override
	public void deletePost(Post post) {
		postRepository.deleteById(post.getId());
	}

	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}
	@Override
	public List<Post> findAllByUserName(String userName) {
		System.out.println(postRepository.findAllByUserName(userName));
		
		return postRepository.findAllByUserName(userName);
	}

	@Override
	public String getCurrentUser() {
		String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		return currentUser;
	}
	
}
