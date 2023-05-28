package com.itwill.ilhajob.common.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;
import com.itwill.ilhajob.common.dto.AppDto;
import com.itwill.ilhajob.common.entity.App;
import com.itwill.ilhajob.corp.dto.RecruitDto;

class AppServiceImplTest extends FinalProjectTeam1IlhajobApplicationTests{

	@Autowired
	private AppService appService;
	
//	@Test
//	void testFindAllByRecruitId() {
//		System.out.println(appService.findAllByRecruitId(1L));
//	}

	@Test
	void testCreateApp() {
		RecruitDto recruitDto = RecruitDto.builder()
										.rcAppCount(1)
										.build();
		AppDto appDto = AppDto.builder()
							.recruit(recruitDto)
							.build();
		appService.insertApp(appDto);
	}
	@Test
	void testUpdateApp() {
		appService.updateApp(5L, 3);
		appService.updateApp(7L, 1);
		appService.updateApp(8L, 2);
	}
	@Test
	void testFindAllByUserId() {
		System.out.println(appService.findAllByUserId(1L));
	}

}
