package com.itwill.shop.ui.최민영;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.shop.member.Member;
import com.itwill.shop.member.MemberService;

import javax.swing.JTabbedPane;

public class ShopMainFrame_최민영 extends JFrame {
	
	/*
	 * Panel상수
	 */
	public static final int  MemberLoginPanel_최민영=1;
	public static final int  MemberJoinPanel_최민영=2;
	public static final int  MemberDetailPanel_최민영=3;
	
	
	
	
	/*********1.MemberService멤버필드선언*****/
	public MemberService memberService;
	/*************로그인한회원****************/
	public Member loginMember=null;
	
	public MemberDetailPanel memberDetailPanel;
	public MemberJoinPanel memberJoinPanel;
	public MemberLoginPanel memberLoginPanel;
	public JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMainFrame_최민영 frame = new ShopMainFrame_최민영();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public ShopMainFrame_최민영() throws Exception {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/***********서비스객체 생성**************/
		memberService = new MemberService();
		
		/*******ShopMainFrame참조를 Panel에 넘겨줌*******
		memberDetailPanel.setFrame(this);
		memberJoinPanel.setFrame(this);
		memberLoginPanel.setFrame(this);
		*/
		
		JTabbedPane memberTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		memberTabbedPane.setBounds(0, 0, 458, 615);
		contentPane.add(memberTabbedPane);
		
		MemberDetailPanel memberDetailPanel_최민영 = new MemberDetailPanel();
		memberTabbedPane.addTab("New tab", null, memberDetailPanel_최민영, null);
		
		MemberJoinPanel memberJoinPanel_최민영 = new MemberJoinPanel();
		memberTabbedPane.addTab("New tab", null, memberJoinPanel_최민영, null);
		
		MemberLoginPanel memberLoginPanel_최민영 = new MemberLoginPanel();
		memberTabbedPane.addTab("New tab", null, memberLoginPanel_최민영, null);
		
	}
}
