package com.itwill.ilhajob.user.dto;


import com.itwill.ilhajob.corp.dto.CorpDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CorpBookmarkDto {
	Long id;
	String user_email;
	CorpDto corp;
}
