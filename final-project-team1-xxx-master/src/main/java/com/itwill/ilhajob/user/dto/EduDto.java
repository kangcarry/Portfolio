package com.itwill.ilhajob.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EduDto {
	private Long id;
	private String eduMajor;
	private String eduName;
	private LocalDateTime eduStartDate;
	private LocalDateTime eduEndDate;
	private String eduScore;
	private String eduContent;
	@ToString.Exclude
	private UserDto user;
}
