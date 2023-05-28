package com.itwill.shop.ui;

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
import com.itwill.shop.ui.김강산.CartListPanel;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ShopMainFrame extends JFrame {
	
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
	public static final int PANEL_MAIN = 11;
	
	/*
	 * 1. Service 객체 선언
	 */
	public MemberService memberService;
	public OrderService orderService;
	public CartService cartService;
	public ProductService productService;
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
	public CartListPanel cartListPanel;
	public JPanel mainPanel;
	public JLabel mainPageLB;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMainFrame frame = new ShopMainFrame();
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
	public ShopMainFrame() throws Exception{
		setFont(new Font("D2Coding", Font.PLAIN, 12));
		setBackground(SystemColor.info);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopMainFrame.class.getResource("/images/home 타이틀로고.png")));
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
		globalLogoLB.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/든든마켓화이트 상단.png")));
		globalNorthMenuPanel.add(globalLogoLB);
		
		JPanel globalSouthMenuPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) globalSouthMenuPanel.getLayout();
		flowLayout.setHgap(50);
		globalSouthMenuPanel.setBackground(new Color(153, 102, 255));
		contentPane.add(globalSouthMenuPanel, BorderLayout.SOUTH);
		
		JButton globalSearchMenuButton = new JButton("");
		globalSearchMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(PANEL_PRODUCT_LIST, null);
			}
		});
		globalSearchMenuButton.setContentAreaFilled(false);
		globalSearchMenuButton.setBorderPainted(false);
		globalSearchMenuButton.setOpaque(false);
		globalSearchMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalSearchMenuButton.setBorder(null);
		globalSearchMenuButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/search 50.png")));
		globalSouthMenuPanel.add(globalSearchMenuButton);
		
		JButton globalHomeMenuButton = new JButton("");
		globalHomeMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(PANEL_MAIN, null);
			}
		});
		globalHomeMenuButton.setContentAreaFilled(false);
		globalHomeMenuButton.setBorderPainted(false);
		globalHomeMenuButton.setOpaque(false);
		globalHomeMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalHomeMenuButton.setBorder(null);
		globalHomeMenuButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/home 50.png")));
		globalSouthMenuPanel.add(globalHomeMenuButton);
		
		JButton globalMemberMenuButton = new JButton("");
		globalMemberMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(PANEL_CART, null);
			}
		});
		globalMemberMenuButton.setContentAreaFilled(false);
		globalMemberMenuButton.setBorderPainted(false);
		globalMemberMenuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		globalMemberMenuButton.setBorder(null);
		globalMemberMenuButton.setOpaque(false);
		globalMemberMenuButton.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/cart50.png")));
		globalSouthMenuPanel.add(globalMemberMenuButton);
		
		shopTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		shopTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				/*
				 * 2->3으로 수정
				 */
				if(((JTabbedPane)e.getSource()).getSelectedIndex()==3) {
					try {
						cartListPanel.displayCartList();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if(((JTabbedPane)e.getSource()).getSelectedIndex()==4) {
					try {
						orderListPanel.orderList();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
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
				/************************/
				if (selectedTabIndex == 0) {
					memberLoginPanel.loginIdTF.setText("");
					memberLoginPanel.loginPassTF.setText("");
				}
			}
		});
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setFont(new Font("D2Coding", Font.PLAIN, 12));
		shopTabbedPane.addTab("메인", null, mainPanel, null);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		mainPageLB = new JLabel("");
		mainPageLB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (loginMember == null) {
					changePanel(PANEL_MEMBER_LOGIN, null);
					// 로그인 안 되어있으면 장바구니, 주문 탭 불활성화
					shopTabbedPane.setEnabledAt(3, false);
					shopTabbedPane.setEnabledAt(4, false);
					memberTabbedPane.setEnabledAt(2, false);
				} else {
					changePanel(PANEL_PRODUCT_LIST, null);
				}
				
			}
		});
		
		mainPageLB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		mainPageLB.setAlignmentX(Component.CENTER_ALIGNMENT);
		mainPageLB.setHorizontalAlignment(SwingConstants.CENTER);
		mainPageLB.setHorizontalTextPosition(SwingConstants.CENTER);
		mainPageLB.setIcon(new ImageIcon(ShopMainFrame.class.getResource("/images/든든마켓 보라.png")));
		mainPanel.add(mainPageLB, BorderLayout.CENTER);
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
		
		cartListPanel = new CartListPanel();
		cartTabbedPane.addTab("나의 장바구니", null, cartListPanel, null);
		
		orderTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		orderTabbedPane.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 주문 탭 클릭 -> 주문 리스트 없으면 주문목록 탭 불활성화
				 */
				try {
					List<Order> orderList;
					orderList = orderService.orderList(loginMember.getM_id());
					if (orderList.size() == 0) {
						orderTabbedPane.setEnabledAt(1, false);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		shopTabbedPane.addTab("주문", null, orderTabbedPane, null);
		
		orderCreatePanel = new OrderCreatePanel();
		orderTabbedPane.addTab("주문하기", null, orderCreatePanel, null);
		
		orderListPanel = new OrderListPanel();
		orderTabbedPane.addTab("주문목록", null, orderListPanel, null);
		
	
		/***************************************************/
		/*
		 * 3. Service 객체 생성
		 */
		memberService = new MemberService();
		orderService = new OrderService();
		cartService = new CartService();
		productService = new ProductService();
		
		//loginMember = new Member("aaa", null, null, null, null, null, null);
		
		
		/******* ShopMainFrame 참조를 Panel에 넘겨줌 *******/
		memberLoginPanel.setFrame(this);
		memberJoinPanel.setFrame(this);
		memberDetailPanel.setFrame(this);
		productListPanel.setFrame(this);
		productDetailPanel.setFrame(this);
		productRecommendPanel.setFrame(this);
		cartListPanel.setFrame(this);
		orderCreatePanel.setFrame(this);
		orderListPanel.setFrame(this);
		
		
	}// 생성자 끝
	public void changePanel(int panel_no, Object data) {
		if (panel_no == PANEL_MEMBER_LOGIN) {
			shopTabbedPane.setSelectedIndex(1);
			memberTabbedPane.setSelectedIndex(0);
		} else if (panel_no == PANEL_MEMBER_JOIN) {
			shopTabbedPane.setSelectedIndex(1);
			memberTabbedPane.setSelectedIndex(1);
		} else if (panel_no == PANEL_MEMBER_INFO) {
			shopTabbedPane.setSelectedIndex(1);
			memberTabbedPane.setSelectedIndex(2);
		} else if (panel_no == PANEL_PRODUCT_LIST) {
			shopTabbedPane.setSelectedIndex(2);
			productTabbedPane.setSelectedIndex(0);
		} else if (panel_no == PANEL_PRODUCT_RECOMMEND) {
			shopTabbedPane.setSelectedIndex(2);
			productTabbedPane.setSelectedIndex(1);
		} else if (panel_no == PANEL_PRODUCT_DETAIL) {
			Product product = (Product)data;
			//System.out.println("recv product" + product);
			shopTabbedPane.setSelectedIndex(2);
			productTabbedPane.setSelectedIndex(2);
			productDetailPanel.displayProductDetail(product);
		} else if (panel_no == PANEL_CART) {
			shopTabbedPane.setSelectedIndex(3);
			cartTabbedPane.setSelectedIndex(0);
		} else if (panel_no == PANEL_ORDER_CREATE) {
			shopTabbedPane.setSelectedIndex(4);
			orderTabbedPane.setSelectedIndex(0);
		} else if (panel_no == PANEL_ORDER_LIST) {
			shopTabbedPane.setSelectedIndex(4);
			orderTabbedPane.setSelectedIndex(1);
		} else if (panel_no == PANEL_ORDER_DETAIL) {
			shopTabbedPane.setSelectedIndex(4);
			orderTabbedPane.setSelectedIndex(2);
		}else if (panel_no == PANEL_MAIN) {
			shopTabbedPane.setSelectedIndex(0);
			orderTabbedPane.setSelectedIndex(0);
		}

	}
	
	
	

}
