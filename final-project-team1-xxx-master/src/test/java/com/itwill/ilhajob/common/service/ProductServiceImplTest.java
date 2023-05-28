package com.itwill.ilhajob.common.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;

class ProductServiceImplTest extends FinalProjectTeam1IlhajobApplicationTests {
    @Autowired
    ProductService productService;
	
    //@Disabled
	@Test
	void testselectByNo() throws Exception {
    	System.out.println(">>>>>>>"+productService.selectByDiv("user"));
	}

}
