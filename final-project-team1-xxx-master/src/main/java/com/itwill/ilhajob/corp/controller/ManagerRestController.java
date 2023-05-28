package com.itwill.ilhajob.corp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.ManagerDto;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.corp.service.ManagerService;

@RestController
public class ManagerRestController {
	
	@Autowired
	private CorpService corpService;
	@Autowired
	private ManagerService managerService;
	//매니저생성
		@PostMapping(value = "manager", produces = "application/json;charset=UTF-8" )
	    public Map<String,Object> create(@RequestBody Map<String,String> reqMap, HttpServletRequest request) throws Exception {
			Map<String,Object> map = new HashMap<String, Object>();
			System.out.println("레스트 컨트롤러 도착");
			Long sCorpId =(Long)request.getSession().getAttribute("id");
			CorpDto corpDto=corpService.findByCorpId(sCorpId);
			System.out.println("생성 컨트롤러 도착");
			System.out.println(reqMap);
			ManagerDto manager = new ManagerDto();
			manager.setCorp(corpDto);
			manager.setManagerName(reqMap.get("managerName"));
			manager.setManagerEmail(reqMap.get("managerEmail"));
			manager.setManagerPosition(reqMap.get("managerPosition"));
			manager.setManagerPhone(reqMap.get("managerPhone"));
			ManagerDto createManager = managerService.create(manager);
			System.out.println(createManager+"생성완료");
			List<ManagerDto> managerList = managerService.findManagerByCorpID(sCorpId);
			
			map.put("data", managerList);
	        return map;
	    }
}
