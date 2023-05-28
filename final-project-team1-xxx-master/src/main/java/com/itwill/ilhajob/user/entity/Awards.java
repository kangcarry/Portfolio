package com.itwill.ilhajob.user.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
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
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "awards_id_SEQ_gen", sequenceName = "awards_id_SEQ", allocationSize = 1)
@Table(name = "awards")
public class Awards {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "awards_id_SEQ_gen")
	private Long id;

	@NonNull
	@Column(nullable = false)
	private String awardsName;

	@NonNull
	@Column(nullable = false)
	private LocalDateTime awardsDate;

	@Column(length = 1000)
	private String awardsContent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
}
