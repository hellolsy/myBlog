package com.yuer.service;

import java.util.List;

import com.yuer.entity.Comment;

public interface ICommentService {
	
	List<Comment> listCommentByBlogId(Long blogId);
	
	
	
	Integer saveComment(Comment comment);
	
}
