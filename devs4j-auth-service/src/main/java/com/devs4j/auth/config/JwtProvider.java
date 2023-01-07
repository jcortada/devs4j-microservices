package com.devs4j.auth.config;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.auth.model.entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

	@Value("${jwt.secret}")
	private String secret;

	public String createToken(UserEntity user) {
		String token = null;
		Map<String, Object> claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("id", user.getId());
		// TODO remove hardcode
		claims.put("twitter_account", "@raidentrance");
		Date now = new Date();
		Date exp = new Date(now.getTime() + 3600 * 1000);
		
		token = Jwts.builder().setClaims(claims)
				// TODO remove hardcode
				.setHeaderParam("company_name", "devs4j").setIssuedAt(now).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
		
		return token;
	}

	public void validate(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
		}

	}

	public String getUsernameFromToken(String token) {
		try {
			return Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"invalid token");
		} 
	}
}
