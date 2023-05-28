package com.itwill.ilhajob.common.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.itwill.ilhajob.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "BlogComment_id_SEQ_gen",
				   sequenceName = "BlogComment_id_SEQ",
				   allocationSize = 1)
@Table(name = "blog_comment")
public class BlogComment extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BlogComment_id_SEQ_gen")
	private Long id;
	
	@Column(nullable = false, length = 1000)
	private String commentContent;
	
	@CreationTimestamp
	private LocalDateTime commentDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	@ToString.Exclude
	private Blog blog;
}
