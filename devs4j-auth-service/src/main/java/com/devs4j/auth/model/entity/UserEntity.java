package com.devs4j.auth.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	
	/*
	public UserEntity(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}


	public UserEntity(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public UserEntity() {
	}*/
	
	
	
}
