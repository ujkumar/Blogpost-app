package com.ujjwal.kumar.blogapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ujjwal.kumar.blogapp.payloads.UserDto;
import com.ujjwal.kumar.blogapp.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//	POST -> to create new user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);	
	}
	
	// Put -> to update user
	@PutMapping("/{userId}") //here {userId} is identify as path variable.
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, 
			@PathVariable Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	// Delete ->  to delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto,@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity(Map.of("message","User Deleted successfully"),HttpStatus.OK);
	}
	
	//Get -> to get user 
	@GetMapping("/") // Here it will fetch all user details
	public ResponseEntity<List<UserDto>> getAllUser(){
		return new ResponseEntity<List<UserDto>>(this.userService.getAllUser(),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		return new ResponseEntity<UserDto>(this.userService.getUserById(userId),HttpStatus.OK);
	}
	
}
