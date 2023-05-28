package com.itwill.shop.test;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import com.itwill.shop.member.Member;
import com.itwill.shop.member.MemberService;

public class MemberServiceTestMain {

	public static void main(String[] args) throws Exception {
		MemberService memberService = new MemberService();
		
		System.out.println("1. 회원가입");
		boolean addSuccess1 = memberService.addMember(new Member("aaaa", "aaaa", "서테스", "010-1234-5677",
				new SimpleDateFormat("yyyy/MM/dd").parse("1977/04/15"), "s@naver.com", "서울시 송파구"));

		if (addSuccess1) {
			System.out.println("로그인 화면으로 이동");
		} else {
			JOptionPane.showMessageDialog(null, "중복된아이디입니다.");
		}
		
		System.out.println("2. 회원로그인");
		System.out.println(">>"+memberService.logIn("aaaa", "aaaa"));
		
		
		System.out.println("3. 아이디 중복확인용");
		System.out.println(">>"+memberService.isDuplicateId("aaaa"));
		
		
		System.out.println("4. 비밀번호 일치확인용");
		System.out.println(">>"+memberService.isDuplicatePw("aaaa"));
		
		System.out.println("5. 회원 상세보기");
		System.out.println(">>"+memberService.memberDetail("sy0"));
		
		System.out.println("6. 회원추가");
		Member nMember = new Member("sy4", "pass", "업뎃", "xxx-xxx", new SimpleDateFormat("yyyy/MM/dd").parse("1977/04/15"), "up@up", "업뎃");
		memberService.memberWrite(nMember);
		
		System.out.println("7. 회원정보 수정");
		Member upMember = new Member("sy0", "pass", "업뎃", "xxx-xxx", new SimpleDateFormat("yyyy/MM/dd").parse("1977/04/15"), "up@up", "업뎃");
		memberService.memberUpdate(upMember);
		
		System.out.println("8. 회원탈퇴");
		System.out.println(">>"+memberService.memberDelete("aaaa"));
		
		System.out.println("9. 회원리스트보기");
		System.out.println(">> "+memberService.memberList());
		
		
		
		
		
		
		
		
	}

}
