package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.LoginController;
import Model.NhanVien;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.lang.ModuleLayer.Controller;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginForm extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsername;
	private Image img_logo = new ImageIcon(LoginForm.class.getResource("/pictures/book.png")).getImage()
			.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private Image img_username = new ImageIcon(LoginForm.class.getResource("/pictures/user.png")).getImage()
			.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private Image img_password = new ImageIcon(LoginForm.class.getResource("/pictures/key.png")).getImage()
			.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	private JPasswordField txtPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 153));
		contentPane.setBorder(new LineBorder(new Color(0, 128, 128), 2));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(122, 148, 383, 57);
		contentPane.add(panel);
		panel.setLayout(null);

		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtUsername.getText().equals("Username")) {
					txtUsername.setText("");
				} else {
					txtUsername.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsername.getText().equals("")) {
					txtUsername.setText("Username");
				}
			}
		});
		txtUsername.setBorder(null);
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		txtUsername.setBounds(73, 11, 300, 35);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lb_username = new JLabel("");
		lb_username.setBounds(10, 11, 35, 35);
		panel.add(lb_username);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(122, 248, 383, 57);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lb_password = new JLabel("");
		lb_password.setBounds(10, 11, 35, 35);
		panel_1.add(lb_password);

		JLabel lb_close = new JLabel("X");
		lb_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình?", "Thông báo",
						JOptionPane.YES_NO_OPTION) == 0) {
					LoginForm.this.dispose();
				}
			}
		});
		lb_close.setForeground(Color.RED);
		lb_close.setFont(new Font("Maiandra GD", Font.BOLD, 34));
		lb_close.setBounds(560, 11, 30, 30);
		contentPane.add(lb_close);

		JLabel lb_logo_icon = new JLabel("Nhà sách Trí Tuệ");
		lb_logo_icon.setForeground(Color.WHITE);
		lb_logo_icon.setFont(new Font("Segoe UI", Font.BOLD, 47));
		lb_logo_icon.setBounds(50, 11, 500, 126);
		contentPane.add(lb_logo_icon);
		lb_logo_icon.setIcon(new ImageIcon(img_logo));
		lb_username.setIcon(new ImageIcon(img_username));
		lb_password.setIcon(new ImageIcon(img_password));

		JCheckBox cb_showpass = new JCheckBox("Show password");
		cb_showpass.setForeground(Color.WHITE);
		cb_showpass.setBackground(new Color(51, 153, 153));
		cb_showpass.setSelected(false);
		cb_showpass.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		cb_showpass.setBounds(122, 325, 225, 48);
		contentPane.add(cb_showpass);

		txtPassword = new JPasswordField();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPassword.getText().equals("Password")) {
					if (cb_showpass.isSelected() != true) {
						txtPassword.setEchoChar('●');
					}
					txtPassword.setText("");
				} else {
					txtPassword.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPassword.getText().equals("")) {
					txtPassword.setEchoChar((char) 0);
					txtPassword.setText("Password");
				}
			}
		});
		txtPassword.setText("Password");
		txtPassword.setEchoChar((char) 0);
		txtPassword.setBorder(null);
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		txtPassword.setBounds(73, 11, 300, 35);
		panel_1.add(txtPassword);

		cb_showpass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cb_showpass.isSelected() == true && !txtPassword.getText().equals("")
						&& !txtPassword.getText().equals("Password")) {
					txtPassword.setEchoChar((char) 0);
				} else if (cb_showpass.isSelected() == false && !txtPassword.getText().equals("")
						&& !txtPassword.getText().equals("Password")) {
					txtPassword.setEchoChar('●');
				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.WHITE);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				if (!username.equals("") && !password.equals("")) {
					LoginController loginController = new LoginController();
					NhanVien nv = new NhanVien();
					nv.setTaiKhoan(username);
					nv.setMatKhau(password);

					boolean check = loginController.login(nv);
					if (check == true) {
						LoginForm.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Username hoặc Password không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Username và Password không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_2.setBackground(new Color(95, 158, 160));
		panel_2.setBounds(122, 395, 383, 57);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lb_login = new JLabel("Login");
		lb_login.setForeground(Color.WHITE);
		lb_login.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lb_login.setBounds(161, 11, 63, 35);
		panel_2.add(lb_login);

	}
	private class PanleButtonMouseAdpter extends MouseAdapter{
		JPanel panel;
		public PanleButtonMouseAdpter(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground(new Color(51, 160, 160));
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
