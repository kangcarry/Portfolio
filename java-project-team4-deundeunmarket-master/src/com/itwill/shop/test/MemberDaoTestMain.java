package com.itwill.shop.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.print.SimpleDoc;

import com.itwill.shop.member.Member;
import com.itwill.shop.member.MemberDao;

public class MemberDaoTestMain {

	public static void main(String[] args) throws Exception {
		MemberDao memberDao=new MemberDao();
	
		Member memeber = new Member("myt9", "myt9", "최민영", "010-0000-0000", new SimpleDateFormat("yyyy/MM/dd").parse("1990/04/15"), "cmy@naver.com",
				"서울시 동대문구");
		int rowCount = memberDao.insert(memeber);
		System.out.println("1. insert -->" + rowCount);

		int deleteCount = memberDao.delete("myt10");
		System.out.println("2. delete -->" + deleteCount);

		Member member = new Member("sy2", "pass", "테스트", "phonenumber", new SimpleDateFormat("yyyy/MM/dd").parse("1990/04/15"), "text1", "Text2");
		int updateCount = memberDao.update(member);
		System.out.println("3. update -->" + updateCount);

		System.out.println("4. select -->" + memberDao.findByPrimaryKey("sy2"));

		List<Member> memberList = memberDao.findAll();
		System.out.println("5. selectAll -->" + memberList);

	}

}
