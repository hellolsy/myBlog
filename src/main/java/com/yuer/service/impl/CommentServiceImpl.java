package com.yuer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuer.dao.ICommentDao;
import com.yuer.entity.Comment;
import com.yuer.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	private ICommentDao commentDao;
	
	private List<Comment> replyComments = new ArrayList<>();

	@Override
	public List<Comment> listCommentByBlogId(Long blogId) {
		// 先筛选出根评论
		List<Comment> rootComments = commentDao.listCommentByBlogIdAndRoot(blogId);
		
		circleGetComments(rootComments);
		
		return rootComments;
	}
	
	/**
	 * 
	 * @param comments root节点的集合
	 */
	public void circleGetComments(List<Comment> comments) {
		
		for (Comment comment : comments) {
			List<Comment> temps = commentDao.getComments(comment.getId());
			
			// 循环将该节点的所有下层节点加入replyComments
			for (Comment temp : temps) {
				recursively(temp);
			}
			comment.setComments(replyComments);
			
			// 将该comment的所有子节点赋父节点
			setParent(replyComments);
			
			// 清空子代缓存区，准备下一次的填充
			replyComments = new ArrayList<>();
			
			
		}
		
		
	}
	
	/**
	 * 递归找出该评论的所有子节点
	 * @param comment
	 */
	private void recursively(Comment comment) {
		replyComments.add(comment); // 顶节点添加到临时存放集合
		if (commentDao.getComments(comment.getId()).size() > 0) {
			List<Comment> replys = commentDao.getComments(comment.getId());
			for (Comment reply : replys) {
				
				if (commentDao.getComments(reply.getId()).size() > 0) {
					recursively(reply);
				} else {
					replyComments.add(reply);
					
					
				}
			}
		}
		
		
	}
	
	/**
	 * 根据comment中的parent_id将parent的所有信息查出并set进comment
	 * @param comments
	 */
	public void setParent(List<Comment> comments) {
		
		for (Comment comment : comments) {
			Comment p = commentDao.getCommentById(comment.getParent().getId());
			comment.setParent(p);
		}
		
	}
	
	

	@Override
	public Integer saveComment(Comment comment) {
		comment.setCreateTime(new Date());
		Integer num = commentDao.saveComment(comment);
		return num;
	}

	
}
