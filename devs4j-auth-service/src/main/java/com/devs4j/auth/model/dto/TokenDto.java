package com.devs4j.auth.model.dto;

import lombok.Data;

@Data
public class TokenDto {
	private String token;

	public TokenDto(String token) {
		this.token = token;
	}

	public TokenDto() {
	}
	
	
	
}
