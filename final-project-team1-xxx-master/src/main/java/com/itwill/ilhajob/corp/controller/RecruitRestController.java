package com.itwill.ilhajob.corp.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.ilhajob.common.dto.CorpTagDto;
import com.itwill.ilhajob.common.dto.RecruitTagDto;
import com.itwill.ilhajob.common.dto.RecruitTagListDto;
import com.itwill.ilhajob.common.dto.TagDto;
import com.itwill.ilhajob.common.service.RecruitTagService;
import com.itwill.ilhajob.common.service.TagService;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.corp.service.RecruitService;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.service.RecruitScrapService;
import com.itwill.ilhajob.user.service.UserService;

import io.netty.handler.codec.http.HttpRequest;

@RestController
public class RecruitRestController {
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private TagService tagService;
	@Autowired
	private RecruitTagService recruitTagService;
	@Autowired
	private UserService userService;
	@Autowired
	private RecruitScrapService recruitScrapService;
	@Autowired
	private CorpService corpService;
	
	@GetMapping(value="/getRecruitTag", produces = "application/json;charset=UTF-8")
	public Map<String,Object> getRecruitTagData(@RequestParam(defaultValue = "0") int page,
												@RequestParam(defaultValue = "8") int size,
												@RequestParam long tagId,
												Model model, HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		List<RecruitDto> recruitList = recruitService.findRecruitAll();
		List<RecruitTagListDto> recruitTagListDto = new ArrayList<RecruitTagListDto>();
		Long id = 0L;
		List<TagDto> tagList = new ArrayList<TagDto>();
		
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = null;
		if(sUserId!=null) {
			loginUser = userService.findUser(sUserId);
		}
		map.put("loginUser", loginUser);
		System.out.println(loginUser);
		
		Map<Integer,Integer> status = new HashMap<Integer, Integer>();
		if(loginUser!=null) {
		//스크랩 (북마크)로고 출력
			List<RecruitScrapDto> recruitScrapList = recruitScrapService.sellectByUserId(loginUser.getId());
			//스크랩확인 카운트 리스트
			
			for(RecruitDto recruit :recruitList) {
			    boolean hasRecruitScrap = false; // 리크루트 스크랩이 있는지 여부를 나타내는 변수
			    for(RecruitScrapDto recruitscrap:recruitScrapList) {
			        if(recruit.getId() == recruitscrap.getRecruit().getId()) {
			            hasRecruitScrap = true; // 리크루트 스크랩이 있을 때
			            break; // 리크루트 스크랩이 있으면 반복문 종료
			        }
			    }
			    if(hasRecruitScrap) {
			    	status.put( Integer.valueOf(recruit.getId().intValue()), 1); // 리크루트 스크랩이 있을 때
			    } else {
			    	status.put(Integer.valueOf(recruit.getId().intValue()), 0); // 리크루트 스크랩이 없을 때
			    }
			}
			System.out.print("status:"+status+"loginUser"+loginUser);
			
		}
		//전체태그선택
		if(tagId==99999) {
			List<RecruitTagDto> recruitTagList = recruitTagService.selectAll();
			for(RecruitDto recruit : recruitList) {
				tagList=new ArrayList<TagDto>();
				for(RecruitTagDto recruitTag:recruitTagList) {
					
					if(recruitTag.getRecruit().getId()==recruit.getId()) {
								id = recruitTag.getId();
								tagList.add(recruitTag.getTag());
					}
					
				}
				recruitTagListDto.add(RecruitTagListDto.builder().id(id).recruit(recruit).tagList(tagList).build());
			}
			map.put("data", recruitTagListDto);
			System.out.println("전체실행완료");
			return map;
		}else {
		//일부태그선택시
		Pageable pageable = PageRequest.of(page, size,Sort.Direction.ASC,"id");
		Page<RecruitDto> recruitTagPage = recruitTagService.selectRecruitsByTagId(tagId, pageable);
		List<RecruitDto> recruitDtoList = recruitTagPage.getContent();
		for (RecruitDto recruitDto : recruitDtoList) {
		    Long rId = recruitDto.getId();
		    List<RecruitTagDto> recruitTagListByRecruitId = recruitTagService.selectAllByRecruitId(rId);

		    // 각 recruitDto에 대한 새로운 tagList를 생성합니다.
		    List<TagDto> tagList1 = new ArrayList<>();

		    for(RecruitTagDto recruitTag: recruitTagListByRecruitId) {
		        tagList1.add(recruitTag.getTag());
		    }
		    recruitTagListDto.add(RecruitTagListDto.builder().id(id).recruit(recruitDto).tagList(tagList1).build());
		}
		map.put("data", recruitTagListDto);
		map.put("status", status);
		map.put("recruitList", recruitTagPage.getContent());
		map.put("nowPage", recruitTagPage.getNumber());
		map.put("totalPage", recruitTagPage.getTotalPages());
		map.put("prePage", recruitTagPage.hasPrevious() ? recruitTagPage.previousPageable().getPageNumber() : 0);
		map.put("nextPage", recruitTagPage.hasNext() ? recruitTagPage.nextPageable().getPageNumber() : recruitTagPage.getTotalPages() - 1);
	    map.put("page", recruitTagPage);	
	    map.put("url", "/final-project-team1-ilhajob/getRecruitTag");
	    map.put("tagId", tagId);
		return map;
		
		}
	}
	
	
	//리쿠르트 태그 생성 (AJAX방식)
	@PostMapping(value = "recruit-tag-insert-action", produces = "application/json;charset=UTF-8")
	public Map<String, Object> recruit_tag_insert_action(HttpServletRequest request,@RequestBody Map<String,String> reMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Long id = Long.parseLong(reMap.get("tagId"));
		Long rid = Long.parseLong(reMap.get("reqId"));
		System.out.println("태그아이디:"+id+"리크루트 아이디:"+rid);
		
		RecruitDto recruitDto = recruitService.findRecruit(rid);
		
		//공고 태그생성
		recruitTagService.insertRecruitTag(new RecruitTagDto(0, recruitDto, tagService.selectTag(id)));
		
		//태그리스트 - 공고태그 연산작업 
				List<RecruitTagDto> recruitTagList = recruitTagService.selectAllByRecruitId(rid);
				List<TagDto>rTagList = new ArrayList<TagDto>();
				for (RecruitTagDto recruitTagDto : recruitTagList) {
					rTagList.add(recruitTagDto.getTag());
				}
				
				List<TagDto>tagList = tagService.selectAll().subList(0, 3); //태그 1~3번만 출력
				tagList.removeAll(rTagList);
		
				
		map.put("tagList",tagList);
		map.put("recruitTagList",recruitTagList);		
		map.put("recruit",recruitDto);		
		return map;
		
		
		
	}
	//리쿠르트 태그 제거 (AJAX방식)
	@PostMapping(value = "recruit-tag-delete-action", produces = "application/json;charset=UTF-8")
	public Map<String, Object> recruit_tag_delete_action(HttpServletRequest request,@RequestBody Map<String,String> reMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Long id = Long.parseLong(reMap.get("reqruitTagId"));
		Long rid = Long.parseLong(reMap.get("reqId"));
		System.out.println("리크루트태그아이디:"+id);
		
		RecruitDto recruitDto = recruitService.findRecruit(rid);
		
		//공고 태그생성
		recruitTagService.deleteRecruitTag(id);
		System.out.println(id+"번 삭제성공");
		
		//태그리스트 - 공고태그 연산작업 
		List<RecruitTagDto> recruitTagList = recruitTagService.selectAllByRecruitId(rid);
		List<TagDto>rTagList = new ArrayList<TagDto>();
		for (RecruitTagDto recruitTagDto : recruitTagList) {
			rTagList.add(recruitTagDto.getTag());
		}
		
		List<TagDto>tagList = tagService.selectAll().subList(0, 3); //태그 1~3번만 출력
		tagList.removeAll(rTagList);
		
		
		map.put("tagList",tagList);
		map.put("recruitTagList",recruitTagList);		
		map.put("recruit",recruitDto);		
		return map;
		
	}
	
}

	
