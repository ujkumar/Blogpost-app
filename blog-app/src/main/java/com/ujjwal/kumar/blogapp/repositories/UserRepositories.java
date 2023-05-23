package com.ujjwal.kumar.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ujjwal.kumar.blogapp.entity.User;

public interface UserRepositories extends JpaRepository<User, Integer>{
		
}
