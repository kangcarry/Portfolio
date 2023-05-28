package com.itwill.ilhajob.common.service;

import java.util.List;

import com.itwill.ilhajob.common.dto.AppDto;

public interface AppService {
	
	void insertApp(AppDto appDto);
	
	AppDto updateApp(long id, int appStatus);
	
	void deleteApp(Long id);
	
	AppDto findById(Long id);
	
	//기업대쉬보드에서 사용
	List<AppDto> findAllByRecruitId(long id) throws Exception;
	//유저대쉬보드에서 사용
	List<AppDto> findAllByUserId(long id);
	
	List<AppDto> findAllByCvId(long id);
}
