package com.itwill.ilhajob.user.service;

import java.util.List;

import com.itwill.ilhajob.user.dto.EduDto;

public interface EduService {
	EduDto createEdu(EduDto eduDto);

	EduDto updateEdu(Long id, EduDto eduDto);

	void deleteEdu(Long id);
	
	EduDto findById(Long id);

	List<EduDto> findEduListByUserId(Long userId);

	List<EduDto> findAll();
}
