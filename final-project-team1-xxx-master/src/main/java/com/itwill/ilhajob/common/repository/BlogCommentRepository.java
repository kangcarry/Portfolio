package com.itwill.ilhajob.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.ilhajob.common.dto.BlogCommentDto;
import com.itwill.ilhajob.common.entity.Blog;
import com.itwill.ilhajob.common.entity.BlogComment;

public interface BlogCommentRepository extends JpaRepository<BlogComment, Long>{

	List<BlogComment> findByBlogId(Long blogId);
	
	Page<BlogComment> findByBlogId(Long blogId, Pageable pageable);

}
