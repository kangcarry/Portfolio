package com.itwill.ilhajob.corp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.ManagerDto;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.corp.service.ManagerService;

import io.swagger.annotations.ApiOperation;

@Controller
public class ManagerController {
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private CorpService corpService;
	
	//매니저리스트
	@RequestMapping("/dashboard-manager-list")
	public String corp_dashboard_manager_list(HttpServletRequest request,Model model) throws Exception{
		
		Long sCorpId =(Long)request.getSession().getAttribute("id");
		CorpDto corpDto=corpService.findByCorpId(sCorpId);
		List<ManagerDto> managerList = managerService.findManagerByCorpID(corpDto.getId());
		
		//System.out.println(managerList);
		model.addAttribute("managerList",managerList);
		return "dashboard-manager-list";
	}
	

	//매니저생성
	
	@PostMapping("manager-create")
    public String createManager(@ModelAttribute ManagerDto managerDto, HttpServletRequest request,Model model) throws Exception {
		Long sCorpId =(Long)request.getSession().getAttribute("id");
		CorpDto corpDto=corpService.findByCorpId(sCorpId);
		System.out.println("생성 컨트롤러 도착");
		System.out.println(managerDto);
		managerDto.setCorp(corpDto);
		ManagerDto createManager = managerService.create(managerDto);
		System.out.println(createManager+"생성완료");
		
        return "redirect:dashboard-manager-list";
    }
	
	//매니저업데이트
	@RequestMapping("manager-update")
	public String updateManager(@ModelAttribute ManagerDto managerDto, HttpServletRequest request,Model model) throws Exception {
		System.out.println("업데이트 컨트롤러 도착");
		System.out.println(managerDto);
		Long sCorpId =(Long)request.getSession().getAttribute("sCorpId");
		CorpDto corpDto=corpService.findByCorpId(sCorpId);
		managerDto.setCorp(corpDto);
		ManagerDto createManager = managerService.update(managerDto);
		System.out.println(createManager+"업뎃완료");
		
		return "redirect:dashboard-manager-list";
	}
	
	//매니저삭제
	@RequestMapping("manager-delete")
	public String deleteManager(@ModelAttribute ManagerDto managerDto, HttpServletRequest request,Model model) throws Exception {
		System.out.println("삭제 컨트롤러 도착");
		System.out.println(managerDto);
		managerService.remove(managerDto.getId());
		System.out.println("삭제완료");
		
		return "redirect:dashboard-manager-list";
	}

	
}
