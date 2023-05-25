package com.ujjwal.kumar.blogapp.services;

import java.util.List;

import com.ujjwal.kumar.blogapp.payloads.CategoryDto;

public interface CategoryService {

	//Create
	public CategoryDto createCategory(CategoryDto cdto);
	//Update
	public CategoryDto updateCategory(CategoryDto cdto,Integer id);
	//Delete
	public void deleteCategory(Integer id);
	// Get
	public CategoryDto getCategory(Integer id);
	//Get all
	List<CategoryDto> getAllCategory();
	
}
