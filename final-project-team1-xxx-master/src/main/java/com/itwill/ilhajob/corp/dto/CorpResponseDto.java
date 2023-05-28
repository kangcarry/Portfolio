package com.itwill.ilhajob.corp.dto;


import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CorpResponseDto {
	private Long id;
	private String corpLoginId;
	private String corpPassword;
	private String corpName;
	private String corpPhone;
	private String corpBusinessNo;
	private String corpWebsite;
	private String corpSize;
	private String corpSales;
	private String corpOriginalFileName;
    private String corpStoredFileName;
	private String corpComment;
	private String corpWelfare;
	private Integer corpPostCode;
	private String corpAddress;
	private String corpAddressDetail;
	private String job;
	private Integer role;
	
	
	
	/*
	 *패쓰워드 일치여부 검사 
	 */
	public boolean isMatchPassword(String corpPassword){
		boolean isMatch=false;
		if(this.corpPassword.equals(corpPassword)){
			isMatch=true;
		}
		return isMatch;
	}



	public CorpResponseDto(Long id, String corpLoginId, String corpPassword) {
		this.id = id;
		this.corpLoginId = corpLoginId;
		this.corpPassword = corpPassword;
	}
}
/*
	이름               널?       유형             
---------------- -------- -------------- 
CORP_ID          NOT NULL VARCHAR2(30)   
CORP_PASSWORD    NOT NULL VARCHAR2(45)   
CORP_NAME        NOT NULL VARCHAR2(50)   
CORP_PHONE                VARCHAR2(20)   
CORP_BUSINESS_NO          VARCHAR2(20)   
CORP_WEBSITE              VARCHAR2(50)   
CORP_EST                  DATE           
CORP_SIZE                 VARCHAR2(20)   
CORP_SALES                VARCHAR2(20)   
CORP_COMMENT              VARCHAR2(1000) 
CORP_WELFARE              VARCHAR2(50)   
CORP_ADDRESS              VARCHAR2(100)  
CORP_STATUS               CHAR(1)        
JOB_ID                    NUMBER(10)     
ROLE_ID                   NUMBER(20)   
*/

	

