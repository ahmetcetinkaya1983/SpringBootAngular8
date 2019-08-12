package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;

@RestController
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	public ResponseEntity<?> findAllUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}
	

}
