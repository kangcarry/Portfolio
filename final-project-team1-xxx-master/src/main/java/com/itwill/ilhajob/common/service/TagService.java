package com.itwill.ilhajob.common.service;

import java.util.List;

import com.itwill.ilhajob.common.dto.CorpTagDto;
import com.itwill.ilhajob.common.dto.TagDto;

public interface TagService {

	TagDto selectTag(Long id);

	List<TagDto> selectAll();
	
}
