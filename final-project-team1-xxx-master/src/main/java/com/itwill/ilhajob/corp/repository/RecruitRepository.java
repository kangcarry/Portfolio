package com.itwill.ilhajob.corp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Recruit;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long>{
	//corpId(Long id)로 recruit List 불러오기
	List<Recruit> findByCorpId(Long id);
	
	//corpId(Long id)로 현재활성화중인 공고갯수 불러오기
	Long countByCorpId(Long id);
	
	//paging 기능 구현
	Page<Recruit> findAll(Pageable pageable);
	
	//공고 오름차순, 내림차순
	List<Recruit> findAllByOrderByRcDeadlineAsc();
	List<Recruit> findAllByOrderByRcDeadlineDesc();
	
	Page<Recruit> findByRcTitleContaining(String rcTitle, Pageable pageable);
	
	@Query("SELECT r FROM Recruit r WHERE lower(replace(r.rcTitle, ' ', '')) like lower(concat('%', replace(:keyword, ' ', ''), '%'))")
	List<Recruit> findByRcTitleContainingIgnoreCase(@Param("keyword") String rcTitle);
	
	
}

