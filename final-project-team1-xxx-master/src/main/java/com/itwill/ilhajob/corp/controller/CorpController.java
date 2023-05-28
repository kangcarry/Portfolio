
package com.itwill.ilhajob.corp.controller;

import java.io.File;

import java.util.Comparator;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.type.LocalDateType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.ilhajob.common.dto.AppDto;
import com.itwill.ilhajob.common.dto.BlogDto;
import com.itwill.ilhajob.common.dto.CorpTagDto;
import com.itwill.ilhajob.common.dto.TagDto;
import com.itwill.ilhajob.common.entity.Tag;
import com.itwill.ilhajob.common.service.AppService;
import com.itwill.ilhajob.common.service.CorpTagService;
import com.itwill.ilhajob.common.service.TagService;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.CorpImageDto;
import com.itwill.ilhajob.corp.dto.ManagerDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.entity.CorpImage;
import com.itwill.ilhajob.corp.entity.Manager;
import com.itwill.ilhajob.corp.exception.CorpNotFoundException;
import com.itwill.ilhajob.corp.repository.ManagerRepository;
import com.itwill.ilhajob.corp.service.CorpImageService;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.corp.service.CorpServiceImpl;
import com.itwill.ilhajob.corp.service.ManagerService;
import com.itwill.ilhajob.corp.service.RecruitService;
import com.itwill.ilhajob.user.controller.LoginCheck;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.dto.ReviewDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.RecruitScrap;
import com.itwill.ilhajob.user.exception.PasswordMismatchException;
import com.itwill.ilhajob.user.service.RecruitScrapService;
import com.itwill.ilhajob.user.service.ReviewService;
import com.itwill.ilhajob.user.service.UserService;

@Controller
public class CorpController {

	@Autowired
	private CorpService corpService;
	@Autowired
	private CorpImageService corpImageService;
	@Autowired
	private RecruitService recruitService;
	
	@Autowired
	private RecruitScrapService recruitScrapService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private AppService appService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private CorpTagService corpTagService;

	@Autowired
	private TagService tagService;
	
	@Autowired
	private ManagerService managerService;
//
//	@RequestMapping("/index")
//	public String main() {
//		return "redirect:/";
//	}

	
	@GetMapping("/corp-list")
	public String corp_list(@RequestParam(defaultValue = "0", name = "page") int curPage,
	                        @RequestParam(defaultValue = "8") int pageScale,
	                        @RequestParam(defaultValue = "5") int blockScale,
	                        HttpServletRequest request,
	                        Model model) throws Exception {
		String forward_path="";
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
		
		
		//페이징 기능 추가->일단 8개씩 나오게 해놓음
	    Pageable pageable = PageRequest.of(curPage, pageScale, Sort.Direction.ASC, "id");
	    Page<CorpDto> corpPageList = corpService.findAll(pageable);
	    
	    //이전, 다음페이지 설정해야함...
	    model.addAttribute("corpList", corpPageList.getContent());
	    
		//페이지블록번호
		int curBlock = (int) Math.ceil((corpPageList.getNumber()) / blockScale) + 1;
	    System.out.println("페이지블록번호 :"+curBlock);
	    //페이지 블록의 시작번호
	 	int blockBegin = (curBlock - 1) * blockScale + 1;
	 	//페이지 블록의 끝 번호
	 	int	blockEnd = blockBegin + blockScale - 1;
	 	System.out.println("페이지블록시작번호 :"+blockBegin);
	 	System.out.println("페이지블록  끝번호 :"+blockEnd);
	 	
	 	model.addAttribute("queryString", "corp-list?"+queryString);
	 	model.addAttribute("blockBegin", blockBegin);
	 	model.addAttribute("blockEnd", blockEnd);
	    model.addAttribute("curPage", corpPageList.getNumber());
	    model.addAttribute("totalPage", corpPageList.getTotalPages());
	    model.addAttribute("prePage", corpPageList.previousOrFirstPageable().getPageNumber());
	    model.addAttribute("nextPage", corpPageList.nextOrLastPageable().getPageNumber());
	    
		List<CorpTagDto> corpTagList = corpTagService.selectAll();
		List<TagDto> tagList = tagService.selectAll();
		model.addAttribute("tagList", tagList);
		model.addAttribute("corpTagList", corpTagList);
		
		//채용중 띄우기->해당 corp의 recruit 개수가 0보다 클 때 띄우려고 함
		Map<Long, Long>rcCountMap=corpService.getRcCountByCorpIdList(
				corpPageList.getContent().stream().map(CorpDto::getId).collect(Collectors.toList()));
		model.addAttribute("rcCountMap", rcCountMap);
		forward_path = "corp-list";
	    
	    return forward_path;
	}

