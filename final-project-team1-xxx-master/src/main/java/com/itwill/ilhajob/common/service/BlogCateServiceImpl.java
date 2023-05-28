package com.itwill.ilhajob.common.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.common.dto.BlogCateDto;
import com.itwill.ilhajob.common.entity.BlogCate;
import com.itwill.ilhajob.common.repository.BlogCateRepository;

@Service
public class BlogCateServiceImpl implements BlogCateService {
	
	private final BlogCateRepository blogCateRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public BlogCateServiceImpl(BlogCateRepository blogCateRepository, ModelMapper modelMapper) {
		this.blogCateRepository = blogCateRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<BlogCateDto> selectAll(){
		List<BlogCate> blogCateList = blogCateRepository.findAll();
		return blogCateList.stream().map(blogCate -> modelMapper.map(blogCate, BlogCateDto.class))
				.collect(Collectors.toList());
	}

}
