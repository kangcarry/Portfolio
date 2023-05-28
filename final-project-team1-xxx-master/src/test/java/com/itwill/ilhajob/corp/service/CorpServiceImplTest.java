package com.itwill.ilhajob.corp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.repository.CorpRepository;

class CorpServiceImplTest extends FinalProjectTeam1IlhajobApplicationTests{

	@Autowired
	CorpService corpService;
	@Autowired
	CorpRepository corpRepository;
	
//	@Disabled
//	@Test
//	void testLogin() throws Exception {
//		//System.out.println(corpService.findCorp("corp_01"));
//		Corp corp = corpRepository.findById(5L).orElse(null);
//		System.out.println(corp);
//	}
//	@Disabled
//	@Test
//	void testFindCorp() throws Exception {
//		System.out.println(corpService.findByCorpLoginId("corp_03"));
//	}
//	@Disabled
//	@Test
//	void testUpdate() {
//		fail("Not yet implemented"); // TODO
//	}
//	@Disabled
//	@Test
//	void testRemove() {
//		fail("Not yet implemented"); // TODO
//	}
//	
//	//corpName으로 검색기능구현->테스트 완료
//	@Disabled
//	@Test
//	void testSearchBycorpName()throws Exception {
//		Corp corp1=corpRepository.findById(2L).orElse(null);
//		Corp corp2=corpRepository.findById(3L).orElse(null);
//		System.out.println("corp1>>>>>>>>>>"+corp1);
//		System.out.println("corp2>>>>>>>>>>"+corp2);
//		corpRepository.save(corp1);
//		corpRepository.save(corp2);
//		List<CorpDto> result1=corpService.searchByCorpName("우아");
//		List<CorpDto> result2=corpService.searchByCorpName("우아한청년들");
//		System.out.println("result1:회사이름 일부만 쳤을때>>>>>>>>>>"+result1);
//		System.out.println("result2:회사이름 전체 쳤을때>>>>>>>>>>"+result2);
//	}
//	//job으로 검색기능구현->테스트 완료
//	@Disabled
//	@Test
//	void testSearchByJob()throws Exception{
//		List<CorpDto> result1=corpService.searchByjob("웹프로그래머");
//		List<CorpDto> result2=corpService.searchByjob("응용프로그램");
//		System.out.println("result1:웹프로그래머>>>>>"+result1);
//		System.out.println("result2:응용프로그램>>>>>"+result2);
//	}
//	
//	//corpname이랑 job 둘다 검색기능->테스트 완료
//	@Disabled
//	@Test
//	void testSearchCorps()throws Exception{
//		List<CorpDto> result1=corpService.searchCorps("대보정보통신", "네트워크");
//		List<CorpDto> result2=corpService.searchCorps("우아", "");
//		List<CorpDto> result3=corpService.searchCorps("", "게임");
//		List<CorpDto> result4=corpService.searchCorps("", "");
//		System.out.println("result1:둘다 알 때>>>>"+result1);
//		System.out.println("result2:이름만 알 때>>>>"+result2);
//		System.out.println("result3:job만 알 때>>>>"+result3);
//		System.out.println("result4:둘다 모를때>>>>"+result4);
//	}
//	
//	//corpList 페이징 기능->테스트 완료
//	@Disabled
//	@Test
//	void testGetCorpList() throws Exception{
//		int page=0;
//		int size=10;
//		//List<CorpDto>corpList=corpService.findCorpAll();
//		//System.out.println("전체 corpList>>>>>>>>"+corpList);
//		Page<CorpDto> corpDtoPage=corpService.getCorpList(page, size);
//		List<CorpDto> corpDtoList=corpDtoPage.getContent();
//		assertEquals(size, corpDtoList.size()); //size 둘다 같음
//	}
//	
//	//corpList 페이징 기능 수정된 버전->테스트 완료
//	@Disabled
//	@Test
//	void testFindAll() throws Exception{
//		 int pageSize = 10;
//	        PageRequest pageable = PageRequest.of(0, pageSize);
//
//	        Page<CorpDto> result = corpService.findAll(pageable);
//
//	        assertThat(result).isNotNull();
//	        assertThat(result.getTotalElements()).isGreaterThan(0);
//	        assertThat(result.getContent().size()).isEqualTo(pageSize);
//	}
//	
	
	
}
