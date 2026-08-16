package View;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.HDCTController;
import Model.HDCT;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class HDCTPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private HoaDonPanel hdPanel;
	private JTextField tf_search;

	private Image img_search = new ImageIcon(QuanLyForm.class.getResource("/pictures/search.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_refresh = new ImageIcon(QuanLyForm.class.getResource("/pictures/refresh.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

	/**
	 * Create the panel.
	 */
	public HDCTPanel() {
		setBackground(new Color(51, 153, 153));
		setBorder(new LineBorder(Color.WHITE, 3));
		setBounds(0, 0, 1096, 573);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1076, 439);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, { null, null, null, null, null, null },
						{ null, null, null, null, null, null }, },
				new String[] { "M\u00E3 HD", "T\u00EAn NV", "T\u00EAn s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng",
						"T\u1ED5ng ti\u1EC1n", "Ng\u00E0y t\u1EA1o HD" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(175);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setResizable(false);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);

		tf_search = new JTextField();
		tf_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Chỉ được nhập số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		tf_search.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_search.setColumns(10);
		tf_search.setBounds(584, 509, 368, 40);
		add(tf_search);

		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo tháng:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNewLabel_1.setBounds(586, 461, 368, 40);
		add(lblNewLabel_1);
		showData(HDCTController.getAllHoaDon());

		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(null);
		panelSearch.setBackground(new Color(51, 153, 153));
		panelSearch.setBounds(962, 499, 50, 50);
		add(panelSearch);
		panelSearch.addMouseListener(new PanleButtonMouseAdpter(panelSearch) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tf_search.getText().equals("1")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_1(hdct));
					if (HDCTController.findByMonth_1(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("2")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_2(hdct));
					if (HDCTController.findByMonth_2(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("3")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_3(hdct));
					if (HDCTController.findByMonth_3(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("4")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_4(hdct));
					if (HDCTController.findByMonth_4(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("5")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_5(hdct));
					if (HDCTController.findByMonth_5(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("6")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_6(hdct));
					if (HDCTController.findByMonth_6(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("7")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_7(hdct));
					if (HDCTController.findByMonth_7(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("8")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_8(hdct));
					if (HDCTController.findByMonth_8(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("9")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_9(hdct));
					if (HDCTController.findByMonth_9(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("10")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_10(hdct));
					if (HDCTController.findByMonth_10(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("11")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_11(hdct));
					if (HDCTController.findByMonth_11(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("12")) {
					HDCT hdct = new HDCT();
					showData(HDCTController.findByMonth_12(hdct));
					if (HDCTController.findByMonth_12(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HDCTController.getAllHoaDon());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn tháng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Tháng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					tf_search.setText("");
				}
			}
		});

		JLabel lb_search = new JLabel("");
		lb_search.setBounds(0, 0, 50, 50);
		panelSearch.add(lb_search);
		lb_search.setHorizontalAlignment(SwingConstants.CENTER);
		lb_search.setIcon(new ImageIcon(img_search));
		
		JPanel panelRefresh = new JPanel();
		panelRefresh.setLayout(null);
		panelRefresh.setBorder(null);
		panelRefresh.setBackground(new Color(51, 153, 153));
		panelRefresh.setBounds(170, 488, 150, 60);
		add(panelRefresh);
		panelRefresh.addMouseListener(new PanleButtonMouseAdpter(panelRefresh) {
			@Override
			public void mouseClicked(MouseEvent e) {
				HDCTController hdctController = new HDCTController();
				showData(hdctController.getAllHoaDon());
			}
		});
		
		JLabel lb_refresh = new JLabel("Refresh");
		lb_refresh.setHorizontalAlignment(SwingConstants.CENTER);
		lb_refresh.setForeground(Color.WHITE);
		lb_refresh.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lb_refresh.setBounds(10, 11, 127, 40);
		panelRefresh.add(lb_refresh);
		lb_refresh.setIcon(new ImageIcon(img_refresh));
	}

	public void showData(List<HDCT> hdct) {
		List<HDCT> listhoadon = new ArrayList<>();
		listhoadon = hdct;
		DefaultTableModel tableModel;
		table.getModel();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		listhoadon.forEach((HDCT) -> {
			tableModel.addRow(new Object[] { HDCT.getMaHD(), HDCT.getTenNV(), HDCT.getTenSach(), HDCT.getSoLuong(),
					HDCT.getTongTien(), HDCT.getNgayTaoHD() });
		});

	}

	private class PanleButtonMouseAdpter extends MouseAdapter {
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
