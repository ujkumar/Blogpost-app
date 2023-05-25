package com.ujjwal.kumar.blogapp.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ujjwal.kumar.blogapp.payloads.ApiResponse;
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
	
	//To fetch the any detail we use GET api 
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
		CategoryDto category = this.catService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	//To fetch all the details we use Get api
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategory = this.catService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(allCategory,HttpStatus.OK);
	}
	
	//To delete any thing through api we use Delete mapping.
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		this.catService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully",true),HttpStatus.OK);
	}

}
