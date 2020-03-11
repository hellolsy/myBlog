package com.yuer.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuer.entity.User;
import com.yuer.service.IUserService;
import com.yuer.util.AesUtil;
import com.yuer.util.IResultCode;
import com.yuer.util.MD5Utils;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@Autowired
	private IUserService userService;

	
//	@GetMapping("/") 这样写输入localhost:8080/admin会提示404，只能/admin/访问
	@GetMapping
	public String index() {

		return "admin/login";
	}

	@PostMapping("/login")
	public String login( String userName, String password, HttpSession session, Model model) throws Exception {
		// 先判断是否已经登录过了
		if (session.getAttribute("user") != null) {
			model.addAttribute("message", "您已经登录过了，请勿重复登录");
			return "admin/index";
		}
		
		// 这里有时不知道什么原因报空指针异常，？？？
		User user = userService.findByUserNameAndPassword(AesUtil.aesDecrypt(userName,IResultCode.AES_KEY), MD5Utils.code(AesUtil.aesDecrypt(password,IResultCode.AES_KEY)));

		if (user != null) {
			user.setPassword(null);
			session.setAttribute("user", user);

			return "admin/index";
		} else {
			// 应该还要将数据传回去(后面再改进)
			model.addAttribute("message", "账号或密码错误，请重新输入...");
			
			return "admin/login";
		}

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		
		return "redirect:/admin";
	}
	

}
