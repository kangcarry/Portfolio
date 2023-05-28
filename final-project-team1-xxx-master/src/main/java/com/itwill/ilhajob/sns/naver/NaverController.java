package com.itwill.ilhajob.sns.naver;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.exception.ExistedUserException;
import com.itwill.ilhajob.user.service.UserService;

@Controller
public class NaverController {

    @Autowired
    private NaverService service;
    
    @Autowired
    private UserService userService;
    
    /*
    naver_main.html 페이지보여주기
   
    @GetMapping("/naver_main")
    public String index(){
        return "naver_main";
    }
     */
    @GetMapping(value="/naver_login_action",produces = "application/json;charset=UTF-8")
    public  String naver_login_action(@RequestParam Map<String, String> resValue,HttpServletRequest request,HttpServletResponse response) throws Exception{
        // code 를 받아오면 code 를 사용하여 access_token를 발급받는다.
    	System.out.println(resValue);
        final NaverLoginVo naverLoginVo = service.requestNaverLoginAcceccToken(resValue, "authorization_code");
        // access_token를 사용하여 사용자의 고유 id값을 가져온다.
        final NaverLoginProfile naverLoginProfile = service.requestNaverLoginProfile(naverLoginVo);
        String naverId = naverLoginProfile.getId();
        boolean isRegistered = userService.isDuplicateEmail(naverLoginProfile.getEmail());
        /*
         * 이미가입한사용자라면 로그인진행
         * 미가입사용자라면 회원가입진행
         */
        if(isRegistered) {
        	HttpSession session=request.getSession();
        	session.invalidate();
        	// 위 코드는 session객체에 담긴 정보를 초기화 하는 코드.
        	session=request.getSession();
        	session.setAttribute("sUserId", naverLoginProfile.getEmail());
        	UserDto findUser = userService.findUser(naverLoginProfile.getEmail());
        	session.setAttribute("id", findUser.getId());
        	session.setAttribute("role", "user");
        	session.setAttribute("paymentStatus", findUser.getPaymentStatus());
        	request.setAttribute("naverProfile", naverLoginProfile);
        	
        	return "redirect:index";
        	
        }else {
        	String email = naverLoginProfile.getEmail();
        	String password = UUID.randomUUID().toString();
        	String gender = naverLoginProfile.getGender();
        	UserDto userDto = new UserDto();
        	userDto.setSnsId(naverId);
        	userDto.setSnsType("naver");
        	userDto.setUserEmail(email);
        	userDto.setUserPassword(password);
        	userDto.setUserSex(gender);
        	userDto.setPaymentStatus(0);
        	try {
        		userService.create(userDto);
        		HttpSession session = request.getSession();
        		session.invalidate();
        		session = request.getSession();
        		UserDto findUser = userService.findUser(naverLoginProfile.getEmail());
        		session.setAttribute("id", findUser.getId());
        		session.setAttribute("sUserId", naverLoginProfile.getEmail());
        		session.setAttribute("role", "user");
        		session.setAttribute("paymentStatus", findUser.getPaymentStatus());
        		session.setAttribute("snsType","naver");
        		
        		request.setAttribute("naverProfile", naverLoginProfile);
        		
        		return "redirect:index";
        	}catch(ExistedUserException e) {
        		return "redirect:login";
        	}
        }
        //Cookie authorize_access_token=new Cookie("authorize-access-token", access_token);
        //response.addCookie(authorize_access_token);
    }

}
