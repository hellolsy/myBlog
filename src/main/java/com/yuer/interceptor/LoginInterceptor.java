package com.yuer.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yuer.entity.User;

// 继承适配器而不是直接实现其接口的原因是适配器给每个方法都提供了空实现，这样我继承适配器只用实现我目前功能用到的方法
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	// 请求之前的处理
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// 没登陆则转到/admin,即会访问登录页面
			request.setAttribute("message", "您还没有登录，请先登录！");
			request.getRequestDispatcher("/admin").forward(request, response);
			return false;
		}

		return true;
	}

}
