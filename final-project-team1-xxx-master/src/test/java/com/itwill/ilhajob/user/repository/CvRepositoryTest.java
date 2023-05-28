package com.itwill.ilhajob.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;

class CvRepositoryTest extends FinalProjectTeam1IlhajobApplicationTests {
	@Autowired
	CvRepository cvRepository;
	
	@Test
	void testFindByUserId() {
		System.out.println(cvRepository.findAll());
		
		System.out.println(cvRepository.findByUserId(1L));
		System.out.println(cvRepository.findByUserId(3L));
		
	}

}
