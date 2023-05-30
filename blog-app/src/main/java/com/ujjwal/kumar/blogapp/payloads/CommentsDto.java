package com.ujjwal.kumar.blogapp.payloads;

public class CommentsDto {

	private int commentId;

	private String comment;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CommentsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
