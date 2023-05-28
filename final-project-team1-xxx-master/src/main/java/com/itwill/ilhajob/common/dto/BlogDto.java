package com.itwill.ilhajob.common.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.itwill.ilhajob.common.entity.BlogCate;
import com.itwill.ilhajob.user.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * 
이름              널?       유형            
--------------- -------- ------------- 
BLOG_SEQ        NOT NULL NUMBER(20)    
BLOG_TITLE               VARCHAR2(30)  
BLOG_CONTENT             VARCHAR2(200) 
BLOG_IMAGE               VARCHAR2(45)  
BLOG_DATE                DATE          
BLOG_READ_COUNT          NUMBER(10)    
BLOG_LIKE                NUMBER(10)    
BLOG_CATE_SEQ   NOT NULL NUMBER(20)    
USER_SEQ                 NUMBER(20)    

 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BlogDto {
	private Long id;
	private String blogTitle;
	private String blogContent;
	private String blogImage;
	private LocalDateTime blogDate;
	private int blogReadCount;
	private int blogLike;
	private UserDto user;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private List<BlogCommentDto> blogCommentList;
	@ToString.Exclude
	private BlogCate blogCate;
	
}
