package com.itwill.ilhajob.corp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.ilhajob.common.dto.AppDto;
import com.itwill.ilhajob.common.dto.RecruitTagDto;
import com.itwill.ilhajob.common.dto.TagDto;
import com.itwill.ilhajob.common.service.AppService;
import com.itwill.ilhajob.common.service.RecruitTagService;
import com.itwill.ilhajob.common.service.TagService;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.ManagerDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.corp.service.ManagerService;
import com.itwill.ilhajob.corp.service.RecruitService;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.dto.ReviewDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.service.RecruitScrapService;
import com.itwill.ilhajob.user.service.ReviewService;
import com.itwill.ilhajob.user.service.UserService;

@Controller
public class RecruitController {
	@Autowired
	private CorpService corpService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RecruitService recruitService;
	@Autowired
	private TagService tagService;
	@Autowired
	private RecruitTagService recruitTagService;
	@Autowired
	private AppService appService;
	@Autowired
	private RecruitScrapService recruitScrapService;
	@Autowired
	private UserService userService;
	
	//home에 recruitList 뿌리기
	@RequestMapping(value = { "/", "/index" })
	public String main(Model model) throws Exception {
		// 전체 공고 리스트
		List<RecruitDto> recruitListAll = recruitService.findRecruitAll();
		List<RecruitDto> recruitList = new ArrayList<RecruitDto>();
		Random random = new Random();
		while (recruitList.size() < 9) {
		    int randomIndex = random.nextInt(recruitListAll.size());
		    RecruitDto randomRecruit = recruitListAll.get(randomIndex);
		    if (randomRecruit.getRcDeadline().isAfter(LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0))) {
		        recruitList.add(randomRecruit);
		    }
		}
		
		model.addAttribute("recruitList", recruitList);
		
		// 마감 임박(Today+7 까지) 공고 리스트
		List<RecruitDto> deadLineRecruitList = new ArrayList<RecruitDto>();

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime endToday = now.withHour(23).withMinute(59).withSecond(59);
		System.out.println(">>>>>>>>>>>> 오늘의 끝 : " + endToday);
		
		LocalDateTime deadline = endToday.plusDays(7);
		System.out.println(">>>>>>>>>>>> deadline : " + deadline);
		
		for (RecruitDto recruit : recruitListAll) {
			if (recruit.getRcDeadline().isAfter(now.minusDays(1).withHour(23).withMinute(59).withSecond(59)) && recruit.getRcDeadline().isBefore(deadline) && recruit.getRcDeadline().compareTo(deadline) <= 0) {
					deadLineRecruitList.add(recruit);
					System.out.println("공고 마감날짜  : " + recruit.getRcDeadline());
					model.addAttribute("deadLineRecruitList", deadLineRecruitList);
			} 
		}
		System.out.println("마감임박 공고리스트 : " + deadLineRecruitList);
		System.out.println("마감임박 공고리스트 수 : " + deadLineRecruitList.size());
		
		if (deadLineRecruitList.size() == 0) {
			System.out.println("마감임박 공고 없음");
		}
		
		//태그리스트
		List<RecruitTagDto> recruitTagList = recruitTagService.selectAll();
		List<TagDto> tagList = tagService.selectAll();
		model.addAttribute("recruitTagList", recruitTagList);
		model.addAttribute("tagList", tagList);
		
		String forward_path = "index";
		return forward_path;
	}
	
	
	//조회수 증가 기능->수정중...ㅠ
	@RequestMapping("/increase-readCount-action")
	public String increase_readCount(@RequestParam("id")Long id,@ModelAttribute RecruitDto recruitDto,HttpServletRequest request,Model model) throws Exception {
		RecruitDto recruit=recruitService.findRecruit(id);
		recruitService.increaseReadCount(id);
		//RecruitDto updateRecruit=recruitService.update(recruit.getId());
		//System.out.println("update>>>"+updateRecruit);
		model.addAttribute("updateRecruit",recruit);
		return "recruit-detail";
	}

	@GetMapping("/recruit-list")
	public String recruit_list(@RequestParam(defaultValue = "0", name = "page") int curPage,
					           @RequestParam(defaultValue = "8") int pageScale,
					           @RequestParam(defaultValue = "5") int blockScale,
	                           @RequestParam(value = "sortType", required = false) String sortType,
	                           Model model,HttpServletRequest request) throws Exception {
		
		//공고 정렬에 페이징 기능 추가->pageScale 8개씩 나오게 해놓음
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

		// 새로운 &page= 추가
		
		Page<RecruitDto> recruitPage;
		if("rcDeadlinedesc".equalsIgnoreCase(sortType)) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>내림");
			Pageable pageable = PageRequest.of(curPage, pageScale, Sort.Direction.DESC, "rcDeadline");
			recruitPage = recruitService.findAll(pageable);
		}else if("rcDeadlineasc".equalsIgnoreCase(sortType)){
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>오름");
			Pageable pageable = PageRequest.of(curPage, pageScale, Sort.Direction.ASC, "rcDeadline");
			recruitPage = recruitService.findAll(pageable);
		}
		else {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>일반");
			Pageable pageable = PageRequest.of(curPage, pageScale, Sort.Direction.ASC, "id");
			recruitPage = recruitService.findAll(pageable);
		}
	    //이전, 다음페이지 설정해야함...
	    model.addAttribute("recruitList", recruitPage.getContent());
