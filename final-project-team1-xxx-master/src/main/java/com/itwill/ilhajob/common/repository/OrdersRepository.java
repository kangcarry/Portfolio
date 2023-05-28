package com.itwill.ilhajob.common.repository;

import com.itwill.ilhajob.common.entity.Orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long>{
	List<Orders> findByUserId(long id);
	List<Orders> findByCorpId(long id);
}
