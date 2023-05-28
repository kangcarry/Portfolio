package com.itwill.shop.ui.김세영;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

import com.itwill.shop.member.Member;
import com.itwill.shop.order.Order;
import com.itwill.shop.order.OrderService;
import com.itwill.shop.ui.ShopMainFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class OrderListPanel extends JPanel {
	
	ShopMainFrame frame;
	
	/******************************/
	/*
	 * Service객체 선언
	 */
	OrderService orderService;
	
	/*
	 * loginMember객체 선언
	 */
	private Member loginMember = null;
	/******************************/
	private JPanel orderListPanel;
	private JScrollPane orderListScrollPane;
	private JLabel o_noDisplayLabel_1;
	private JLabel o_descDisplayLabel_1;
	private JLabel o_dateDisplayLabel_1;
	private JLabel o_addressDisplayLabel_1;
	private JLabel o_priceDisplayLabel_1;
	private JLabel o_paymentDisplayLabel_1;

	/**
	 * Create the panel.
	 */
	public OrderListPanel() throws Exception {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		orderListScrollPane = new JScrollPane();
		orderListScrollPane.setBounds(0, 0, 326, 298);
		add(orderListScrollPane);
		
		orderListPanel = new JPanel();
		orderListPanel.setBackground(new Color(255, 255, 255));
		orderListPanel.setPreferredSize(new Dimension(10, 700));
		orderListScrollPane.setViewportView(orderListPanel);
		
		
		
		/**********************주문패널생성**********************/
		
		JPanel orderPanel = new JPanel();
		orderPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		
		orderPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		orderPanel.setPreferredSize(new Dimension(270, 80));
		orderListPanel.add(orderPanel);
		orderPanel.setLayout(null);
		
		o_noDisplayLabel_1 = new JLabel("order.getO_no()");
		o_noDisplayLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		o_noDisplayLabel_1.setBounds(12, 30, 110, 15);
		orderPanel.add(o_noDisplayLabel_1);
		
		o_descDisplayLabel_1 = new JLabel("order.getO_desc()");
		o_descDisplayLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		o_descDisplayLabel_1.setBounds(12, 50, 110, 15);
		orderPanel.add(o_descDisplayLabel_1);
		
		o_dateDisplayLabel_1 = new JLabel("order.getO_date()");
		o_dateDisplayLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		o_dateDisplayLabel_1.setBounds(12, 10, 110, 15);
		orderPanel.add(o_dateDisplayLabel_1);
		
		o_addressDisplayLabel_1 = new JLabel("order.getO_address()");
		o_addressDisplayLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		o_addressDisplayLabel_1.setBounds(148, 10, 110, 15);
		orderPanel.add(o_addressDisplayLabel_1);
		
		o_priceDisplayLabel_1 = new JLabel("order.getO_price()");
		o_priceDisplayLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		o_priceDisplayLabel_1.setBounds(148, 30, 110, 15);
		orderPanel.add(o_priceDisplayLabel_1);
		
		o_paymentDisplayLabel_1 = new JLabel("order.getO_payment()");
		o_paymentDisplayLabel_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		o_paymentDisplayLabel_1.setBounds(148, 50, 110, 15);
		orderPanel.add(o_paymentDisplayLabel_1);
		
		/**********************주문패널종료**********************/
		

		//새로고침 테스트용 버튼
		JButton refreshButton = new JButton("추천 상품 보기");
		refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refreshButton.setHorizontalTextPosition(SwingConstants.CENTER);
		refreshButton.setForeground(Color.WHITE);
		refreshButton.setBackground(new Color(147, 112, 219));
		refreshButton.setFont(new Font("D2Coding", Font.PLAIN, 16));
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					frame.changePanel(ShopMainFrame.PANEL_PRODUCT_RECOMMEND, null);
				}catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		refreshButton.setBounds(30, 318, 247, 28);
		add(refreshButton);
		
		
		/******************************/
		/*
		 * Service객체 생성
		 */
		orderService = new OrderService();
		
		/*
		 * loginMember객체 생성. 취합시 ShopMainFrame의 멤버필드에 위치한 loginMember를 가져와서 사용해야 함.
		 */
		//수정!!! loginMember = new Member("sy0", null, null, null, null, null, null);
		/******************************/
		/*
		 * 메소드 사용
		 */
		//orderList();
		/******************************/
	}//생성자 종료
	
	/******************************/
	/*
	 * 메소드 선언
	 */
	public void orderList() throws Exception {
		List<Order> orderList = orderService.orderList(frame.loginMember.getM_id());
		orderListPanel = new JPanel();
		orderListPanel.setBackground(new Color(255, 255, 255));
		orderListPanel.setPreferredSize(new Dimension(10, 700));
		orderListScrollPane.setViewportView(orderListPanel);
		
		for (Order order : orderList) {
			JPanel orderPanel = new JPanel();
			orderPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					/*
					 * 마우스로 패널 클릭시 실행할 메소드 기술
					 */
					//frame.changePanel(ShopMainFrame.PANEL_ORDER_DETAIL, order);
					try {
						
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});
			
			orderPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			orderPanel.setPreferredSize(new Dimension(270, 80));
			orderPanel.setLayout(null);
			
			JLabel o_noDisplayLabel = new JLabel(""+order.getO_no());
			o_noDisplayLabel.setFont(new Font("D2Coding", Font.PLAIN, 12));
			o_noDisplayLabel.setBounds(12, 30, 110, 15);
			orderPanel.add(o_noDisplayLabel);
			
			JLabel o_descDisplayLabel = new JLabel(order.getO_desc());
			o_descDisplayLabel.setFont(new Font("D2Coding", Font.PLAIN, 12));
			o_descDisplayLabel.setBounds(12, 50, 110, 15);
			orderPanel.add(o_descDisplayLabel);
			
			JLabel o_dateDisplayLabel = new JLabel(""+order.getO_date());
			o_dateDisplayLabel.setFont(new Font("D2Coding", Font.PLAIN, 12));
			o_dateDisplayLabel.setBounds(12, 10, 110, 15);
			orderPanel.add(o_dateDisplayLabel);
			
			JLabel o_addressDisplayLabel = new JLabel(order.getO_address());
			o_addressDisplayLabel.setFont(new Font("D2Coding", Font.PLAIN, 12));
			o_addressDisplayLabel.setBounds(148, 10, 110, 15);
			orderPanel.add(o_addressDisplayLabel);
			
			JLabel o_priceDisplayLabel = new JLabel(""+order.getO_price());
			o_priceDisplayLabel.setFont(new Font("D2Coding", Font.PLAIN, 12));
			o_priceDisplayLabel.setBounds(148, 30, 110, 15);
			orderPanel.add(o_priceDisplayLabel);
			
			JLabel o_paymentDisplayLabel = new JLabel(order.getO_payment());
			o_paymentDisplayLabel.setFont(new Font("D2Coding", Font.PLAIN, 12));
			o_paymentDisplayLabel.setBounds(148, 50, 110, 15);
			orderPanel.add(o_paymentDisplayLabel);

			
			orderListPanel.add(orderPanel);
		}
	}
	
	public void setFrame(ShopMainFrame frame) throws Exception {
		this.frame = frame;
		// 수정!!!! orderList();
	}
}
