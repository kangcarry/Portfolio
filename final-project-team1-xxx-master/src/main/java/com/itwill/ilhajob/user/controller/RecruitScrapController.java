package com.itwill.ilhajob.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.ilhajob.corp.service.RecruitService;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.service.RecruitScrapService;
import com.itwill.ilhajob.user.service.UserService;

@Controller
public class RecruitScrapController {
	@Autowired
	RecruitService recruitService;
	@Autowired
	RecruitScrapService recruitScrapService;
	@Autowired
	UserService userService;
	
	//공고 스크랩 리스트 출력
		@LoginCheck
		@GetMapping("candidate-dashboard-shortlisted-resume")
		public String recruit_scrap_form(HttpServletRequest request,Model model) throws Exception {
			String forwardPath = "";
			String sUserId = (String)request.getSession().getAttribute("sUserId");
			UserDto loginUser = userService.findUser(sUserId);
			
			List<RecruitScrapDto> scrapList = recruitScrapService.sellectByUserId(loginUser.getId());
			model.addAttribute("recruitScrapList",scrapList);
			
			forwardPath = "candidate-dashboard-shortlisted-resume";
			return forwardPath;
		}
		
		
	//공고 스크랩 추가/제거
		/*
		@LoginCheck
		@GetMapping("recruit_scrap_insert_delete")
		public String recruit_scrap_insert_delete(HttpServletRequest request,@RequestParam int id) throws Exception {
			String sUserId = (String)request.getSession().getAttribute("sUserId");
			UserDto loginUser = userService.findUser(sUserId);


			RecruitScrapDto count=recruitScrapService.sellectByUserIdAndRecruitId(loginUser.getId(),id);
			System.out.println("셀렉트"+count);
			//리쿠르트 스크랩 없으면 생성
			if(count==null) {
				recruitScrapService.insertScrap(RecruitScrapDto.builder()
																.recruit(recruitService.findRecruit(id))
																.user(loginUser).build());
			}else {
				recruitScrapService.deleteScrapByUserIdAndRecruitId(loginUser.getId(),id);
			}
			
			return "redirect:recruit-list";
		}
		*/
		
}
