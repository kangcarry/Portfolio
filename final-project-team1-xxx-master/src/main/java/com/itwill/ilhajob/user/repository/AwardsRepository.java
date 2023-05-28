package com.itwill.ilhajob.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.ilhajob.user.entity.Awards;

@Repository
public interface AwardsRepository extends JpaRepository<Awards, Long> {
	List<Awards> findByUserIdOrderByAwardsDateDesc(Long userId);
}
