package com.itwill.shop.ui.김준;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartService;
import com.itwill.shop.member.Member;
import com.itwill.shop.member.MemberService;
import com.itwill.shop.order.OrderService;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;
import com.itwill.shop.ui.ShopMainFrame;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class ProductDetailPanel extends JPanel {
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
	private MemberService memberService;
	/*
	 * logInMember 객체 선언
	 */
	public Member loginMember;
	/*
	 * Product product
	 */
	Product product;
	
	public JLabel imageLB;
	public JLabel nameContentLB;
	public JLabel priceContentLB;
	public JLabel detailContentLB;
	public JButton cartAddBtn;
	public JButton buyBtn;

	/**
	 * Create the panel.
	 */
	public ProductDetailPanel() throws Exception{
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel nameLB = new JLabel("이름");
		nameLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		nameLB.setBounds(54, 254, 57, 15);
		panel_1.add(nameLB);
		
		JLabel priceLB = new JLabel("가격");
		priceLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		priceLB.setBounds(54, 279, 57, 15);
		panel_1.add(priceLB);
		
		
		JLabel detailLB = new JLabel("설명");
		detailLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		detailLB.setBounds(54, 326, 57, 15);
		panel_1.add(detailLB);
		
		
		cartAddBtn = new JButton("담기");
		
		cartAddBtn.setBounds(38, 491, 97, 23);
		
		panel_1.add(cartAddBtn);
		
		imageLB = new JLabel("");
		imageLB.setIcon(new ImageIcon(ProductDetailPanel.class.getResource("/images/떡복이_큰.jpg")));
		imageLB.setBounds(78, 10, 180, 221);
		panel_1.add(imageLB);
		
		buyBtn = new JButton("구매하기");
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 구매하기 클릭 시 주문생성 패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_ORDER_CREATE, null);
			}	
		});		
		buyBtn.setBounds(192, 491, 97, 23);
		panel_1.add(buyBtn);
		
		detailContentLB = new JLabel("<html>자꾸 생각나는 매콤 달콤함<html>");
		detailContentLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		detailContentLB.setHorizontalTextPosition(SwingConstants.CENTER);
		detailContentLB.setBounds(134, 304, 155, 55);
		panel_1.add(detailContentLB);
		
		priceContentLB = new JLabel("10,000");
		priceContentLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		priceContentLB.setBounds(134, 277, 116, 15);
		panel_1.add(priceContentLB);
		
		nameContentLB = new JLabel("<html>떡볶이<html>");
		nameContentLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		nameContentLB.setBounds(134, 252, 97, 15);
		panel_1.add(nameContentLB);		
		/*
		 * Service 객체 생성
		 */
		orderService = new OrderService();
		productService = new ProductService();
		cartService = new CartService();
		/*
		 * loginMember 객체 생성
		 */
		//loginMember = new Member("sy1",null,null,null,null,null,null);
		//frame.loginMember = frame.memberService.memberDetail(loginMember.getM_id());
		
	}// 생성자 끝

	public void setFrame(ShopMainFrame frame) {
		this.frame = frame;
	}

	/*
	 * 상품 상세보기 메소드
	 */
	public void displayProductDetail(Product product) {
		imageLB.setIcon(new ImageIcon(ProductDetailPanel.class.getResource(product.getP_image())));
		nameContentLB.setText("<html>" + product.getP_name() + "<html>");
		detailContentLB.setText("<html>" + product.getP_desc() + "<html>");
		priceContentLB.setText("<html>" + product.getP_price() + "<html>");
	}
	/*
	 * 클릭 -> 장바구니 담기 메소드
	 */
	public void clickOrder(Product product) {
		try {
			int isAdd = cartService.addCart(new Cart(0, 1, loginMember.getM_id(), 
					new Product(product.getP_no(), product.getP_name(), 
								product.getP_price(), product.getP_image(), 
								product.getP_desc())));
			if (isAdd >= 1) {
				JOptionPane.showMessageDialog(null,"카트에 상품이 담겼습니다.");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
		
	/*
	 	public void clickOrder(Product product) {
		cartAddBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
						try {
							cartService.addCart(new Cart(0, 1, loginMember.getM_id(), 
									new Product(product.getP_no(), product.getP_name(), 
												product.getP_price(), product.getP_image(), 
												product.getP_desc())));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
			});
		cartAddBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	 */
	
	
	
	
	
}	