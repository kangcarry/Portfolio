package com.itwill.ilhajob.corp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "corp_id_SEQ_gen",
				   sequenceName = "corp_id_SEQ",
				   allocationSize = 1)
@Table(name = "corp")
public class Corp {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "corp_id_SEQ_gen")
	private Long id;
	
	@NonNull
	@Column(nullable = false)
	private String corpLoginId;
	
	@NonNull
	@Column(nullable = false)
	private String corpPassword;
	
	private String corpName;
	
	private String corpPhone;
	
	private String corpBusinessNo;
	
	private String corpWebsite;
	
	private LocalDateTime corpEst;
	
	private String corpSize;
	
	private String corpSales;
	
    private String corpStoredFileName;
	
	@Column(length = 1000)
	private String corpComment;
	
	@Column(length = 600)
	private String corpWelfare;
	
	private Integer corpPostCode;

	@Column(length = 600)
	private String corpAddress;

	@Column(length = 600)
	private String corpAddressDetail;
	
	
	private String job;
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 0")
	private Integer paymentStatus;
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 0")
	private Integer updateStatus;
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 2")
	private Integer role;
	
	@Column(columnDefinition = "NUMBER(1) DEFAUlT 0")
	private Integer rcCount;
	
}

