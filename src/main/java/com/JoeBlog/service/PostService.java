package com.JoeBlog.service;

import java.util.List;
import java.util.Optional;

import com.JoeBlog.model.Post;

public interface PostService {
	String getCurrentUser();
	
	Post createPost(Post post);
	
	void deletePost(Post post);
	
	Post editPost(Post post);
	
	List<Post> getPosts();
	
	List<Post> findAllByUserName(String userName);
	
	Post getPostWithId(Integer id);
}
