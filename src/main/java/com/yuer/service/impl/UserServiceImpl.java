package com.yuer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuer.dao.IUserDao;
import com.yuer.entity.User;
import com.yuer.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	
	// 尽量用事务把
	@Transactional
	@Override
	public User findByUserNameAndPassword(String userName, String password) {
		return userDao.findByUserNameAndPassword(userName, password);
	}
	
	
	
	

}
