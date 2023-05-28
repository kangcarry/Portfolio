package com.itwill.ilhajob.corp.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.CorpImageDto;

class CorpImageServiceImplTest extends FinalProjectTeam1IlhajobApplicationTests{

	@Autowired
	private CorpImageService corpImageService;
	
	@Test
	void testInsertCorpImage() {
		CorpDto corpDto = CorpDto.builder().id(1L).corpLoginId("corp_01").corpPassword("1111").build();
		System.out.println(
				corpImageService.insertCorpImage(CorpImageDto.builder()
													.id(0)
													.originalFileName("testinsert.jpg")
													.corp(corpDto)
													.build()));
	}
//	@Test
//	void testInsertCorpImage() {
//		CorpDto corpDto = CorpDto.builder().id(1L).corpLoginId("corp_01").corpPassword("1111").build();
//		System.out.println(
//				corpImageService.insertCorpImage(CorpImageDto.builder().id(0)
//													.corpImage("testinsert.jpg")
//													.corp(corpDto)
//													.build()));
//	}

	@Test
	void testSelectById() {
	}

	@Test
	void testFindAllByCorpId() {
		System.out.println(corpImageService.findAllByCorpId(1L));
	}

	@Test
	void testDeleteCorpImageById() {
	}

}