//	    model.addAttribute("nowPage", nowPage);
//	    model.addAttribute("totalPage", recruitPage.getTotalPages());
//	    model.addAttribute("prePage", recruitPage.hasPrevious() ? recruitPage.previousPageable().getPageNumber() : 0);
//	    model.addAttribute("nextPage", recruitPage.hasNext() ? recruitPage.nextPageable().getPageNumber() : recruitPage.getTotalPages() - 1);
	    
	    //페이지블록번호
  		int curBlock = (int) Math.ceil((recruitPage.getNumber()) / blockScale) + 1;
  	    System.out.println("페이지블록번호 :"+curBlock);
  	    //페이지 블록의 시작번호
  	 	int blockBegin = (curBlock - 1) * blockScale + 1;
  	 	//페이지 블록의 끝 번호
  	 	int	blockEnd = blockBegin + blockScale - 1;
  	 	System.out.println("페이지블록시작번호 :"+blockBegin);
  	 	System.out.println("페이지블록  끝번호 :"+blockEnd);
  	 	
  	 	model.addAttribute("queryString","recruit-list?"+queryString);
  	 	model.addAttribute("blockBegin", blockBegin);
  	 	model.addAttribute("blockEnd", blockEnd);
  	    model.addAttribute("curPage", recruitPage.getNumber());
  	    model.addAttribute("totalPage", recruitPage.getTotalPages());
  	    model.addAttribute("prePage", recruitPage.previousOrFirstPageable().getPageNumber());
  	    model.addAttribute("nextPage", recruitPage.nextOrLastPageable().getPageNumber());
		
		//태그리스트
		List<RecruitTagDto> recruitTagList = recruitTagService.selectAll();
		List<TagDto> tagList = tagService.selectAll();
		model.addAttribute("recruitTagList", recruitTagList);
		model.addAttribute("tagList", tagList);
		
		
		//스크랩리스트(로그인아이디가 있다면 북마크리스트 넣어줌)
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = null;
		if(sUserId!=null) {
			loginUser = userService.findUser(sUserId);
		}
		model.addAttribute("loginUser", loginUser);
		
		
		//로그인시에만 스크랩 (북마크)로고 출력
		if(loginUser!=null) {
			List<RecruitScrapDto> recruitScrapList = recruitScrapService.sellectByUserId(loginUser.getId());
			List<RecruitDto> recruitList = recruitService.findRecruitAll();
			//스크랩상태확인 리스트
			Map<Long,Integer> status = new HashMap<Long, Integer>();
			
			for(RecruitDto recruit :recruitList) {
			    boolean hasRecruitScrap = false; // 리크루트 스크랩이 있는지 여부를 나타내는 변수
			    for(RecruitScrapDto recruitscrap:recruitScrapList) {
			        if(recruit.getId() == recruitscrap.getRecruit().getId()) {
			            hasRecruitScrap = true; // 리크루트 스크랩이 있을 때
			            break; // 리크루트 스크랩이 있으면 반복문 종료
			        }
			    }
			    if(hasRecruitScrap) {
			    	status.put(recruit.getId(), 1); // 리크루트 스크랩이 있을 때
			    } else {
			    	status.put(recruit.getId(), 0); // 리크루트 스크랩이 없을 때
			    }
			}
			System.out.print("status"+status);
			model.addAttribute("status", status);
			model.addAttribute("recruitScrapList", recruitScrapList);
		}
		
		String forward_path = "recruit-list";
		return forward_path;
		
		
	}


	@RequestMapping(value = "/recruit-detail", params = "!id")
	public String recruit_detail() {
		return "redirect:index";
	}

	//@LoginCheck
	@RequestMapping(value = "/recruit-detail", params = "id")
	public String recruit_detail(@RequestParam long id, Model model,HttpServletRequest request) throws Exception {
		RecruitDto recruit = recruitService.findRecruit(id);
		model.addAttribute("recruit", recruit);
//		List<AppDto> appList = appService.findAllByRecruitId(id);
		List<AppDto> appList = new ArrayList<AppDto>();
		if (appService.findAllByRecruitId(id).size() != 0) {
			appList = appService.findAllByRecruitId(id);
			model.addAttribute("appList", appList);
		}
		List<ManagerDto> managerList = managerService.findManagerByCorpID(recruit.getCorp().getId());
		model.addAttribute("managerList", managerList);
		
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		if(sUserId!=null) {
			UserDto user= userService.findUser(sUserId);
			//공고스크랩
			if(user!=null) {
				RecruitScrapDto scrap = 
						recruitScrapService.sellectByUserIdAndRecruitId(user.getId(), recruit.getId());
				System.out.println("공고스크랩:"+scrap);
				model.addAttribute("scrap", scrap);
			}
		}
		
		
		//공고태그리스트 선별
		List<RecruitTagDto> recruitTagList = recruitTagService.selectAllByRecruitId(id);
		List<String> recruitTagNameList = new ArrayList<String>();
		for (RecruitTagDto recruitTagDto : recruitTagList) {
			TagDto tag = tagService.selectTag(recruitTagDto.getTag().getTagId());
			recruitTagNameList.add(tag.getTagName());
		}
		model.addAttribute("recruitTagNameList", recruitTagNameList);
		model.addAttribute("scrap", null);
		
		
		String forward_path = "recruit-detail";
		return forward_path;
	}

	@RequestMapping("/dashboard-post-job")
	public String dashboard_post_job_form(HttpServletRequest request, Model model) throws Exception {
		
		CorpDto loginCorp = corpService.findByCorpId((Long) request.getSession().getAttribute("id"));
		model.addAttribute("corp", loginCorp);
		
		
		//결제확인 및 업데이트상태확인
		int paymentStatus = (Integer)request.getSession().getAttribute("paymentStatus");
		int updateStatus = (Integer)request.getSession().getAttribute("updateStatus");
		System.out.println("결제상태:"+paymentStatus+"업데이트상태:"+updateStatus);
		if(updateStatus!=1) {
			return "redirect:dashboard-company-profile";
		}else if(paymentStatus!=1) {
			return "redirect:product";
		}
		String forward_path = "dashboard-post-job";
		return forward_path;
		
	}

	@PostMapping("/dashboard-post-job-action")
	public String dashboard_post_job_action(@ModelAttribute RecruitDto recruitDto, HttpServletRequest request)
			throws Exception {
		CorpDto loginCorp = corpService.findByCorpId((Long) request.getSession().getAttribute("id"));
		System.out.println("가져온 recruitDto : " + recruitDto);
		recruitDto.setRcDate(LocalDateTime.now());
		//마감일 설정
		recruitDto.setRcDeadline(LocalDateTime.now().plusDays(30));
		recruitDto.setRcStatus(0);
		recruitDto.setRcAppCount(0);
		recruitDto.setRcReadCount(0);
		recruitDto.setCorp(loginCorp);
		recruitDto = recruitService.create(recruitDto);
		
		//수정시에만 권한부여
		request.getSession().setAttribute("updateStatus", 1);
		
		String forward_path = "redirect:recruit-detail?id=" + recruitDto.getId();
		return forward_path;
	}

	// 테스트아직 안함
