package com.devs4j.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.auth.Service.AuthService;
import com.devs4j.auth.model.dto.TokenDto;
import com.devs4j.auth.model.dto.UserDto;

@RequestMapping("/auth")
@RestController
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody UserDto user) {
		return ResponseEntity.ok(authService.login(user));
	}
	
	
	@PostMapping("/validate")
	public ResponseEntity<TokenDto> validate(@RequestParam String token) {
		return ResponseEntity.ok(authService.validate(token));
	}
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
		log.info("user: {}", user);	
		return ResponseEntity.ok(authService.save(user));
	}
	
}
