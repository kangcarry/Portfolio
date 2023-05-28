package com.itwill.shop.ui.김세영;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.itwill.shop.member.Member;
import com.itwill.shop.order.Order;
import com.itwill.shop.order.OrderItem;
import com.itwill.shop.order.OrderService;
import com.itwill.shop.product.Product;
import com.itwill.shop.ui.ShopMainFrame;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class OrderDetailPanel extends JPanel {
	ShopMainFrame frame;
	
	/******************************/
	/*
	 * Service객체 선언
	 */
	OrderService orderService;
	
	/*
	 * loginMember객체 선언
	 */
	Member loginMember = null;
	/******************************/
	private JPanel orderDetailPanel;
	private JScrollPane orderDetailScrollPane;

	/**
	 * Create the panel.
	 */
	public OrderDetailPanel() throws Exception {
		
		orderService = new OrderService();
		//
		loginMember = new Member("sy0", null, "김세영", null, null, null, null);
		//loginMember = frame.loginMember;
		//
		//Order order = new Order();
		//
		setLayout(null);
		
		orderDetailScrollPane = new JScrollPane();
		orderDetailScrollPane.setBounds(12, 30, 312, 100);
		add(orderDetailScrollPane);
		
		orderDetailPanel = new JPanel();
		orderDetailPanel.setPreferredSize(new Dimension(10, 700));
		orderDetailScrollPane.setViewportView(orderDetailPanel);
		orderDetailPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/**************orderItemList****************/
		/*
		JPanel orderItemPanel = new JPanel();
		orderItemPanel.setPreferredSize(new Dimension(290, 110));
		orderDetailPanel.add(orderItemPanel);
		orderItemPanel.setLayout(null);
		
		JLabel p_imageLabel = new JLabel("");
		p_imageLabel.setIcon(new ImageIcon(OrderDetailPanel_김세영.class.getResource("/images/닭도리탕_작은.jpg")));
		p_imageLabel.setBounds(12, 10, 87, 73);
		orderItemPanel.add(p_imageLabel);
		
		JLabel p_descLabel = new JLabel("p_desc");
		p_descLabel.setVerticalAlignment(SwingConstants.TOP);
		p_descLabel.setBounds(111, 10, 152, 48);
		orderItemPanel.add(p_descLabel);
		
		JLabel p_priceLabel = new JLabel("p_price");
		p_priceLabel.setBounds(111, 68, 74, 15);
		orderItemPanel.add(p_priceLabel);
		
		JLabel oi_qtyLabel = new JLabel("oi_qty");
		oi_qtyLabel.setBounds(187, 68, 63, 15);
		orderItemPanel.add(oi_qtyLabel);
		*/
		/***********************orderDetail*************************/
		/*
		JLabel o_locLabel = new JLabel("받는장소");
		o_locLabel.setBounds(12, 435, 57, 15);
		add(o_locLabel);
		
		JLabel o_locDisplayLabel = new JLabel(order.getO_loc());
		o_locDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_locDisplayLabel.setBounds(95, 435, 224, 15);
		add(o_locDisplayLabel);
		
		JLabel o_addressLabel = new JLabel("주소");
		o_addressLabel.setBounds(12, 410, 57, 15);
		add(o_addressLabel);
		
		JLabel o_addressDisplayLabel = new JLabel(order.getO_address());
		o_addressDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_addressDisplayLabel.setBounds(95, 410, 224, 15);
		add(o_addressDisplayLabel);
		
		JLabel o_nameLabel = new JLabel("받는분");
		o_nameLabel.setBounds(12, 385, 57, 15);
		add(o_nameLabel);
		
		JLabel o_nameDisplayLabel = new JLabel(order.getO_name());
		o_nameDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_nameDisplayLabel.setBounds(95, 385, 224, 15);
		add(o_nameDisplayLabel);
		
		JLabel deliveryInfoLabel = new JLabel("배송정보");
		deliveryInfoLabel.setBounds(12, 320, 57, 15);
		add(deliveryInfoLabel);
		
		JLabel m_nameLabel = new JLabel("보내는분");
		m_nameLabel.setBounds(12, 360, 57, 15);
		add(m_nameLabel);
		
		JLabel m_nameDisplayLabel = new JLabel(loginMember.getM_name());
		m_nameDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		m_nameDisplayLabel.setBounds(95, 360, 224, 15);
		add(m_nameDisplayLabel);
		
		JLabel o_no_2Label = new JLabel("주문번호");
		o_no_2Label.setBounds(12, 255, 57, 15);
		add(o_no_2Label);
		
		JLabel o_noDisplay2Label = new JLabel(""+order.getO_no());
		o_noDisplay2Label.setHorizontalAlignment(SwingConstants.TRAILING);
		o_noDisplay2Label.setBounds(95, 255, 224, 15);
		add(o_noDisplay2Label);
		
		JLabel orderInfoLabel = new JLabel("주문정보");
		orderInfoLabel.setBounds(12, 230, 57, 15);
		add(orderInfoLabel);
		
		JLabel o_paymentLabel = new JLabel("결제방법");
		o_paymentLabel.setBounds(12, 190, 57, 15);
		add(o_paymentLabel);
		
		JLabel o_paymentDisplayLabel = new JLabel(order.getO_payment());
		o_paymentDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_paymentDisplayLabel.setBounds(95, 190, 224, 15);
		add(o_paymentDisplayLabel);
		
		JLabel o_priceLabel = new JLabel("주문금액");
		o_priceLabel.setBounds(12, 165, 57, 15);
		add(o_priceLabel);
		
		JLabel o_priceDisplayLabel = new JLabel(order.getO_price()+" 원");
		o_priceDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_priceDisplayLabel.setBounds(95, 165, 224, 15);
		add(o_priceDisplayLabel);
		
		JLabel paymentInfoLabel = new JLabel("결제정보");
		paymentInfoLabel.setBounds(12, 140, 57, 15);
		add(paymentInfoLabel);
		
		JButton orderCancleButton = new JButton("주문취소");
		orderCancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//1일 지나면 주문취소 불가
					 
					long gapDay = (System.currentTimeMillis() - order.getO_date().getTime())/1000/60/60/24;
					//조건(일수) 변경시 아래의 1을 원하는 일수로 변경
					if(gapDay <= 1) {
						orderService.deleteByOrderNo(order.getO_no());
						JOptionPane.showMessageDialog(null, "주문이 취소되었습니다.");
						// 메인화면으로 화면 전환
					} else {
						JOptionPane.showMessageDialog(null, "주문시각으로부터 "+gapDay+"일 경과하였습니다. (1일 지나면 주문취소 불가)");
					}
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		orderCancleButton.setBounds(65, 480, 97, 23);
		add(orderCancleButton);
		
		JLabel o_noLabel = new JLabel("주문번호");
		o_noLabel.setBounds(12, 10, 57, 15);
		add(o_noLabel);
		
		JLabel o_noDisplayLabel = new JLabel(""+order.getO_no());
		o_noDisplayLabel.setBounds(95, 10, 224, 15);
		add(o_noDisplayLabel);
		
		JLabel o_dateLabel = new JLabel("주문일");
		o_dateLabel.setBounds(12, 280, 57, 15);
		add(o_dateLabel);
		
		JLabel o_dateDisplayLabel = new JLabel(""+order.getO_date());
		o_dateDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_dateDisplayLabel.setBounds(95, 280, 224, 15);
		add(o_dateDisplayLabel);
		
		JButton goBackButton = new JButton("뒤로가기");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					//뒤로가기 - 주문목록으로 화면전환
					 
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		goBackButton.setBounds(198, 480, 97, 23);
		add(goBackButton);
		
		//새로고침기능 테스트용 버튼. 합칠 때 지우자.
		/*
		JButton refreshButton = new JButton("새로고침");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					displayOrderDetail(order);
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		refreshButton.setBounds(117, 262, 97, 23);
		add(refreshButton);
		*/
		
		/******************************/
		/*
		 * Service객체 생성
		 */
		//orderService = new OrderService();
		
		/*
		 * loginMember객체 생성. 취합시 ShopMainFrame의 멤버필드에 위치한 loginMember를 가져와서 사용해야 함.
		 */
		//loginMember = new Member("sy0", null, "김세영", null, null, null, null);
		
		/*
		 * orderService.orderDetail을 사용해서 다 채워진 order객체 생성. 취합시 OrderListPanel에서 o_no를 받아서 사용해야 함.
		 */
		//Order order = orderService.orderDetail(loginMember.getM_id(), 1);//1 --> o_no
		/******************************/
		/*
		 * 메소드 사용
		 */
		
		/******************************/
	}//생성자 종료
	
	/******************************/
	/*
	 * 메소드 선언
	 */
	public void orderItemList(int o_no) throws Exception {
		
		if(loginMember!=null) {
			Order order = orderService.orderDetail(loginMember.getM_id(), o_no);
			orderDetailPanel = new JPanel();
			orderDetailPanel.setPreferredSize(new Dimension(10, 700));
			orderDetailScrollPane.setViewportView(orderDetailPanel);
			orderDetailPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			for (OrderItem orderItem : order.getOrderItemList()) {
				JPanel orderItemPanel = new JPanel();
				orderItemPanel.setPreferredSize(new Dimension(290, 110));
				orderItemPanel.setBounds(10, 10, 275, 95);
				orderItemPanel.setLayout(null);
				
				JLabel p_imageLabel = new JLabel("");
				p_imageLabel.setIcon(new ImageIcon(OrderDetailPanel.class.getResource(orderItem.getProduct().getP_image())));
				p_imageLabel.setBounds(12, 10, 87, 73);
				orderItemPanel.add(p_imageLabel);
				
				JLabel p_descLabel = new JLabel(orderItem.getProduct().getP_desc());
				p_descLabel.setVerticalAlignment(SwingConstants.TOP);
				p_descLabel.setBounds(111, 10, 152, 48);
				orderItemPanel.add(p_descLabel);
				
				JLabel p_priceLabel = new JLabel(orderItem.getProduct().getP_price()+"원");
				p_priceLabel.setBounds(111, 68, 74, 15);
				orderItemPanel.add(p_priceLabel);
				
				JLabel oi_qtyLabel = new JLabel(orderItem.getOi_qty()+" 개");
				oi_qtyLabel.setBounds(187, 68, 63, 15);
				orderItemPanel.add(oi_qtyLabel);
				
				orderDetailPanel.add(orderItemPanel);
			}
		}
	}
	
	public void orderDetail(Order order) {
		
		JLabel o_locLabel = new JLabel("받는장소");
		o_locLabel.setBounds(12, 460, 57, 15);
		add(o_locLabel);
		
		JLabel o_locDisplayLabel = new JLabel(order.getO_loc());
		o_locDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_locDisplayLabel.setBounds(95, 460, 224, 15);
		add(o_locDisplayLabel);
		
		JLabel o_addressLabel = new JLabel("주소");
		o_addressLabel.setBounds(12, 435, 57, 15);
		add(o_addressLabel);
		
		JLabel o_addressDisplayLabel = new JLabel(order.getO_address());
		o_addressDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_addressDisplayLabel.setBounds(95, 435, 224, 15);
		add(o_addressDisplayLabel);
		
		JLabel o_nameLabel = new JLabel("받는분");
		o_nameLabel.setBounds(12, 410, 57, 15);
		add(o_nameLabel);
		
		JLabel o_nameDisplayLabel = new JLabel(order.getO_name());
		o_nameDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_nameDisplayLabel.setBounds(95, 410, 224, 15);
		add(o_nameDisplayLabel);
		
		JLabel deliveryInfoLabel = new JLabel("배송정보");
		deliveryInfoLabel.setBounds(12, 385, 57, 15);
		add(deliveryInfoLabel);
		
		JLabel m_nameLabel = new JLabel("보내는분");
		m_nameLabel.setBounds(12, 345, 57, 15);
		add(m_nameLabel);
		
		JLabel m_nameDisplayLabel = new JLabel(loginMember.getM_name());
		m_nameDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		m_nameDisplayLabel.setBounds(95, 345, 224, 15);
		add(m_nameDisplayLabel);
		
		JLabel o_no_2Label = new JLabel("주문번호");
		o_no_2Label.setBounds(12, 295, 57, 15);
		add(o_no_2Label);
		
		JLabel o_noDisplay2Label = new JLabel(""+order.getO_no());
		o_noDisplay2Label.setHorizontalAlignment(SwingConstants.TRAILING);
		o_noDisplay2Label.setBounds(95, 295, 224, 15);
		add(o_noDisplay2Label);
		
		JLabel orderInfoLabel = new JLabel("주문정보");
		orderInfoLabel.setBounds(12, 270, 57, 15);
		add(orderInfoLabel);
		
		JLabel o_paymentLabel = new JLabel("결제방법");
		o_paymentLabel.setBounds(12, 230, 57, 15);
		add(o_paymentLabel);
		
		JLabel o_paymentDisplayLabel = new JLabel(order.getO_payment());
		o_paymentDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_paymentDisplayLabel.setBounds(95, 230, 224, 15);
		add(o_paymentDisplayLabel);
		
		JLabel o_priceLabel = new JLabel("주문금액");
		o_priceLabel.setBounds(12, 205, 57, 15);
		add(o_priceLabel);
		
		JLabel o_priceDisplayLabel = new JLabel(order.getO_price()+" 원");
		o_priceDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_priceDisplayLabel.setBounds(95, 205, 224, 15);
		add(o_priceDisplayLabel);
		
		JLabel paymentInfoLabel = new JLabel("결제정보");
		paymentInfoLabel.setBounds(12, 180, 57, 15);
		add(paymentInfoLabel);
		
		JButton orderCancleButton = new JButton("주문취소");
		orderCancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//1일 지나면 주문취소 불가
					long gapDay = (System.currentTimeMillis() - order.getO_date().getTime())/1000/60/60/24;
					//조건(일수) 변경시 아래의 1을 원하는 일수로 변경
					if(gapDay <= 1) {
						orderService.deleteByOrderNo(order.getO_no());
						JOptionPane.showMessageDialog(null, "주문이 취소되었습니다.");
						// 메인화면으로 화면 전환
						frame.changePanel(ShopMainFrame.PANEL_PRODUCT_LIST, null);
					} else {
						JOptionPane.showMessageDialog(null, "주문시각으로부터 "+gapDay+"일 경과하였습니다.\n(1일 이상 경과시 주문취소 불가)");
					}
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		orderCancleButton.setBounds(55, 505, 97, 23);
		add(orderCancleButton);
		
		JLabel o_noLabel = new JLabel("주문번호");
		o_noLabel.setBounds(12, 13, 57, 15);
		add(o_noLabel);
		
		JLabel o_noDisplayLabel = new JLabel(""+order.getO_no());
		o_noDisplayLabel.setBounds(95, 13, 224, 15);
		add(o_noDisplayLabel);
		
		JLabel o_dateLabel = new JLabel("주문일");
		o_dateLabel.setBounds(12, 320, 57, 15);
		add(o_dateLabel);
		
		JLabel o_dateDisplayLabel = new JLabel(""+order.getO_date());
		o_dateDisplayLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		o_dateDisplayLabel.setBounds(95, 320, 224, 15);
		add(o_dateDisplayLabel);
		
		JButton goBackButton = new JButton("뒤로가기");
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//뒤로가기 - 주문목록으로 화면전환
					frame.changePanel(ShopMainFrame.PANEL_ORDER_LIST, null);
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		goBackButton.setBounds(183, 505, 97, 23);
		add(goBackButton);
	}
	
	public void displayOrderDetail(Order order) throws Exception {
		/*
		loginMember = frame.loginMember;
		orderDetailPanel.removeAll();
		orderDetailScrollPane = new JScrollPane();
		orderDetailScrollPane.setBounds(12, 38, 312, 132);
		add(orderDetailScrollPane);
		
		orderDetailPanel = new JPanel();
		orderDetailPanel.setPreferredSize(new Dimension(10, 700));
		orderDetailScrollPane.setViewportView(orderDetailPanel);
		orderDetailPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		*/
		
		orderItemList(order.getO_no());
		orderDetail(order);
	}
	
	public void setFrame(ShopMainFrame frame) {
		this.frame = frame;
	}
}
