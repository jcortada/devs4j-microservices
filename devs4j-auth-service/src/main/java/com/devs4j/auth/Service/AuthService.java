package com.devs4j.auth.Service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.auth.config.JwtProvider;
import com.devs4j.auth.model.dto.TokenDto;
import com.devs4j.auth.model.dto.UserDto;
import com.devs4j.auth.model.entity.UserEntity;
import com.devs4j.auth.model.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private JwtProvider jwtProvider;

	
	public UserDto save(UserDto user) {
		Optional<UserEntity> response = repository.findByUsername(user.getUsername());
		if(response.isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, 
					String.format("User %s already exist", user.getUsername()));
		}
		
		UserEntity entity = repository.save(UserEntity.builder()
				.username(user.getUsername())
				.password(encoder.encode(user.getPassword()))
				.build());
		
		return mapper.map(entity, UserDto.class);
	}
	
	
	public TokenDto login(UserDto user) {
		UserEntity entity = repository.findByUsername(user.getUsername())
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
		
		
		if(encoder.matches(user.getPassword(), entity.getPassword())) {
			return new TokenDto(jwtProvider.createToken(entity));
		}else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			
		}
	}
	
	public TokenDto validate(String token) {
		jwtProvider.validate(token);
		
		String username = jwtProvider.getUsernameFromToken(token);
		
		repository.findByUsername(username)
				.orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
		
		
		return new TokenDto(token);
		
	}
}
