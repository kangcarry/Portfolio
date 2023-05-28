package com.itwill.ilhajob.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.ilhajob.user.entity.CorpBookmark;
import com.itwill.ilhajob.user.entity.RecruitScrap;

public interface RecruitScrapRepository extends JpaRepository<RecruitScrap, Long> {
	public List<RecruitScrap> findByUserId(Long userId);
	
}
