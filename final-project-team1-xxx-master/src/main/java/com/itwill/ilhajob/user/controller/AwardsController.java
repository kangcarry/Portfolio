package com.itwill.ilhajob.user.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.ilhajob.user.dto.AwardsDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.service.AwardsService;
import com.itwill.ilhajob.user.service.UserService;

@Controller
public class AwardsController {
	@Autowired
	AwardsService awardsService;
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/awards-create")
	public String createAwards(
			HttpServletRequest request, 
			@RequestParam String awardsName, 
			@RequestParam String awardsContent, 
			@RequestParam String awardsDate, 
			@RequestParam String cvId, 
			RedirectAttributes redirectAttributes) {
		String userEmail = (String)request.getSession().getAttribute("sUserId");
		UserDto user;
		AwardsDto awardsDto = new AwardsDto();
		try {
			user = userService.findUser(userEmail);
			
			awardsName = awardsName.replace(",", "");
			awardsContent = awardsContent.replace(",", "");
			awardsDate = awardsDate.replace(",", "");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime awardsDateTime = LocalDate.parse(awardsDate, formatter).atStartOfDay();
			
			awardsDto.setAwardsName(awardsName);
			awardsDto.setAwardsContent(awardsContent);
			awardsDto.setAwardsDate(awardsDateTime);
			awardsDto.setUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(">>>>>>>>>>>>> awardsDto : " + awardsDto);
		awardsService.createAwards(awardsDto);
		redirectAttributes.addAttribute("cvId", cvId);
		return "redirect:cv-detail";
	}
	
	@PostMapping(value = "/awards-delete")
	public String deleteAwards(@RequestParam(name="awardsId") String id, @RequestParam String cvId, RedirectAttributes redirectAttributes) {
		Long awardsId = Long.parseLong(id.replace(",", ""));
		awardsService.removeAwardsById(awardsId);
		redirectAttributes.addAttribute("cvId", cvId);
		return "redirect:cv-detail";
	}
	
	@PostMapping(value = "/awards-update")
	public String updateAwards(
			HttpServletRequest request, 
			@RequestParam(name="awardsId") String id, 
			@RequestParam String awardsName, 
			@RequestParam String awardsContent,  
			@RequestParam String awardsDate, 
			@RequestParam String cvId, 
			RedirectAttributes redirectAttributes
			) {
		AwardsDto awardsDto = new AwardsDto();
		
		Long awardsId = Long.parseLong(id.replace(",", ""));
		awardsName = awardsName.replace(",", "");
		awardsContent =awardsContent.replace(",", "");
		awardsDate = awardsDate.replace(",", "");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime awardsDateTime = LocalDate.parse(awardsDate, formatter).atStartOfDay();
		
		awardsDto.setAwardsName(awardsName);
		awardsDto.setAwardsContent(awardsContent);
		awardsDto.setAwardsDate(awardsDateTime);
		
		awardsService.updateAwards(awardsId, awardsDto);
		
		redirectAttributes.addAttribute("cvId", cvId);
		return "redirect:cv-detail";
	}
}
