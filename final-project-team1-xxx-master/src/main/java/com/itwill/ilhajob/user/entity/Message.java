package com.itwill.ilhajob.user.entity;

import java.time.LocalDateTime;

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
@SequenceGenerator(name = "message_id_SEQ_gen",
				   sequenceName = "message_id_SEQ",
				   allocationSize = 1)
@Table(name = "message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_id_SEQ_gen")
	private Long id;
	
	private String messageTitle;
	
	@Column(length = 1000)
	private String messageContents;
	
	private LocalDateTime messageDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
}
