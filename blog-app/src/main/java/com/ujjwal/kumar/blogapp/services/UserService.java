package com.ujjwal.kumar.blogapp.services;

import java.util.List;

import com.ujjwal.kumar.blogapp.entity.User;
import com.ujjwal.kumar.blogapp.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer id);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	void deleteUser(Integer userId);
}
