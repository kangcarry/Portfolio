package com.itwill.ilhajob.common.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.itwill.ilhajob.common.entity.Blog;
import com.itwill.ilhajob.user.dto.UserDto;

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
public class BlogCateDto {
	
	private Long id;
	private String blogCateName;

}