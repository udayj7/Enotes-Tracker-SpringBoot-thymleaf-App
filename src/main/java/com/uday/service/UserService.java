package com.uday.service;

import com.uday.entity.User;

public interface UserService {

	public User saveUser(User user);

	public boolean existEmailCheck(String email);
	
}
