package com.itwill.shop.ui.김민선;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartService;
import com.itwill.shop.member.Member;
import com.itwill.shop.order.Order;
import com.itwill.shop.order.OrderItem;
import com.itwill.shop.order.OrderService;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;
import com.itwill.shop.ui.ShopMainFrame;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

public class OrderCreatePanel extends JPanel {
	/*
	 * 프레임 참조
	 */
	ShopMainFrame frame;
	/*
	 * Service 객체 선언
	 */
	private OrderService orderService;
	private ProductService productService;
	private CartService cartService;
	/*
	 * logInMember 객체 선언
	 */
	private Member loginMember = null;


	
	private JTextField orderNameTF;
	private JTextField orderAddressTF;
	private JComboBox paymentCB;
	private JComboBox orderLocCB;
	public JLabel orderTotPriceLB;
	private JButton orderCreateBtn;

	public OrderCreatePanel() throws Exception{
		
		
		setLayout(null);
		
		JPanel orderCreatePanel = new JPanel();
		orderCreatePanel.setBackground(Color.WHITE);
		orderCreatePanel.setBounds(0, 0, 360, 540);
		add(orderCreatePanel);
		orderCreatePanel.setLayout(null);
		
		JLabel OrderNameLB = new JLabel("받으실 분");
		OrderNameLB.setFont(new Font("D2Coding", Font.PLAIN, 13));
		OrderNameLB.setBounds(39, 43, 87, 15);
		orderCreatePanel.add(OrderNameLB);
		
		orderNameTF = new JTextField();
		orderNameTF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		orderNameTF.setColumns(10);
		orderNameTF.setBounds(160, 40, 136, 21);
		orderCreatePanel.add(orderNameTF);
		
		JLabel OrderAddressLB = new JLabel("배송지");
		OrderAddressLB.setFont(new Font("D2Coding", Font.PLAIN, 13));
		OrderAddressLB.setBounds(39, 70, 87, 15);
		orderCreatePanel.add(OrderAddressLB);
		
		orderAddressTF = new JTextField();
		orderAddressTF.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		orderAddressTF.setColumns(10);
		orderAddressTF.setBounds(39, 88, 257, 21);
		orderCreatePanel.add(orderAddressTF);
		
		JLabel OrderLocLB = new JLabel("수령장소");
		OrderLocLB.setFont(new Font("D2Coding", Font.PLAIN, 13));
		OrderLocLB.setBounds(39, 128, 87, 15);
		orderCreatePanel.add(OrderLocLB);
		
		JLabel OrderPaymentLB = new JLabel("결제수단");
		OrderPaymentLB.setFont(new Font("D2Coding", Font.PLAIN, 13));
		OrderPaymentLB.setBounds(39, 160, 71, 15);
		orderCreatePanel.add(OrderPaymentLB);
		
		paymentCB = new JComboBox();
		paymentCB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		paymentCB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		paymentCB.setModel(new DefaultComboBoxModel(new String[] {"카드", "계좌이체"}));
		paymentCB.setEditable(true);
		paymentCB.setBounds(160, 156, 136, 23);
		orderCreatePanel.add(paymentCB);
		
		orderLocCB = new JComboBox();
		orderLocCB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		orderLocCB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		orderLocCB.setModel(new DefaultComboBoxModel(new String[] {"문 앞", "경비실", "택배함"}));
		orderLocCB.setEditable(true);
		orderLocCB.setBounds(160, 124, 136, 23);
		orderCreatePanel.add(orderLocCB);
		
		JLabel OrderPriceLB = new JLabel("총 결제금액");
		OrderPriceLB.setFont(new Font("D2Coding", Font.BOLD, 16));
		OrderPriceLB.setBounds(39, 212, 136, 28);
		orderCreatePanel.add(OrderPriceLB);
		
		orderCreateBtn = new JButton("주문하기");
		orderCreateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		orderCreateBtn.setForeground(new Color(255, 255, 255));
		orderCreateBtn.setBackground(new Color(147, 112, 219));
		orderCreateBtn.setFont(new Font("D2Coding", Font.PLAIN, 18));
		orderCreateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 주문 생성 메소드 사용
				 */
				orderCreate();
				// 주문생성 시 적었던 이름, 주소 빈칸 만들기
				orderNameTF.setText("");
				orderAddressTF.setText("");
				/*
				 * 총 주문 금액 0으로 초기화
				 */
				orderTotPriceLB.setText(""+0);
			}
		});
		orderCreateBtn.setBounds(77, 279, 176, 40);
		orderCreatePanel.add(orderCreateBtn);
		
		orderTotPriceLB = new JLabel("");
		orderTotPriceLB.setFont(new Font("D2Coding", Font.BOLD, 16));
		orderTotPriceLB.setHorizontalAlignment(SwingConstants.TRAILING);
		orderTotPriceLB.setBounds(145, 212, 151, 28);
		orderCreatePanel.add(orderTotPriceLB);
		/*****************************************/
		/*
		 * Service 객체 생성
		 */
		orderService = new OrderService();
		productService = new ProductService();
		cartService = new CartService();

		/*****************************************/
		
		/*
		 * 주문 총 금액 메소드 사용
		 */
		//orderTotPriceLB.setText(""+orderTotPrice(loginMember.getM_id()));
		
		
	}// 생성자 끝
	
	public void setFrame(ShopMainFrame frame) {
		this.frame = frame;
	}

	/*
	 * 주문 총 금액 메소드
	 */
	public int orderTotPrice(String m_id) throws Exception{
		List<Cart> cartList = cartService.getCartItemByMemberId(frame.loginMember.getM_id()); // m_id
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		int o_tot_price = 0;
		if (cartList.size() >= 1) {
			for (Cart cart : cartList) {
				OrderItem orderItem = new OrderItem(0, cart.getCart_qty(), cart.getProduct(), 0);
				orderItemList.add(orderItem);
				o_tot_price += orderItem.getOi_qty() * orderItem.getProduct().getP_price();
			}
			return o_tot_price + 3000;
		} else {
			return o_tot_price + 3000;
		}
	}
	
	/*
	 * 주문하기 메소드
	 */
	
	public void orderCreate() {
		try {
			String o_name = orderNameTF.getText();
			String o_address = orderAddressTF.getText();
			String o_payment = (String) paymentCB.getSelectedItem();
			String o_loc = (String) orderLocCB.getSelectedItem();
           
			Order newOrder = new Order(0, o_name, null, null, orderTotPrice(frame.loginMember.getM_id()), o_address, o_loc, o_payment, frame.loginMember.getM_id());
			//Order newOrder = new Order(0, "김민선", null, null, 0, "서울시 강남구", "조심히 놔주세요", "현금", "sy0");
			//List<Cart> findCart = cartService.getCartItemByMemberId(frame.loginMember.getM_id());
			int isCreated=-9999;
			isCreated = orderService.orderCreate(newOrder);
			if(isCreated==1){
			JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.");
				// 주문 성공 시 주문 목록 화면으로 전환
				frame.changePanel(ShopMainFrame.PANEL_ORDER_LIST, null);
				// 주문 목록 탭 활성화
				frame.orderTabbedPane.setEnabledAt(1, true);
			}
		} catch (Exception e1) {
			
			e1.printStackTrace();
			
		}
	}
	
	
	
	
	
}
