package com.itwill.ilhajob.sns.kakao;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.exception.ExistedUserException;
import com.itwill.ilhajob.user.service.UserService;

@Controller
public class KaKaoController {
	@Autowired
	private KaKaoService kakaoService;
	@Autowired
	private UserService userService;
	/*
    kakao_main.html 페이지보여주기
    
	@GetMapping("/kakao_main")
	public String kakao_main() {
		return "kakao_main";
	}
	*/
	/*
    - redirection url로 등록된 요청
    - kakao가 발행한 code를 파라메타로 붙혀서 호출해준다.
    - ajax방식으로는 불가능하다.
     */
	@RequestMapping(value = "/kakao_login_action", method = RequestMethod.GET)
	public String kakao_login_action(@RequestParam(value = "code", required = false) String code,
			HttpServletRequest request,HttpServletResponse response) throws Exception {
		 String access_token=kakaoService.getToken(code);
   		 System.out.println("access_token:"+access_token);
   		 KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(access_token);
   		 String kakaoId = Long.toString(kakaoProfile.getId());
   		 boolean isRegistered = userService.isDuplicateEmail(kakaoProfile.getKakao_account().email);
   		 if(isRegistered) {
   			 HttpSession session=request.getSession();
   			 session.invalidate();
   			 // 위 코드는 session객체에 담긴 정보를 초기화 하는 코드.
   			 session=request.getSession();
   			 
   			 session.setAttribute("sUserId", kakaoProfile.getKakao_account().email);
   			 UserDto findUser = userService.findUser(kakaoProfile.getKakao_account().email);
   			 session.setAttribute("id", findUser.getId());
   			 session.setAttribute("role", "user");
   			 session.setAttribute("paymentStatus", findUser.getPaymentStatus());
   			 request.setAttribute("kakaoProfile", kakaoProfile);
   			 
   			 Cookie authorize_access_token=new Cookie("authorize-access-token", access_token);
   			 response.addCookie(authorize_access_token);
   			 return "redirect:index";
   		 }else {
   		    String email = kakaoProfile.getKakao_account().getEmail();
	        String password = UUID.randomUUID().toString(); // 임시 비밀번호 생성
	        UserDto userDto = new UserDto();
	        userDto.setSnsId(kakaoId);
	        userDto.setUserEmail(email);
	        userDto.setUserPassword(password);
	        userDto.setSnsType("kakao");
	        
	        try {
	            userService.create(userDto);
	            HttpSession session=request.getSession();
	   			 session.invalidate();
	   			 // 위 코드는 session객체에 담긴 정보를 초기화 하는 코드.
	   			 session=request.getSession();
	   		     UserDto findUser = userService.findUser(kakaoProfile.getKakao_account().email);
        		 session.setAttribute("id", findUser.getId());
	   			 session.setAttribute("sUserId", kakaoProfile.getKakao_account().email);
	   			 session.setAttribute("role", "user");
	   			 session.setAttribute("snsType","kakao");
	   			 session.setAttribute("paymentStatus", findUser.getPaymentStatus());
	   			 request.setAttribute("kakaoProfile", kakaoProfile);
	   			 
	   			 Cookie authorize_access_token=new Cookie("authorize-access-token", access_token);
	   			 response.addCookie(authorize_access_token);
	            // 가입 성공시 로그인 처리 후 메인 페이지로 이동
	            // 로그인 처리 방법은 로그인 API를 참고하세요.
	            return "redirect:index";
	        } catch (ExistedUserException e) {
	            // 가입 실패시 에러 페이지로 이동
	            return "error";
	        }
   		 }
		 
	}
	
	
	@ResponseBody
	@GetMapping("/kakao_userinfo_with_token")
	public KakaoProfile getKakaoUserInfoWithToken(String access_token, HttpSession session) throws Exception {
		System.out.println(access_token);
		KakaoProfile kakaoProfile = kakaoService.getKakaoProfile(access_token);
		return kakaoProfile;
	}
}