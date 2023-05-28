package com.itwill.ilhajob.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.ilhajob.common.dto.LoginRequestDto;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.User;
import com.itwill.ilhajob.user.service.UserService;

@RestController
public class ChangePasswordController {
	private final UserService userService;
	
	private final CorpService corpService;
	
	@Autowired
	public ChangePasswordController(UserService userService, CorpService corpService ) {
		this.userService = userService;
		this.corpService = corpService;
	}
	
	@PostMapping("sessionAjax")
	public ResponseEntity<Object> ajaxModifyPassword(@RequestBody LoginRequestDto loginRequest,HttpSession session ) throws Exception {
		UserDto loginUser = null;
		CorpDto loginCorp = null;
		String password = "";
		String oldPassword = "";
		String newPassword = "";
		String newPasswordConfirm = "";
		
		
		if(session.getAttribute("role").equals("user")) {
			String sUserId = (String)session.getAttribute("sUserId");
			loginUser = userService.findUser(sUserId);
			password = loginUser.getUserPassword();
			oldPassword = loginRequest.getOldPassword();
			newPassword = loginRequest.getNewPassword();
			newPasswordConfirm = loginRequest.getNewPasswordConfirm();
		}else if(session.getAttribute("role").equals("corp")) {
			String sCorpId= (String)session.getAttribute("sCorpId");
			loginCorp = corpService.findByCorpLoginId(sCorpId);
			password = loginCorp.getCorpPassword();
			oldPassword = loginRequest.getOldPassword();
			newPassword = loginRequest.getNewPassword();
			newPasswordConfirm = loginRequest.getNewPasswordConfirm();
		}
		
		if(oldPassword != null && !password.equals(oldPassword)) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("기존 비밀번호가 일치하지 않습니다.");
		}else if(oldPassword.equals(password) && !newPassword.equals(newPasswordConfirm)) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호 확인이 일치하지 않습니다.");
		}else {
			if(loginUser != null) {
				Long id = loginUser.getId();
				String email = loginUser.getUserEmail();
				UserDto user = userService.findUser(email);
				user.setUserPassword(newPassword);
				userService.update(id, user);
				return ResponseEntity.ok().body("{\"success\": true, \"message\": \"비밀번호 변경 완료\"}");
			}else if(loginCorp != null){
				Long id = loginCorp.getId();
				String email = loginCorp.getCorpLoginId();
				CorpDto corp = corpService.findByCorpLoginId(email);
				corp.setCorpPassword(newPassword);
				corpService.update(id, corp);
				return ResponseEntity.ok().body("{\"success\": true, \"message\": \"비밀번호 변경 완료\"}");
			}
		}
		 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"success\": false, \"message\": \"잘못된 형식입니다.\", \"location\": \"/final-project-team1-ilhajob\"}");
	}
}
