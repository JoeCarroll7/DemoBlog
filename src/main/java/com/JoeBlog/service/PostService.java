package com.JoeBlog.service;

import java.util.List;

import com.JoeBlog.model.Post;

public interface PostService {
	Post createPost(Post post);
	
	void deletePost(Post post);
	
	List<Post> getPosts();
}
