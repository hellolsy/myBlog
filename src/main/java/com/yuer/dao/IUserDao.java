package com.yuer.dao;

import com.yuer.entity.User;

public interface IUserDao {
	
	User findByUserNameAndPassword(String userName, String password);
	
	User findById(Long id);

}
