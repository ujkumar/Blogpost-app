package com.ujjwal.kumar.blogapp.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_Id;
	
	@Column(length=100, nullable=false)
	private String post_Title;
	
	@Column(length=10000,nullable = false)
	private String post_content;
	
	private String post_Image_name;
	
	private Date post_Added_date;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;

	public Integer getPost_Id() {
		return post_Id;
	}

	public void setPost_Id(Integer post_Id) {
		this.post_Id = post_Id;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
