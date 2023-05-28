package com.itwill.ilhajob.user.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.ReverbType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.ilhajob.common.dto.AppDto;
import com.itwill.ilhajob.common.service.AppService;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.user.dto.CvDto;
import com.itwill.ilhajob.user.dto.MessageDto;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.dto.ReviewDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.Review;
import com.itwill.ilhajob.user.exception.ExistedReviewException;
import com.itwill.ilhajob.user.exception.ExistedUserException;
import com.itwill.ilhajob.user.exception.PasswordMismatchException;
import com.itwill.ilhajob.user.exception.UserNotFoundException;
import com.itwill.ilhajob.user.service.CvService;
import com.itwill.ilhajob.user.service.MessageService;
import com.itwill.ilhajob.user.service.RecruitScrapService;
import com.itwill.ilhajob.user.service.ReviewService;
import com.itwill.ilhajob.user.service.UserService;

/*
/user_main 
/user_write_form 
/user_write_action 
/user_login_form
/user_login_action 
/user_logout_action 
/user_view /user_modify_form
/user_modify_action 
/user_remove_action
*/
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppService appService;

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private CorpService corpService;
	
	@Autowired
	private CvService cvService;
	
	@Autowired
	private RecruitScrapService recruitScrapService;
	
	


	/**************Local Exception Handler*************
	@ExceptionHandler(Exception.class)
	public String user_excetpion_handler(Exception e) {
		return "user_error";
	}
	*/
	/*
	 * <<유저 정보>> my profile 유저정보 업데이트 폼
	 * 회원 로그인
	 * 회원 로그아웃
	 * 회원 정보 수정
	 * 회원 탈퇴
	 * 회원 정보 보기
	 * ++ 메시지 알림
	 */
	
