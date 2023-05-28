package com.itwill.ilhajob.common.service;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.ilhajob.common.dto.BlogDto;
import com.itwill.ilhajob.common.dto.BlogHeartDto;
import com.itwill.ilhajob.common.entity.Blog;
import com.itwill.ilhajob.common.entity.BlogHeart;
import com.itwill.ilhajob.common.repository.BlogHeartRepository;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.User;


@Service
public class BlogHeartServiceImpl implements BlogHeartService {
	
	private final BlogHeartRepository blogHeartRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public BlogHeartServiceImpl(BlogHeartRepository blogHeartRepository, ModelMapper modelMapper) {
		this.blogHeartRepository = blogHeartRepository;
		this.modelMapper = modelMapper;
	}

	
	
	@Override
	@Transactional
	public BlogHeartDto findBlogLikeState(Long blogId, Long userId) throws Exception {
	    BlogHeart blogHeart = blogHeartRepository.findByBlogIdAndUserId(blogId, userId).orElse(null);
	    if (blogHeart != null) {
	        return modelMapper.map(blogHeart, BlogHeartDto.class);
	    }
	    return null;
	}

}
