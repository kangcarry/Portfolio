package com.itwill.ilhajob.user.service;

import java.util.List;
import java.util.stream.Collectors;

import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.entity.RecruitScrap;

public interface RecruitScrapService {
	public void insertScrap(RecruitScrapDto recruiScrapDto) throws Exception;
	
	public void deleteScrap(long id) throws Exception;
	
	public void deleteScrapByUserIdAndRecruitId(long userId, long recruitId) throws Exception;
	
	public List<RecruitScrapDto> sellectByUserId(long userId);
	
	public RecruitScrapDto sellectById(long id);
	
	public RecruitScrapDto sellectByUserIdAndRecruitId(long userId,long recruitId);
}
