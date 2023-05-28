package com.itwill.ilhajob.corp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.ilhajob.common.dto.CorpTagDto;
import com.itwill.ilhajob.common.dto.RecruitTagDto;
import com.itwill.ilhajob.common.dto.RecruitTagListDto;
import com.itwill.ilhajob.common.dto.TagDto;
import com.itwill.ilhajob.common.service.CorpTagService;
import com.itwill.ilhajob.common.service.TagService;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.dto.UserDto;

@RestController
public class CorpRestController {
	@Autowired
	private TagService tagService;
	@Autowired
	private CorpTagService corpTagService;
	@Autowired
	private CorpService corpService;
	
	@GetMapping(value="/getTagData", produces = "application/json;charset=UTF-8")
	public Map<String, Object> getTagData(@RequestParam(defaultValue = "0") int page,
										  @RequestParam(defaultValue = "8") int size,
										  @RequestParam long tagId,
										  Model model, HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		
		Pageable pageable = PageRequest.of(page, size,Sort.Direction.ASC,"id");
		Page<CorpTagDto> corpTagPage = corpTagService.selectListByTagId(tagId, pageable);
		List<CorpTagDto> corpTagDtoList = corpTagPage.getContent();
		
		map.put("data", corpTagDtoList);
		map.put("corpList", corpTagPage.getContent());
		map.put("nowPage", corpTagPage.getNumber());
		map.put("totalPage", corpTagPage.getTotalPages());
		map.put("prePage", corpTagPage.hasPrevious() ? corpTagPage.previousPageable().getPageNumber() : 0);
		map.put("nextPage", corpTagPage.hasNext() ? corpTagPage.nextPageable().getPageNumber() : corpTagPage.getTotalPages() - 1);
	    map.put("page", corpTagPage);	
	    map.put("url", "/final-project-team1-ilhajob/getTagData");
	    map.put("tagId", tagId);
		return map;
		
	}
	
	//corpName으로 검색
	@GetMapping(value="/search-corpName", produces = "application/json;charset=UTF-8")
	public Map<String, Object> searchByCorpName(@RequestParam(defaultValue = "0") int page,
										  @RequestParam(defaultValue = "8") int size,
										  @RequestParam String corpName,
										  Model model, HttpServletRequest request) throws Exception{

		Map<String, Object> map = new HashMap<String,Object>();
		Pageable pageable = PageRequest.of(page, size,Sort.Direction.ASC,"id");
		Page<CorpDto> corpPage = corpService.searchByCorpName(corpName, pageable);
		List<CorpDto> corpDtoList = corpPage.getContent();
		
		map.put("data", corpDtoList);
		map.put("corpList", corpPage.getContent());
		map.put("nowPage", corpPage.getNumber());
		map.put("totalPage", corpPage.getTotalPages());
		map.put("prePage", corpPage.hasPrevious() ? corpPage.previousPageable().getPageNumber() : 0);
		map.put("nextPage", corpPage.hasNext() ? corpPage.nextPageable().getPageNumber() : corpPage.getTotalPages() - 1);
	    map.put("page", corpPage);	
	    map.put("url", "/final-project-team1-ilhajob/search-corpName");
	    //map.put("tagId", tagId);
	    map.put("corpName", corpName);
		
		return map;
	}
	
	
}
