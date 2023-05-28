package com.itwill.ilhajob.user.entity;

import javax.persistence.Column;
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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "review_id_SEQ_gen",
				   sequenceName = "review_id_SEQ",
				   allocationSize = 1)
@Table(name = "review")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_SEQ_gen")
	private Long id;
	
	@Column(nullable = false)
	private int reviewGrade;

	@NonNull
	@Column(nullable = false)
	private String reviewTitle;
	
	@NonNull
	@Column(nullable = false,length = 1000)
	private String reviewContent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "corp_id")
	@ToString.Exclude
	private Corp corp;
}

