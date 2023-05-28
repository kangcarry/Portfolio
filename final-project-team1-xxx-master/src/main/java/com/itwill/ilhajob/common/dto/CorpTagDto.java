package com.itwill.ilhajob.common.dto;


import com.itwill.ilhajob.corp.dto.CorpDto;

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
public class CorpTagDto {
	private long id;
	private CorpDto corp;
	private TagDto tag;
}
