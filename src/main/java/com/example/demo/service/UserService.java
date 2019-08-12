package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

	User findByUsername(String username);

	User saveUser(User user);
	
	List<User> findAllUsers();


}
