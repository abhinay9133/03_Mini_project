package com.abhi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.abhi.binding.CreateBlogForm;
import com.abhi.constants.AppConstants;
import com.abhi.entity.BlogsEntity;
import com.abhi.entity.CommentsEntity;
import com.abhi.entity.UserDtlsEntity;
import com.abhi.repo.BlogsRepository;
import com.abhi.repo.CommentsRepository;
import com.abhi.repo.UserDtlsRepository;
import com.abhi.service.BlogsService;

@Service
public class BlogsServiceImpl implements BlogsService {
	
	@Autowired
	private UserDtlsRepository userRepo;
	
	@Autowired
	private BlogsRepository blogRepo;
	
	@Autowired
	private CommentsRepository commentRepo;

	@Override
	public boolean createBlog(CreateBlogForm form,Integer userId) {
		
		UserDtlsEntity user = userRepo.findById(userId).get();
		BlogsEntity blog = new BlogsEntity();
		
		BeanUtils.copyProperties(form, blog);
		
		blog.setUser(user);
		blog.setStatus(AppConstants.BLOG_ACTIVE_STATUS);
		
		blogRepo.save(blog);
		
		return true;
	}
	
	@Override
	public List<BlogsEntity> viewBlogs(Integer userId) {
		
		UserDtlsEntity user = userRepo.findById(userId).get();
		
		BlogsEntity blog = new BlogsEntity();
		blog.setUser(user);
		blog.setStatus(AppConstants.BLOG_ACTIVE_STATUS);
		
		Example<BlogsEntity> example = Example.of(blog);
		
		List<BlogsEntity> blogs = blogRepo.findAll(example);
		
		return blogs;
	}
	
	@Override
	public List<CommentsEntity> getComments(Integer userId) {
		
		UserDtlsEntity user = userRepo.findById(userId).get();
		List<CommentsEntity> comments = new ArrayList<>();
		
		List<BlogsEntity> blogs = blogRepo.findByUser(user);
		
		for(BlogsEntity blog : blogs) {
			List<CommentsEntity> lstOfComments = blog.getLstOfComments();
			comments.addAll(lstOfComments);
		}
		
		return comments;
	}
	
	@Override
	public BlogsEntity getBlog(Integer blogId) {
		
		BlogsEntity blog = blogRepo.findById(blogId).get();
		
		return blog;
	}

	@Override
	public boolean disableBlog(Integer blogId) {
		
		BlogsEntity blog = blogRepo.findById(blogId).get();
		blog.setStatus(AppConstants.BLOG_INACTIVE_STATUS);
		
		blogRepo.save(blog);
		
		return true;
	}
	
	public boolean deleteComment(Integer commentId) {
		
		/*
		 * CommentsEntity commentsEntity = commentRepo.findById(commentId).get();
		 * commentRepo.delete(commentsEntity);
		 * 
		 * if(commentRepo.existsById(commentId)) { commentRepo.deleteById(commentId); }
		 */
		
		Integer rowsAffected = commentRepo.deleteComment(commentId);
		
		return rowsAffected > 0;
	}
	
	@Override
	public List<BlogsEntity> viewUserSearchBlogs(Integer userId, String search) {
		
		UserDtlsEntity user = userRepo.findById(userId).get();
		List<BlogsEntity> blogs = blogRepo.findByBlogTitleContainingIgnoreCase(search, user);
		
		return blogs;
	}

}
