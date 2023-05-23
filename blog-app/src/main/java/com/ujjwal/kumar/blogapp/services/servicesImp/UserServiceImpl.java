package com.ujjwal.kumar.blogapp.services.servicesImp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ujjwal.kumar.blogapp.entity.User;
import com.ujjwal.kumar.blogapp.execption.ResourceNotFoundExecption;
import com.ujjwal.kumar.blogapp.payloads.UserDto;
import com.ujjwal.kumar.blogapp.repositories.UserRepositories;
import com.ujjwal.kumar.blogapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositories userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User data = this.dtoToUser(userDto);
		User saveData = this.userRepo.save(data);
		return this.userToDto(saveData);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExecption("user", "Id", id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundExecption("user", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtoData = users.stream().
				map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtoData;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user= this.userRepo.findById(userId).
		orElseThrow(()->new ResourceNotFoundExecption("User", "Id", userId));
		this.userRepo.delete(user);
		
	}

	private User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto newUser = new UserDto();
		newUser.setId(user.getId());
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setAbout(user.getAbout());
		return newUser;
	}

}
