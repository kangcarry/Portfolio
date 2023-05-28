package com.itwill.ilhajob.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;

class MessageServiceImplTest extends FinalProjectTeam1IlhajobApplicationTests{

	@Autowired
	private MessageService messageService;
	
	@Test
	void test() {
		
		System.out.println(messageService.fineMessageOfUser(2L));
	}

}
