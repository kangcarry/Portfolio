package com.itwill.ilhajob.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.itwill.ilhajob.corp.service.RecruitService;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.service.RecruitScrapService;
import com.itwill.ilhajob.user.service.UserService;


@RestController
public class RecruitScrapRestController {
	@Autowired
	RecruitScrapService recruitScrapService;
	@Autowired
	RecruitService recruitService;
	@Autowired
	UserService userService;
	
	
	//공고 스크랩 리스트 출력
	@PostMapping(value = "delete-recruit-scrap", produces = "application/json;charset=UTF-8")
	public Map<String, Object> refresh_form(@RequestBody Map<String, String> params) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Long id = Long.parseLong(params.get("id"));
		System.out.println("컨트롤러도착");
		
		RecruitScrapDto scrap = recruitScrapService.sellectById(id);
		recruitScrapService.deleteScrap(id);
		UserDto loginUser = scrap.getUser();
		
		List<RecruitScrapDto> scrapList = recruitScrapService.sellectByUserId(loginUser.getId());
		
		map.put("data", scrapList);
		return map;
	}
	
	@PostMapping(value = "recruit_scrap_insert_delete", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Integer> recruit_scrap_insert_delete(HttpServletRequest request,@RequestBody String requestData) throws Exception {
		Long id = Long.parseLong(requestData.substring(4));
		System.out.println("데이터555 :"+id);
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
		int count = 0;

		RecruitScrapDto recruitScrap=recruitScrapService.sellectByUserIdAndRecruitId(loginUser.getId(),id);
		System.out.println("셀렉트"+count);
		//리쿠르트 스크랩 없으면 생성
		if(recruitScrap==null) {
			recruitScrapService.insertScrap(RecruitScrapDto.builder()
															.recruit(recruitService.findRecruit(id))
															.user(loginUser).build());
			count = 1;
		}else {
			recruitScrapService.deleteScrapByUserIdAndRecruitId(loginUser.getId(),id);
			count = 0;
		}
		
		return ResponseEntity.ok(count);
	}
	
	
	
}
