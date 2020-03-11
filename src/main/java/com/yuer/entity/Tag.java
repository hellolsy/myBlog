package com.yuer.entity;

import java.util.ArrayList;
import java.util.List;


public class Tag {

	

	private Long id;
	private String tagName; // 标签名
	
	private List<Blog> blogs = new ArrayList<>();
	
	private Integer blogNum = 0;
	
	
	public Integer getBlogNum() {
		return blogNum;
	}

	public void setBlogNum(Integer blogNum) {
		this.blogNum = blogNum;
	}

	public Long getId() {
		return id;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