//	//메인 페이지
//	@RequestMapping("/index")
//	public String main() {
//		return "index";
//	}
	
	//회원 대시보드 보기
	@LoginCheck
	@RequestMapping("/candidate-dashboard")
	public String dashboard(HttpServletRequest request, Model model) throws Exception {
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		//System.out.println(">>>>>>>>"+sUserId);
		UserDto loginUser = userService.findUser(sUserId);
		request.setAttribute("loginUser", loginUser);
 		
		// app list
		List<AppDto> appList = appService.findAllByUserId(loginUser.getId());
 		model.addAttribute("appList", appList);
 		
 		// cvList
 		List<CvDto> cvList = cvService.findByUserId(loginUser.getId()); 		
 		model.addAttribute("cvList", cvList);
 		
 		// message list
 		List<MessageDto> messageList = userService.findMessageList(loginUser.getId());
		model.addAttribute("messageList", messageList);
		
		//
		List<RecruitScrapDto> recruitScrapList = recruitScrapService.sellectByUserId(loginUser.getId());
		model.addAttribute("recruitScrapList", recruitScrapList);
		
		String forwardPath = "candidate-dashboard";
		return forwardPath;
	}
	
	//회원 정보 보기
	@LoginCheck
	@RequestMapping("/candidate-dashboard-profile")
	public String user_profile(HttpServletRequest request) throws Exception {
		String forwardPath = "";
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
		request.setAttribute("loginUser", loginUser);
		forwardPath = "candidate-dashboard-profile";
		return forwardPath;
	}
	
	// 회원 정보수정 폼 ***템플릿 복사해서 수정 가능한 modify-form 만들어야 함****
	@LoginCheck
	@RequestMapping("/candidate-dashboard-profile-modify-form")
	public String modify_form(HttpServletRequest request) throws Exception {
		String forwardPath = "";
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
		request.setAttribute("loginUser", loginUser);
		forwardPath = "candidate-dashboard-profile-modify-form";
		return forwardPath;
	}
	
	// 회원 정보수정
	@LoginCheck
	@RequestMapping("/modify_action")
	public ResponseEntity<UserDto> modify_action(@RequestBody UserDto userDto, HttpServletRequest request) throws Exception {
		String email = (String)request.getSession().getAttribute("sUserId");
		UserDto findUser = userService.findUser(email);
		if(findUser.getSnsType() != null) {
			userDto.setSnsId(findUser.getSnsId());
			userDto.setSnsType(findUser.getSnsType());
		}
		userDto.setRole(findUser.getRole());
		userDto.setUserPassword(findUser.getUserPassword());
		UserDto updateUser = userService.update(findUser.getId(), userDto);
		
		
		/*
		Long id = (Long)request.getSession().getAttribute("id");
		UserDto findUser = userService.findUser((String)request.getSession().getAttribute("sUserId"));
		userDto.setUserPassword(findUser.getUserPassword());
	    UserDto updateUser = userService.update(id,userDto);
	    */
	    
		return ResponseEntity.ok(updateUser);
	}
	
	
	//회원 로그인 폼
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	//회원 로그인 액션
	@PostMapping("/user_login_action")
	public String user_login_action(@ModelAttribute("fuser")UserDto userDto,Model model,HttpSession session) throws Exception{
		String forwardPath = "";
		try {
			UserDto loginUser = userService.login(userDto.getUserEmail(),userDto.getUserPassword());
			session.setAttribute("id", loginUser.getId());
			session.setAttribute("role", "user");
			session.setAttribute("paymentStatus", loginUser.getPaymentStatus());
			session.setAttribute("profileAvatar", loginUser.getUserImage());
			session.setAttribute("sUserId", loginUser.getUserEmail());
			session.setAttribute("msgList", userService.findMessageList(loginUser.getId()));
			forwardPath = "redirect:index";
		}catch (UserNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("msg1",e.getMessage());
			forwardPath = "login";
		}catch (PasswordMismatchException e) {
			e.printStackTrace();
			model.addAttribute("msg2", e.getMessage());
			forwardPath = "login";
		}
		return forwardPath;
	}
	
	// 회원 로그아웃 액션
	@LoginCheck
	@RequestMapping("/user_logout_action")
	public String user_logout_action(HttpServletRequest request) {
		String forwardPath = "";
		request.getSession().invalidate();
		forwardPath = "redirect:index";
		return forwardPath;
	}
	

	// 회원 가입 폼
	@RequestMapping("/register")
	public String user_join() {
		String forwardPath = "";
		forwardPath = "register";
		return forwardPath;
	}
	
	// 회원 가입 액션
	@RequestMapping("/user_join_action")
	public String user_join_action(@ModelAttribute("fuser")UserDto user,Model model) throws Exception {
		String forwardPath = "";
		try {
			userService.create(user);
			forwardPath = "redirect:login";
		}catch (ExistedUserException e) {
			model.addAttribute("msg",e.getMessage());
			forwardPath = "redirect:register";
		}
		return forwardPath;
	}
	
	// 회원 탈퇴
	@LoginCheck
	@RequestMapping("/delete-action")
	public String user_delete(HttpServletRequest request, HttpSession session) throws Exception {
		String forwardPath="";
		
		String userEmail = (String)request.getSession().getAttribute("sUserId");
		
		userService.remove(userEmail);
		request.getSession().invalidate();
		forwardPath = "redirect:index";
		return forwardPath;
	}
			
	
	// 회원 알림 전체보기
	@LoginCheck
	@RequestMapping("/candidate-dashboard-job-alerts")
	public String user_alerts(HttpServletRequest request,UserDto user,Model model) throws Exception {
		String forwardPath="";
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
		request.setAttribute("loginUser", loginUser);
		List<MessageDto> messageList = userService.findMessageList(loginUser.getId());
		//System.out.println(loginUser.getId());
		//System.out.println(messageList);
		model.addAttribute("messageList",messageList);
		request.getSession().setAttribute("msgList", 0);
		forwardPath = "candidate-dashboard-job-alerts";
		return forwardPath;
	}
	
	// 알림 선택삭제
	@LoginCheck
	@RequestMapping( value = "/alerts-remove", method = RequestMethod.POST)
	public String user_alerts_remove(HttpServletRequest request, @RequestParam(name = "messageId") String id) throws Exception {
		String forwardPath="";
		System.out.println(id);
		Long messageId = Long.parseLong(id);
		//System.out.println(">>>>>>"+messageId);
		userService.removeMessageBySeq(messageId);

		forwardPath="redirect:candidate-dashboard-job-alerts";
		return forwardPath;
	}
	
	// 지원한 목록 보기
	@LoginCheck
	@RequestMapping("/candidate-dashboard-applied-job")
	public String user_applied_job(HttpServletRequest request, Model model) throws Exception{
		String forwardPath="";
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
		request.setAttribute("loginUser", loginUser);
 		List<AppDto> appList = appService.findAllByUserId(loginUser.getId());
 		model.addAttribute("appList",appList);
		forwardPath = "/candidate-dashboard-applied-job";
		return forwardPath;
	}
			
	/*
	// 지원한 목록 보기
	@LoginCheck
	@RequestMapping("/candidate-dashboard-applied-job")
	public String user_applied_job(HttpServletRequest request) throws Exception{
		String forwardPath="";
		//request.getSession().setAttribute("sUserId", "test3@test.com");
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
 		UserDto user = userService.findAppListById(loginUser.getId());
		System.out.println(user);
 		request.setAttribute("loginUser", user);
		forwardPath = "/candidate-dashboard-applied-job";
		return forwardPath;
	}
	*/
	@LoginCheck
	@RequestMapping(value = "/remove-applied-job")                             //appSeq-> appDto의 id로 들어가야함
	public String remove_applied_job(HttpServletRequest request, @RequestParam int appSeq) throws Exception{
		//appService.deleteApp(appSeq);
		return "redirect:candidate-dashboard-applied-job";
	}
	
	
	// my resume 이력서 작성 폼
	
	/*
	 * <<지원>> applied jobs 지원한 공고 목록
	 * 회사에 이력서 지원 하기
	 * 이력서 지원한 회사 목록 보기
	 */
	//회원이 지원한 공고

	
	
	/*
	 * 리뷰(기업에 대한 리뷰) -- > 템플릿 페이지 만들 필요 있음
	 * 리뷰 작성 , 삭제 , --수정 
	 */
	
	//리뷰 작성
		//corpSeq필요 -> delete할떄 appseq처럼 input hidden corpseq필요(redirect용)
	/*	@RequestMapping("/review_write_action")
		public String review_write_action(@ModelAttribute ReviewDto reviewDto, @RequestParam("corpId") String corpId, Model model,HttpServletRequest request) throws Exception{
			request.getSession().setAttribute("sUserId", "test3@test.com");
			String sUserId = (String)request.getSession().getAttribute("sUserId");
			UserDto loginUser = userService.findUser(sUserId);
			CorpDto corpDto = CorpDto.builder().corpLoginId(corpId).build();
			reviewDto.setCorp(corpDto);
			reviewDto.setUser(loginUser);
			System.out.println(reviewDto);
			request.setAttribute("loginUser", loginUser);	
		    userService.insertReview(reviewDto);
			String forwardPath = "redirect:corp-detail?corpId="+corpDto.getCorpLoginId();
			return forwardPath;
			
		} */
	
		// 리뷰 작성
		@LoginCheck
		@RequestMapping("/review_write_action")
		public String review_write_action(@ModelAttribute ReviewDto reviewDto,@ModelAttribute UserDto userDto ,HttpServletRequest request,@RequestParam("corpId") Long corpId,Model model) throws Exception{
			String forwardPath="";
			try {	
			String sUserId = (String)request.getSession().getAttribute("sUserId");
			UserDto loginUser = userService.findUser(sUserId);
			CorpDto corpDto = corpService.findByCorpId(corpId);
		

			reviewDto.setCorp(corpDto);
			reviewDto.setUser(loginUser);
			
			
			
			
			//request.setAttribute("loginUser", loginUser);
			userService.insertReview(reviewDto);
			
			
			forwardPath="redirect:corp-detail?corpId="+corpId;
			return forwardPath;
		}catch (ExistedReviewException e) {
			
			model.addAttribute("msg",e.getMessage());
			forwardPath = "redirect:corp-detail?corpId="+corpId;
			return forwardPath;
		}
			
	}
	
	
	
		
		// 리뷰 삭제
		@LoginCheck
		@RequestMapping("/review_delete")
		public String review_delete(Long id,Long corpId) throws Exception{
			reviewService.remove(id);
			return "redirect:corp-detail?corpId="+corpId;
		}

}
