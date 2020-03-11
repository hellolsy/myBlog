package com.yuer.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/admin/**") // 这个是拦截的路径
		.excludePathPatterns("/admin") // 下面两个是不拦截的路径
		.excludePathPatterns("/admin/login");
	}

	
	
	
}
