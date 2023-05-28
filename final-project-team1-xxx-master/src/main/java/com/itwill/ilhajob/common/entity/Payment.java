package com.itwill.ilhajob.common.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(name = "payment_id_SEQ_gen",
				   sequenceName = "payment_id_SEQ",
				   allocationSize = 1)
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "payment_id_SEQ_gen")
	private Long id;
	
	private String paymentMethod;
	
	private int paymentPrice;
	
	private LocalDateTime paymentDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orders_id")
	@ToString.Exclude
	private Orders orders;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corp_id")
	@ToString.Exclude
	private Corp corp;
}
