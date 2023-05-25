package com.ujjwal.kumar.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ujjwal.kumar.blogapp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
