package com.JoeBlog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Post {
	
	private @Id @GeneratedValue @Column Integer id;
	@Column
	private String userName;
	@Column
	private String blogPost;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBlogPost() {
		return blogPost;
	}
	public void setBlogPost(String blogPost) {
		this.blogPost = blogPost;
	}
	
	public Post() {
		super();
	}
	
	public Post(String userName, String blogPost) {
		super();
		this.userName = userName;
		this.blogPost = blogPost;
	}
	
	
}
