package com.itwill.ilhajob.common.entity;

import java.util.List;

import javax.persistence.Column;
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
@SequenceGenerator(name = "tag_id_SEQ_gen",
				   sequenceName = "tag_id_SEQ",
				   allocationSize = 1)
@Table(name = "tag")
public class Tag {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tag_id_SEQ_gen")
	private Long id;
	
	@Column(length = 60)
	private String tagName;
	
	@OneToMany(mappedBy = "tag")
	private List<RecruitTag> recruitTag;
	
	@OneToMany(mappedBy = "tag")
	private List<CorpTag> corpTag;
}
