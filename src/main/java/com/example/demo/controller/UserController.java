package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user/registration")
	public ResponseEntity<?> register(@RequestBody User user) {
		
		if(userService.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		user.setRole(Role.USER);
		return new ResponseEntity<>(userService.saveUser(user),  HttpStatus.CREATED);
		
	}
	
	@GetMapping("/api/user/login")
	public ResponseEntity<?> login(Principal principal) {
		
		if(principal == null) {
			return ResponseEntity.ok(principal);
		}
		
		 UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
	        User user = userService.findByUsername(authenticationToken.getName());
	        user.setToken(jwtTokenProvider.generateToken(authenticationToken));
		return new ResponseEntity<>(user,  HttpStatus.OK);
		
	}
	
	

}
