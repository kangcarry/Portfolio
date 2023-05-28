package com.itwill.shop.member;
/* 
VO(Value Object),DTO(Data Transfer Object)
* 이름        널?       유형            
--------- -------- ------------- 
M_ID      NOT NULL VARCHAR2(50)  
M_PASS             VARCHAR2(50)  
M_NAME             VARCHAR2(50)  
M_PHONE            VARCHAR2(100) 
M_BDAY             DATE          
M_EMAIL            VARCHAR2(100) 
M_ADDRESS          VARCHAR2(500) 
*/

public class MemberSQL {
	public static final String MEMBER_INSERT=
			"insert into member(m_id,m_pass,m_name,m_phone,m_bday,m_email,m_address) values(?,?,?,?,?,?,?)";
	public static final String MEMBER_DELETE=
			"delete from member where m_id=?";
	public static final String MEMBER_UPDATE=
			"update member set m_pass=?, m_name=?, m_phone=?, m_bday=?, m_email=?, m_address=? where m_id=?";
	public static final String MEMBER_SELECT_BY_M_ID=
			"select * from member where m_id=?";
	public static final String MEMBER_SELECT_BY_M_ID_COUNT=
			"select count(*) cnt from member where m_id=?";
	public static final String MEMBER_SELECT_BY_M_PASS_COUNT=
			"select count(*) cnt from member where m_pass=?";
	public static final String MEMBER_SELECT_ALL=
			"select * from member";
	
	
	
	
	
	
}


