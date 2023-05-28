package com.itwill.ilhajob.user.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.itwill.ilhajob.corp.entity.Recruit;

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
@SequenceGenerator(name = "recruit_scrap_id_SEQ_gen",
				   sequenceName = "recruit_scrap_id_SEQ",
				   allocationSize = 1)
@Table(name = "recruit_scrap")
public class RecruitScrap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recruit_scrap_id_SEQ_gen")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recruit_id")
	@ToString.Exclude
	private Recruit recruit;
}

