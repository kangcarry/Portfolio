package com.itwill.ilhajob.corp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
		manager_email                 		VARCHAR2(40)		 NOT NULL,
		manager_name                  		VARCHAR2(20)		 NULL ,
		manager_position              		VARCHAR2(30)		 NOT NULL,
		manager_phone                 		VARCHAR2(20)		 NOT NULL,
		corp_id                       		VARCHAR2(30)		 NOT NULL
);
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManagerDto {
	private Long id;
	private String managerEmail;
	private String managerName;
	private String managerPosition;
	private String managerPhone;
	@ToString.Exclude
	private CorpDto corp;
	
	
	//Manager 이메일/이름 일치하는지 확인해야함? user password 확인처럼?
}
