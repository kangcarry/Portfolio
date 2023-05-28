package com.itwill.ilhajob.common.entity;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "corp_tag_id_SEQ_gen",
				   sequenceName = "corp_tag_id_SEQ",
				   allocationSize = 1)
@Table(name="corp_tag")
public class CorpTag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "corp_tag_id_SEQ_gen")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id")
	@ToString.Exclude
	private Tag tag;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corp_id")
	@ToString.Exclude
	private Corp corp;
}