	@RequestMapping("corp-detail")
	public String corp_detail_view(@RequestParam("corpId") Long corpId, HttpServletRequest request, Model model)
			throws Exception {
		String sUserId = (String) request.getSession().getAttribute("sUserId");

		// 공고 개수 불러오기
		CorpDto corpDto1 = corpService.findByCorpId(corpId);
		Long recruitCount = recruitService.countByCorpId(corpDto1.getId());
		model.addAttribute("recruitCount", recruitCount);

		if (sUserId == null) {
			CorpDto corpDto = corpService.findByCorpId(corpId);
			model.addAttribute("corp", corpDto);

			// 기업 태그 리스트 뿌리기
			List<CorpTagDto> corpTagList = corpTagService.selectAllByCorpId(corpDto.getId());
			model.addAttribute("corpTagList", corpTagList);

			// 공고 목록 뿌리기
			List<RecruitDto> recruitList = recruitService.findRecruitAll();
			List<RecruitDto> recruitList1 = new ArrayList<>();
			for (RecruitDto recruitDto : recruitList) {
				if (recruitDto.getCorp().getId() == corpDto.getId()) {
					recruitList1.add(recruitDto);
				}
			}
			model.addAttribute("recruitList", recruitList1);
			// 리뷰 목록 뿌리기

			List<ReviewDto> reviewList = corpService.findReviewList(corpDto.getId());
			model.addAttribute("reviewList", reviewList);
		} else {
			CorpDto corpDto = corpService.findByCorpId(corpId);
			model.addAttribute("corp", corpDto);

			// 공고 목록 뿌리기
			List<RecruitDto> recruitList = recruitService.findRecruitAll();
			List<RecruitDto> recruitList1 = new ArrayList<>();

			for (RecruitDto recruitDto : recruitList) {
				if (recruitDto.getCorp().getId() == corpDto.getId()) {
					recruitList1.add(recruitDto);
				}
			}
			model.addAttribute("recruitList", recruitList1);
			// String sUserId = (String)request.getSession().getAttribute("sUserId");
			UserDto loginUser = userService.findUser(sUserId);
			long count = reviewService.isReviewDuplicate(loginUser.getId(), corpDto.getId()); // 이미 리뷰존재하면 1, 없으면 0
			model.addAttribute("count", count);
			request.setAttribute("loginUser", loginUser);

			// 리뷰 목록 뿌리기

			List<ReviewDto> reviewList = corpService.findReviewList(corpDto.getId());
			model.addAttribute("reviewList", reviewList);
		}

		return "corp-detail";

	}

//	@RequestMapping("/login")
//	public String login() {
//		String forward_path = "login";
//		return forward_path;
//	}
	@PostMapping("corp_login_action")
	public String corp_login_action(@ModelAttribute("fcorp") CorpDto corpDto, Model model, HttpSession session)
			throws Exception {
		String forwardPath = "";
		try {
			corpService.login(corpDto.getCorpLoginId(), corpDto.getCorpPassword());
			session.setAttribute("sCorpId", corpDto.getId());
			System.out.println(corpDto.getCorpLoginId());
			forwardPath = "redirect:dashboard";
		} catch (CorpNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("msg1", e.getMessage());
			forwardPath = "login";
		} catch (PasswordMismatchException e) {
			e.printStackTrace();
			model.addAttribute("msg2", e.getMessage());
			forwardPath = "login";
		}
		return forwardPath;
	}
	
	@RequestMapping("/corp_logout_action")
	public String corp_logout_action(@ModelAttribute("fcorp")CorpDto corpDto,HttpServletRequest request) {
		String forwardPath="";
		request.getSession().invalidate();
		forwardPath="redirect:index";
		return forwardPath;
	}
	

	/*****************************************************************************
	 * 대쉬보드
	 ******************************************************************************/

