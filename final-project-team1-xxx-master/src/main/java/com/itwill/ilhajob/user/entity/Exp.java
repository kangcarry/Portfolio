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
@SequenceGenerator(name = "exp_id_SEQ_gen", sequenceName = "exp_id_SEQ", allocationSize = 1)
@Table(name = "exp")
public class Exp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exp_id_SEQ_gen")
	private Long id;

	@NonNull
	@Column(nullable = false)
	private String expPosition;

	@NonNull
	@Column(nullable = false)
	private String expCorpName;

	@NonNull
	@Column(nullable = false)
	private LocalDateTime expStartDate;

	@NonNull
	@Column(nullable = false)
	private LocalDateTime expEndDate;

	@Column(length = 1000)
	private String expContent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
}
