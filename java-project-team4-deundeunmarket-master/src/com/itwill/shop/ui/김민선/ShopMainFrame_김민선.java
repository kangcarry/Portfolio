package com.itwill.shop.ui.김민선;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import com.itwill.shop.ui.김준.ProductListPanel;
import com.itwill.shop.ui.김세영.OrderListPanel;
import com.itwill.shop.ui.김강산.CartListPanel;
import com.itwill.shop.ui.김세영.OrderDetailPanel;
import com.itwill.shop.ui.최민영.MemberDetailPanel;

public class ShopMainFrame_김민선 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopMainFrame_김민선 frame = new ShopMainFrame_김민선();
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
	public ShopMainFrame_김민선() throws Exception{
		initGUI();
	}
	private void initGUI() throws Exception{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
				setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		OrderDetailPanel orderDetailPanel_김세영 = new OrderDetailPanel();
		contentPane.add(orderDetailPanel_김세영);
	}

}
