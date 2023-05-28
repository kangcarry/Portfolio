package com.itwill.ilhajob.corp.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitDto {

	private Long id;
	private String rcTitle; 
	private String rcPosition;
	private String rcContent;
	private String rcQualification;
	private Integer rcSalary;
	private LocalDateTime rcDate;
	private LocalDateTime rcDeadline;
	private Integer rcStatus;
	private Integer rcAppCount;
	private Integer rcReadCount;
	//@ToString.Exclude
	private CorpDto corp;
	
}
