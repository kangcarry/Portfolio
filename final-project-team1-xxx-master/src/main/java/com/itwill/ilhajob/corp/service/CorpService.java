package com.itwill.ilhajob.corp.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.user.dto.ReviewDto;


public interface CorpService {

	/*
	 * 기업가입
	 */
	// id, password, name 으로 가입
	CorpDto create(CorpDto corpDto) throws Exception;

	/*
	 * 기업로그인
	 */
	CorpDto login(String corpId,String corpPassword) throws Exception;
	/*
	 * 기업로그아웃
	 */

	/*
	 * 기업 기본정보상세보기
	 */
	CorpDto findByCorpId(Long id) throws Exception;
	CorpDto findByCorpLoginId(String corpLoginId) throws Exception;

	/*
	 * 기업 추가정보등록 및 수정
	 */
	CorpDto update(Long id, CorpDto corpDto) throws Exception;

	/*
	 * 기업탈퇴
	 */
	void remove(String corpLoginId) throws Exception;

	/*
	 * 아이디중복체크
	 */
	boolean isDuplicateId(String corpLoginId) throws Exception;
	
	//회사 이미지리스트와 매니저정보 리스트로 불러오기 (공고등록용)
	public CorpDto findCorpWithimagesAndManagers(String corpLoginId) throws Exception;
	
	//회사 오더와 상품정보 가져오기
	public CorpDto findCorpWithOrdersWithProduct(String corpLoginId) throws Exception;
	
	//회사 공고정보목록 가져오기
	public CorpDto findCorpWithRecruits(String corpLoginId) throws Exception;
	
	//회사 리뷰 목록 가져오기
	List<ReviewDto> findReviewList(Long corpId) throws Exception;
	
	//회사 리스트
	public List<CorpDto> findCorpAll() throws Exception;
	
	//회사 검색
	//List<CorpDto> searchCorpList(String query) throws Exception;
	
	//corpName으로만 검색 기능
	Page<CorpDto> searchByCorpName(String corpName, Pageable pageable) throws Exception;
	
	List<CorpDto> searchByCorpName(String corpName);
	
	//job으로만 검색 기능
	Page<CorpDto> searchByjob(String job, Pageable pageable) throws Exception;
	
	//corpName이랑 job으로 검색 기능
	Page<CorpDto> searchCorps(String corpName, String job, Pageable pageable) throws Exception;
	
	//corpList paging 기능...수정해야할 듯
	Page<CorpDto> getCorpList(int page, int size) throws Exception;
	
	//corpList paging 수정 후
	Page<CorpDto> findAll(Pageable pageable);
	
	//corpId로 rcCount
	public Long getRcCountByCorpId(Long corpId) throws Exception;
	public Map<Long,Long> getRcCountByCorpIdList(List<Long>corpIdList);

}