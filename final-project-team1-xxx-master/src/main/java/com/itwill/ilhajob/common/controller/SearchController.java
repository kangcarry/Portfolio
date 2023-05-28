package com.itwill.ilhajob.common.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itwill.ilhajob.common.dto.CorpTagDto;
import com.itwill.ilhajob.common.dto.RecruitTagDto;
import com.itwill.ilhajob.common.dto.TagDto;
import com.itwill.ilhajob.common.service.AppService;
import com.itwill.ilhajob.common.service.CorpTagService;
import com.itwill.ilhajob.common.service.RecruitTagService;
import com.itwill.ilhajob.common.service.TagService;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.corp.service.ManagerService;
import com.itwill.ilhajob.corp.service.RecruitService;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.service.RecruitScrapService;
import com.itwill.ilhajob.user.service.UserService;

@Controller
public class SearchController {
		
	@Autowired
	private CorpService corpService;
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private TagService tagService;
	@Autowired
	private RecruitTagService recruitTagService;
	@Autowired
	private RecruitScrapService recruitScrapService;
	@Autowired
	private CorpTagService corpTagService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/searchMain", method = RequestMethod.GET)
	public String searchRecruit(@RequestParam("searchKeyword") String searchKeyword, 
								  @RequestParam("searchSelect") String searchSelect,
								  @RequestParam(defaultValue = "0", name = "page") int curPage,
								  @RequestParam(defaultValue = "6") int pageScale,
			                      @RequestParam(defaultValue = "5") int blockScale,
								  Pageable pageable,
		                          Model model, HttpServletRequest request) {
		String forwardPath = "";
		//url queryString 잘라내기
		String queryString = request.getQueryString();
		if (queryString != null) {
		    int pageIndex = queryString.indexOf("&page=");
		    if (pageIndex != -1) {
		        queryString = queryString.substring(0, pageIndex) + queryString.substring(pageIndex + "&page=".length()+1);
		    }
		} else {
		    queryString = "";
		}
		queryString += "&page=";
		model.addAttribute("queryString","searchMain?"+queryString);
		try {
			if (searchSelect.equals("recruit")) {
				pageable = PageRequest.of(curPage, pageScale, Sort.Direction.ASC, "id");
				Page<RecruitDto> searchRecruitList = recruitService.searchRcTitle(searchKeyword, curPage, pageScale);

				// 페이지블록번호
				int curBlock = (int) Math.ceil((searchRecruitList.getNumber()) / blockScale) + 1;
				System.out.println("페이지블록번호 :" + curBlock);
				// 페이지 블록의 시작번호
				int blockBegin = (curBlock - 1) * blockScale + 1;
				// 페이지 블록의 끝 번호
				int blockEnd = blockBegin + blockScale - 1;

			    
				model.addAttribute("recruitList", searchRecruitList.getContent());
				model.addAttribute("blockBegin", blockBegin);
				model.addAttribute("blockEnd", blockEnd);
				model.addAttribute("curPage", searchRecruitList.getNumber());
				model.addAttribute("totalPage", searchRecruitList.getTotalPages());
				model.addAttribute("prePage", searchRecruitList.previousOrFirstPageable().getPageNumber());
				model.addAttribute("nextPage", searchRecruitList.nextOrLastPageable().getPageNumber());

				List<RecruitTagDto> recruitTagList = recruitTagService.selectAll();
				List<TagDto> tagList = tagService.selectAll();
				model.addAttribute("recruitTagList", recruitTagList);
				model.addAttribute("tagList", tagList);

				// 스크랩리스트(로그인아이디가 있다면 북마크리스트 넣어줌)
				String sUserId = (String) request.getSession().getAttribute("sUserId");
				UserDto loginUser = null;
				if (sUserId != null) {
					loginUser = userService.findUser(sUserId);
				}
				model.addAttribute("loginUser", loginUser);

				// 로그인시에만 스크랩 (북마크)로고 출력
				if (loginUser != null) {
					List<RecruitScrapDto> recruitScrapList = recruitScrapService.sellectByUserId(loginUser.getId());
					List<RecruitDto> recruitList = recruitService.findRecruitAll();
					// 스크랩상태확인 리스트
					Map<Long, Integer> status = new HashMap<Long, Integer>();

					for (RecruitDto recruit : recruitList) {
						boolean hasRecruitScrap = false; // 리크루트 스크랩이 있는지 여부를 나타내는 변수
						for (RecruitScrapDto recruitscrap : recruitScrapList) {
							if (recruit.getId() == recruitscrap.getRecruit().getId()) {
								hasRecruitScrap = true; // 리크루트 스크랩이 있을 때
								break; // 리크루트 스크랩이 있으면 반복문 종료
							}
						}
						if (hasRecruitScrap) {
							status.put(recruit.getId(), 1); // 리크루트 스크랩이 있을 때
						} else {
							status.put(recruit.getId(), 0); // 리크루트 스크랩이 없을 때
						}
					}
					System.out.print("status" + status);
					model.addAttribute("status", status);
					model.addAttribute("recruitScrapList", recruitScrapList);
				}
				forwardPath = "recruit-list";
			} else if (searchSelect.equals("corp")) {
				Pageable pageable1 = PageRequest.of(curPage, 8, Sort.Direction.ASC, "id");
				Page<CorpDto> corpPageList = corpService.searchByCorpName(searchKeyword, pageable1);
				int nowPage = corpPageList.getNumber();
				List<CorpDto> corpSearchList = new ArrayList<>();

				// 채용중 뿌리기
				Map<Long, Long> rcCountMap = corpService.getRcCountByCorpIdList(
						corpPageList.getContent().stream().map(CorpDto::getId).collect(Collectors.toList()));
				model.addAttribute("rcCountMap", rcCountMap);

				// 태그리스트 뿌리기
				List<CorpTagDto> corpTagList = corpTagService.selectAll();
				List<TagDto> tagList = tagService.selectAll();
				model.addAttribute("tagList", tagList);
				model.addAttribute("corpTagList", corpTagList);

				// 페이지블록번호
				int curBlock = (int) Math.ceil((corpPageList.getNumber()) / blockScale) + 1;
				System.out.println("페이지블록번호 :" + curBlock);
				// 페이지 블록의 시작번호
				int blockBegin = (curBlock - 1) * blockScale + 1;
				// 페이지 블록의 끝 번호
				int blockEnd = blockBegin + blockScale - 1;

				model.addAttribute("blockBegin", blockBegin);
				model.addAttribute("blockEnd", blockEnd);
				model.addAttribute("curPage", corpPageList.getNumber());
				model.addAttribute("totalPage", corpPageList.getTotalPages());
				model.addAttribute("prePage", corpPageList.previousOrFirstPageable().getPageNumber());
				model.addAttribute("nextPage", corpPageList.nextOrLastPageable().getPageNumber());
				model.addAttribute("corpList", corpPageList.getContent());
				forwardPath = "corp-list";
			}

		} catch (Exception e) {
			// 예외 처리
			e.printStackTrace();
			model.addAttribute("errorMsg", "검색어를 찾을 수 없습니다!");
		}
		return forwardPath;
	}

