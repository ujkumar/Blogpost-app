package com.ujjwal.kumar.blogapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ujjwal.kumar.blogapp.entity.Category;
import com.ujjwal.kumar.blogapp.entity.Post;
import com.ujjwal.kumar.blogapp.entity.User;

public interface PostRepository extends JpaRepository<Post,Integer>{
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
//	List<Post> findByTitleContaining(String post_Title);
}