package com.itwill.ilhajob.common.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.entity.Recruit;
import com.itwill.ilhajob.user.entity.Cv;
import com.itwill.ilhajob.user.entity.Message;
import com.itwill.ilhajob.user.entity.User;

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
//@EntityListeners(value = { AppEntityListener.class})
@SequenceGenerator(name = "app_id_SEQ_gen",
				   sequenceName = "app_id_SEQ",
				   allocationSize = 1)
@Table(name = "app")
public class App {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "app_id_SEQ_gen")
	private Long id;
	
	@Column(columnDefinition = "NUMBER(1) DEFAUlT 0")
	private Integer appStatus;
	
	private LocalDateTime appCreateDate;
	private String appCvName; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "recruit_id")
	private Recruit recruit;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cv_id")
	@ToString.Exclude
	private Cv cv;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
}