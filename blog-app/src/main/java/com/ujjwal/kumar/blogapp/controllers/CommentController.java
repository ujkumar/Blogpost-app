package com.ujjwal.kumar.blogapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.ujjwal.kumar.blogapp.payloads.CommentsDto;
import com.ujjwal.kumar.blogapp.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	// create a comment for any post whose post id provided...

	@PostMapping("/user/comment/{post_Id}")
	public ResponseEntity<CommentsDto> createCommnet(@RequestBody CommentsDto commentDto,
			@PathVariable Integer post_Id) {

		CommentsDto createCommnet = this.commentService.createCommnet(commentDto, post_Id);
		return new ResponseEntity<CommentsDto>(createCommnet, HttpStatus.CREATED);
	}

	// updating the comment of any post whose post id is provided...

	@PutMapping("/user/comment/{commentID}")
	public ResponseEntity<CommentsDto> updateComment(@RequestBody CommentsDto commentDto,
			@PathVariable Integer commentID) {
		CommentsDto updatedComment = this.commentService.updateComment(commentDto, commentID);
		return new ResponseEntity<CommentsDto>(updatedComment, HttpStatus.OK);
	}

	// delete the comment of any post with the help of comment id....

	@DeleteMapping("/user/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteCommnet(@PathVariable Integer commentId) {
		this.commentService.deleteCommnet(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment has been deleted!!!...", false), HttpStatus.OK);
	}
	
	@GetMapping("/user/comment/{commentId}")
	public ResponseEntity<CommentsDto> getCommnetByCommentId(@PathVariable Integer comment_Id){
		
		CommentsDto commentDetails = this.commentService.getCommnetByCommentId(comment_Id);
		return new ResponseEntity<CommentsDto>(commentDetails,HttpStatus.OK);
	}
}
