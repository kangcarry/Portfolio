package com.itwill.ilhajob.corp.repository;

import java.util.List;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itwill.ilhajob.corp.entity.Corp;

@Repository
public interface CorpRepository extends JpaRepository<Corp, Long> {
	
    Optional<Corp> findByCorpLoginId(String corpLoginId);
    boolean existsByCorpLoginId(String corpLoginId);
    
    //corpName으로 검색기능
    Page<Corp> findByCorpNameContaining(String corpName, Pageable pageable);
    
	@Query("SELECT c FROM Corp c WHERE lower(replace(c.corpName, ' ', '')) like lower(concat('%', replace(:keyword, ' ', ''), '%'))")
	List<Corp> findByCorpNameContainingIgnoreCase(@Param("keyword") String corpName);
    
    //job으로만 검색기능
    Page<Corp> findByJobContaining(String job,Pageable pageable);
    
    //corpName이랑 job으로 검색하기
    Page<Corp> findByCorpNameContainingAndJobContaining(String corpName, String job,Pageable pageable);
    
    //paging 기능 구현
    Page<Corp> findAll(Pageable pageable);
    
}
