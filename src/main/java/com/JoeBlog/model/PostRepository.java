package com.JoeBlog.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByUserName(String userName);
	//Optional<List<Post>> findAllByUser(String username);
}
