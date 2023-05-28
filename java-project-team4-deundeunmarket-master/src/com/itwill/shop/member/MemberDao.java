package com.itwill.shop.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itwill.shop.member.Member;
import com.itwill.shop.common.DataSource;

public class MemberDao {
	private DataSource dataSource;
	
	public MemberDao() throws Exception {
		dataSource = new DataSource();
	}
	/*
	 * create(insert):member 테이블에 새로운사용자생성
	 */
	public int insert(Member member) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(MemberSQL.MEMBER_INSERT);
			/* 이름        널?       유형            
			--------- -------- ------------- 
			M_ID      NOT NULL VARCHAR2(50)  
			M_PASS             VARCHAR2(50)  
			M_NAME             VARCHAR2(50)  
			M_PHONE            VARCHAR2(100) 
			M_BDAY             DATE          
			M_EMAIL            VARCHAR2(100) 
			M_ADDRESS          VARCHAR2(500) 
			*/
		
		pstmt.setString(1, member.getM_id());
		pstmt.setString(2, member.getM_pass());
		pstmt.setString(3, member.getM_name());
		pstmt.setString(4, member.getM_phone());
		pstmt.setDate(5, new java.sql.Date(member.getM_bday().getTime()));
		pstmt.setString(6, member.getM_email());
		pstmt.setString(7, member.getM_address());
		
		int rowCount=pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}
	/*
	 * update : 기존 사용자 정보의 수정
	 */
	
	public int update(Member member) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_UPDATE);
		pstmt.setString(1, member.getM_pass());
		pstmt.setString(2, member.getM_name());
		pstmt.setString(3, member.getM_phone());
		pstmt.setDate(4, new java.sql.Date(member.getM_bday().getTime()));
		pstmt.setString(5, member.getM_email());
		pstmt.setString(6, member.getM_address());
		pstmt.setString(7, member.getM_id());
		
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}
	
	/*
	 * delete : 해당 아이디에 해당하는 사용자 삭제
	 */
	
	public int delete(String m_id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_DELETE);
		
		pstmt.setString(1, m_id);
		
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}
	
	/*
	 * 사용자아이디에 해당하는 정보를 데이터베이스에서 1개를 찾아 Member 객체로 반환
	 * findByPk
	 */
	
	public Member findByPrimaryKey(String m_id) throws Exception {
		Member member =null;
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_SELECT_BY_M_ID);
		pstmt.setString(1, m_id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			member = new Member(
					rs.getString("m_id"),
					rs.getString("m_pass"),
					rs.getString("m_name"),
					rs.getString("m_phone"),
					rs.getDate("m_bday"),
					rs.getString("m_email"),
					rs.getString("m_address")
					);
		}
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return member;
	}
		/*
		 * 회원전체보기 : memberList
		 */
	
	public List<Member> findAll() throws Exception{
		
		List<Member> memberList = new ArrayList<Member>();
		
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(MemberSQL.MEMBER_SELECT_ALL);
		ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				
					do {
						Member member = new Member(
								rs.getString("m_id"),
								rs.getString("m_pass"),
								rs.getString("m_name"),
								rs.getString("m_phone"),
								rs.getDate("m_bday"),
								rs.getString("m_email"),
								rs.getString("m_address"));
								memberList.add(member);
					} while (rs.next());	
			}
			rs.close();
			pstmt.close();
			dataSource.close(con);
			return memberList;
	}
	/*
	 * 인자로 넘겨받는 아이디를 가진 사용자가 존재하는지 여부를 판별
	 */
			
	public int countByMemberId(String m_id) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_SELECT_BY_M_ID_COUNT);
		pstmt.setString(1, m_id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int memberCount = rs.getInt(1);
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return memberCount;
	}	

	/*
	 * 인자로 넘겨받는 비밀번호를 가진 사용자가 존재하는지 여부를 판별
	 */
	
	public int countByMemberPw(String m_pass) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(MemberSQL.MEMBER_SELECT_BY_M_PASS_COUNT);
		pstmt.setString(1, m_pass);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		int memberCount = rs.getInt(1);
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return memberCount;
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

