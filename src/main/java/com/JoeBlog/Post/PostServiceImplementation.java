package com.JoeBlog.Post;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImplementation implements PostService {
	private final PostRepository postRepository;
	
	public PostServiceImplementation(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	@Override
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public void deletePost(Post post) {
		postRepository.deleteById(post.getId());
	}

	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}
	
}
