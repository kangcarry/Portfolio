package com.itwill.ilhajob.corp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.ilhajob.corp.entity.CorpImage;

public interface CorpImageRepository extends JpaRepository<CorpImage, Long> {
	
	List<CorpImage> findByCorpId(long id);

}
