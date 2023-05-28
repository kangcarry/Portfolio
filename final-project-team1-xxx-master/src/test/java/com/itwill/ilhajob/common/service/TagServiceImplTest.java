package com.itwill.ilhajob.common.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.ilhajob.common.dto.RecruitTagDto;
import com.itwill.ilhajob.common.repository.RecruitTagRepository;
import com.itwill.ilhajob.corp.dto.RecruitDto;

@SpringBootTest
class TagServiceImplTest {

	@Autowired
	TagService tagService;
	@Autowired
	CorpTagService corpTagService;
	@Autowired
	RecruitTagService recruitTagService;
	@Autowired
	RecruitTagRepository recruitTagRepository;
	
	@Test
	@Transactional
	void test2() {
		//System.out.println(corpTagService.selectAll());
		int page = 0;
		int size = 6;
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
		Page<RecruitDto> recruitTagPage = recruitTagService.selectRecruitsByTagId(1L, pageable);
		System.out.println(">>"+recruitTagPage.getContent().get(0));
		
	}

}
