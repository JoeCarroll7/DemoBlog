package com.JoeBlog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JoeBlog.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByUserName(String userName);
	void deleteById(Integer id);
	Post getById(Integer id);
	//Optional<List<Post>> findAllByUser(String username);
}
