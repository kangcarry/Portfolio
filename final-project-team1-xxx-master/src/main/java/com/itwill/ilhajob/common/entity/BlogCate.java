package com.itwill.ilhajob.common.entity;


import java.util.ArrayList;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "Blog_cate_id_SEQ_gen",
				   sequenceName = "Blog_cate_id_SEQ",
				   allocationSize = 1)
@Table(name = "blog_cate")
public class BlogCate {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Blog_cate_id_SEQ_gen")
	private Long id;
	
	@Column(nullable = false, length = 1000)
	private String blogCateName;
	
}
