package com.itwill.ilhajob.common.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.ilhajob.common.dto.CorpTagDto;
import com.itwill.ilhajob.corp.dto.CorpDto;

public interface CorpTagService {

	void insertCorpTag(CorpTagDto corpTagDto);

	void deleteCorpTag(Long id);

	List<CorpTagDto> selectAllByCorpId(Long corpid);
	
	List<CorpTagDto> selectAll();

	Page<CorpTagDto> selectListByTagId(Long tagId, Pageable pageable);
	
}
