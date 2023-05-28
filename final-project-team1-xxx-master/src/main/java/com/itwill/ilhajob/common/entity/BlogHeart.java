package com.itwill.ilhajob.common.entity;


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

import com.itwill.ilhajob.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "BlogHeart_id_SEQ_gen",
				   sequenceName = "BlogHeart_id_SEQ",
				   allocationSize = 1)
@Table(name = "blog_heart")
public class BlogHeart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BlogHeart_id_SEQ_gen")
	private Long id;
	
	@Column(columnDefinition = "NUMBER(1) DEFAULT 0")
	private Integer heartState;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	@ToString.Exclude
	private Blog blog;
	
	public BlogHeart(Blog blog, User user) {
		this.blog = blog;
		this.user = user;
		this.heartState = 1;
	}

	
}