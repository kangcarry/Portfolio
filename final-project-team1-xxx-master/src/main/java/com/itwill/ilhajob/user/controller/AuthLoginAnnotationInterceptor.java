package com.itwill.ilhajob.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/*
①HandlerInterceptor 인터페이스
②HandlerInterceptorAdapter 추상클래스

	- public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)
     	Controller 요청 전 실행
	
	- public boolean postHandle(HttpServletRequest request, HttpServletResponse response,Object handler, ModelAndVeiw modelAndVeiw)
     	Controller 처리가 끝나고 화면에 띄어주는 처리 직전에 수행
	
	- afterCompletion() : 모든 처리가 끝난 후 호출
 */

public class AuthLoginAnnotationInterceptor implements HandlerInterceptor {
	public AuthLoginAnnotationInterceptor() {
	}

	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod == false) {
			
			return true;
		}
		
	
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		
		LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);
		
		if (loginCheck == null) {
			return true;
		}
		HttpSession session = request.getSession();
		String sUserId = (String) session.getAttribute("sUserId");
		if (sUserId == null) {
			response.sendRedirect("login");
			return false; 
		}
	
		return true;
	}

	
	

	
}