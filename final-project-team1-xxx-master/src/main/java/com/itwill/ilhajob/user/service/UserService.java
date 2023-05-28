package com.itwill.ilhajob.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.ilhajob.user.dto.MessageDto;
import com.itwill.ilhajob.user.dto.ReviewDto;
import com.itwill.ilhajob.user.dto.UserDto;

public interface UserService {
	
	/*
	 * 회원가입
	 */
	/**************1.반환값사용***********************/
	UserDto create(UserDto userDto) throws Exception;
	/*********************************************/

	/*
	 * 회원로그인
	 * 
	 * 0:아이디존재안함
	 * 1:패쓰워드 불일치
	 * 2:로그인성공
	 */
	UserDto login(String userEmail, String userPassword) throws Exception;
	/*
	 * 회원로그아웃
	 */

	/*
	 * 회원상세보기
	 */
	UserDto findUser(String userEmail) throws Exception;

	/*
	 * 회원수정
	 */
	UserDto update(Long id, UserDto userDto) throws Exception;

	/*
	 * 회원탈퇴
	 */
	void remove(Long id) throws Exception;
	
	/*
	 * 이메일로 회원탈퇴
	 */
	void remove(String userEmail) throws Exception;
	
	/*
	 * 아이디중복체크
	 */
	boolean isDuplicateEmail(String userEmail) throws Exception;
	
	/*
	 * 카카오 아이디 있는지 확인
	 */
	//boolean findKakaoUser(String snsId) throws Exception;
	/*
	 *  회원의 지원리스트
	 */
	//UserDto findAppListById(Long id) throws Exception;
	
	/*
	 * 유저 알림 list 
	 */
	List<MessageDto> findMessageList(Long messageId);
	
	/*
	 * 유저 알림 선택삭제
	 */
	void removeMessageBySeq(Long messageId);
	
	//리뷰 select는 Corp에서 담당
	/*
	 * 리뷰 작성하기
	 */

	ReviewDto insertReview(ReviewDto reviewDto) throws Exception;
	
	/*
	 * 
	 * 리뷰 수정하기
	 */
	ReviewDto updateReview(ReviewDto reviewDto) throws Exception;
	
	

	

}