	@RequestMapping("/dashboard")
	public String corp_dashboard_view(HttpServletRequest request,Model model) throws Exception {
		String forwardPath = "";

		/************** login check **************/
		// request.getSession().setAttribute("id", "1L"); //임시로 아이디 로그인상태
		//request.getSession().setAttribute("id", 1L); // 임시로 아이디 로그인상태
		//request.getSession().setAttribute("role", "corp"); // 임시로 아이디 로그인상태
		Long sCorpId = (Long) request.getSession().getAttribute("id");
		if (sCorpId == null) {
			forwardPath = "redirect:login";
		} else {
			// System.out.println(loginCorp);
			CorpDto loginCorp = corpService.findByCorpId(sCorpId);
			request.setAttribute("corp", loginCorp);
			
			//로그인한 회사의 공고리스트 뽑기
			List<RecruitDto> recruitList = recruitService.findAllByCorpId(loginCorp.getId());
			
			//로그인한 회사의 공고리스트 내 지원 전부출력
			List<AppDto> appList = new ArrayList<AppDto>();
			for(RecruitDto recruit:recruitList) {
			appList.addAll(appService.findAllByRecruitId(recruit.getId())); 
			}
			
			//매니저리스트
			List<ManagerDto> managerList = managerService.findManagerByCorpID(sCorpId);
			
			//차트필요한 리스트
			List<String>nameList = new ArrayList<String>();
			List<Integer>rcCountList = new ArrayList<Integer>();
			for(RecruitDto recruit :recruitList) {
				nameList.add(recruit.getCorp().getCorpName());
				rcCountList.add(recruit.getRcAppCount());
			}
			
			model.addAttribute("recruitList",recruitList);
			model.addAttribute("appList",appList);
			model.addAttribute("managerList",managerList);
			model.addAttribute("nameList",nameList);
			model.addAttribute("rcCountList",rcCountList);
			forwardPath = "dashboard";
		}
		
		return forwardPath;
	}

	@RequestMapping("/dashboard-company-profile")
	public String corp_dashboard_company_profile(HttpServletRequest request, Model model) throws Exception {

		String forwardPath = "";
		Long sCorpId = (Long) request.getSession().getAttribute("id");
		CorpDto corpDto = corpService.findByCorpId(sCorpId);
		/*********** CorpImage 코프 로그인아이디로 리스트뽑아오기 *****************/
		List<CorpImageDto> corpImageList = corpImageService.findAllByCorpId(sCorpId);
		/*******************************************************************/
		model.addAttribute("corp", corpDto);
		
		//업데이트 아직 안했을때 알림주기 위한 세션
		int updateStatus = (Integer)request.getSession().getAttribute("updateStatus");
		model.addAttribute("updateStatus", updateStatus);
		
		
		forwardPath = "dashboard-company-profile";
		return forwardPath;
	}

	@PostMapping("/corp-update-action")
	public String corp_update_action(@ModelAttribute("corp") CorpDto corp, @RequestParam("date") String date,
			HttpServletRequest request)
			throws Exception {
		
		//session 업데이트권한 변경
		request.getSession().setAttribute("updateStatus", 1);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime time = LocalDate.parse(date, formatter).atStartOfDay();
		corp.setCorpEst(time);
		corp.setUpdateStatus(1);
		corp.setPaymentStatus(corpService.findByCorpId((Long)request.getSession().getAttribute("id")).getPaymentStatus());
		corp.setRcCount(corpService.findByCorpId((Long)request.getSession().getAttribute("id")).getRcCount());
		corpService.update(corp.getId(), corp);
		request.setAttribute("corpId", corp.getId());
		return "redirect:corp-detail?corpId="+corp.getId();
		
	}

	@RequestMapping("/dashboard-manage-job")
	public String corp_dashboard_manage_job(@ModelAttribute("message") String message, HttpServletRequest request,
			Model model, @RequestParam(value = "sortType", required = false) String sortType) throws Exception {
		// 회사의 작성 공고 띄우기
		Long sCorpId = (Long) request.getSession().getAttribute("id");
		CorpDto corpDto = corpService.findByCorpId(sCorpId);
		List<RecruitDto> recruitList = recruitService.findAllByCorpId(corpDto.getId());
		model.addAttribute("recruitList", recruitList);

		// 공고 정렬
		// 마감일 내림차순
		try {
			if ("rcDeadlinedesc".equalsIgnoreCase(sortType)) {
				recruitList.sort((o1, o2) -> o2.getRcDeadline().compareTo(o1.getRcDeadline()));
			} else {
				// 마감일 오름차순
				recruitList.sort(Comparator.comparing(RecruitDto::getRcDeadline));
				model.addAttribute("recruitList", recruitList);      
			}
			
		} catch (Exception e) {
		}

		return "dashboard-manage-job";
	}

