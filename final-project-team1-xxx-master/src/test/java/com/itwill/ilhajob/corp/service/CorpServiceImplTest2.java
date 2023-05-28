package com.itwill.ilhajob.corp.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;

class CorpServiceImplTest2 extends FinalProjectTeam1IlhajobApplicationTests{

	@Autowired
	private CorpService corpService;
	@Test
	void testFindCorp() throws Exception {
		System.out.println(corpService.findByCorpLoginId("corp_01"));
	}

}
