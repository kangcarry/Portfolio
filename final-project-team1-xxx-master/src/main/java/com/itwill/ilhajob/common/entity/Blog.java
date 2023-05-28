package com.itwill.ilhajob.common.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.itwill.ilhajob.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "blog_id_SEQ_gen",
				   sequenceName = "blog_id_SEQ",
				   allocationSize = 1)
@Table(name = "blog")
public class Blog extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_id_SEQ_gen")
	private Long id;
	
	@NonNull
	@Column(nullable = false, length = 500)
	private String blogTitle;
	
	@NonNull
	@Column(nullable = false, length = 1000)
	private String blogContent;
	
	private String blogImage;
	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDateTime blogDate;
	
	private int blogReadCount;
	
	private int blogLike;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
	@OneToMany(mappedBy = "blog",
			   cascade = CascadeType.ALL)
	private List<BlogComment> blogCommentList;
	
//	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
//	private Set<BlogHeart> blogHeart = new HashSet<>();
//	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_cate_id")
	@ToString.Exclude
	private BlogCate blogCate;
}


