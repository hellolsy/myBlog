package com.yuer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yuer.entity.Comment;
import com.yuer.entity.User;
import com.yuer.service.ICommentService;

@Controller
public class CommentController {

	@Autowired
	private ICommentService commentService;



	// 注入来自配置文件配置的值
	@Value("${comment.headPicture}")
	private String headPicture;

	@GetMapping("/comments/{id}")
	public String comments(@PathVariable long id, Model model) {
		model.addAttribute("comments", commentService.listCommentByBlogId(id));

		return "blog :: commentList";
	}

	@PostMapping("/comments")
	public String post(Comment comment, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			comment.setHeadPicture(user.getHeadImage());
			comment.setAdminComment(true);
		} else {
			comment.setHeadPicture(headPicture);
			comment.setAdminComment(false);

		}

		commentService.saveComment(comment);
		return "redirect:/comments/" + comment.getBlog().getId();
	}

}
