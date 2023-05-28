package com.itwill.ilhajob.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.ilhajob.common.dto.BlogHeartDto;
import com.itwill.ilhajob.common.entity.Blog;
import com.itwill.ilhajob.common.entity.BlogHeart;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.User;

public interface BlogHeartRepository extends JpaRepository<BlogHeart, Long> {

	 /** 유저가 특정 게시물을 좋아요 했는지 확인 **/
    boolean existsByblogIdAndUserId(Long blogId, Long UserId);

	BlogHeart findByBlogAndUser(Blog blog, User user);

	Optional<BlogHeart> findByBlogIdAndUserId(Long blogId, Long userId);

	void deleteByBlogIdAndUserId(Long blogId, Long userId);
}
