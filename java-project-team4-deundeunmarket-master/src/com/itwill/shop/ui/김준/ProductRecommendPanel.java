package com.itwill.shop.ui.김준;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;
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

public class ProductRecommendPanel extends JPanel {
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
	Product product;
	/*****************************************/

	private JLabel ProductListLB;
	private JPanel productListPanel;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */
	
	public ProductRecommendPanel() throws Exception {
		setBackground(new Color(255, 255, 255));
setLayout(null);
		
		JScrollPane productListScrollPane = new JScrollPane();
		productListScrollPane.setBounds(12, 10, 307, 677);
		add(productListScrollPane);
		
		productListPanel = new JPanel();
		productListPanel.setBackground(new Color(255, 255, 255));
		productListPanel.setPreferredSize(new Dimension(10, 1000));
		FlowLayout fl_productListPanel = (FlowLayout) productListPanel.getLayout();
		fl_productListPanel.setHgap(10);
		fl_productListPanel.setAlignment(FlowLayout.LEFT);
		productListScrollPane.setViewportView(productListPanel);
		/********************제품 패널 생성*******************************/
		JPanel tteokbokkiPanel = new JPanel();
		tteokbokkiPanel.setBackground(Color.WHITE);
		tteokbokkiPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tteokbokkiPanel.setPreferredSize(new Dimension(125, 310));
		
		tteokbokkiPanel.setLayout(null);
		
		JLabel productImageLB = new JLabel("");
		productImageLB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 이미지 클릭 시 상품 상세패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_PRODUCT_DETAIL, new Product(2, "떡볶이", 10000, "/images/떡볶이_작은.jpg", "자꾸 생각나는 매콤 달콤함"));
			}
		});
		productImageLB.setVerticalTextPosition(SwingConstants.BOTTOM);
		productImageLB.setPreferredSize(new Dimension(100, 140));
		productImageLB.setIcon(new ImageIcon(ProductListPanel.class.getResource("/images/떡볶이_작은.jpg")));
		productImageLB.setBounds(13, 0, 100, 148);
		tteokbokkiPanel.add(productImageLB);
		
		JLabel productNameLB = new JLabel("<html>떡볶이<html>\r\n");
		productNameLB.setHorizontalTextPosition(SwingConstants.CENTER);
		productNameLB.setHorizontalAlignment(SwingConstants.CENTER);
		productNameLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productNameLB.setMinimumSize(new Dimension(24, 15));
		productNameLB.setMaximumSize(new Dimension(24, 15));
		productNameLB.setPreferredSize(new Dimension(24, 15));
		productNameLB.setBounds(0, 158, 125, 41);
		tteokbokkiPanel.add(productNameLB);
		productListPanel.add(tteokbokkiPanel);
		
		JLabel productPriceLB = new JLabel("<html> 가격 : 10,000 </html>");
		productPriceLB.setHorizontalTextPosition(SwingConstants.CENTER);
		productPriceLB.setHorizontalAlignment(SwingConstants.CENTER);
		productPriceLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productPriceLB.setPreferredSize(new Dimension(96, 30));
		productPriceLB.setBounds(5, 209, 115, 22);
		tteokbokkiPanel.add(productPriceLB);
		
		productListPanel.add(tteokbokkiPanel);
		
		JButton cartBtn1 = new JButton("담기");
		cartBtn1.setBackground(new Color(240, 255, 240));
		cartBtn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 담기 클릭 시 카트에 추가
				 */
				//frame.productDetailPanel.clickOrder(product);
				try {
					cartService.addCart(new Cart(0, 1, frame.loginMember.getM_id(), 
							new Product(2, "떡볶이", 10000, "/images/떡볶이_작은.jpg", "자꾸 생각나는 매콤 달콤함")));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cartBtn1.setBounds(14, 241, 97, 23);
		tteokbokkiPanel.add(cartBtn1);
		
		JButton buyBtn1 = new JButton("구매하기");
		buyBtn1.setBackground(new Color(240, 255, 240));
		buyBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 구매하기 클릭 시 주문생성 패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_ORDER_CREATE, null);
			}
		});
		buyBtn1.setBounds(14, 274, 97, 23);
		tteokbokkiPanel.add(buyBtn1);
		/***************************************************/
		JPanel creampastaPanel_2 = new JPanel();
		creampastaPanel_2.setBackground(Color.WHITE);
		creampastaPanel_2.setLayout(null);
		creampastaPanel_2.setPreferredSize(new Dimension(125, 310));
		productListPanel.add(creampastaPanel_2);
		
		JLabel productImageLB_2 = new JLabel("");
		productImageLB_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		productImageLB_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 이미지 클릭 시 상품 상세패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_PRODUCT_DETAIL, new Product(10, "크림파스타", 10000, "/images/크림파스타_작은.jpg", "매콤한 매력의 크림 파스타"));
			}
		});
		productImageLB_2.setIcon(new ImageIcon(ProductRecommendPanel.class.getResource("/images/크림파스타_작은.jpg")));
		productImageLB_2.setVerticalTextPosition(SwingConstants.BOTTOM);
		productImageLB_2.setPreferredSize(new Dimension(100, 140));
		productImageLB_2.setBounds(12, 0, 100, 148);
		creampastaPanel_2.add(productImageLB_2);
		
		JLabel productNameLB_2 = new JLabel("<html>크림파스타<html>\r\n");
		productNameLB_2.setHorizontalTextPosition(SwingConstants.CENTER);
		productNameLB_2.setHorizontalAlignment(SwingConstants.CENTER);
		productNameLB_2.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productNameLB_2.setPreferredSize(new Dimension(24, 15));
		productNameLB_2.setMinimumSize(new Dimension(24, 15));
		productNameLB_2.setMaximumSize(new Dimension(24, 15));
		productNameLB_2.setBounds(0, 158, 125, 41);
		creampastaPanel_2.add(productNameLB_2);
		
		JLabel productPriceLB_2 = new JLabel("<html> 가격 : 10,000 </html>");
		productPriceLB_2.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productPriceLB_2.setHorizontalTextPosition(SwingConstants.CENTER);
		productPriceLB_2.setHorizontalAlignment(SwingConstants.CENTER);
		productPriceLB_2.setPreferredSize(new Dimension(96, 30));
		productPriceLB_2.setBounds(5, 209, 115, 22);
		creampastaPanel_2.add(productPriceLB_2);
		
		JButton cartBtn2 = new JButton("담기");
		cartBtn2.setBackground(new Color(240, 255, 240));
		cartBtn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 담기 클릭 시 카트에 추가
				 */
				//frame.productDetailPanel.clickOrder(product);
				try {
					cartService.addCart(new Cart(0, 1, frame.loginMember.getM_id(), 
							new Product(10, "크림파스타", 10000, "/images/크림파스타_작은.jpg", "매콤한 매력의 크림 파스타")));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cartBtn2.setBounds(15, 241, 97, 23);
		creampastaPanel_2.add(cartBtn2);
		
		JButton buyBtn2 = new JButton("구매하기");
		buyBtn2.setBackground(new Color(240, 255, 240));
		buyBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 구매하기 클릭 시 주문생성 패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_ORDER_CREATE, null);
			}
		});
		buyBtn2.setBounds(15, 274, 97, 23);
		creampastaPanel_2.add(buyBtn2);
		/***************************************************/
		JPanel steakPanel_3 = new JPanel();
		steakPanel_3.setBackground(Color.WHITE);
		steakPanel_3.setLayout(null);
		steakPanel_3.setPreferredSize(new Dimension(125, 310));
		productListPanel.add(steakPanel_3);
		
		JLabel productImageLB_3 = new JLabel("");
		productImageLB_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		productImageLB_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 이미지 클릭 시 상품 상세패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_PRODUCT_DETAIL, new Product(6, "스테이크", 20000, "/images/스테이크_작은.jpg", "쫄깃한 식감, 풍부한 육즙"));
			}
		});
		productImageLB_3.setIcon(new ImageIcon(ProductRecommendPanel.class.getResource("/images/스테이크_작은.jpg")));
		productImageLB_3.setVerticalTextPosition(SwingConstants.BOTTOM);
		productImageLB_3.setPreferredSize(new Dimension(100, 140));
		productImageLB_3.setBounds(13, 0, 100, 148);
		steakPanel_3.add(productImageLB_3);
		
		JLabel productNameLB_3 = new JLabel("<html>스테이크<html>\r\n");
		productNameLB_3.setHorizontalTextPosition(SwingConstants.CENTER);
		productNameLB_3.setHorizontalAlignment(SwingConstants.CENTER);
		productNameLB_3.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productNameLB_3.setPreferredSize(new Dimension(24, 15));
		productNameLB_3.setMinimumSize(new Dimension(24, 15));
		productNameLB_3.setMaximumSize(new Dimension(24, 15));
		productNameLB_3.setBounds(0, 158, 125, 41);
		steakPanel_3.add(productNameLB_3);
		
		JLabel productPriceLB_3 = new JLabel("<html> 가격 : 20,000 </html>");
		productPriceLB_3.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productPriceLB_3.setHorizontalTextPosition(SwingConstants.CENTER);
		productPriceLB_3.setHorizontalAlignment(SwingConstants.CENTER);
		productPriceLB_3.setPreferredSize(new Dimension(96, 30));
		productPriceLB_3.setBounds(5, 209, 115, 22);
		steakPanel_3.add(productPriceLB_3);
		
		JButton cartBtn3 = new JButton("담기");
		cartBtn3.setBackground(new Color(240, 255, 240));
		cartBtn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 담기 클릭 시 카트에 추가
				 */
				//frame.productDetailPanel.clickOrder(product);
				try {
					cartService.addCart(new Cart(0, 1, frame.loginMember.getM_id(), 
							new Product(6, "스테이크", 20000, "/images/스테이크_작은.jpg", "쫄깃한 식감, 풍부한 육즙")));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cartBtn3.setBounds(14, 241, 97, 23);
		steakPanel_3.add(cartBtn3);
		
		JButton buyBtn3 = new JButton("구매하기");
		buyBtn3.setBackground(new Color(240, 255, 240));
		buyBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 구매하기 클릭 시 주문생성 패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_ORDER_CREATE, null);
			}
		});
		buyBtn3.setBounds(14, 274, 97, 23);
		steakPanel_3.add(buyBtn3);
		/***************************************************/
		JPanel shabuPanel_4 = new JPanel();
		shabuPanel_4.setBackground(Color.WHITE);
		shabuPanel_4.setLayout(null);
		shabuPanel_4.setPreferredSize(new Dimension(125, 310));
		productListPanel.add(shabuPanel_4);
		
		JLabel productImageLB_4 = new JLabel("");
		productImageLB_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		productImageLB_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 이미지 클릭 시 상품 상세패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_PRODUCT_DETAIL, new Product(5, "샤브샤브", 14000, "/images/샤브샤브_작은.jpg", "매장의 노하우로 완성"));
			}
		});
		productImageLB_4.setIcon(new ImageIcon(ProductRecommendPanel.class.getResource("/images/샤브샤브_작은.jpg")));
		productImageLB_4.setVerticalTextPosition(SwingConstants.BOTTOM);
		productImageLB_4.setPreferredSize(new Dimension(100, 140));
		productImageLB_4.setBounds(12, 0, 100, 148);
		shabuPanel_4.add(productImageLB_4);
		
		JLabel productNameLB_4 = new JLabel("<html>샤브샤브<html>\r\n");
		productNameLB_4.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productNameLB_4.setHorizontalTextPosition(SwingConstants.CENTER);
		productNameLB_4.setHorizontalAlignment(SwingConstants.CENTER);
		productNameLB_4.setPreferredSize(new Dimension(24, 15));
		productNameLB_4.setMinimumSize(new Dimension(24, 15));
		productNameLB_4.setMaximumSize(new Dimension(24, 15));
		productNameLB_4.setBounds(0, 158, 125, 41);
		shabuPanel_4.add(productNameLB_4);
		
		JLabel productPriceLB_4 = new JLabel("<html> 가격 : 14,000 </html>");
		productPriceLB_4.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productPriceLB_4.setHorizontalTextPosition(SwingConstants.CENTER);
		productPriceLB_4.setHorizontalAlignment(SwingConstants.CENTER);
		productPriceLB_4.setPreferredSize(new Dimension(96, 30));
		productPriceLB_4.setBounds(5, 209, 115, 22);
		shabuPanel_4.add(productPriceLB_4);
		
		JButton cartBtn4 = new JButton("담기");
		cartBtn4.setBackground(new Color(240, 255, 240));
		cartBtn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 담기 클릭 시 카트에 추가
				 */
				//frame.productDetailPanel.clickOrder(product);
				try {
					cartService.addCart(new Cart(0, 1, frame.loginMember.getM_id(), 
							new Product(5, "샤브샤브", 14000, "/images/샤브샤브_작은.jpg", "매장의 노하우로 완성")));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cartBtn4.setBounds(14, 241, 97, 23);
		shabuPanel_4.add(cartBtn4);
		
		JButton buyBtn4 = new JButton("구매하기");
		buyBtn4.setBackground(new Color(240, 255, 240));
		buyBtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * 구매하기 클릭 시 주문생성 패널로 이동
				 */
				frame.changePanel(ShopMainFrame.PANEL_ORDER_CREATE, null);
			}
		});
		buyBtn4.setBounds(14, 274, 97, 23);
		shabuPanel_4.add(buyBtn4);
		/********************************/	

		/*
		 * Service 객체 생성
		 */
		productService = new ProductService();
		cartService = new CartService();
		product = new Product();
		/*
		 * loginMember 객체 생성
		 */
		//loginMember = new Member("sy1", null, null, null, null, null, null);
	}// 생성자 끝
	
	public void setFrame(ShopMainFrame frame) {
		this.frame = frame;
	}

	/*	private void productList() throws Exception {
			List<Product> productList = productService.productList();
			productListPanel.removeAll();
			
			for (Product product : productList) {
				JPanel productPanel = new JPanel();
				productPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				productPanel.setPreferredSize(new Dimension(125, 280));
				
				productPanel.setLayout(null);
				
				JLabel productImageLB = new JLabel("");
				productImageLB.setVerticalTextPosition(SwingConstants.BOTTOM);
				productImageLB.setPreferredSize(new Dimension(100, 140));
				productImageLB.setIcon(new ImageIcon(ProductListPanel_김준.class.getResource(product.getP_image())));
				productImageLB.setBounds(0, 0, 100, 148);
				productPanel.add(productImageLB);
				
				JLabel productNameLB = new JLabel(product.getP_name());
				productNameLB.setMinimumSize(new Dimension(24, 15));
				productNameLB.setMaximumSize(new Dimension(24, 15));
				productNameLB.setPreferredSize(new Dimension(24, 15));
				productNameLB.setBounds(0, 158, 115, 41);
				productPanel.add(productNameLB);
				productListPanel.add(productPanel);
				
				JLabel productPriceLB = new JLabel("<html>"+ product.getP_price()+"</html>");
				productPriceLB.setPreferredSize(new Dimension(96, 30));
				productPriceLB.setBounds(0, 209, 115, 22);
				productPanel.add(productPriceLB);
				
				JLabel productDescLB = new JLabel("<html>"+ product.getP_desc() +"</html>");
				productDescLB.setBounds(0, 230, 115, 40);
				productPanel.add(productDescLB);
				
				productListPanel.add(productPanel);
			}
			
			
		}*/
	public void ProductDetail(Product product) {
		
	}
}