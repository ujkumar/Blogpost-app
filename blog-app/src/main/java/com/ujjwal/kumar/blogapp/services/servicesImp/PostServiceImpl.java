package com.ujjwal.kumar.blogapp.services.servicesImp;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ujjwal.kumar.blogapp.entity.Category;
import com.ujjwal.kumar.blogapp.entity.Post;
import com.ujjwal.kumar.blogapp.entity.User;
import com.ujjwal.kumar.blogapp.execption.ResourceNotFoundExecption;
import com.ujjwal.kumar.blogapp.payloads.CategoryDto;
import com.ujjwal.kumar.blogapp.payloads.PostDto;
import com.ujjwal.kumar.blogapp.payloads.PostResponse;
import com.ujjwal.kumar.blogapp.repositories.CategoryRepository;
import com.ujjwal.kumar.blogapp.repositories.PostRepository;
import com.ujjwal.kumar.blogapp.repositories.UserRepositories;
import com.ujjwal.kumar.blogapp.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepositories userReo;
	
	@Autowired
	private CategoryRepository cgtRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		//Here we are fetching the details of user
		User user =this.userReo.findById(userId).orElseThrow(() -> new ResourceNotFoundExecption("User", "Id", userId));
		
		//Here we are fetching the details of category		
		Category category =this.cgtRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundExecption("category", "Id", categoryId));
		
		Post postValue = this.modelMapper.map(postDto, Post.class);
		postValue.setPost_Image_name("Default.png");
		postValue.setPost_Added_date(new Date());
		postValue.setCategory(category);
		postValue.setUser(user);
		
		Post createdPost = this.postRepo.save(postValue);
		
		return this.modelMapper.map(createdPost, PostDto.class);
	}

	
	
	@Override
	public PostDto updatPost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post olddata = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExecption("Post", "postId", postId));
		olddata.setPost_Title(postDto.getPost_Title());
		olddata.setPost_content(postDto.getPost_content());
		olddata.setPost_Added_date(new Date());
		Post saveddata = this.postRepo.save(olddata);
		return this.modelMapper.map(saveddata, PostDto.class);
	}

	
	
	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post data = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundExecption("Post", "PostIt", postId));
		this.postRepo.delete(data);
	}

	
//	Integer pageNumber,Integer pageSize
	
//	@Override
//	public List<PostDto> getAllPost() {
//		// TODO Auto-generated method stub
//		List<Post> listOfPosts = this.postRepo.findAll();
//		List<PostDto> list_OF_Post_DTO=listOfPosts.stream().map(list->this.modelMapper.map(list, PostDto.class)).collect(Collectors.toList());
//		return list_OF_Post_DTO;
//	}

	
	
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub
		Pageable page = PageRequest.of(pageNumber, pageSize);
		
		Page<Post> pageOfPost = this.postRepo.findAll(page);
		
		List<Post> allPost = pageOfPost.getContent();
		List<PostDto> list_OF_Post_DTO=allPost.stream().map(list->this.modelMapper.map(list, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(list_OF_Post_DTO);
		postResponse.setPageNumber(pageOfPost.getNumber());
		postResponse.setPageSize(pageOfPost.getSize());
		postResponse.setTotalContent(pageOfPost.getTotalElements());
		postResponse.setTotalPages(pageOfPost.getTotalPages());
		postResponse.setLastPage(pageOfPost.isLast());

		return postResponse;
	}
	
	
	
	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post data = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExecption("Post", "PostId", postId));
		return this.modelMapper.map(data, PostDto.class);
	}
	
	
//	@Override
//	public List<PostDto> getPostByUser(Integer userId) {
//		// TODO Auto-generated method stub
//		User userData = this.userReo.findById(userId).orElseThrow(()->new ResourceNotFoundExecption("User", "UserId", userId));
//		List<Post> findByUserList = this.postRepo.findByUser(userData);
//		List<PostDto> listValue=findByUserList.stream().map(list->this.modelMapper.map(list,PostDto.class)).collect(Collectors.toList());
//		return listValue;
//	}

	
	
	@Override
	public PostResponse getPostByUser(Integer pageNumber, Integer pageSize,Integer userId) {
		// TODO Auto-generated method stub
		Pageable pages = PageRequest.of(pageNumber, pageSize); //Here I have created Pageable parameter
		
		User userData = this.userReo.findById(userId).
				orElseThrow(()->new ResourceNotFoundExecption("User", "UserId", userId)); //Here we are fetching the user details
		Page<Post> findAll = this.postRepo.findAll(pages); // Here I'm fetching all data for pagination...
		
		List<Post> findByUserList = this.postRepo.findByUser(userData);
		
		List<PostDto> listValue=findByUserList.stream().map(list->this.modelMapper.map(list,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(listValue);
		postResponse.setPageNumber(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalContent(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastPage(findAll.isLast());

		return postResponse;
	}
	
	
	
	
	
	@Override
	public PostResponse getPostByCategory(Integer pageNumber,Integer pageSize,Integer categoryId) {
		// TODO Auto-generated method stub
		
		Pageable pages = PageRequest.of(pageNumber, pageSize);
		Page<Post> findAll = this.postRepo.findAll(pages);
		
		Category cat = this.cgtRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundExecption("category", "categoryId", categoryId));
		List<Post> catdata = this.postRepo.findByCategory(cat);
		List<PostDto> postDtoData = catdata.stream().map(list->this.modelMapper.map(list, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtoData);
		postResponse.setPageNumber(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalContent(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		postResponse.setLastPage(findAll.isLast());
		
		
		return postResponse;
	}
	
	
	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
