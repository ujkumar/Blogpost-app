package com.ujjwal.kumar.blogapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ujjwal.kumar.blogapp.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer>{

}
