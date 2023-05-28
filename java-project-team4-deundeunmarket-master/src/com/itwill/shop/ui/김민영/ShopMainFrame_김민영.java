package com.itwill.shop.ui.김민영;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.itwill.shop.member.MemberService;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Cursor;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ShopMainFrame_김민영 extends JFrame {
	
	/************************************
	 * 1.Service객체선언
	 ************************************/
	MemberService memberService;
	
	
	/**************************************
	 * 2.login User객체선언(선택한Product객체선언)
	 *************************************/
	private JPanel contentPane;
	private JTabbedPane shopTabbedPane;
	private JTabbedPane memberTabbedPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPasswordField passwordField_1;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JPasswordField passwordField_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMainFrame_김민영 frame = new ShopMainFrame_김민영();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShopMainFrame_김민영() throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		
		JPanel globalSouthMenuPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) globalSouthMenuPanel.getLayout();
		flowLayout.setHgap(20);
		globalSouthMenuPanel.setBackground(Color.WHITE);
		contentPane.add(globalSouthMenuPanel, BorderLayout.SOUTH);
		
		JButton globalSearchMenuButton = new JButton("");
		globalSearchMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalSearchMenuButton.setBorder(null);
		globalSearchMenuButton.setOpaque(false);
		globalSearchMenuButton.setBackground(new Color(240, 240, 240));
		
		globalSouthMenuPanel.add(globalSearchMenuButton);
		
		JButton globalHomeMenuButton = new JButton("");
		globalHomeMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalHomeMenuButton.setOpaque(false);
		globalHomeMenuButton.setBorder(null);
		
		globalSouthMenuPanel.add(globalHomeMenuButton);
		
		JButton globalMemberMenuButton = new JButton("");
		globalMemberMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalMemberMenuButton.setOpaque(false);
		globalMemberMenuButton.setBorder(null);
		
		globalSouthMenuPanel.add(globalMemberMenuButton);
		
		shopTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(shopTabbedPane, BorderLayout.CENTER);
		
		
		
		memberTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		shopTabbedPane.addTab("회원", null, memberTabbedPane, null);
		
		JPanel memberLoginPanel_1 = new JPanel();
		memberLoginPanel_1.setLayout(null);
		memberLoginPanel_1.setBackground(new Color(46, 139, 87));
		memberTabbedPane.addTab("로그인", null, memberLoginPanel_1, null);
		
		JLabel 아이디LB = new JLabel("아이디");
		아이디LB.setForeground(Color.WHITE);
		아이디LB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		아이디LB.setBounds(50, 173, 57, 15);
		memberLoginPanel_1.add(아이디LB);
		
		JLabel 패쓰워드LB = new JLabel("패쓰워드");
		패쓰워드LB.setForeground(Color.WHITE);
		패쓰워드LB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		패쓰워드LB.setBounds(50, 216, 57, 15);
		memberLoginPanel_1.add(패쓰워드LB);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(119, 170, 116, 21);
		memberLoginPanel_1.add(textField_12);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(119, 213, 116, 21);
		memberLoginPanel_1.add(passwordField_2);
		
		JButton loginBtn = new JButton("");
		loginBtn.setIcon(new ImageIcon(ShopMainFrame_김민영.class.getResource("/image/login_my.png")));
		loginBtn.setBounds(50, 275, 75, 23);
		memberLoginPanel_1.add(loginBtn);
		
		JButton joinBtn = new JButton("회원가입");
		joinBtn.setFont(new Font("D2Coding", Font.PLAIN, 14));
		joinBtn.setBounds(179, 275, 89, 23);
		memberLoginPanel_1.add(joinBtn);
		
		JLabel lblNewLabel_1 = new JLabel("로그인");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(134, 88, 66, 38);
		memberLoginPanel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ShopMainFrame_김민영.class.getResource("/image/user_my.png")));
		lblNewLabel.setBounds(213, 0, 99, 100);
		memberLoginPanel_1.add(lblNewLabel);
		
		JPanel memberJoinPanel = new JPanel();
		memberJoinPanel.setLayout(null);
		memberJoinPanel.setBackground(new Color(46, 139, 87));
		memberTabbedPane.addTab("회원가입", null, memberJoinPanel, null);
		
		JLabel 아이디_1 = new JLabel("아이디");
		아이디_1.setForeground(Color.WHITE);
		아이디_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		아이디_1.setBounds(43, 44, 57, 15);
		memberJoinPanel.add(아이디_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(115, 44, 156, 21);
		memberJoinPanel.add(textField);
		
		JLabel 비밀번호_1 = new JLabel("비밀번호");
		비밀번호_1.setForeground(Color.WHITE);
		비밀번호_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		비밀번호_1.setBounds(43, 93, 57, 15);
		memberJoinPanel.add(비밀번호_1);
		
		JLabel 이름_1 = new JLabel("이름");
		이름_1.setForeground(Color.WHITE);
		이름_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		이름_1.setBounds(43, 140, 57, 15);
		memberJoinPanel.add(이름_1);
		
		JLabel 전화번호_1 = new JLabel("전화번호");
		전화번호_1.setForeground(Color.WHITE);
		전화번호_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		전화번호_1.setBounds(43, 190, 57, 15);
		memberJoinPanel.add(전화번호_1);
		
		JLabel 생일_1 = new JLabel("생일");
		생일_1.setForeground(Color.WHITE);
		생일_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		생일_1.setBounds(43, 240, 57, 15);
		memberJoinPanel.add(생일_1);
		
		JLabel 이메일_1 = new JLabel("이메일");
		이메일_1.setForeground(Color.WHITE);
		이메일_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		이메일_1.setBounds(43, 290, 57, 15);
		memberJoinPanel.add(이메일_1);
		
		JLabel 배송지_1 = new JLabel("배송지");
		배송지_1.setForeground(Color.WHITE);
		배송지_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		배송지_1.setBounds(43, 340, 57, 15);
		memberJoinPanel.add(배송지_1);
		
		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		passwordField.setBounds(115, 94, 156, 18);
		memberJoinPanel.add(passwordField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(115, 142, 156, 21);
		memberJoinPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(115, 192, 156, 21);
		memberJoinPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(115, 242, 156, 21);
		memberJoinPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(115, 292, 156, 21);
		memberJoinPanel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(115, 342, 156, 21);
		memberJoinPanel.add(textField_5);
		
		JButton memberJoinBtn = new JButton("가입");
		memberJoinBtn.setBounds(61, 391, 97, 23);
		memberJoinPanel.add(memberJoinBtn);
		
		JButton memberCancleBtn = new JButton("취소");
		memberCancleBtn.setBounds(174, 391, 97, 23);
		memberJoinPanel.add(memberCancleBtn);
		
		JLabel idMsgLB = new JLabel("");
		idMsgLB.setForeground(Color.RED);
		idMsgLB.setBounds(131, 82, 116, 15);
		memberJoinPanel.add(idMsgLB);
		
		JPanel memberDetailPanel = new JPanel();
		memberTabbedPane.addTab("회원상세", null, memberDetailPanel, null);
		memberDetailPanel.setLayout(null);
		memberDetailPanel.setBackground(Color.LIGHT_GRAY);
		
		JLabel 아이디 = new JLabel("아이디");
		아이디.setBounds(61, 28, 57, 15);
		memberDetailPanel.add(아이디);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		textField_6.setBounds(139, 25, 116, 21);
		memberDetailPanel.add(textField_6);
		
		JLabel 패쓰워드 = new JLabel("패쓰워드");
		패쓰워드.setBounds(61, 77, 57, 15);
		memberDetailPanel.add(패쓰워드);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setEditable(false);
		passwordField_1.setBounds(139, 75, 116, 18);
		memberDetailPanel.add(passwordField_1);
		
		JLabel 이름 = new JLabel("이름");
		이름.setBounds(61, 122, 57, 15);
		memberDetailPanel.add(이름);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(139, 119, 116, 21);
		memberDetailPanel.add(textField_7);
		
		JLabel 전화번호 = new JLabel("전화번호");
		전화번호.setBounds(61, 173, 57, 15);
		memberDetailPanel.add(전화번호);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);
		textField_8.setBounds(139, 170, 116, 21);
		memberDetailPanel.add(textField_8);
		
		JLabel 생일 = new JLabel("생일");
		생일.setBounds(61, 221, 57, 15);
		memberDetailPanel.add(생일);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setColumns(10);
		textField_9.setBounds(139, 217, 116, 23);
		memberDetailPanel.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setColumns(10);
		textField_10.setBounds(139, 290, 116, 19);
		memberDetailPanel.add(textField_10);
		
		JLabel 주소 = new JLabel("주소");
		주소.setBounds(61, 354, 57, 15);
		memberDetailPanel.add(주소);
		
		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setColumns(10);
		textField_11.setBounds(139, 352, 116, 19);
		memberDetailPanel.add(textField_11);
		
		JButton updateFormBtn = new JButton("수정폼");
		updateFormBtn.setBounds(48, 398, 97, 23);
		memberDetailPanel.add(updateFormBtn);
		
		JButton updateBtn = new JButton("수정");
		updateBtn.setBounds(157, 398, 97, 23);
		memberDetailPanel.add(updateBtn);
		
		JLabel 이메일 = new JLabel("이메일");
		이메일.setBounds(61, 292, 57, 15);
		memberDetailPanel.add(이메일);
		
	
		
	}
}
