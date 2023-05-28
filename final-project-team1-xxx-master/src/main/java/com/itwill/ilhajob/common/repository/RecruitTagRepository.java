package com.itwill.ilhajob.common.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itwill.ilhajob.common.entity.RecruitTag;
import com.itwill.ilhajob.corp.entity.Recruit;

public interface RecruitTagRepository extends JpaRepository<RecruitTag, Long>{
	public List<RecruitTag> findByRecruitId(long recruitId);
	public List<RecruitTag> findByTagId(long tagId);
	
	@Query("SELECT rt.recruit FROM RecruitTag rt WHERE rt.tag.id = :tagId")
	Page<Recruit> findRecruitsByTagId(@Param("tagId") Long tagId, Pageable pageable);
}
