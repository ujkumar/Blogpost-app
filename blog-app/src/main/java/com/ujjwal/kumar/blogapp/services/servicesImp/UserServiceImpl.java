package com.ujjwal.kumar.blogapp.services.servicesImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ujjwal.kumar.blogapp.entity.User;	
import com.ujjwal.kumar.blogapp.payloads.UserDto;
import com.ujjwal.kumar.blogapp.repositories.UserRepositories;
import com.ujjwal.kumar.blogapp.services.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositories userRepo;
	
	@Override
	public UserDto createUser(UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateUser(UserDto user, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}
	
	private User dtoToUser(UserDto userDto) {
		User user= new User();		
		return null;
		
	}

}