	@ResponseBody
	@PostMapping("/keywordSearch")
	public ResponseEntity<String> keywordSearch(@RequestBody String requestData, HttpServletRequest request, Model model) {
		
		Gson gson = new Gson();
	    JsonObject jsonObject = gson.fromJson(requestData, JsonObject.class);
	    String searchSelect = jsonObject.get("searchSelect").getAsString();
	    String keyword = jsonObject.get("keyword").getAsString();
	    JsonObject jsonResult = null;
	    if(searchSelect.equals("recruit")) {
	    	List<RecruitDto> resultList = recruitService.searchRcTitle(keyword);
	    	JsonArray jsonArray = new JsonArray();
	        for (RecruitDto recruitDto : resultList) {
	            JsonObject rcTitleObject = new JsonObject();
	            rcTitleObject.addProperty("searchData", recruitDto.getRcTitle());
	            jsonArray.add(rcTitleObject);
	        }
	        jsonResult = new JsonObject();
	        jsonResult.add("results", jsonArray);
	    }else if(searchSelect.equals("corp")) {
	    	List<CorpDto> resultList = corpService.searchByCorpName(keyword);
	    	JsonArray jsonArray = new JsonArray();
	        for (CorpDto corpDto : resultList) {
	            JsonObject corpNameObject = new JsonObject();
	            corpNameObject.addProperty("searchData", corpDto.getCorpName());
	            jsonArray.add(corpNameObject);
	        }
	        jsonResult = new JsonObject();
	        jsonResult.add("results", jsonArray);
	    }
	    
		return ResponseEntity.ok(jsonResult.toString());
	}
	

}
