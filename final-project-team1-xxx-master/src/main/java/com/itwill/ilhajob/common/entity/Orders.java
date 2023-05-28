package com.itwill.ilhajob.common.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "orders_id_SEQ_gen",
				   sequenceName = "orders_id_SEQ",
				   allocationSize = 1)
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "orders_id_SEQ_gen")
	private Long id;
		
	private LocalDateTime orderStartDate;
	
	private LocalDateTime orderEndDate;
	//상품구매 목록 확인시 order_valid로 만료 표시
	
	private String orderValid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	@ToString.Exclude
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corp_id")
	@ToString.Exclude
	private Corp corp;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
}