//	   @PostMapping("/dashboard-post-remove-action")
//	   public String dashboard_post_remove_action(@ModelAttribute RecruitDto recruitDto, HttpServletRequest request) throws Exception {
//		  CorpDto loginCorp = corpService.findCorp((String)request.getSession().getAttribute("sCorpId"));
//		  System.out.println(recruitDto);
//		  //recruitService.remove(0);
//	      String forward_path = "redirect:recruit-list";
//	      return forward_path;
//	   }

	@PostMapping("/recruit-delete-action")
	public String recruit_delete_action(@ModelAttribute RecruitDto recruitDto) throws Exception {
		System.out.println("삭제 전");
		System.out.println(">>>>>>>recruit>>>>>>>" + recruitDto);
		System.out.println(recruitDto.getId());
		recruitService.remove(recruitDto.getId());
		System.out.println("삭제 후");
		
		return "redirect:dashboard-manage-job";
	}
 
	@RequestMapping("/recruit-modify-form")
	public String recruit_modify_form(HttpServletRequest request,@ModelAttribute RecruitDto recruitDto, Model model) throws Exception {
		// 일단 회사 정보가져와서 담기
		Long sCorpId =(Long)request.getSession().getAttribute("id");
		CorpDto corpDto=corpService.findByCorpId(sCorpId);
		recruitDto.setCorp(corpDto);
		
		//태그리스트 - 공고태그 연산작업 
		List<RecruitTagDto> recruitTagList = recruitTagService.selectAllByRecruitId(recruitDto.getId());
		List<TagDto>rTagList = new ArrayList<TagDto>();
		for (RecruitTagDto recruitTagDto : recruitTagList) {
			rTagList.add(recruitTagDto.getTag());
		}
		
		List<TagDto>tagList = tagService.selectAll().subList(0, 3); //태그 1~3번만 출력
		tagList.removeAll(rTagList);
		System.out.println(tagList);
		
		
		RecruitDto setRecruit=recruitService.findRecruit(recruitDto.getId());
		System.out.println("setRecruit>>>"+setRecruit);
		model.addAttribute("recruit",setRecruit);
		model.addAttribute("tagList",tagList);
		model.addAttribute("recruitTagList",recruitTagList);
		
		
		return "recruit-modify-form";
	}
	
	@RequestMapping("/recruit-modify-action")
	public String recruit_modify_action(@RequestParam("date")String date,@ModelAttribute RecruitDto recruitDto,Model model,HttpServletRequest request) throws Exception {
		Long sCorpId =(Long)request.getSession().getAttribute("id");
		CorpDto corpDto=corpService.findByCorpId(sCorpId);
		recruitDto.setCorp(corpDto);
		recruitDto.setRcDate(LocalDateTime.now());
		
		//마감일 설정
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime time=LocalDate.parse(date,formatter).atStartOfDay();
		recruitDto.setRcDeadline(time);
		
		//마감일=등록일+30일로 설정
		//recruitDto.setRcDeadline(LocalDateTime.now().plusDays(30));
		
		//공고 등록시 진행중으로 변경
		recruitDto.setRcStatus(0);
		
		//폼에 없는 데이터 업데이트
		recruitDto.setRcAppCount(recruitService.findRecruit(recruitDto.getId()).getRcAppCount());
		recruitDto.setRcDate(recruitService.findRecruit(recruitDto.getId()).getRcDate());
		recruitDto.setRcReadCount(recruitService.findRecruit(recruitDto.getId()).getRcReadCount());
		
		RecruitDto checkRecruit = recruitService.update(recruitDto);
		
		//System.out.println("update check>>>>"+checkRecruit);
		model.addAttribute("id",recruitDto.getId());
		return "redirect:recruit-detail?id=" + recruitDto.getId();
		
	}
	
	//공고리스트-지원자 이력서보기-승인 버튼 클릭 시
	@RequestMapping("/cv-approve-action")
	public String cv_approve_action(@ModelAttribute AppDto appDto){
		System.out.println("승인 전 컨트롤러 확인");
		System.out.println("appDto>>>>>>>>"+appDto); //appstatus=0,cv=null인 상태
		appDto.setAppStatus(2);
		System.out.println("appDto 승인 버튼 클릭 후>>>>"+appDto); //appstatus=2로 변경됨
		AppDto approveAppDto=appService.updateApp(appDto.getId(), appDto.getAppStatus());
		System.out.println("update된 appDtp>>>>"+approveAppDto); //recruit, cv다 불러옴 +appStatus 2되는 것 확인
		return "redirect:dashboard-applicants?id="+approveAppDto.getRecruit().getId();
	}
	//공고리스트-지원자 이력서보기-거절 버튼 클릭 시
	@RequestMapping("/cv-reject-action")
	public String cv_reject_action(@ModelAttribute AppDto appDto) {
		appDto.setAppStatus(3);
		System.out.println("appDto 거절 버튼 클릭 후>>>>"+appDto);
		AppDto rejectAppDto=appService.updateApp(appDto.getId(), appDto.getAppStatus());
		System.out.println("reject된 appDto>>>>"+rejectAppDto); //recruit, cv다 불러옴 +appStatus 3되는 것 확인
		return "redirect:dashboard-applicants?id="+rejectAppDto.getRecruit().getId();
	}
	
	
}
