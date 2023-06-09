package com.ujjwal.kumar.blogapp.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ujjwal.kumar.blogapp.config.AppConstant;
import com.ujjwal.kumar.blogapp.payloads.ApiResponse;
import com.ujjwal.kumar.blogapp.payloads.PostDto;
import com.ujjwal.kumar.blogapp.payloads.PostResponse;
import com.ujjwal.kumar.blogapp.services.FileService;
import com.ujjwal.kumar.blogapp.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// create the post
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createdpost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createdpost, HttpStatus.CREATED);
	}

	// Update by Id
	@PutMapping("/user/post/{postId}")
	public ResponseEntity<PostDto> UpdatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedpost = this.postService.updatPost(postDto, postId);

		return new ResponseEntity<PostDto>(updatedpost, HttpStatus.OK);
	}

	// Delete by id
	@DeleteMapping("/user/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}

	// Get all the post
//	@GetMapping("/user/posts")
//	public ResponseEntity<List<PostDto>> getAllPost(){
//		return new ResponseEntity<List<PostDto>>(this.postService.getAllPost(),HttpStatus.OK);
//	}

	// Get all the post with pagination and shorting
	@GetMapping("/user/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize
//			@RequestParam(value = "sortBy",defaultValue = "post_Id",required = false)String sortBy,
//			@RequestParam(value = "sortDir",defaultValue="asc",required = false)String sortDir
	) {
		return new ResponseEntity<PostResponse>(this.postService.getAllPost(pageNumber, pageSize), HttpStatus.OK);
	}

	// Get the post by id
	@GetMapping("/user/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	// Get all the post posted by user
//	@GetMapping("/user/{userId}/posts")
//	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
//		
//		return new ResponseEntity<List<PostDto>>(this.postService.getPostByUser(userId),HttpStatus.OK);
//	}

	// Get all the post posted by user with pagination.
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByUser(@PathVariable Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize) {
		return new ResponseEntity<PostResponse>(this.postService.getPostByUser(pageNumber, pageSize, userId),
				HttpStatus.OK);
	}

	// Get all the post posted in category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize) {
		return new ResponseEntity<PostResponse>(this.postService.getPostByCategory(pageNumber, pageSize, categoryId),
				HttpStatus.OK);
	}

	// Search the content by title.
//	@GetMapping("/user/search/{keywords}")
//	public ResponseEntity<List<PostDto>> searchKeywords(@PathVariable String keywords){
//		return new ResponseEntity<List<PostDto>>(this.postService.searchPost(keywords),HttpStatus.OK);
//	}
//	

	// post image upload...
	@PostMapping("/post/image/upload/{post_Id}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer post_Id) throws IOException {
		PostDto postById = this.postService.getPostById(post_Id);
		String uploadImage = this.fileService.uploadImage(path, image);
		
		postById.setPost_Image_name(uploadImage);
		PostDto updatPost = this.postService.updatPost(postById, post_Id);
		return new ResponseEntity<PostDto>(updatPost, HttpStatus.OK);
	}

	// fetch the image from database...
	@GetMapping(value = "/post/image/download/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

}
