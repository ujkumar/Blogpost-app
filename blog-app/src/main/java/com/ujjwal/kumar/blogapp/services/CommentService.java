package com.ujjwal.kumar.blogapp.services;

import com.ujjwal.kumar.blogapp.payloads.CommentsDto;

public interface CommentService {

	CommentsDto createCommnet(CommentsDto commentDto,Integer post_Id);
	
	void deleteCommnet(Integer commentId);
	
	CommentsDto updateComment(CommentsDto commentDto,Integer categoryId);
	
	CommentsDto getCommnetByCommentId(Integer commentID);
	
}
