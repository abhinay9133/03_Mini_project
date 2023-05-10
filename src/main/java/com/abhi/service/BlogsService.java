package com.abhi.service;

import java.util.List;

import com.abhi.binding.CreateBlogForm;
import com.abhi.entity.BlogsEntity;
import com.abhi.entity.CommentsEntity;

public interface BlogsService {

	boolean createBlog(CreateBlogForm form,Integer userId);
	
	List<BlogsEntity> viewBlogs(Integer userId);
	
	List<BlogsEntity> viewUserSearchBlogs(Integer userId,String search);
	
	List<CommentsEntity> getComments(Integer userId);
	
	BlogsEntity getBlog(Integer blogId);
	
	boolean disableBlog(Integer blogId);
	
	boolean deleteComment(Integer commentId);
}
