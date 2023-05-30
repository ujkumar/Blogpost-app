package com.ujjwal.kumar.blogapp.services.servicesImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ujjwal.kumar.blogapp.entity.Comments;
import com.ujjwal.kumar.blogapp.entity.Post;
import com.ujjwal.kumar.blogapp.execption.ResourceNotFoundExecption;
import com.ujjwal.kumar.blogapp.payloads.CommentsDto;
import com.ujjwal.kumar.blogapp.repositories.CommentRepository;
import com.ujjwal.kumar.blogapp.repositories.PostRepository;
import com.ujjwal.kumar.blogapp.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CommentRepository commentRepo;

	@Override
	public CommentsDto createCommnet(CommentsDto commentDto, Integer post_Id) {
		// TODO Auto-generated method stub
		Post postDetails = this.postRepo.findById(post_Id)
				.orElseThrow(() -> new ResourceNotFoundExecption("Post", "Post_Id", post_Id));
		Comments comment = this.modelMapper.map(commentDto, Comments.class);
		comment.setPost(postDetails);
		Comments savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentsDto.class);
	}

	@Override
	public void deleteCommnet(Integer commentId) {
		// TODO Auto-generated method stub
		Comments commentDtails = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundExecption("Comments", "Comment_Id", commentId));
		this.commentRepo.delete(commentDtails);
		
	}

	@Override
	public CommentsDto updateComment(CommentsDto commentDto, Integer commentID) {
		// TODO Auto-generated method stub
		Comments commentDetails = this.commentRepo.findById(commentID)
				.orElseThrow(() -> new ResourceNotFoundExecption("Comment", "CommentID", commentID));

		commentDetails.setComment(commentDto.getComment());
		
		this.commentRepo.save(commentDetails);

		return this.modelMapper.map(commentDetails, CommentsDto.class);
	}

	@Override
	public CommentsDto getCommnetByCommentId(Integer comment_Id) {
		// TODO Auto-generated method stub
		Comments commentDetails = this.commentRepo.findById(comment_Id)
				.orElseThrow(() -> new ResourceNotFoundExecption("Comment", "comment_Id", comment_Id));

		return this.modelMapper.map(commentDetails, CommentsDto.class);
	}
}
