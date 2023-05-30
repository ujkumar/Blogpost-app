package com.ujjwal.kumar.blogapp.payloads;

import java.util.Date;

import com.ujjwal.kumar.blogapp.entity.Category;

public class PostDto {

	private String post_Title;
	
	private String post_content;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private String post_Image_name;
	
	private Date post_Added_date;

	public String getPost_Image_name() {
		return post_Image_name;
	}

	public void setPost_Image_name(String post_Image_name) {
		this.post_Image_name = post_Image_name;
	}

	public Date getPost_Added_date() {
		return post_Added_date;
	}

	public void setPost_Added_date(Date post_Added_date) {
		this.post_Added_date = post_Added_date;
	}

	public String getPost_Title() {
		return post_Title;
	}

	public void setPost_Title(String post_Title) {
		this.post_Title = post_Title;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
