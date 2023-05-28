package com.itwill.ilhajob.user.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;

class RecruitScrapServiceTest extends FinalProjectTeam1IlhajobApplicationTests{
	
	@Autowired
	RecruitScrapService recruitScrapService;
	
	@Test
	@Transactional
	void testInsertScrap() throws Exception {
		recruitScrapService.deleteScrap(6);
	}


}
