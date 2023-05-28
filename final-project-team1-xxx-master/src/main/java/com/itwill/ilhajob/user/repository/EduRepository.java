package com.itwill.ilhajob.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.ilhajob.user.entity.Edu;

@Repository
public interface EduRepository extends JpaRepository<Edu, Long> {
	List<Edu> findByUserIdOrderByEduEndDateDesc(Long userId);
}
