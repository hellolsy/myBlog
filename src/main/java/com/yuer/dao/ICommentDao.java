package com.yuer.dao;

import java.util.List;

import com.yuer.entity.Comment;

public interface ICommentDao {

	List<Comment> listCommentByBlogId(Long blogId);
	
	// 获取父节点为该id的子节点
	List<Comment> getComments(Long commentId);
	
	
	
	List<Comment> listCommentByBlogIdAndRoot(Long blogId);
	
	Comment getCommentById(Long id);

	Integer saveComment(Comment comment);
	
	

}
