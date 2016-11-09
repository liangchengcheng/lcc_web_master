package com.lcc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcc.bean.User;
import com.lcc.dao.UserMapper;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserMapper userMapper;
	
	@Transactional
	public User loadUserByUsername(String username){
		return userMapper.loadUserByUsername(username);
	}
	
	@Transactional
	public void saveUser(User user){
		userMapper.saveUser(user);
	}
	
	private void getError(){
		int i =1/0;
		logger.info("i:{}",i);
	}
}
