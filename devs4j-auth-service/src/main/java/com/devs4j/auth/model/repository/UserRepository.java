package com.devs4j.auth.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devs4j.auth.model.entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, String> {

	public Optional<UserEntity> findByUsername(String username);
}
