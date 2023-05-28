package com.itwill.shop.ui.김강산;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartService;
import com.itwill.shop.member.Member;
import com.itwill.shop.order.OrderService;
import com.itwill.shop.product.ProductService;
import com.itwill.shop.ui.ShopMainFrame;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CartListPanel2 extends JPanel {
	/*
	 * 프레임 참조
	 */
	ShopMainFrame frame;
	/*
	 * Service 멤버변수 선언
	 */
	private CartService cartService;
	private ProductService productService;
	private OrderService orderService;
	/*
	 * login member 선언
	 */
	public Member loginMember;
	
	private JPanel finalPricePanel;
	private JButton orderBtn;
	private JLabel finalCartListTotalLB;
	private JLabel baesongLB;
	private JLabel cartListTotalLB;
	private JPanel cartListPanel;
	private JScrollPane cartListScrollPane;
	private JPanel cartPanel;
	private JButton productImgBtn;
	private JButton exitBtn;
	private JLabel cartProductDesc;
	private JLabel cartProductAddPrice;
	private JPanel jp;

	/**
	 * Create the panel.
	 * 
	 * @throws Exception
	 */

	// ****************카트리스트패널만들기******************

	public CartListPanel2() throws Exception {
		

		setLayout(null);

		JPanel cartListMainPanel = new JPanel();
		cartListMainPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				System.out.println(frame.loginMember);
			}
		});
		cartListMainPanel.setBackground(Color.WHITE);
		cartListMainPanel.setBounds(0, 0, 360, 540);
		add(cartListMainPanel);
		cartListMainPanel.setLayout(null);

		orderBtn = new JButton("구매하기");
		orderBtn.setForeground(new Color(255, 255, 255));
		orderBtn.setBackground(new Color(147, 112, 219));
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 오더창으로 넘어가기
			}
		});
		orderBtn.setFont(new Font("D2Coding", Font.PLAIN, 18));
		orderBtn.setBounds(98, 352, 142, 23);
		cartListMainPanel.add(orderBtn);

		finalPricePanel = new JPanel();
		finalPricePanel.setBackground(new Color(240, 255, 240));
		finalPricePanel.setBounds(5, 284, 317, 63);
		cartListMainPanel.add(finalPricePanel);
		finalPricePanel.setLayout(null);

		JLabel productTotalPriceTF = new JLabel("상품금액");
		productTotalPriceTF.setFont(new Font("D2Coding", Font.PLAIN, 12));
		productTotalPriceTF.setBounds(12, 4, 48, 15);
		finalPricePanel.add(productTotalPriceTF);

		JLabel baesongbiTF = new JLabel("배송비");
		baesongbiTF.setFont(new Font("D2Coding", Font.PLAIN, 12));
		baesongbiTF.setBounds(12, 21, 48, 15);
		finalPricePanel.add(baesongbiTF);

		JLabel FinalPriceTF = new JLabel("총 결제금액");
		FinalPriceTF.setFont(new Font("D2Coding", Font.BOLD, 16));
		FinalPriceTF.setBounds(12, 38, 98, 23);
		finalPricePanel.add(FinalPriceTF);

		cartListTotalLB = new JLabel("100000원");
		cartListTotalLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		cartListTotalLB.setHorizontalAlignment(SwingConstants.TRAILING);
		cartListTotalLB.setBounds(195, 4, 108, 15);
		cartListTotalLB.setText(addCartListTotal() + "원");
		finalPricePanel.add(cartListTotalLB);

		baesongLB = new JLabel("3000원");
		baesongLB.setFont(new Font("D2Coding", Font.PLAIN, 12));
		baesongLB.setHorizontalAlignment(SwingConstants.TRAILING);
		baesongLB.setBounds(205, 21, 98, 15);
		finalPricePanel.add(baesongLB);

		finalCartListTotalLB = new JLabel("103000원");
		finalCartListTotalLB.setFont(new Font("D2Coding", Font.PLAIN, 14));
		finalCartListTotalLB.setHorizontalAlignment(SwingConstants.TRAILING);
		finalCartListTotalLB.setBounds(195, 40, 108, 19);
		finalCartListTotalLB.setText(finalCartListTotal() + "원");
		finalPricePanel.add(finalCartListTotalLB);
		
		allCartDelete = new JButton("전체삭제");
		allCartDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cartService.deleteCartItemByMemberId(frame.loginMember.getM_id());
					refresh();
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		/*
		allCartDelete.setBounds(257, 51, 89, 23);
		northPanel.add(allCartDelete);
		*/

		cartListScrollPane = new JScrollPane();
		cartListScrollPane.setBackground(Color.WHITE);
		cartListScrollPane.setBounds(0, 26, 326, 254);
		cartListMainPanel.add(cartListScrollPane);

		cartListPanel = new JPanel();
		cartListPanel.setBackground(Color.WHITE);
		cartListPanel.setPreferredSize(new Dimension(10, 1500));

		cartListScrollPane.setViewportView(cartListPanel);

		/**************** cart panel ******************/

		cartPanel = new JPanel();
		cartPanel.setBackground(Color.WHITE);
		cartPanel.setPreferredSize(new Dimension(320, 130));
		cartPanel.setLayout(null);

		productImgBtn = new JButton("");
		productImgBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 상세보기 추가
			}
		});
		productImgBtn.setIcon(new ImageIcon(CartListPanel2.class.getResource("/images/떡볶이_작은.jpg")));
		productImgBtn.setBackground(Color.WHITE);
		productImgBtn.setBounds(9, 6, 102, 118);
		cartPanel.add(productImgBtn);

		exitBtn = new JButton("");
		exitBtn.setIcon(new ImageIcon(CartListPanel2.class.getResource("/images/close10.png")));
		exitBtn.setFont(new Font("D2Coding", Font.BOLD, 10));
		exitBtn.setBounds(292, 10, 16, 15);
		cartPanel.add(exitBtn);

		cartProductDesc = new JLabel();
		cartProductDesc.setFont(new Font("D2Coding", Font.PLAIN, 12));
		cartProductDesc.setBounds(128, 37, 180, 62);

		cartPanel.add(cartProductDesc);

		cartProductAddPrice = new JLabel("상품금액");
		cartProductAddPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		cartProductAddPrice.setFont(new Font("D2Coding", Font.PLAIN, 14));
		cartProductAddPrice.setBounds(240, 105, 68, 15);
		cartPanel.add(cartProductAddPrice);

		JComboBox productQty = new JComboBox();
		productQty.setBackground(new Color(240, 255, 240));
		productQty.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
		productQty.setBounds(138, 97, 32, 23);
		cartPanel.add(productQty);

		/**********************************************/
		cartListPanel.add(cartPanel);
		
		cartProductName = new JLabel();
		cartProductName.setFont(new Font("D2Coding", Font.PLAIN, 12));
		cartProductName.setBounds(128, 10, 152, 29);
		cartPanel.add(cartProductName);
		
		JButton deleteAllBtn = new JButton("전체삭제");
		deleteAllBtn.setForeground(new Color(255, 255, 255));
		deleteAllBtn.setFont(new Font("D2Coding", Font.PLAIN, 11));
		deleteAllBtn.setBackground(new Color(147, 112, 219));
		deleteAllBtn.setBounds(228, 0, 81, 23);
		cartListMainPanel.add(deleteAllBtn);

		// 서비스 생성자 생성
		
		cartService = new CartService();
		orderService = new OrderService();
		productService = new ProductService();

		// loginMember객체생성

		loginMember = new Member("sy0", null, null, null, null, null, null);
		//loginMember = frame.memberService.memberDetail(frame.loginMember.getM_id());
		// 메인 메소드 호출 (수정)
		displayCartList();

	}// 생성자 끝
	
	public void setFrame(ShopMainFrame frame) {
		this.frame = frame;
	}

	public void setLoginMember(Member loginMember) {
		this.loginMember = loginMember;
	}

	List<Cart> selectedCartList = null;
	JCheckBox[] cartCBArray = null;
	private JLabel cartProductName;
	private JButton F5Btn;
	private JButton allCartDelete;

	// 메인 메소드
	/*
	 * 카트 보여주기 메소드(수정)
	 */
	
	
	/*
	 * 카트 보여주기 메소드
	 */
	
	public void displayCartList() throws Exception {
		List<Cart> cartList = cartService.getCartItemByMemberId(loginMember.getM_id());
		cartListPanel.removeAll();
		cartCBArray = new JCheckBox[cartList.size()];

		for (int i = 0; i < cartList.size(); i++) {
			Cart cart = cartList.get(i);
			
			cartPanel = new JPanel();
			cartPanel.setPreferredSize(new Dimension(320, 130));
			cartPanel.setLayout(null);

			productImgBtn = new JButton("");
			productImgBtn.setIcon(new ImageIcon(CartListPanel2.class.getResource(cart.getProduct().getP_image())));
			productImgBtn.setBackground(Color.WHITE);
			productImgBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 상품 상세보기 추가
				}
			});
			productImgBtn.setBounds(19, 10, 97, 65);
			cartPanel.add(productImgBtn);

			// 장바구니 1개 삭제 X버튼
			exitBtn = new JButton("");
			exitBtn.setIcon(new ImageIcon(CartListPanel2.class.getResource("/images/close10.png")));
			exitBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						cartService.deleteCartItemByCartNo(cart.getCart_no());
						refresh();
