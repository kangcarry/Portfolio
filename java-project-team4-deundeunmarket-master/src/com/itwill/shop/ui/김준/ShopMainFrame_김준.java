package com.itwill.shop.ui.김준;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.shop.cart.CartService;
import com.itwill.shop.member.Member;
import com.itwill.shop.member.MemberService;
import com.itwill.shop.order.OrderService;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;

import javax.swing.JTabbedPane;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;

public class ShopMainFrame_김준 extends JFrame {
	
	public static final int PRODUCT_LIST_PANEL = 1;
	public static final int PRODUCT_DETAIL_PANEL = 2;
	public static final int PRODUCT_RECOMMEND_PANEL = 3;
	
	
	
	/*
	 * 1.모든 service 객체 선언
	 */
	MemberService memberService;
	CartService cartService;
	ProductService productService;
	OrderService orderService;
	
	/*
	 * 2.login user 객체 선언
	 */
	Member loginMember = null;
	
	private JPanel contentPane;
	private ProductListPanel productListPanel_김준;
	private ProductRecommendPanel productRecommendPanel_김준;
	private ProductDetailPanel productDetailPanel_김준;
	private JTabbedPane productTabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMainFrame_김준 frame = new ShopMainFrame_김준();
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
	public ShopMainFrame_김준() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		productTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(productTabbedPane, BorderLayout.CENTER);
		
		productListPanel_김준 = new ProductListPanel();
		productListPanel_김준.setBackground(new Color(255, 255, 255));
		productTabbedPane.addTab("New tab", null, productListPanel_김준, null);
		
		productRecommendPanel_김준 = new ProductRecommendPanel();
		productTabbedPane.addTab("New tab", null, productRecommendPanel_김준, null);
		
		productDetailPanel_김준 = new ProductDetailPanel();
		productTabbedPane.addTab("New tab", null, productDetailPanel_김준, null);
		
		/**************************************
		 * 3.Service 객체 생성
		 **************************************/
		memberService = new MemberService();
		productService = new ProductService();
		orderService = new OrderService();
		cartService = new CartService();
		
		/*******ShopMainFrame참조를 Panel에 넘겨줌*******
		productListPanel_김준.setFrame(this);
		productDetailPanel_김준.setFrame(this);
		productRecommendPanel_김준.setFrame(this);
		*/
	}
	
	/***********패널변경메쏘드********/
	public void changePanel(int panel_no, Object data) {
		if(panel_no==PRODUCT_LIST_PANEL) {
			productTabbedPane.setSelectedIndex(0);
		}else if(panel_no==PRODUCT_DETAIL_PANEL) {
			Product product=(Product)data;
			
			productTabbedPane.setSelectedIndex(2);
			productDetailPanel_김준.displayProductDetail(product);
			productDetailPanel_김준.clickOrder(product);
			
		}else if(panel_no==PRODUCT_RECOMMEND_PANEL) {
			Product product=(Product)data;
			productTabbedPane.setSelectedIndex(1);
		}
	}
	
}