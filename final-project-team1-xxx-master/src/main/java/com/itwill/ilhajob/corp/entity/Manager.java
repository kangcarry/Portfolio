package com.itwill.ilhajob.corp.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "manager_id_SEQ_gen",
				   sequenceName = "manager_id_SEQ",
				   allocationSize = 1)
@Table(name = "manager")
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "manager_id_SEQ_gen")
	private Long id;
	
	private String managerEmail;
	
	private String managerName;
	
	private String managerPosition;
	
	private String managerPhone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corp_id")
	@ToString.Exclude
	private Corp corp;
}
