package com.yuer.entity;

import java.util.ArrayList;
import java.util.List;


public class Type {
	

	private Long id;
	private String typeName; // 类别名
	
	private List<Blog> blogs = new ArrayList<Blog>();
	
	private Integer blogNum = 0;
	
	public Integer getBlogNum() {
		return blogNum;
	}
	public void setBlogNum(Integer blogNum) {
		this.blogNum = blogNum;
	}
	public Type() {
		super();
	}
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	

}
