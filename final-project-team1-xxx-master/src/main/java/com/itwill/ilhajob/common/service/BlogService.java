package com.itwill.ilhajob.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.ilhajob.common.dto.BlogDto;
import com.itwill.ilhajob.common.dto.BlogHeartDto;

public interface BlogService {


	BlogDto insertBlog(BlogDto blogDto) throws Exception;

	List<BlogDto> selectAll();

	BlogDto updateBlog(Long id, BlogDto blogDto) throws Exception;

	void deleteBlog(Long blogId) throws Exception;

	BlogDto findBlog(Long id) throws Exception;

	Page<BlogDto> findAll(Pageable pageable);

	int updateViews(Long id);

	Page<BlogDto> findAllByBlogCateId(Long blogCateId, Pageable pageable);

	int updateLike(Long blogId, Long userId);

	


		
	/*
	List<BlogDto> selectByBlogCate(int blogCateSeq);
	List<BlogDto> findByUserSeqBlogList(int userSeq);
	List<BlogDto> findByBlogAndCommetAll(int blogSeq);
	int updateBlogLikeDiscount(int blogSeq);
	 */

}
