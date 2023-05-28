package com.itwill.ilhajob.common.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;
import com.itwill.ilhajob.common.dto.OrdersDto;
import com.itwill.ilhajob.common.dto.ProductDto;
import com.itwill.ilhajob.corp.dto.CorpDto;

class OrdersServiceImplTest extends FinalProjectTeam1IlhajobApplicationTests{

	@Autowired
	private OrdersService ordersService; 
	
	@Test
	void test() {
//		OrdersDto order = OrdersDto.builder()
//				.orderStartDate(LocalDateTime.now())
//				.orderEndDate(LocalDateTime.now().plusDays(3L))
//				.corpId(1L).orderValid(3)
//				.productId(1L)
//				.build();
//		ordersService.saveOrder(order);
		//ProductDto product = ProductDto.builder().id(1L).pEndMonth(90L).pPrice(30000).build();
		//ordersService.checkAndSaveOrder("user", 1L, product, "카드");
	}

}