	// 지원자 관련
	@RequestMapping(value = "/dashboard-applicants", params = "id")
	public String corp_dashboard_applicants(@RequestParam("id") long id, Model model,
			RedirectAttributes redirectAttributes) throws Exception {
		// 지원자 이력서 리스트 불러오기
		try {
			List<AppDto> appList = appService.findAllByRecruitId(id);
			// 리스트 있을 때
			model.addAttribute("appList", appList);
			//appStatus=2인것만 따로 담기(승인)
			List<AppDto> approveList=appList.stream().filter(appDto->appDto.getAppStatus()==2)
													 .collect(Collectors.toList());
			System.out.println("approveList>>>>>>>>>"+approveList);
			model.addAttribute("approveList",approveList);
			//appStatus=3인것만 따로 담기(거절)
			List<AppDto> rejectList=appList.stream().filter(appDto->appDto.getAppStatus()==3)
													.collect(Collectors.toList());
			model.addAttribute("rejectList",rejectList);
			
			
		} catch (Exception e) {
			// 리스트 없을 때
			// redirectAttributes.addFlashAttribute("message", e.getMessage());
			// redirectAttributes.addFlashAttribute("alertType", "danger"); // alert 창 색상을
			// 지정하기 위한 속성
			model.addAttribute("errorMsg", e.getMessage());
			return "redirect:dashboard-manage-job";
		}

		// 이력서의 회원 정보 가져오기
		List<AppDto> userList = appService.findAllByUserId(id);
		System.out.println("이력서 회원 정보 확인>>>>>"+userList);
		model.addAttribute("userList", userList);

		// 해당 공고 디테일 뿌리기
		RecruitDto recruit = recruitService.findRecruit(id);
		model.addAttribute("recruit", recruit);
		
		return "dashboard-applicants";
	}

	// corpName, job 둘 중 하나만 알아도 + 둘다 알때 검색할 수 있는 기능
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchCorps(@RequestParam("corpName") String corpName, 
							  @RequestParam("job") String job,
							  @RequestParam(defaultValue = "0", name = "page") int curPage,
							  @RequestParam(defaultValue = "8") int pageScale,
		                      @RequestParam(defaultValue = "5") int blockScale,
							  Pageable pageable,
	                          Model model, HttpServletRequest request) {
		System.out.println(curPage);
		System.out.println();
		System.out.println();
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
	    try {
	    	//페이징 기능 추가->일단 12개씩 나오게 해놓음
	    	Pageable pageable1 = PageRequest.of(curPage, pageScale, Sort.Direction.ASC, "id");
	    	Page<CorpDto> corpPage = null;
	        // corpName만 알때
	        if (job.isEmpty()) {
	        	corpPage = corpService.searchByCorpName(corpName, pageable1);
	        	
	        	// job만 알 때
	        } else if(corpName.isEmpty()){
	        	corpPage = corpService.searchByjob(job, pageable1);
	        	// 둘 다 알 때
	        } else {
	        	corpPage = corpService.searchCorps(corpName, job, pageable1);
	        }
	        //검색 결과 없을 때
	     
		    //채용중 뿌리기
		    Map<Long, Long>rcCountMap=corpService.getRcCountByCorpIdList(
					corpPage.getContent().stream().map(CorpDto::getId).collect(Collectors.toList()));
			model.addAttribute("rcCountMap", rcCountMap);
			
			//태그리스트 뿌리기
			List<CorpTagDto> corpTagList = corpTagService.selectAll();
			List<TagDto> tagList = tagService.selectAll();
			model.addAttribute("tagList", tagList);
			model.addAttribute("corpTagList", corpTagList);
			
		    
			//페이지블록번호
			int curBlock = (int) Math.ceil((corpPage.getNumber()) / blockScale) + 1;
		    System.out.println("페이지블록번호 :"+curBlock);
		    //페이지 블록의 시작번호
		 	int blockBegin = (curBlock - 1) * blockScale + 1;
		 	//페이지 블록의 끝 번호
		 	int	blockEnd = blockBegin + blockScale - 1;
		 	System.out.println("페이지블록시작번호 :"+blockBegin);
		 	System.out.println("페이지블록  끝번호 :"+blockEnd);
		 	
		 	model.addAttribute("queryString", "search?"+queryString);
		 	model.addAttribute("blockBegin", blockBegin);
		 	model.addAttribute("blockEnd", blockEnd);
		    model.addAttribute("curPage", corpPage.getNumber());
		    model.addAttribute("totalPage", corpPage.getTotalPages());
		    model.addAttribute("prePage", corpPage.previousOrFirstPageable().getPageNumber());
		    model.addAttribute("nextPage", corpPage.nextOrLastPageable().getPageNumber());
			

	        model.addAttribute("corpList", corpPage.getContent());
	    } catch (Exception e) {
	        // 예외 처리
	        e.printStackTrace();
	        model.addAttribute("errorMsg", "검색어를 찾을 수 없습니다!");
	    }
	    return "corp-list";
	}
}