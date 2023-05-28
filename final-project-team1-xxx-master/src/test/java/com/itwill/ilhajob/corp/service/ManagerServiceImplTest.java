package com.itwill.ilhajob.corp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import javax.transaction.Transactional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.ManagerDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.entity.Manager;
import com.itwill.ilhajob.corp.repository.CorpImageRepository;
import com.itwill.ilhajob.corp.repository.ManagerRepository;

@SpringBootTest
class ManagerServiceImplTest {
	@Autowired
	ManagerService managerService;

	@Test
	void testFindManager() throws Exception {
		System.out.println(managerService.findManager(1L));
	}

	@Test
	@Disabled
	void testUpdate() {
		fail("Not yet implemented");
	}

}
