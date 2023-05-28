package com.itwill.ilhajob.common.service;

import java.util.Optional;

import com.itwill.ilhajob.common.dto.BlogHeartDto;
import com.itwill.ilhajob.common.entity.BlogHeart;
import com.itwill.ilhajob.user.dto.UserDto;

public interface BlogHeartService {

	BlogHeartDto findBlogLikeState(Long blogId, Long userId) throws Exception;


}