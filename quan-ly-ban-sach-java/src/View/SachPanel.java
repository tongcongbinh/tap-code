package View;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.NhanVienController;
import Controller.SachController;
import Model.NhanVien;
import Model.Sach;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SachPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_masach;
	private JTextField tf_tensach;
	private JTextField tf_tacgia;
	private JTextField tf_theloai;
	private JTextField tf_soluong;
	private JTextField tf_nxb;
	private JTextField tf_gia;
	private JTable table;

	private Image img_search = new ImageIcon(QuanLyForm.class.getResource("/pictures/search.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_refresh = new ImageIcon(QuanLyForm.class.getResource("/pictures/refresh.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_add = new ImageIcon(QuanLyForm.class.getResource("/pictures/add.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_update = new ImageIcon(QuanLyForm.class.getResource("/pictures/update.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_delete = new ImageIcon(QuanLyForm.class.getResource("/pictures/delete.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

	/**
	 * Create the panel.
	 */
	public SachPanel() {
		setBorder(new LineBorder(Color.WHITE, 3));
		setBackground(new Color(51, 153, 153));
		setBounds(0, 0, 1096, 573);
		setLayout(null);

		JLabel lblMSch = new JLabel("Mã sách:");
		lblMSch.setForeground(Color.WHITE);
		lblMSch.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMSch.setBounds(10, 12, 115, 40);
		add(lblMSch);

		JLabel lblTnSch = new JLabel("Tên sách:");
		lblTnSch.setForeground(Color.WHITE);
		lblTnSch.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblTnSch.setBounds(525, 12, 115, 40);
		add(lblTnSch);

		JLabel lblTcGi = new JLabel("Tác giả:");
		lblTcGi.setForeground(Color.WHITE);
		lblTcGi.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblTcGi.setBounds(10, 62, 115, 40);
		add(lblTcGi);

		JLabel lblThLoi = new JLabel("Thể loại:");
		lblThLoi.setForeground(Color.WHITE);
		lblThLoi.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblThLoi.setBounds(525, 60, 115, 40);
		add(lblThLoi);

		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setForeground(Color.WHITE);
		lblSLng.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSLng.setBounds(10, 112, 115, 40);
		add(lblSLng);

		JLabel lblNxb = new JLabel("NXB:");
		lblNxb.setForeground(Color.WHITE);
		lblNxb.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNxb.setBounds(525, 112, 115, 40);
		add(lblNxb);

		JLabel lblGi = new JLabel("Giá:");
		lblGi.setForeground(Color.WHITE);
		lblGi.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblGi.setBounds(525, 162, 115, 40);
		add(lblGi);

		tf_masach = new JTextField();
		tf_masach.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_masach.setColumns(10);
		tf_masach.setBounds(125, 12, 340, 40);
		add(tf_masach);

		tf_tensach = new JTextField();
		tf_tensach.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_tensach.setColumns(10);
		tf_tensach.setBounds(650, 12, 436, 40);
		add(tf_tensach);

		tf_tacgia = new JTextField();
		tf_tacgia.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_tacgia.setColumns(10);
		tf_tacgia.setBounds(125, 62, 340, 40);
		add(tf_tacgia);

		tf_theloai = new JTextField();
		tf_theloai.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_theloai.setColumns(10);
		tf_theloai.setBounds(650, 62, 436, 40);
		add(tf_theloai);

		tf_soluong = new JTextField();
		tf_soluong.setText("0");
		tf_soluong.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Chỉ được nhập số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		tf_soluong.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_soluong.setColumns(10);
		tf_soluong.setBounds(125, 112, 340, 40);
		add(tf_soluong);

		tf_nxb = new JTextField();
		tf_nxb.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_nxb.setColumns(10);
		tf_nxb.setBounds(650, 112, 436, 40);
		add(tf_nxb);

		JComboBox cb_vitri = new JComboBox();
		cb_vitri.setEditable(true);
		cb_vitri.setModel(new DefaultComboBoxModel(new String[] { "Kệ sách 1, tầng 1", "Kệ sách 2, tầng 1",
				"Kệ sách 3, tầng 1", "Kệ sách 4, tầng 1", "Kệ sách 5, tầng 1", "Kệ sách 6, tầng 1", "Kệ sách 7, tầng 1",
				"Kệ sách 1, tầng 2", "Kệ sách 2, tầng 2", "Kệ sách 3, tầng 2", "Kệ sách 4, tầng 2", "Kệ sách 5, tầng 2",
				"Kệ sách 6, tầng 2", "Kệ sách 7, tầng 2", "Kệ sách 1, tầng 3", "Kệ sách 2, tầng 3", "Kệ sách 3, tầng 3",
				"Kệ sách 4, tầng 3", "Kệ sách 5, tầng 3", "Kệ sách 6, tầng 3", "Kệ sách 7, tầng 3" }));
		cb_vitri.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		cb_vitri.setBounds(125, 162, 340, 40);
		add(cb_vitri);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 233, 1076, 219);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, { null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "T\u00E1c gi\u1EA3", "Th\u1EC3 lo\u1EA1i",
						"Nh\u00E0 xu\u1EA5t b\u1EA3n", "Gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "V\u1ECA tr\u00ED" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(175);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setRowHeight(30);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					int selectedRow = table.getSelectedRow();
					tf_masach.setText(table.getValueAt(selectedRow, 0).toString());
					tf_tensach.setText(table.getValueAt(selectedRow, 1).toString());
					tf_tacgia.setText(table.getValueAt(selectedRow, 2).toString());
					tf_theloai.setText(table.getValueAt(selectedRow, 3).toString());
					tf_nxb.setText(table.getValueAt(selectedRow, 4).toString());
					tf_gia.setText(table.getValueAt(selectedRow, 5).toString());
					tf_soluong.setText(table.getValueAt(selectedRow, 6).toString());
					cb_vitri.setSelectedItem(table.getValueAt(selectedRow, 7).toString());
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo mức giá:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNewLabel_1.setBounds(650, 463, 368, 40);
		add(lblNewLabel_1);
		showData(SachController.getAllSach());

		JPanel panelRefresh = new JPanel();
		panelRefresh.setLayout(null);
		panelRefresh.setBorder(null);
		panelRefresh.setBackground(new Color(51, 153, 153));
		panelRefresh.setBounds(10, 484, 150, 60);
		add(panelRefresh);
		panelRefresh.addMouseListener(new PanleButtonMouseAdpter(panelRefresh) {
			@Override
			public void mouseClicked(MouseEvent e) {
				SachController sachController = new SachController();
				showData(sachController.getAllSach());
			}
		});

		JLabel lb_refresh = new JLabel("Refresh");
		lb_refresh.setHorizontalAlignment(SwingConstants.CENTER);
		lb_refresh.setForeground(Color.WHITE);
		lb_refresh.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lb_refresh.setBounds(10, 11, 127, 40);
		panelRefresh.add(lb_refresh);

		JPanel panelAdd = new JPanel();
		panelAdd.setLayout(null);
		panelAdd.setBorder(null);
		panelAdd.setBackground(new Color(51, 153, 153));
		panelAdd.setBounds(170, 484, 150, 60);
		add(panelAdd);
		panelAdd.addMouseListener(new PanleButtonMouseAdpter(panelAdd) {
			@Override
			public void mouseClicked(MouseEvent e) {
				String masach = tf_masach.getText();
				String tensach = tf_tensach.getText();
				String tacgia = tf_tacgia.getText();
				String theloai = tf_theloai.getText();
				String nhaxuatban = tf_nxb.getText();
				int gia = Integer.parseInt(tf_gia.getText());
				int soluong = Integer.parseInt(tf_soluong.getText());
				String vitri = cb_vitri.getSelectedItem().toString();

				if (!masach.equals("") && !tensach.equals("") && !tacgia.equals("") && !theloai.equals("")
						&& !nhaxuatban.equals("") && gia != 0 && soluong != 0 && !vitri.equals("")) {
					Sach sach = new Sach();
					sach.setMaSach(masach);
					sach.setTenSach(tensach);
					sach.setTacGia(tacgia);
					sach.setTheLoai(theloai);
					sach.setNhaXuatBan(nhaxuatban);
					sach.setGia(gia);
					sach.setSoLuong(soluong);
					sach.setViTri(vitri);

					SachController sachController = new SachController();
					int check = sachController.Add(sach);
					showData(sachController.getAllSach());
					if (check <= 0) {
						JOptionPane.showMessageDialog(null, "Mã sách là duy nhất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Thêm thành công!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Các thông tin không được để trống!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel lb_add = new JLabel("Add");
		lb_add.setHorizontalAlignment(SwingConstants.CENTER);
		lb_add.setForeground(Color.WHITE);
		lb_add.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lb_add.setBounds(10, 11, 127, 40);
		panelAdd.add(lb_add);

		JPanel panelUpdate = new JPanel();
		panelUpdate.setLayout(null);
		panelUpdate.setBorder(null);
		panelUpdate.setBackground(new Color(51, 153, 153));
		panelUpdate.setBounds(330, 484, 150, 60);
		add(panelUpdate);
		panelUpdate.addMouseListener(new PanleButtonMouseAdpter(panelUpdate) {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String masach = tf_masach.getText();
		        String tensach = tf_tensach.getText();
		        String tacgia = tf_tacgia.getText();
		        String theloai = tf_theloai.getText();
		        String nhaxuatban = tf_nxb.getText();
		        int gia = Integer.parseInt(tf_gia.getText());
		        int soluong = Integer.parseInt(tf_soluong.getText());  // Lấy số lượng nhập
		        String vitri = cb_vitri.getSelectedItem().toString();

		        Sach sachCu = SachController.getSachByMaSach(masach);
		        int oldSoLuong = sachCu.getSoLuong();

		        // Kiểm tra nếu người dùng cố gắng thay đổi số lượng
		        if (soluong != oldSoLuong) {
		            JOptionPane.showMessageDialog(null, "Không thể thay đổi số lượng sách!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;  // Ngừng lại, không thực hiện cập nhật
		        }

		        if (!masach.equals("") && !tensach.equals("") && !tacgia.equals("") && !theloai.equals("")
		                && !nhaxuatban.equals("") && gia != 0 && !vitri.equals("")) {
		            Sach sach = new Sach();
		            sach.setMaSach(masach);
		            sach.setTenSach(tensach);
		            sach.setTacGia(tacgia);
		            sach.setTheLoai(theloai);
		            sach.setNhaXuatBan(nhaxuatban);
		            sach.setGia(gia);
		            // Giữ nguyên số lượng cũ
		            sach.setSoLuong(oldSoLuong);  // Dùng số lượng cũ từ DB hoặc đối tượng Sach
		            sach.setViTri(vitri);

		            SachController sachController = new SachController();
		            int check = sachController.Update(sach);
		            showData(sachController.getAllSach());
		            if (check <= 0) {
		                JOptionPane.showMessageDialog(null, "Không tìm thấy sách này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Sửa thành công!");
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Các thông tin không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});



		JLabel lb_update = new JLabel("Update");
		lb_update.setHorizontalAlignment(SwingConstants.CENTER);
		lb_update.setForeground(Color.WHITE);
		lb_update.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lb_update.setBounds(10, 11, 127, 40);
		panelUpdate.add(lb_update);

		JPanel panelDelete = new JPanel();
		panelDelete.setLayout(null);
		panelDelete.setBorder(null);
		panelDelete.setBackground(new Color(51, 153, 153));
		panelDelete.setBounds(490, 484, 150, 60);
		add(panelDelete);
		panelDelete.addMouseListener(new PanleButtonMouseAdpter(panelDelete) {
			@Override
			public void mouseClicked(MouseEvent e) {
				String masach = tf_masach.getText();

				if (!masach.equals("")) {
					Sach sach = new Sach();
					sach.setMaSach(masach);					

					SachController sachController = new SachController();
					int check = sachController.Delete(sach);
					showData(sachController.getAllSach());
					if (check <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sách này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Các thông tin không được để trống!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel lb_delete = new JLabel("Delete");
		lb_delete.setHorizontalAlignment(SwingConstants.CENTER);
		lb_delete.setForeground(Color.WHITE);
		lb_delete.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lb_delete.setBounds(10, 11, 127, 40);
		panelDelete.add(lb_delete);

		JComboBox cb_search = new JComboBox();
		cb_search.setModel(new DefaultComboBoxModel(new String[] { "Chọn mức giá", "Dưới 100k", "100k - 200k",
				"200k - 500k", "500k - 1 triệu", "Trên 1 triệu", "Tất cả mức giá" }));
		cb_search.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		cb_search.setBounds(650, 504, 368, 40);
		add(cb_search);

		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(null);
		panelSearch.setBackground(new Color(51, 153, 153));
		panelSearch.setBounds(1023, 494, 50, 50);
		add(panelSearch);
		panelSearch.addMouseListener(new PanleButtonMouseAdpter(panelSearch) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cb_search.getSelectedIndex() == 1) {
					Sach sach = new Sach();
					showData(SachController.findByCostIndex_1(sach));
					if (SachController.findByCostIndex_1(sach).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sách có mức giá này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(SachController.getAllSach());
					}
				} else if (cb_search.getSelectedIndex() == 2) {
					Sach sach = new Sach();
					showData(SachController.findByCostIndex_2(sach));
					if (SachController.findByCostIndex_2(sach).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sách có mức giá này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
					showData(SachController.getAllSach());

				} else if (cb_search.getSelectedIndex() == 3) {
					Sach sach = new Sach();
					showData(SachController.findByCostIndex_3(sach));
					if (SachController.findByCostIndex_3(sach).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sách có mức giá này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(SachController.getAllSach());
					}
				} else if (cb_search.getSelectedIndex() == 4) {
					Sach sach = new Sach();
					showData(SachController.findByCostIndex_4(sach));
					if (SachController.findByCostIndex_4(sach).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sách có mức giá này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(SachController.getAllSach());
					}
				} else if (cb_search.getSelectedIndex() == 5) {
					Sach sach = new Sach();
					showData(SachController.findByCostIndex_5(sach));
					if (SachController.findByCostIndex_5(sach).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy sách có mức giá này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(SachController.getAllSach());
					}
				} else if (cb_search.getSelectedIndex() == 6) {
					Sach sach = new Sach();
					showData(SachController.getAllSach());
				} else {
					JOptionPane.showMessageDialog(null, "Bạn chưa chọn mức giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		JLabel lb_search = new JLabel("");
		lb_search.setHorizontalAlignment(SwingConstants.CENTER);
		lb_search.setBounds(0, 0, 50, 50);
		panelSearch.add(lb_search);

		JLabel lblVTr = new JLabel("Vị trí:");
		lblVTr.setForeground(Color.WHITE);
		lblVTr.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblVTr.setBounds(10, 162, 115, 40);

		add(lblVTr);

		tf_gia = new JTextField();
		tf_gia.setText("0");
		tf_gia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Chỉ được nhập số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		tf_gia.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_gia.setColumns(10);
		tf_gia.setBounds(650, 162, 436, 40);
		add(tf_gia);

		lb_refresh.setIcon(new ImageIcon(img_refresh));
		lb_add.setIcon(new ImageIcon(img_add));
		lb_delete.setIcon(new ImageIcon(img_delete));
		lb_update.setIcon(new ImageIcon(img_update));
		lb_search.setIcon(new ImageIcon(img_search));

	}

	public void showData(List<Sach> sach) {
		List<Sach> listsach = new ArrayList<>();
		listsach = sach;
		DefaultTableModel tableModel;
		table.getModel();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		listsach.forEach((Sach) -> {
			tableModel.addRow(new Object[] { Sach.getMaSach(), Sach.getTenSach(), Sach.getTacGia(), Sach.getTheLoai(),
					Sach.getNhaXuatBan(), Sach.getGia(), Sach.getSoLuong(), Sach.getViTri() });
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
