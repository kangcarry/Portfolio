package com.itwill.shop.ui.김준;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartService;
import com.itwill.shop.member.Member;
import com.itwill.shop.order.OrderService;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;
import com.itwill.shop.ui.ShopMainFrame;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ProductListPanel extends JPanel {
	/*
	 * 프레임 참조
	 */
	ShopMainFrame frame;
	/*****************************************/
	/*
	 * Service 객체 선언
	 */
	ProductService productService;
	CartService cartService;
	/*
	 * logInMember 객체 선언
	 */
	public Member loginMember;
	/*****************************************/
	
	private JLabel ProductListLB;
	private JPanel productListPanel;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */

	public ProductListPanel() throws Exception {
	
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JScrollPane productListScrollPane = new JScrollPane();
		productListScrollPane.setBounds(12, 10, 307, 405);
		add(productListScrollPane);
		
		productListPanel = new JPanel();
		productListPanel.setBackground(new Color(255, 255, 255));
		productListPanel.setPreferredSize(new Dimension(10, 1600));
		FlowLayout fl_productListPanel = (FlowLayout) productListPanel.getLayout();
		fl_productListPanel.setHgap(10);
		fl_productListPanel.setAlignment(FlowLayout.LEFT);
		productListScrollPane.setViewportView(productListPanel);
		/******** 제품 패널 생성***********/
		JPanel productPanel = new JPanel();
		productPanel.setBackground(Color.WHITE);
		productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		productPanel.setPreferredSize(new Dimension(125, 310));
		
		productPanel.setLayout(null);
		
		JLabel productImageLB = new JLabel("");
		productImageLB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 이미지 클릭 시 상품상세패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_PRODUCT_DETAIL, null);
			}
		});
		productImageLB.setVerticalTextPosition(SwingConstants.BOTTOM);
		productImageLB.setPreferredSize(new Dimension(100, 140));
		productImageLB.setIcon(new ImageIcon(ProductListPanel.class.getResource("/images/떡볶이_작은.jpg")));
		productImageLB.setBounds(11, 0, 100, 148);
		productPanel.add(productImageLB);
		
		JLabel productNameLB = new JLabel("<html>[석관동 떡볶이] 오리지널 떡볶이<html>\r\n");
		productNameLB.setHorizontalTextPosition(SwingConstants.CENTER);
		productNameLB.setHorizontalAlignment(SwingConstants.CENTER);
		productNameLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productNameLB.setMinimumSize(new Dimension(24, 15));
		productNameLB.setMaximumSize(new Dimension(24, 15));
		productNameLB.setPreferredSize(new Dimension(24, 15));
		productNameLB.setBounds(0, 154, 125, 41);
		productPanel.add(productNameLB);
		productListPanel.add(productPanel);
		
		JLabel productPriceLB = new JLabel("<html> 가격 : 10,000 </html>");
		productPriceLB.setHorizontalTextPosition(SwingConstants.CENTER);
		productPriceLB.setHorizontalAlignment(SwingConstants.CENTER);
		productPriceLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productPriceLB.setPreferredSize(new Dimension(96, 30));
		productPriceLB.setBounds(5, 200, 115, 22);
		productPanel.add(productPriceLB);
		
		
		JButton cartBtn = new JButton("담기");
		cartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cartBtn.setBackground(new Color(240, 255, 240));
		cartBtn.setFont(new Font("D2Coding", Font.PLAIN, 12));
		cartBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 담기 클릭 시 카트에 추가
				 */
				//frame.productDetailPanel.clickOrder(product);
			}
		});
		cartBtn.setBounds(12, 241, 97, 23);
		productPanel.add(cartBtn);
		
		JButton buyBtn = new JButton("구매하기");
		buyBtn.setBackground(new Color(240, 255, 240));
		buyBtn.setFont(new Font("D2Coding", Font.PLAIN, 12));
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 구매하기 클릭 시 주문생성 패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_ORDER_CREATE, null);
			}
		});
		buyBtn.setBounds(12, 274, 97, 23);
		productPanel.add(buyBtn);
		
		productListPanel.add(productPanel);
		/********************************/	

		//객체 생성
		productService = new ProductService();
		cartService = new CartService();
		
		//loginMember = new Member();
		productList();
	}// 생성자 끝
	
	public void setFrame(ShopMainFrame frame) throws Exception {
		this.frame = frame;
		productList();
	}

	private void productList() throws Exception {
		List<Product> productList = productService.productList();
		productListPanel.removeAll();
		
		for (Product product : productList) {
			
			JPanel productPanel = new JPanel();
			productPanel.setBackground(Color.WHITE);
			productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			productPanel.setPreferredSize(new Dimension(125, 310));
			productPanel.setLayout(null);
			
			JLabel productImageLB = new JLabel("");
			productImageLB.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					/*
					 * 이미지 클릭 시 상품상세패널로 이동
					 */
					frame.changePanel(ShopMainFrame.PANEL_PRODUCT_DETAIL, product);
				}
			});
			
			productImageLB.setVerticalTextPosition(SwingConstants.BOTTOM);
			productImageLB.setPreferredSize(new Dimension(100, 140));
			productImageLB.setIcon(new ImageIcon(ProductListPanel.class.getResource(product.getP_image())));
			productImageLB.setBounds(11, 0, 100, 148);
			productPanel.add(productImageLB);
			
			JLabel productNameLB = new JLabel(product.getP_name());
			productNameLB.setHorizontalTextPosition(SwingConstants.CENTER);
			productNameLB.setHorizontalAlignment(SwingConstants.CENTER);
			productNameLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
			productNameLB.setMinimumSize(new Dimension(24, 15));
			productNameLB.setMaximumSize(new Dimension(24, 15));
			productNameLB.setPreferredSize(new Dimension(24, 15));
			productNameLB.setBounds(0, 154, 125, 41);
			productPanel.add(productNameLB);
			productListPanel.add(productPanel);
			
			JLabel productPriceLB = new JLabel("<html>"+ product.getP_price()+"</html>");
			productPriceLB.setHorizontalTextPosition(SwingConstants.CENTER);
			productPriceLB.setHorizontalAlignment(SwingConstants.CENTER);
			productPriceLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
			productPriceLB.setPreferredSize(new Dimension(96, 30));
			productPriceLB.setBounds(5, 200, 115, 22);
			productPanel.add(productPriceLB);
			
			JButton cartBtn = new JButton("담기");
			cartBtn.setBackground(new Color(240, 255, 240));
			cartBtn.setFont(new Font("D2Coding", Font.PLAIN, 12));
			cartBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					/*
					 * 담기 클릭 시 카트에 추가
					 */
					//frame.productDetailPanel.clickOrder(product); 메소드 사용안됨
					///*
					try {
						int isAdd = cartService.addCart(new Cart(0, 1, frame.loginMember.getM_id(), 
								new Product(product.getP_no(), product.getP_name(), 
											product.getP_price(), product.getP_image(), 
											product.getP_desc())));
						if (isAdd >= 1) {
							JOptionPane.showMessageDialog(null,"카트에 상품이 담겼습니다.");
							//frame.changePanel(ShopMainFrame.PANEL_CART, null);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					//*/
				}
			});
			
			cartBtn.setBounds(12, 241, 97, 23);
			productPanel.add(cartBtn);
			
			JButton buyBtn = new JButton("구매하기");
			buyBtn.setBackground(new Color(240, 255, 240));
			buyBtn.setFont(new Font("D2Coding", Font.PLAIN, 12));
			buyBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					/*
					 * 구매하기 클릭 시 주문생성 패널로 이동
					 */
					frame.changePanel(ShopMainFrame.PANEL_ORDER_CREATE, null);
				}
			});
			buyBtn.setBounds(12, 274, 97, 23);
			productPanel.add(buyBtn);
			
			productListPanel.add(productPanel);
			
		}
		
		
	}
}