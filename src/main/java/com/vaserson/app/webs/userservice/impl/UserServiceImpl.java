package com.vaserson.app.webs.userservice.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaserson.app.webs.shared.Utils;
import com.vaserson.app.webs.ui.model.request.UserDetailsRequestModel;
import com.vaserson.app.webs.ui.model.response.UserResp;
import com.vaserson.app.webs.userservice.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	Map<String, UserResp> users;
	Utils utils;
	
	public UserServiceImpl() {}
	
	@Autowired
	public UserServiceImpl(Utils utils) 
	{
		this.utils = utils;
	}
	
	@Override
	public UserResp createUser(UserDetailsRequestModel userDetails) {
		
	UserResp returnValue = new UserResp();
	returnValue.setEmail(userDetails.getEmail());
	returnValue.setFirstName(userDetails.getFirstName());
	returnValue.setLastName(userDetails.getLastName());

	String userId = utils.generateUserId();
	returnValue.setUserId(userId);
	
	if(users == null) users = new HashMap<>();
	users.put(userId, returnValue);
	
	return returnValue;
	}
}
