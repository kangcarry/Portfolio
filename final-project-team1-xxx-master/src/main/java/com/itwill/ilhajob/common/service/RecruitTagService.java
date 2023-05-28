package com.itwill.ilhajob.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.ilhajob.common.dto.RecruitTagDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;

public interface RecruitTagService {

	void insertRecruitTag(RecruitTagDto recruitTagDto);

	void deleteRecruitTag(Long id);

	List<RecruitTagDto> selectAllByRecruitId(long recruitId);

	List<RecruitTagDto> selectAll();
	
	List<RecruitTagDto> selectAllBytagId(long tagId);
	
	Page<RecruitDto> getRecruitTagList(int Page, int size, long tagId) throws Exception;
	Page<RecruitDto> selectRecruitsByTagId(long tagId, Pageable pageble);
}
