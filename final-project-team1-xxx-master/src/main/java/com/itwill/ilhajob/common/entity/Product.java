package com.itwill.ilhajob.common.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "product_id_SEQ_gen",
				   sequenceName = "product_id_SEQ",
				   allocationSize = 1)
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_id_SEQ_gen")
	private Long id;
	
	private String productName;
	
	private int productPrice;
	
	private int productPeriod;
	
	private String productDiv;
	
}
