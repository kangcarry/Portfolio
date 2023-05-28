package com.itwill.ilhajob.corp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.entity.Recruit;
import com.itwill.ilhajob.corp.repository.RecruitRepository;


@SpringBootTest
class RecruitServiceTest {
	
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private RecruitRepository recruitRepository;

//	@Test
//	public void recruitListTest(Long corpId) throws Exception {
//		Long corpId1=1L;
//		List<RecruitDto> findRecruitList=recruitService.recruitList(corpId1);
//		Corp corp=new Corp();
//		corp.setId(1L);
//		
//		Recruit recruit=new Recruit();
//		recruit.setCorp(corp);
//		recruit.setRcPosition("직급");
//		recruit.setRcQualification("자격");
//		
//		List<RecruitDto> recruitList1=recruitService.recruitList(corpId);
//		assertThat(recruitList1).isNotEmpty();
//		assertThat(recruitList1.size()).isEqualTo(1);
//		
//		
//	}

}
