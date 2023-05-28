package com.itwill.ilhajob.corp.service;

import java.util.List;

import com.itwill.ilhajob.corp.dto.ManagerDto;

public interface ManagerService {
	//매니저 생성
	ManagerDto create(ManagerDto managerDto) throws Exception;


	/*
	 * 매니저 한명 상세보기
	 */
	ManagerDto findManager(Long id) throws Exception;

	/*
	 * 매니저 수정
	 */
	ManagerDto update(ManagerDto managerDto) throws Exception;

	
	/*
	 * 매니저 삭제
	 */
	void remove(Long id) throws Exception;

	/*
	 * 회사 별 매니저보기
	 */
	List<ManagerDto> findManagerByCorpID(Long corpId);
}