//						cartPanel.remove(cartPanel);
						System.out.println("카트1개가 삭제되었습니다");
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			});
			exitBtn.setFont(new Font("D2Coding", Font.BOLD, 10));
			exitBtn.setBounds(292, 10, 16, 15);
			cartPanel.add(exitBtn);
			
			cartProductName = new JLabel();
			cartProductName.setBounds(128, 10, 152, 43);
			cartProductName.setText(cart.getProduct().getP_name());
			cartPanel.add(cartProductName);

			cartProductDesc = new JLabel();
			cartProductDesc.setFont(new Font("굴림", Font.PLAIN, 11));
			cartProductDesc.setBounds(128, 10, 192, 79);
			cartProductDesc.setText(cart.getProduct().getP_desc());
			cartPanel.add(cartProductDesc);

			cartProductAddPrice = new JLabel("상품금액");
			cartProductAddPrice.setFont(new Font("D2Coding", Font.PLAIN, 14));
			cartProductAddPrice.setBounds(225, 101, 68, 15);
			cartProductAddPrice.setText(Integer.toString(cart.getProduct().getP_price()) + "원");
			cartPanel.add(cartProductAddPrice);

			JComboBox productQty = new JComboBox();
			productQty.setBounds(52, 97, 32, 23);
			productQty.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));
			productQty.setSelectedItem(Integer.toString(cart.getCart_qty()));
			cartPanel.add(productQty);
			productQty.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						try {
							String qty = (String) productQty.getSelectedItem();
							int iqty = Integer.parseInt(qty);
							cart.setCart_qty(iqty);
							cartService.updateCart(cart);

							cartListTotalLB.setText(addCartListTotal() + "원");
							finalCartListTotalLB.setText(finalCartListTotal() + "원");
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
			});

			cartListPanel.add(cartPanel);
	 

			/*******************/

		}

	}
	
	

	// 메소드 내 메소드

	// 배송비 제외 카트리스트 합계 (수정)
	/*
	public String addCartListTotal() throws Exception {
		loginMember = new Member("sy0", null, null, null, null, null, null);
		cartService = new CartService();
		return Integer.toString(cartService.addCartListTotal(frame.loginMember.getM_id()));
	}
	*/
	public String addCartListTotal() throws Exception {
		loginMember = new Member("sy0", null, null, null, null, null, null);
		cartService = new CartService();
		return Integer.toString(cartService.addCartListTotal(loginMember.getM_id()));

	}
	// 배송비 제외 카트리스트 합계

	public String finalCartListTotal() throws Exception {
		int total = 0;
		total = Integer.parseInt(this.addCartListTotal()) + 3000;
		return Integer.toString(total);

	}
	
	// 삭제시 새로고침 기능 추가 (수정)
	public void refresh() throws Exception {
		cartListTotalLB.setText(addCartListTotal() + "원");
		finalCartListTotalLB.setText(finalCartListTotal() + "원");
		displayCartList();
	}
	/*
	 * 	public void refresh() throws Exception {
		cartListTotalLB.setText(addCartListTotal() + "원");
		finalCartListTotalLB.setText(finalCartListTotal() + "원");
		displayCartList();
	}
	 */
}