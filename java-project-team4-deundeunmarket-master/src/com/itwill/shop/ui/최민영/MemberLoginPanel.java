package com.itwill.shop.ui.최민영;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itwill.shop.member.Member;
import com.itwill.shop.member.MemberService;
import com.itwill.shop.ui.ShopMainFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class MemberLoginPanel extends JPanel {
	/*
	 * 프레임 참조
	 */
	ShopMainFrame frame;
	/*********1. MemberService 멤버필드 선언 *****/
	private MemberService memberService;
	
	/*************로그인 한 회원****************/
	private Member loginMember=null;
	
	
	public JTextField loginIdTF;
	public JButton loginBtn;
	public JButton joinBtn;
	public JPasswordField loginPassTF;
	public JPanel memberLoginPanel;


	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public MemberLoginPanel() throws Exception {
		setForeground(Color.WHITE);
		setLayout(null);
		
		memberLoginPanel = new JPanel();
		memberLoginPanel.setBackground(Color.WHITE);
		memberLoginPanel.setBounds(0, 0, 360, 540);
		add(memberLoginPanel);
		memberLoginPanel.setLayout(null);
		
		JLabel idLB = new JLabel("아이디");
		idLB.setFont(new Font("D2Coding", Font.PLAIN, 14));
		idLB.setBounds(58, 121, 68, 15);
		memberLoginPanel.add(idLB);
		
		JLabel passLB = new JLabel("비밀번호");
		passLB.setFont(new Font("D2Coding", Font.PLAIN, 14));
		passLB.setBounds(58, 170, 68, 15);
		memberLoginPanel.add(passLB);
		
		loginIdTF = new JTextField();
		loginIdTF.setText("aaaa");
		loginIdTF.setBounds(138, 117, 137, 21);
		memberLoginPanel.add(loginIdTF);
		loginIdTF.setColumns(10);
		
		joinBtn = new JButton("회원가입");
		joinBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		joinBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		joinBtn.setForeground(new Color(255, 255, 255));
		joinBtn.setFont(new Font("D2Coding", Font.PLAIN, 14));
		joinBtn.setBackground(new Color(147, 112, 219));
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 회원가입 버튼 클릭 시 회원가입 패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_MEMBER_JOIN, null);
			}
		});
		joinBtn.setBounds(50, 245, 109, 33);
		memberLoginPanel.add(joinBtn);
		
		loginBtn = new JButton("로그인");
		loginBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		loginBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setFont(new Font("D2Coding", Font.PLAIN, 14));
		loginBtn.setBackground(new Color(147, 112, 219));
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		loginBtn.setBounds(175, 245, 115, 33);
		memberLoginPanel.add(loginBtn);
		
		loginPassTF = new JPasswordField();
		loginPassTF.setText("aaaa");
		loginPassTF.setBounds(138, 167, 137, 21);
		memberLoginPanel.add(loginPassTF);
		
		
		memberService=new MemberService();
		
		//frame.loginMember = frame.memberService.memberDetail(loginMember.getM_id());
		
	}// 생성자 끝
	
	public void setFrame(ShopMainFrame frame) {
		this.frame = frame;
	}

	/**************로그인 성공 시 호출할 메쏘드***************/

	public void loginProcess(String id) throws Exception{
		
		//1.로그인 성공한 멤버 객체 멤버필드에저장
		frame.loginMember = frame.memberService.memberDetail(id);
		//mainFrame.loginUser = mainFrame.userService.findUser(userId); 
		//2.MemberMainFrame 타이틀 변경
		frame.setTitle(id + " 님 로그인");
		//3. 로그인 탭, 회원가입 탭 불활성화, 회원정보 탭 활성화, 장바구니,주문 활성화
		frame.memberTabbedPane.setEnabledAt(0, false);
		frame.memberTabbedPane.setEnabledAt(1, false);
		frame.memberTabbedPane.setEnabledAt(2, true);
		frame.shopTabbedPane.setEnabledAt(3, true);
		frame.shopTabbedPane.setEnabledAt(4, true);
		
		// 4.상품 전체보기로 화면전환
		frame.changePanel(ShopMainFrame.PANEL_PRODUCT_LIST, null);
		//frame.memberDetailPanel.displayMemberInfo(loginMember);
		frame.memberDetailPanel.displayMemberInfo(frame.loginMember);
		frame.memberDetailPanel.updateFormEnable(false);
	}
	
	public void login() {
		/*********** 회원 로그인 method ************/
		try {
			String id = loginIdTF.getText();
			String pass = new String(loginPassTF.getPassword());
			
			//int result = memberService.logIn(id, pass);
			int result=frame.memberService.logIn(id, pass);
			if(result == 1) {
				//로그인 성공
				//JOptionPane.showMessageDialog(null, "로그인 성공");
				//loginProcess(id); //로그인 멤버로 넣어줌
				loginIdTF.setText(""); 
				loginPassTF.setText("");
				loginProcess(id);
			}else {
				JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 확인하세요");
				loginIdTF.setSelectionStart(0);
				loginIdTF.setSelectionEnd(id.length());
				loginIdTF.requestFocus();
			}
			
		}catch (Exception e1) {
			// TODO: handle exception
		}
	}
	
	
	
	
}
