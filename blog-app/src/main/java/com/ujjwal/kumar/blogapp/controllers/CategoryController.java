package com.ujjwal.kumar.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujjwal.kumar.blogapp.payloads.CategoryDto;
import com.ujjwal.kumar.blogapp.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService  catService;
	
	// Create category using Post api
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cdto){
		CategoryDto newCatDto = this.catService.createCategory(cdto);
		return new ResponseEntity<>(newCatDto,HttpStatus.OK);
	}
	//Update the category using Put api
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto cdto, @PathVariable Integer categoryId){
		CategoryDto updatedCdto = this.catService.updateCategory(cdto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCdto,HttpStatus.OK);
	}
	

}
