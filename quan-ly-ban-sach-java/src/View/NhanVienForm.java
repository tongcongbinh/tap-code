package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class NhanVienForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Image img_logo = new ImageIcon(QuanLyForm.class.getResource("/pictures/book.png")).getImage()
			.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	private Image img_employee = new ImageIcon(QuanLyForm.class.getResource("/pictures/employee.png")).getImage()
			.getScaledInstance(51, 51, Image.SCALE_SMOOTH);
	private Image img_customer = new ImageIcon(QuanLyForm.class.getResource("/pictures/customer.png")).getImage()
			.getScaledInstance(51, 51, Image.SCALE_SMOOTH);
	private Image img_book = new ImageIcon(QuanLyForm.class.getResource("/pictures/bookmanager.png")).getImage()
			.getScaledInstance(51, 51, Image.SCALE_SMOOTH);
	private Image img_ncc = new ImageIcon(QuanLyForm.class.getResource("/pictures/delivery.png")).getImage()
			.getScaledInstance(51, 51, Image.SCALE_SMOOTH);
	private Image img_phieunhap = new ImageIcon(QuanLyForm.class.getResource("/pictures/phieunhap.png")).getImage()
			.getScaledInstance(51, 51, Image.SCALE_SMOOTH);
	private Image img_hoadon = new ImageIcon(QuanLyForm.class.getResource("/pictures/bill.png")).getImage()
			.getScaledInstance(51, 51, Image.SCALE_SMOOTH);
	private Image img_logout = new ImageIcon(QuanLyForm.class.getResource("/pictures/logout.png")).getImage()
			.getScaledInstance(51, 51, Image.SCALE_SMOOTH);
	private Image img_exit = new ImageIcon(QuanLyForm.class.getResource("/pictures/exit.png")).getImage()
			.getScaledInstance(51, 51, Image.SCALE_SMOOTH);
	private Image img_banner = new ImageIcon(QuanLyForm.class.getResource("/pictures/banner.jpg")).getImage()
			.getScaledInstance(1096, 96, Image.SCALE_SMOOTH);
	private Image img_hdct = new ImageIcon(QuanLyForm.class.getResource("/pictures/detail.png")).getImage()
			.getScaledInstance(44, 44, Image.SCALE_SMOOTH);
	private Image img_statistical = new ImageIcon(QuanLyForm.class.getResource("/pictures/statistical.png")).getImage()
			.getScaledInstance(44, 44, Image.SCALE_SMOOTH);
	
	private static QLNhanvienPanel_NhanVien qlNhanvienPanel;
	private static HoiVienPanel hoiVienPanel;
	private static SachPanel sachPanel;
	private static NhaCungCapPanel nhaCungCapPanel;
	private static PhieuNhapPanel phieuNhapPanel;
	private static HoaDonPanel hoaDonPanel;
	private static HomePanel homePanel;
	private static HDCTPanel hdctPanel;
	private static ThongKePanel thongKePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVienForm frame = new NhanVienForm();
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
	public NhanVienForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		qlNhanvienPanel = new QLNhanvienPanel_NhanVien();
		hoiVienPanel = new HoiVienPanel();
		sachPanel = new SachPanel();
		nhaCungCapPanel = new NhaCungCapPanel();
		phieuNhapPanel = new PhieuNhapPanel();
		hoaDonPanel = new HoaDonPanel();
		homePanel = new HomePanel();
		hdctPanel = new HDCTPanel();
		thongKePanel = new ThongKePanel();
		contentPane.setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBounds(344, 26, 1096, 77);
		panelTitle.setBackground(new Color(51, 153, 153));
		contentPane.add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lb_title = new JLabel("HỆ THỐNG QUẢN LÝ BÁN SÁCH NHÀ SÁCH TRÍ TUỆ");
		lb_title.setForeground(Color.WHITE);
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lb_title.setBounds(10, 11, 1076, 55);
		panelTitle.add(lb_title);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JPanel panelHDCT = new JPanel();
		panelHDCT.setBounds(1180, 103, 260, 60);
		panelHDCT.setLayout(null);
		panelHDCT.setBackground(new Color(51, 153, 153));
		contentPane.add(panelHDCT);
		
		JPanel panelThongke = new JPanel();
		panelThongke.setBackground(new Color(51, 153, 153));
		panelThongke.setBounds(1275, 103, 165, 60);
		contentPane.add(panelThongke);
		panelThongke.setLayout(null);
		
		panelThongke.addMouseListener(new PanleButtonMouseAdpter(panelThongke){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(thongKePanel);
				lb_title.setText("BÁO CÁO THỐNG KÊ");
				panelHDCT.setVisible(false);
				panelThongke.setVisible(false);
			}
		});
		
		panelHDCT.addMouseListener(new PanleButtonMouseAdpter(panelHDCT){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(hdctPanel);
				lb_title.setText("HÓA ĐƠN CHI TIẾT");
				panelHDCT.setVisible(false);
				panelThongke.setVisible(false);
			}
		});
		
		JLabel lb_hdct = new JLabel("Hóa đơn chi tiết");
		lb_hdct.setForeground(Color.WHITE);
		lb_hdct.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lb_hdct.setBounds(10, 11, 240, 44);
		panelHDCT.add(lb_hdct);
		panelHDCT.setVisible(false);
		lb_hdct.setIcon(new ImageIcon(img_hdct));
		
		JLabel lb_thongke = new JLabel("Thống kê");
		lb_thongke.setForeground(Color.WHITE);
		lb_thongke.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lb_thongke.setBounds(10, 11, 240, 44);
		panelThongke.add(lb_thongke);
		lb_thongke.setIcon(new ImageIcon(img_statistical));
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 334, 750);
		panelMenu.setBackground(new Color(51, 153, 153));
		panelMenu.setBorder(null);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);

		JLabel lb_logo = new JLabel("");
		lb_logo.setForeground(Color.WHITE);
		lb_logo.setFont(new Font("Tahoma", Font.BOLD, 26));
		lb_logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(homePanel);
				lb_title.setText("HỆ THỐNG QUẢN LÝ BÁN SÁCH NHÀ SÁCH TRÍ TUỆ");
				panelHDCT.setVisible(false);
				panelThongke.setVisible(true);
				
			}
		});
		lb_logo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logo.setBounds(0, 0, 334, 115);
		panelMenu.add(lb_logo);
		lb_logo.setIcon(new ImageIcon(img_logo));

		JLabel lblNewLabel = new JLabel("Nhà sách Trí Tuệ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 115, 334, 51);
		panelMenu.add(lblNewLabel);

		JPanel panelNhanvien = new JPanel();
		panelNhanvien.setBackground(new Color(51, 153, 153));
		panelNhanvien.setBounds(0, 166, 334, 73);
		panelMenu.add(panelNhanvien);
		panelNhanvien.setLayout(null);
		panelNhanvien.addMouseListener(new PanleButtonMouseAdpter(panelNhanvien) {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(qlNhanvienPanel);
				lb_title.setText("NHÂN VIÊN");
				panelHDCT.setVisible(false);
				panelThongke.setVisible(false);
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Nhân viên");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(105, 11, 219, 51);
		panelNhanvien.add(lblNewLabel_1);

		JLabel lb_employee = new JLabel("");
		lb_employee.setHorizontalAlignment(SwingConstants.CENTER);
		lb_employee.setBounds(24, 11, 51, 51);
		panelNhanvien.add(lb_employee);
		lb_employee.setIcon(new ImageIcon(img_employee));

		JPanel panelHoivien = new JPanel();
		panelHoivien.setBackground(new Color(51, 153, 153));
		panelHoivien.setBounds(0, 239, 334, 73);
		panelMenu.add(panelHoivien);
		panelHoivien.setLayout(null);
		panelHoivien.addMouseListener(new PanleButtonMouseAdpter(panelHoivien){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(hoiVienPanel);
				lb_title.setText("QUẢN LÝ HỘI VIÊN");
				panelHDCT.setVisible(false);
				panelThongke.setVisible(false);
			}
		});

		JLabel lblNewLabel_1_1 = new JLabel("Quản lý Hội viên");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(105, 11, 219, 51);
		panelHoivien.add(lblNewLabel_1_1);

		JLabel lb_customer = new JLabel("");
		lb_customer.setHorizontalAlignment(SwingConstants.CENTER);
		lb_customer.setBounds(24, 11, 51, 51);
		panelHoivien.add(lb_customer);
		lb_customer.setIcon(new ImageIcon(img_customer));

		JPanel panelSach = new JPanel();
		panelSach.setBackground(new Color(51, 153, 153));
		panelSach.setBounds(0, 312, 334, 73);
		panelMenu.add(panelSach);
		panelSach.setLayout(null);
		panelSach.addMouseListener(new PanleButtonMouseAdpter(panelSach){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(sachPanel);
				lb_title.setText("QUẢN LÝ SÁCH");
				panelHDCT.setVisible(false);
				panelThongke.setVisible(false);
			}
		});

		JLabel lblNewLabel_1_1_1 = new JLabel("Quản lý Sách");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel_1_1_1.setBounds(105, 11, 219, 51);
		panelSach.add(lblNewLabel_1_1_1);

		JLabel lb_book = new JLabel("");
		lb_book.setHorizontalAlignment(SwingConstants.CENTER);
		lb_book.setBounds(24, 11, 51, 51);
		panelSach.add(lb_book);
		lb_book.setIcon(new ImageIcon(img_book));

		JPanel panelNCC = new JPanel();
		panelNCC.setBackground(new Color(51, 153, 153));
		panelNCC.setBounds(0, 385, 334, 73);
		panelMenu.add(panelNCC);
		panelNCC.setLayout(null);

		JLabel lblNewLabel_1_1_2 = new JLabel("Quản lý NCC");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel_1_1_2.setBounds(105, 11, 219, 51);
		panelNCC.add(lblNewLabel_1_1_2);

		JLabel lb_ncc = new JLabel("");
		lb_ncc.setHorizontalAlignment(SwingConstants.CENTER);
		lb_ncc.setBounds(24, 11, 51, 51);
		panelNCC.add(lb_ncc);
		lb_ncc.setIcon(new ImageIcon(img_ncc));
		panelNCC.addMouseListener(new PanleButtonMouseAdpter(panelNCC){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(nhaCungCapPanel);
				lb_title.setText("QUẢN LÝ NHÀ CUNG CẤP");
				panelHDCT.setVisible(false);
				panelThongke.setVisible(false);
			}
		});

		JPanel panelPhieunhap = new JPanel();
		panelPhieunhap.setBackground(new Color(51, 153, 153));
		panelPhieunhap.setBounds(0, 458, 334, 73);
		panelMenu.add(panelPhieunhap);
		panelPhieunhap.setLayout(null);
		panelPhieunhap.addMouseListener(new PanleButtonMouseAdpter(panelPhieunhap){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(phieuNhapPanel);
				lb_title.setText("QUẢN LÝ PHIẾU NHẬP");
				panelHDCT.setVisible(false);
				panelThongke.setVisible(false);
			}
		});

		JLabel lblNewLabel_1_1_3 = new JLabel("Quản lý Phiếu nhập");
		lblNewLabel_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel_1_1_3.setBounds(105, 11, 219, 51);
		panelPhieunhap.add(lblNewLabel_1_1_3);

		JLabel lb_phieunhap = new JLabel("");
		lb_phieunhap.setHorizontalAlignment(SwingConstants.CENTER);
		lb_phieunhap.setBounds(24, 11, 51, 51);
		panelPhieunhap.add(lb_phieunhap);
		lb_phieunhap.setIcon(new ImageIcon(img_phieunhap));

		JPanel panelHoadon = new JPanel();
		panelHoadon.setBackground(new Color(51, 153, 153));
		panelHoadon.setBounds(0, 531, 334, 73);
		panelMenu.add(panelHoadon);
		panelHoadon.setLayout(null);
		panelHoadon.addMouseListener(new PanleButtonMouseAdpter(panelHoadon){
			@Override
			public void mouseClicked(MouseEvent e) {
				menuClicked(hoaDonPanel);
				lb_title.setText("QUẢN LÝ HÓA ĐƠN");
				panelHDCT.setVisible(true);
				panelThongke.setVisible(false);
			}
		});

		JLabel lblNewLabel_1_1_4 = new JLabel("Quản lý Hóa đơn");
		lblNewLabel_1_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel_1_1_4.setBounds(105, 11, 219, 51);
		panelHoadon.add(lblNewLabel_1_1_4);

		JLabel lb_hoadon = new JLabel("");
		lb_hoadon.setHorizontalAlignment(SwingConstants.CENTER);
		lb_hoadon.setBounds(24, 11, 51, 51);
		panelHoadon.add(lb_hoadon);
		lb_hoadon.setIcon(new ImageIcon(img_hoadon));

		JPanel panelLogout = new JPanel();
		panelLogout.setBackground(new Color(51, 153, 153));
		panelLogout.setBounds(0, 604, 334, 73);
		panelMenu.add(panelLogout);
		panelLogout.setLayout(null);

		JLabel lblNewLabel_1_1_5 = new JLabel("Đăng xuất");
		lblNewLabel_1_1_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_1_5.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel_1_1_5.setBounds(105, 11, 219, 51);
		panelLogout.add(lblNewLabel_1_1_5);

		JLabel lb_logout = new JLabel("");
		lb_logout.setHorizontalAlignment(SwingConstants.CENTER);
		lb_logout.setBounds(24, 11, 51, 51);
		panelLogout.add(lb_logout);
		lb_logout.setIcon(new ImageIcon(img_logout));
		panelLogout.addMouseListener(new PanleButtonMouseAdpter(panelLogout){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất?", "Thông báo", JOptionPane.YES_NO_OPTION) == 0) {
					LoginForm lgForm = new LoginForm();
					lgForm.setVisible(true);
					NhanVienForm.this.dispose();
				}
			}
		});

		JPanel panelExit = new JPanel();
		panelExit.setBackground(new Color(51, 153, 153));
		panelExit.setBounds(0, 677, 334, 73);
		panelMenu.add(panelExit);
		panelExit.setLayout(null);

		JLabel lblNewLabel_1_1_6 = new JLabel("Thoát");
		lblNewLabel_1_1_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1_6.setForeground(Color.WHITE);
		lblNewLabel_1_1_6.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel_1_1_6.setBounds(105, 11, 219, 51);
		panelExit.add(lblNewLabel_1_1_6);

		JLabel lb_exit = new JLabel("");
		lb_exit.setHorizontalAlignment(SwingConstants.CENTER);
		lb_exit.setBounds(24, 11, 51, 51);
		panelExit.add(lb_exit);
		lb_exit.setIcon(new ImageIcon(img_exit));
		panelExit.addMouseListener(new PanleButtonMouseAdpter(panelExit){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình?", "Thông báo", JOptionPane.YES_NO_OPTION) == 0) {
					NhanVienForm.this.dispose();
				}
			}
		});

		JPanel panelForm = new JPanel();
		panelForm.setBounds(344, 166, 1096, 573);
		panelForm.setBackground(new Color(51, 204, 204));
		panelForm.setBorder(new LineBorder(new Color(102, 204, 255), 2));
		contentPane.add(panelForm);
		panelForm.setLayout(null);
		
		panelForm.add(qlNhanvienPanel);
		panelForm.add(hoiVienPanel);
		panelForm.add(sachPanel);
		panelForm.add(nhaCungCapPanel);
		panelForm.add(phieuNhapPanel);
		panelForm.add(hoaDonPanel);
		panelForm.add(homePanel);
		panelForm.add(hdctPanel);
		panelForm.add(thongKePanel)
;		menuClicked(homePanel);

		
	}
	
	public static void menuClicked(JPanel panel) {
		qlNhanvienPanel.setVisible(false);
		hoiVienPanel.setVisible(false);
		sachPanel.setVisible(false);
		nhaCungCapPanel.setVisible(false);
		phieuNhapPanel.setVisible(false);
		hoaDonPanel.setVisible(false);
		homePanel.setVisible(false);
		hdctPanel.setVisible(false);
		thongKePanel.setVisible(false);
		
		panel.setVisible(true);
	}
	
	private class PanleButtonMouseAdpter extends MouseAdapter{
		JPanel panel;
		public PanleButtonMouseAdpter(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(51, 140, 140));
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground(new Color(51, 153, 153));
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground(new Color(51, 165, 165));
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground(new Color(51, 140, 140));
		}
	}
}

