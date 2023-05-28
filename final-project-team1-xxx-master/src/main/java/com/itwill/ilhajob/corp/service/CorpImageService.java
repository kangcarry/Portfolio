package com.itwill.ilhajob.corp.service;

import java.util.List;

import com.itwill.ilhajob.corp.dto.CorpImageDto;
import com.itwill.ilhajob.corp.entity.Corp;

public interface CorpImageService {

	List<CorpImageDto> selectAll();
	
	//기업이 갖고있는 이미지리스트
	List<CorpImageDto> findAllByCorpId(Long id);

	void deleteCorpImageByCorpId(Long corpId);

	void deleteCorpImageById(Long id);

	CorpImageDto selectById(Long id);

	CorpImageDto insertCorpImage(CorpImageDto corpImageDto);





}
