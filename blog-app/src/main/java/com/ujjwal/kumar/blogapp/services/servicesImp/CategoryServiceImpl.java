package com.ujjwal.kumar.blogapp.services.servicesImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ujjwal.kumar.blogapp.entity.Category;
import com.ujjwal.kumar.blogapp.entity.User;
import com.ujjwal.kumar.blogapp.execption.ResourceNotFoundExecption;
import com.ujjwal.kumar.blogapp.payloads.CategoryDto;
import com.ujjwal.kumar.blogapp.repositories.CategoryRepository;
import com.ujjwal.kumar.blogapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto cdto) {
		// TODO Auto-generated method stub
		Category categ=this.mapper.map(cdto, Category.class);
		Category saveData=this.categoryRepo.save(categ);
		return this.mapper.map(saveData, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto cdto,Integer id) {
		// TODO Auto-generated method stub
		Category cgt = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExecption("Category", "Id", id));
		cgt.setCategoryTitle(cdto.getCategoryTitle());
		cgt.setCategoryDescription(cdto.getCategoryDescription());
		this.categoryRepo.save(cgt);
		return this.mapper.map(cgt, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		Category cgt = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExecption("Category", "Id", id));
		this.categoryRepo.delete(cgt);
	}

	@Override
	public CategoryDto getCategory(Integer id) {
		// TODO Auto-generated method stub
		Category cgt = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundExecption("Category", "Id", id));
		return this.mapper.map(cgt, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categoryList = this.categoryRepo.findAll();
		List<CategoryDto> categoryListDto=categoryList.stream().map(list->this.mapper.map(list, CategoryDto.class)).collect(Collectors.toList());
		return categoryListDto;
	}

}
