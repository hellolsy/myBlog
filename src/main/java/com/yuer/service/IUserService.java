package com.yuer.service;

import com.yuer.entity.User;

public interface IUserService {
	
	User findByUserNameAndPassword(String userName, String password);

}
