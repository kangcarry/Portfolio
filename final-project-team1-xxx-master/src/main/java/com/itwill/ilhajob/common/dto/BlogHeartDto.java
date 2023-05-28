package com.itwill.ilhajob.common.dto;


import java.time.LocalDateTime;

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
public class BlogHeartDto {


	private long id;
	private int heartState;
	
	private Long userId;
	private Long blogId;


}
