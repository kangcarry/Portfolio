package com.itwill.ilhajob.common.repository;

import com.itwill.ilhajob.common.entity.CorpTag;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.entity.Corp;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorpTagRepository extends JpaRepository<CorpTag, Long>{

	public List<CorpTag> findByCorpId(Long id);
	
	public List<CorpTag> findByTagId(Long tagId);
	
	Page<CorpTag> findByTagId(long tagId, Pageable pageble);
}
