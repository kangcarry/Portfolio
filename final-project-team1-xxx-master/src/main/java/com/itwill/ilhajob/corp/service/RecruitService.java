package com.itwill.ilhajob.corp.service;



import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;

public interface RecruitService {
	
	//공고등록
	RecruitDto create(RecruitDto recruitDto) throws Exception;
	//공고수정
	RecruitDto update(RecruitDto recruitDto) throws Exception;
	//공고삭제
	void remove(long id) throws Exception;
	
	List<RecruitDto> findRecruitAll() throws Exception;
	
	List<RecruitDto> findAllByCorpId(long id) throws Exception;
	
	//공고 상세
	RecruitDto findRecruit(long id) throws Exception;
	
	//corpId(Long id)로 현재활성화중인 공고갯수 불러오기
	Long countByCorpId(Long id) throws Exception;
	
	//마감일 상태변화
	String getStatus(Date rcDeadLine)throws Exception;
	
	//corpId로 recruitList 불러오기
	//List<RecruitDto> recruitList(Long corpId)throws Exception;
	
	//recruitList Paging 기능
	Page<RecruitDto> getRecruitList(int Page, int size) throws Exception;
	Page<RecruitDto> findAll(Pageable pageable)throws Exception;
	
	Page<RecruitDto> searchRcTitle(String rcTitle, int Page, int Size) throws Exception;
	
	List<RecruitDto> searchRcTitle(String rcTitle);
	
	//readCount 증가
	void increaseReadCount(Long id) throws Exception;
	
	//공고 asc, desc
	public List<RecruitDto> getRecruitListOrderByDeadline(String order) throws Exception;
	
	
}