package com.itwill.ilhajob.common.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "recruit_tag")
public class RecruitTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "recruit_tag_id_SEQ_gen")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recruit_id")
	@ToString.Exclude
	private Recruit recruit;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id")
	@ToString.Exclude
	private Tag tag;
}
