package com.abhi.service;

import java.util.List;

import com.abhi.binding.CommentForm;
import com.abhi.entity.BlogsEntity;
import com.abhi.entity.CommentsEntity;

public interface IndexService {

	List<BlogsEntity> getBlogs();
	
	List<BlogsEntity> getFilteredBlogs(String search);
	
	BlogsEntity getBlog(Integer blogId);
	
	List<CommentsEntity> addComment(CommentForm form);
	
}
