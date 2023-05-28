package com.itwill.ilhajob.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AwardsDto {
	private Long id;
	private String awardsName;
	private LocalDateTime awardsDate;
	private String awardsContent;
	@ToString.Exclude
	private UserDto user;
}
