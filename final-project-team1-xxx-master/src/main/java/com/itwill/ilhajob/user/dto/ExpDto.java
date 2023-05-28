package com.itwill.ilhajob.user.dto;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpDto {
	private Long id;
	private String expPosition;
	private String expCorpName;
	private LocalDateTime expStartDate;
	private LocalDateTime expEndDate;
	private String expContent;
	@ToString.Exclude
	private UserDto user;
}
