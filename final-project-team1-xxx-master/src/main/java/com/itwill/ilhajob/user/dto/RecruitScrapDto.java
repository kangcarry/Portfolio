package com.itwill.ilhajob.user.dto;


import com.itwill.ilhajob.corp.dto.RecruitDto;

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
public class RecruitScrapDto {
	private Long id;
	private UserDto user;
	private RecruitDto recruit;
}
