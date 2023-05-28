package com.itwill.ilhajob.user.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.itwill.ilhajob.common.entity.App;
import com.itwill.ilhajob.common.entity.Blog;
import com.itwill.ilhajob.common.entity.BlogComment;
import com.itwill.ilhajob.common.entity.Payment;
import com.itwill.ilhajob.user.entity.Awards;
import com.itwill.ilhajob.user.entity.CorpBookmark;
import com.itwill.ilhajob.user.entity.Cv;
import com.itwill.ilhajob.user.entity.Edu;
import com.itwill.ilhajob.user.entity.Exp;
import com.itwill.ilhajob.user.entity.Message;
import com.itwill.ilhajob.user.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/*
 *  사용자관리를 위하여 필요한 도메인클래스(VO,DTO)
 *  USERINFO 테이블의 각컬럼에해당하는 멤버를 가지고있다
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userPhone;
    //private LocalDateTime userCreateDate;
    private String userSex;
    private Integer userPostCode;
    private String userAddress;
    private String userAddressDetail;
    //private String userCareer;
    private Integer userAge;
    //private String userFinalEducation;
    private String userLanguage;
    private String userSkills;
    private String userImage;
    private String snsType;
    private String snsId;
   // private String job;
    private Integer paymentStatus;
    private Integer writeStatus;
    private int role;
    private int state;
	public UserDto(Long id, String userEmail, String userPassword) {
		this.id = id;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
    
    
}







