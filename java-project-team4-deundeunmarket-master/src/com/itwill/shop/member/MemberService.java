package com.itwill.shop.member;

import java.util.List;

public class MemberService {
	private MemberDao memberDao;
	public MemberService() throws Exception {
		memberDao=new MemberDao();
	}
/*
 * 회원가입- addMember
 */
	public boolean addMember(Member member) throws Exception {
		boolean isSuccess = false;
		// 1.아이디중복확인
		if (memberDao.findByPrimaryKey(member.getM_id()) == null) {
			memberDao.insert(member);
			isSuccess = true;
		} else {
			isSuccess = false;
		}
		return isSuccess;
	}
/*
 * 회원로그인 - logIn	
 */
	public int logIn(String m_id, String m_pass) throws Exception {
		/*
		 * 0 : 해당하는 아이디 없음 - 로그인 실패 
		 * 1 : 일치하는 아이디 있음 - 로그인 성공
		 */
		int result = -999;
		if (memberDao.countByMemberId(m_id) == 1) {
			Member loginMember = memberDao.findByPrimaryKey(m_id);
			if (loginMember.getM_pass().equals(m_pass)) {
				result = 1; // 일치
			} else {
				result = 0; // 없음
			}
		} else {
			result = 0;
		}
		return result;
	}
/*
 * 회원아이디로 검색 - 아이디 중복 확인용 - isDuplicateId
 */
	public boolean isDuplicateId(String m_id) throws Exception {
		if (memberDao.countByMemberId(m_id) >= 1) {
			return true;
		} else {
			return false;
		}
	}
/*
 * 비밀번호 중복 확인 - 비밀번호 중복 확인용 - isDuplicatePw
 */
	public boolean isDuplicatePw(String m_pw) throws Exception {
		if (memberDao.countByMemberPw(m_pw) >= 1) {
			return true; // 비밀번호 존재 -> 입력한 비밀번호랑 같다는 말(비밀번호확인)
		} else {
			return false; // 비밀번호 미존재 ->입력한 비밀번호 다르다는 말(비밀번호불일치)
		}
	}
/*
 * 회원 상세보기 - memberDetail
 */
	 public Member memberDetail(String m_id) throws Exception{
		 return memberDao.findByPrimaryKey(m_id);
	 }
/*
 * 회원추가 - memberWrite
 */
	public int memberWrite(Member member) throws Exception {
		return memberDao.insert(member);
	}
/*
 * 회원정보 수정 - memberUpdate
 */
	public int memberUpdate(Member member) throws Exception {
		return memberDao.update(member);
	}
/*
 * 회원아이디로 삭제(회원탈퇴) - memberDelete
 */
	public int memberDelete(String m_id) throws Exception {
		return memberDao.delete(m_id);
	}
/*
 * 회원리스트 보기 - memberList(관리자)
 */
	public List<Member> memberList() throws Exception{
		return memberDao.findAll();
	}
	
}
