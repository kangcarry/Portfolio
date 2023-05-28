package com.itwill.ilhajob.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.ilhajob.user.entity.Exp;

@Repository
public interface ExpRepository extends JpaRepository<Exp, Long> {
	List<Exp> findByUserIdOrderByExpEndDateDesc(Long userId);
}
