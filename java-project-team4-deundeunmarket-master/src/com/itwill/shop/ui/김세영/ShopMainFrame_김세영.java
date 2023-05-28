package com.itwill.shop.ui.김세영;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itwill.shop.cart.CartService;
import com.itwill.shop.member.Member;
import com.itwill.shop.member.MemberService;
import com.itwill.shop.order.Order;
import com.itwill.shop.order.OrderService;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Cursor;
import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.itwill.shop.ui.최민영.MemberLoginPanel;
import com.itwill.shop.ui.최민영.MemberJoinPanel;
import com.itwill.shop.ui.최민영.MemberDetailPanel;
import com.itwill.shop.ui.김민선.OrderCreatePanel;
import com.itwill.shop.ui.김세영.OrderListPanel;
import com.itwill.shop.ui.김세영.OrderDetailPanel;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Font;
import com.itwill.shop.ui.김준.ProductListPanel;
import com.itwill.shop.ui.김준.ProductRecommendPanel;
import com.itwill.shop.ui.김준.ProductDetailPanel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class ShopMainFrame_김세영 extends JFrame {
	
	/*********************************************************/
	/*
	 * Panel 상수 선언
	 */
	public static final int PANEL_MEMBER_LOGIN = 1;
	public static final int PANEL_MEMBER_JOIN = 2;
	public static final int PANEL_MEMBER_INFO = 3;
	public static final int PANEL_PRODUCT_LIST = 4;
	public static final int PANEL_PRODUCT_RECOMMEND = 5;
	public static final int PANEL_PRODUCT_DETAIL = 6;
	public static final int PANEL_CART = 7;
	public static final int PANEL_ORDER_CREATE = 8;
	public static final int PANEL_ORDER_LIST = 9;
	public static final int PANEL_ORDER_DETAIL = 10;
	
	/*
	 * 1. Service 객체 선언
	 */
	MemberService memberService;
	OrderService orderService;
	CartService cartService;
	ProductService productService;
	/*
	 * 2. login Member 객체 선언(선택한 product 객체 선언)
	 */
	public Member loginMember = null;
	Product selectProduct;
	
	public JPanel contentPane;
	public JTabbedPane shopTabbedPane;
	public JTabbedPane memberTabbedPane;
	public JTabbedPane productTabbedPane;
	public JTabbedPane cartTabbedPane;
	public JTabbedPane orderTabbedPane;
	public MemberLoginPanel memberLoginPanel;
	public MemberJoinPanel memberJoinPanel;
	public MemberDetailPanel memberDetailPanel;
	public ProductListPanel productListPanel;
	public ProductRecommendPanel productRecommendPanel;
	public ProductDetailPanel productDetailPanel;
	public OrderCreatePanel orderCreatePanel;
	public OrderListPanel orderListPanel;
	public OrderDetailPanel orderDetailPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMainFrame_김세영 frame = new ShopMainFrame_김세영();
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
	public ShopMainFrame_김세영() throws Exception{
		setFont(new Font("D2Coding", Font.PLAIN, 12));
		setBackground(SystemColor.info);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopMainFrame_김세영.class.getResource("/images/home 타이틀로고.png")));
		setTitle("든든마켓");
		initGUI();
	}
	private void initGUI() throws Exception{
		setPreferredSize(new Dimension(360, 640));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel globalNorthMenuPanel = new JPanel();
		globalNorthMenuPanel.setBackground(new Color(153, 102, 255));
		contentPane.add(globalNorthMenuPanel, BorderLayout.NORTH);
		globalNorthMenuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel globalLogoLB = new JLabel("");
		globalLogoLB.setBorder(null);
		globalLogoLB.setHorizontalAlignment(SwingConstants.LEFT);
		globalLogoLB.setIcon(new ImageIcon(ShopMainFrame_김세영.class.getResource("/images/든든마켓화이트 상단.png")));
		globalNorthMenuPanel.add(globalLogoLB);
		
		JPanel globalSouthMenuPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) globalSouthMenuPanel.getLayout();
		flowLayout.setHgap(50);
		globalSouthMenuPanel.setBackground(new Color(153, 102, 255));
		contentPane.add(globalSouthMenuPanel, BorderLayout.SOUTH);
		
		JButton globalSearchMenuButton = new JButton("");
		globalSearchMenuButton.setContentAreaFilled(false);
		globalSearchMenuButton.setBorderPainted(false);
		globalSearchMenuButton.setOpaque(false);
		globalSearchMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalSearchMenuButton.setBorder(null);
		globalSearchMenuButton.setIcon(new ImageIcon(ShopMainFrame_김세영.class.getResource("/images/search 50.png")));
		globalSouthMenuPanel.add(globalSearchMenuButton);
		
		JButton globalHomeMenuButton = new JButton("");
		globalHomeMenuButton.setContentAreaFilled(false);
		globalHomeMenuButton.setBorderPainted(false);
		globalHomeMenuButton.setOpaque(false);
		globalHomeMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalHomeMenuButton.setBorder(null);
		globalHomeMenuButton.setIcon(new ImageIcon(ShopMainFrame_김세영.class.getResource("/images/home 50.png")));
		globalSouthMenuPanel.add(globalHomeMenuButton);
		
		JButton globalMemberMenuButton = new JButton("");
		globalMemberMenuButton.setContentAreaFilled(false);
		globalMemberMenuButton.setBorderPainted(false);
		globalMemberMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalMemberMenuButton.setBorder(null);
		globalMemberMenuButton.setOpaque(false);
		globalMemberMenuButton.setIcon(new ImageIcon(ShopMainFrame_김세영.class.getResource("/images/cart50.png")));
		globalSouthMenuPanel.add(globalMemberMenuButton);
		
		shopTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(shopTabbedPane, BorderLayout.CENTER);
		
		memberTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		memberTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				/***********탭이 2번(회원정보)으로 변경 시 회원 정보 보여주기************/
				int selectedTabIndex = memberTabbedPane.getSelectedIndex();
				if (selectedTabIndex == 2) {
					try {
						memberDetailPanel.displayMemberInfo(loginMember);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			}
		});
		shopTabbedPane.addTab("회원", null, memberTabbedPane, null);
		
		memberLoginPanel = new MemberLoginPanel();
		memberTabbedPane.addTab("로그인", null, memberLoginPanel, null);
		
		memberJoinPanel = new MemberJoinPanel();
		memberTabbedPane.addTab("회원가입", null, memberJoinPanel, null);
		
		memberDetailPanel = new MemberDetailPanel();
		memberTabbedPane.addTab("회원정보", null, memberDetailPanel, null);
		
		productTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		shopTabbedPane.addTab("상품", null, productTabbedPane, null);
		
		productListPanel = new ProductListPanel();
		productTabbedPane.addTab("상품목록", null, productListPanel, null);
		
		productRecommendPanel = new ProductRecommendPanel();
		productTabbedPane.addTab("추천상품", null, productRecommendPanel, null);
		
		productDetailPanel = new ProductDetailPanel();
		productTabbedPane.addTab("상품상세", null, productDetailPanel, null);
		
		
		cartTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		shopTabbedPane.addTab("장바구니", null, cartTabbedPane, null);
		
		orderTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		shopTabbedPane.addTab("주문", null, orderTabbedPane, null);
		
		orderCreatePanel = new OrderCreatePanel();
		orderTabbedPane.addTab("주문하기", null, orderCreatePanel, null);
		
		orderListPanel = new OrderListPanel();
		orderTabbedPane.addTab("주문목록", null, orderListPanel, null);
		
		orderDetailPanel = new OrderDetailPanel();
		orderTabbedPane.addTab("주문상세", null, orderDetailPanel, null);
		
	
		/***************************************************/
		/*
		 * 3. Service 객체 생성
		 */
		memberService = new MemberService();
		orderService = new OrderService();
		cartService = new CartService();
		productService = new ProductService();
		
		
		/******* ShopMainFrame 참조를 Panel에 넘겨줌 *******/
		//memberLoginPanel.setFrame(this);
		//memberJoinPanel.setFrame(this);
		//memberDetailPanel.setFrame(this);
		/***************수정**************/
		//productListPanel.setFrame(this);
		//productDetailPanel.setFrame(this);
		//productRecommendPanel.setFrame(this);
		
		//orderListPanel.setFrame(this);
		
	}// 생성자 끝
	/***************패널 변경 메소드******************/
	
	public void changePanel(int panel_no, Object data) throws Exception {
		if (panel_no == PANEL_MEMBER_LOGIN) {
			shopTabbedPane.setSelectedIndex(0);
			memberTabbedPane.setSelectedIndex(0);
		} else if (panel_no == PANEL_MEMBER_JOIN) {
			shopTabbedPane.setSelectedIndex(0);
			memberTabbedPane.setSelectedIndex(1);
		} else if (panel_no == PANEL_MEMBER_INFO) {
			shopTabbedPane.setSelectedIndex(0);
			memberTabbedPane.setSelectedIndex(2);
		} else if (panel_no == PANEL_PRODUCT_LIST) {
			shopTabbedPane.setSelectedIndex(1);
			productTabbedPane.setSelectedIndex(0);
		} else if (panel_no == PANEL_PRODUCT_RECOMMEND) {
			shopTabbedPane.setSelectedIndex(1);
			productTabbedPane.setSelectedIndex(1);
		} else if (panel_no == PANEL_PRODUCT_DETAIL) {
			Product product = (Product)data;
			//System.out.println("recv product" + product);
			shopTabbedPane.setSelectedIndex(1);
			productTabbedPane.setSelectedIndex(2);
			productDetailPanel.displayProductDetail(product);
		} else if (panel_no == PANEL_CART) {
			shopTabbedPane.setSelectedIndex(2);
			cartTabbedPane.setSelectedIndex(0);
		} else if (panel_no == PANEL_ORDER_CREATE) {
			shopTabbedPane.setSelectedIndex(3);
			orderTabbedPane.setSelectedIndex(0);
		} else if (panel_no == PANEL_ORDER_LIST) {
			shopTabbedPane.setSelectedIndex(3);
			orderTabbedPane.setSelectedIndex(1);
		} else if (panel_no == PANEL_ORDER_DETAIL) {
			Order order = (Order)data;
			shopTabbedPane.setSelectedIndex(3);
			orderTabbedPane.setSelectedIndex(2);
			orderDetailPanel.displayOrderDetail(order);
		}

	}
	
	/*
	 public void changePanel(int panel_no,Object data) {
		if(panel_no==PRODUCT_LIST_PANEL) {
			shopTabbedPane.setSelectedIndex(0);
			productTabbedPane.setSelectedIndex(0);
		}else if(panel_no==PRODUCT_DETAIL_PANEL) {
			Product product=(Product)data;
			System.out.println("recv product"+product);
			
			shopTabbedPane.setSelectedIndex(0);
			productTabbedPane.setSelectedIndex(1);
			productDetailPanel.displayProductDetail(product);
			
		}else if(panel_no==USER_INFO_PANEL) {
			shopTabbedPane.setSelectedIndex(1);
			productTabbedPane.setSelectedIndex(1);
		}else if(panel_no==USERT_JOIN_PANEL) {
			shopTabbedPane.setSelectedIndex(1);
			productTabbedPane.setSelectedIndex(0);
		}
		
	}
	 */
	
}
