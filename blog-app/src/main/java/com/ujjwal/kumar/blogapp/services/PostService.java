package com.ujjwal.kumar.blogapp.services;

import java.util.List;


import com.ujjwal.kumar.blogapp.entity.Post;
import com.ujjwal.kumar.blogapp.payloads.PostDto;
import com.ujjwal.kumar.blogapp.payloads.PostResponse;


public interface PostService {

	//Create a post
	PostDto createPost(PostDto postDto,Integer user,Integer category);
	
	//update post
	PostDto updatPost(PostDto postDto,Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	
	//get all post
	PostResponse getAllPost(Integer pageNumber,Integer pageSize);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	// get posts by user 
	PostResponse getPostByUser(Integer pageNumber, Integer pageSize,Integer userId);
	
	//get posts by category
	PostResponse getPostByCategory(Integer pageNumber,Integer pageSize,Integer categoryId);
	
	//Search by keyword
	List<PostDto> searchPost(String keyword);
	
	
}
