package com.itwill.ilhajob.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itwill.ilhajob.common.controller.ResponseStatusCode;
import com.itwill.ilhajob.common.dto.LoginRequestDto;
import com.itwill.ilhajob.common.service.AppService;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.exception.CorpNotFoundException;
import com.itwill.ilhajob.corp.exception.ExistedCorpException;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.user.dto.ReviewDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.exception.ExistedUserException;
import com.itwill.ilhajob.user.exception.PasswordMismatchException;
import com.itwill.ilhajob.user.exception.UserNotFoundException;
import com.itwill.ilhajob.user.service.ReviewService;
import com.itwill.ilhajob.user.service.UserService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
public class UserRestController {
	
	private final UserService userService;
	
	private final AppService appService;

	private final ReviewService reviewService;
	
	private final CorpService corpService;
	
	@Autowired
	public UserRestController(UserService userService, AppService appService, ReviewService reviewService, CorpService corpService) {
		this.userService = userService;
		this.appService = appService;
		this.reviewService = reviewService;
		this.corpService = corpService;
	}
	
	@PostMapping("ajaxRegister")
	public ResponseEntity<Object> ajaxModfiy(@RequestBody LoginRequestDto loginRequest,@ApiIgnore HttpSession session) throws Exception {
	   if(loginRequest.getSeparate().equals("user")) {
		   String id = loginRequest.getEmail();
		   String password = loginRequest.getPassword();
		   UserDto user = new UserDto(0L,id,password);
		   user.setRole(1);
		   try {
			   userService.create(user);
			   return ResponseEntity.ok().body("{\"success\": true, \"message\": \"가입 성공\"}");
		   }catch (ExistedUserException e) {
			 return ResponseEntity.status(ResponseStatusCode.EXISTED_USER).body(e.getMessage());
		}
	   }else if(loginRequest.getSeparate().equals("corp")) {
		   String id = loginRequest.getId();
		   String password = loginRequest.getPassword();
		   CorpDto corp = new CorpDto(0L,id,password);
		 try {
			   corpService.create(corp);
			   return ResponseEntity.ok().body("{\"success\": true, \"message\": \"가입 성공\"}");
		   }catch (ExistedCorpException e) {
			   return ResponseEntity.status(ResponseStatusCode.NOT_FOUND_CORP).body(e.getMessage());
		   }
	   }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"success\": false, \"message\": \"잘못된 형식입니다.\", \"location\": \"/final-project-team1-ilhajob\"}");
	}
	
	
	/*
	 * 리뷰 작성중 ajax 방식
	 */
	
	//test중 -> ResponseStatusCode.written_fail_review 에러(6001 직접만듦)으로 반환됨. -> console.log

	@PostMapping("/createReviewAjax")
	public ResponseEntity<String> createReview(HttpSession session, HttpServletRequest request, @RequestBody String requestData) throws Exception {
		//넘어온 string을 json형식으로 변환
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(requestData, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		long corpId = jsonObject.get("corpId").getAsLong();
		JsonObject reviewObject = jsonObject.getAsJsonObject("reviewData");
		ReviewDto reviewDto = gson.fromJson(reviewObject, ReviewDto.class);
		
	    String sUserId = (String) session.getAttribute("sUserId");
	    if(sUserId == null) {
	        return ResponseEntity.status(ResponseStatusCode.NOT_FOUND_USER).body("{\"success\": false, \"message\": \"로그인이 필요합니다.\", \"location\": \"/final-project-team1-ilhajob/login\"}");
	    }
	    try {
	    	UserDto loginUser = userService.findUser(sUserId);
	    	CorpDto corpDto = corpService.findByCorpId(corpId);
	    	reviewDto.setCorp(corpDto);
	    	reviewDto.setUser(loginUser);
	        userService.insertReview(reviewDto);
	        System.out.println("!!!!!!!!!!!!!!!!!!"+userService.insertReview(reviewDto));
	        return ResponseEntity.ok().body("{\"success\": true, \"message\": \"리뷰가 성공적으로 작성되었습니다.\"}");
	    } catch (Exception e) {
	        return ResponseEntity.status(ResponseStatusCode.WRITTEN_FAIL_REVIEW).body("{\"success\": false, \"message\": \"리뷰 작성에 실패했습니다. 잠시 후 다시 시도해주세요.\"}");
	    }
	}
	
//	@PostMapping(value = "createReviewAjax", produces = "application/json;charset=UTF-8")
//	public Map<String, Object> review_write_Action(@RequestBody ReviewDto reviewDto, HttpSession session, @RequestParam("corpId")String corpId){
//		String sUserId = (String) session.getAttribute("sUserId");
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		int code=1;
//		String msg="성공";
//		List<ReviewDto> data = new ArrayList<ReviewDto>();
//		try {
//			UserDto loginUser = userService.findUser(sUserId);
//			System.out.println("!!!!!!!!!"+loginUser);
//			CorpDto corpDto = corpService.findByCorpId(Long.parseLong(corpId));
//			System.out.println("!!!!!!!!!"+corpDto);
//			reviewDto.setUser(loginUser);
//			reviewDto.setCorp(corpDto);
//			userService.insertReview(reviewDto);
//			code = 1;
//			msg = "성공";
//			data.add(reviewDto);
//		} catch (Exception e) {
//			code = 2;
//			msg = "리뷰작성실패";
//			e.printStackTrace();
//		}
//		resultMap.put("code", code);
//		resultMap.put("msg", msg);
//		resultMap.put("data", data);
//		
//		
//		return resultMap;
//	}
	
	
	
	
	
}