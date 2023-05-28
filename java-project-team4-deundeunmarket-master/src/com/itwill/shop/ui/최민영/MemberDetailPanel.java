package com.itwill.shop.ui.최민영;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itwill.shop.member.Member;
import com.itwill.shop.member.MemberService;
//import com.itwill.shop.ui.ProductListPanel;
import com.itwill.shop.ui.ShopMainFrame;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MemberDetailPanel extends JPanel {
	/*
	 * 프레임 참조
	 */
	ShopMainFrame frame;
	/*********1.MemberService 멤버필드 선언*****/
	private MemberService memberService;
	/************* 로그인 한 회원 ****************/
	public Member loginMember = null;
	
	
	public JTextField infoIdTF;
	public JPasswordField infoPassTF;
	public JTextField infoNameTF;
	public JTextField infoPhoneTF;
	public JTextField infoBdayTF;
	public JTextField infoMailTF;
	public JTextField infoAddressTF;
	public JButton updateFormBtn;
	public JButton updateBtn;
	public JLabel infoMsgLB;
	private JLabel memberBdayLB;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public MemberDetailPanel() throws Exception {
		
		setLayout(null);
		
		JPanel memberInfoPanel = new JPanel();
		memberInfoPanel.setBackground(Color.WHITE);
		memberInfoPanel.setBounds(0, 0, 360, 540);
		add(memberInfoPanel);
		memberInfoPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblNewLabel.setBounds(57, 53, 70, 15);
		memberInfoPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(57, 78, 70, 15);
		memberInfoPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("이름");
		lblNewLabel_2.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(57, 104, 70, 15);
		memberInfoPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("전화번호");
		lblNewLabel_3.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(57, 129, 70, 15);
		memberInfoPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("생일");
		lblNewLabel_4.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(57, 165, 70, 15);
		memberInfoPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("이메일");
		lblNewLabel_5.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(57, 190, 70, 15);
		memberInfoPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("주소");
		lblNewLabel_6.setFont(new Font("D2Coding", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(57, 216, 70, 15);
		memberInfoPanel.add(lblNewLabel_6);
		
		infoIdTF = new JTextField();
		infoIdTF.setEnabled(false);
		infoIdTF.setEditable(false);
		infoIdTF.setBounds(132, 47, 138, 21);
		memberInfoPanel.add(infoIdTF);
		infoIdTF.setColumns(10);
		
		infoPassTF = new JPasswordField();
		infoPassTF.setEditable(false);
		infoPassTF.setBounds(132, 72, 138, 21);
		memberInfoPanel.add(infoPassTF);
		
		infoNameTF = new JTextField();
		infoNameTF.setEditable(false);
		infoNameTF.setBounds(132, 97, 138, 21);
		memberInfoPanel.add(infoNameTF);
		infoNameTF.setColumns(10);
		
		infoPhoneTF = new JTextField();
		infoPhoneTF.setEditable(false);
		infoPhoneTF.setBounds(132, 122, 138, 21);
		memberInfoPanel.add(infoPhoneTF);
		infoPhoneTF.setColumns(10);
		
		infoBdayTF = new JTextField();
		infoBdayTF.setEditable(false);
		infoBdayTF.setBounds(132, 160, 138, 21);
		memberInfoPanel.add(infoBdayTF);
		infoBdayTF.setColumns(10);
		
		infoMailTF = new JTextField();
		infoMailTF.setEditable(false);
		infoMailTF.setBounds(132, 184, 138, 21);
		memberInfoPanel.add(infoMailTF);
		infoMailTF.setColumns(10);
		
		infoAddressTF = new JTextField();
		infoAddressTF.setEditable(false);
		infoAddressTF.setBounds(132, 209, 138, 21);
		memberInfoPanel.add(infoAddressTF);
		infoAddressTF.setColumns(10);
		
		updateFormBtn = new JButton("수정하기");
		updateFormBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		updateFormBtn.setFont(new Font("D2Coding", Font.PLAIN, 14));
		updateFormBtn.setBackground(new Color(147, 112, 219));
		updateFormBtn.setForeground(Color.WHITE);
		updateFormBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateForm();
			}
		});
		updateFormBtn.setBounds(66, 276, 90, 23);
		memberInfoPanel.add(updateFormBtn);
		
		updateBtn = new JButton("수정완료");
		updateBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setFont(new Font("D2Coding", Font.PLAIN, 14));
		updateBtn.setBackground(new Color(147, 112, 219));
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		updateBtn.setBounds(171, 275, 90, 24);
		memberInfoPanel.add(updateBtn);
		
		JButton deleteBtn = new JButton("회원탈퇴");
		deleteBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteBtn.setForeground(Color.WHITE);
		deleteBtn.setFont(new Font("D2Coding", Font.PLAIN, 14));
		deleteBtn.setBackground(new Color(147, 112, 219));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				frame.changePanel(ShopMainFrame.PANEL_PRODUCT_LIST, null);
			}
		});
		deleteBtn.setBounds(66, 304, 90, 23);
		memberInfoPanel.add(deleteBtn);
		
		infoMsgLB = new JLabel("");
		infoMsgLB.setHorizontalAlignment(SwingConstants.CENTER);
		infoMsgLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		infoMsgLB.setForeground(Color.RED);
		infoMsgLB.setBounds(28, 21, 273, 22);
		memberInfoPanel.add(infoMsgLB);
		
		memberBdayLB = new JLabel("(예)1999/01/01)");
		memberBdayLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		memberBdayLB.setBounds(153, 145, 117, 15);
		memberInfoPanel.add(memberBdayLB);
		
		JButton logoutBtn = new JButton("로그아웃");
		logoutBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		logoutBtn.setForeground(Color.WHITE);
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logoutProcess();
			}
		});
		logoutBtn.setBackground(new Color(147, 112, 219));
		logoutBtn.setFont(new Font("D2Coding", Font.PLAIN, 14));
		logoutBtn.setBounds(171, 304, 90, 23);
		memberInfoPanel.add(logoutBtn);
		
		memberService = new MemberService();
		
		//frame.loginMember = frame.memberService.memberDetail(loginMember.getM_id());

	}// 생성자 끝
	
	public void setFrame(ShopMainFrame frame) {
		this.frame = frame;
	}
	

	/************* 수정폼(수정하기) method - 회원정보 수정 가능하도록***************/
	public void updateForm() {
		try {
			String btnText = updateFormBtn.getText();
			if (btnText.equals("수정하기")) {
				updateFormEnable(true);
			} else if (btnText.equals("수정취소")) {
				displayMemberInfo(frame.loginMember);
				updateFormEnable(false);
			}
		} catch (Exception e1) {
			// TODO: handle exception
		}
	}
	
	/*************** 수정완료 method - 회원정보 수정 완료되도록***************/
	
	public void update() {
		/**************** 회원수정 ***************/
		try {
			/****** TextField로 부터 데이타 얻기 *****/
			String id = infoIdTF.getText();
			String password = new String(infoPassTF.getPassword());
			String name = infoNameTF.getText();
			String phone = infoPhoneTF.getText();
			String bday = infoBdayTF.getText();
			String email = infoMailTF.getText();
			String address = infoAddressTF.getText();

			if (id.equals("") || password.equals("") || name.equals("") || phone.equals("") || bday.equals("")
					|| email.equals("") || address.equals("")) {
				infoMsgLB.setText("내용을 입력하십시오.");
				infoIdTF.requestFocus();
				return;
			}

			Member member = new Member(id, password, name, phone, new SimpleDateFormat("yyyy/MM/dd").parse(bday), email,
					address);

			memberService.memberUpdate(member);
			loginMember = memberService.memberDetail(id);
			updateFormEnable(false);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}

	/*
	 * 로그아웃시 해야할 일 메소드
	 */
	private void logoutProcess() {
		
		//1. 로그인 성공한 멤버객체를 멤버필드에서 제거
		this.loginMember = null;
		//2. MemberMainFrame 타이틀 변경
		frame.setTitle("든든마켓");
		//3. 로그인 탭, 회원가입 탭 활성화, 회원정보 탭 불활성화, 장바구니,주문 탭 불활성화
		frame.memberTabbedPane.setEnabledAt(0, true);
		frame.memberTabbedPane.setEnabledAt(1, true);
		frame.memberTabbedPane.setEnabledAt(2, false);
		frame.shopTabbedPane.setEnabledAt(3, false);
		frame.shopTabbedPane.setEnabledAt(4, false);
		// 4. 상품 전체보기로 화면전환
		frame.changePanel(ShopMainFrame.PANEL_MEMBER_LOGIN, null);
		// 5. 회원 정보 빈칸 처리
		infoIdTF.setText("");
		infoPassTF.setText("");
		infoNameTF.setText("");
		infoPhoneTF.setText("");
		infoBdayTF.setText("");
		infoMailTF.setText("");
		infoAddressTF.setText("");	
		
	
		
	}
	
	/************** 회원탈퇴 method ***************************/
	public void delete() {
		try {		
			String selectedId = infoIdTF.getText();
			String pw1 = loginMember.getM_pass();
			String pw2 = new String(infoPassTF.getPassword()); 
			if (pw1.equals(pw2)) {
				memberService.memberDelete(selectedId);
				JOptionPane.showMessageDialog(null,"탈퇴가 완료되었습니다.");
			}else {
				JOptionPane.showMessageDialog(null, "확인바랍니다.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/************* 회원 정보 수정폼 활성화 불활성화 method****************/
	public void updateFormEnable(boolean b) {
		if(b) {
			//활성화
			//infoIdTF.setEnabled(true);
			infoPassTF.setEditable(true);
			infoNameTF.setEditable(true);
			infoPhoneTF.setEditable(true);
			infoBdayTF.setEditable(true);
			infoMailTF.setEditable(true);
			infoAddressTF.setEditable(true);
			
			updateFormBtn.setText("수정취소");
			updateBtn.setEnabled(true);
		}else {
			//불활성화
			infoIdTF.setEnabled(false);
			infoPassTF.setEnabled(false);
			infoNameTF.setEditable(false);
			infoPhoneTF.setEditable(false);
			infoBdayTF.setEditable(false);
			infoMailTF.setEditable(false);
			infoAddressTF.setEditable(false);
			
			updateFormBtn.setText("수정하기");
			updateBtn.setEnabled(false);
		}
		
	}
	public void displayMemberInfo(Member member) throws Exception {
		/**** 회원 상세 데이타 보여주기 method*****/
		infoIdTF.setText(member.getM_id());
		infoPassTF.setText(member.getM_pass());
		infoNameTF.setText(member.getM_name());
		infoPhoneTF.setText(member.getM_phone());
		infoBdayTF.setText(new SimpleDateFormat("yyyy/MM/dd").format(member.getM_bday()));
		infoMailTF.setText(member.getM_email());
		infoAddressTF.setText(member.getM_address());	
	}
}
