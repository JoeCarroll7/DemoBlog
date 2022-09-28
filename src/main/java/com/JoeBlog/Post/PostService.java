package com.JoeBlog.Post;

import java.util.List;

public interface PostService {
	Post createPost(Post post);
	
	void deletePost(Post post);
	
	List<Post> getPosts();
}
