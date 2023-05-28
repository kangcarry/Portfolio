package com.itwill.ilhajob.corp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.ilhajob.corp.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
	List<Manager> findByCorpId(Long corpId);
	
}
