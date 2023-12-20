package com.uday.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uday.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public boolean existsByEmail(String email);

	public User findByEmail(String email);

}
