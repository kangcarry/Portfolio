package com.itwill.ilhajob.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.ilhajob.common.dto.BlogDto;
import com.itwill.ilhajob.common.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>{

	/* 조회수 증가*/
	@Modifying
	@Query("update Blog b set b.blogReadCount = b.blogReadCount + 1 where b.id = :id")
	int updateViews(@Param("id") Long id);

	/* 카테고리 페이징*/
	Page<Blog> findAllByBlogCateId(Long blogCateId, Pageable pageable);
	
	
	 /* 좋아요 카운트 추가 */
    @Modifying
    @Query(value = "update Blog b set b.blogLike = b.blogLike + 1 where b.id = :id")
    int plusLike(@Param("id") Long id);
    
    /* 좋아요 카운트 삭제 */
    @Modifying
    @Query(value = "update Blog b set b.blogLike = b.blogLike - 1 where b.id = :id")
    int minusLike(@Param("id") Long id);
}
