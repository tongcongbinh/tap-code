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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.HoiVienController;
import Controller.NhanVienController;
import Model.NhanVien;

import javax.swing.SwingConstants;

public class QLNhanvienPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_manv;
	private JTextField tf_taikhoan;
	private JTextField tf_matkhau;
	private JTextField tf_tennv;
	private JTextField tf_diachi;
	private JTextField tf_sdt;
	private static JTable table;
	private JTextField tf_search;

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
	public QLNhanvienPanel() {
		setBorder(new LineBorder(new Color(255, 255, 255), 3));
		setBackground(new Color(51, 153, 153));
		setBounds(0, 0, 1096, 573);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã NV:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 12, 115, 40);
		add(lblNewLabel);

		JLabel lblTiKhon = new JLabel("Tài khoản:");
		lblTiKhon.setForeground(Color.WHITE);
		lblTiKhon.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblTiKhon.setBounds(10, 72, 115, 40);
		add(lblTiKhon);

		JLabel lblMtKhu = new JLabel("Mật khẩu:");
		lblMtKhu.setForeground(Color.WHITE);
		lblMtKhu.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMtKhu.setBounds(10, 132, 115, 40);
		add(lblMtKhu);

		JLabel lblTnNv = new JLabel("Tên NV:");
		lblTnNv.setForeground(Color.WHITE);
		lblTnNv.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblTnNv.setBounds(10, 192, 115, 40);
		add(lblTnNv);

		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setForeground(Color.WHITE);
		lblChcV.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblChcV.setBounds(10, 252, 115, 40);
		add(lblChcV);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setForeground(Color.WHITE);
		lblaCh.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblaCh.setBounds(10, 312, 115, 40);
		add(lblaCh);

		JLabel lblSdt = new JLabel("SDT:");
		lblSdt.setForeground(Color.WHITE);
		lblSdt.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSdt.setBounds(10, 372, 115, 40);
		add(lblSdt);

		tf_manv = new JTextField();
		tf_manv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_manv.setBounds(148, 12, 239, 40);
		add(tf_manv);
		tf_manv.setColumns(10);

		tf_taikhoan = new JTextField();
		tf_taikhoan.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_taikhoan.setColumns(10);
		tf_taikhoan.setBounds(148, 72, 239, 40);
		add(tf_taikhoan);

		tf_matkhau = new JTextField();
		tf_matkhau.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_matkhau.setColumns(10);
		tf_matkhau.setBounds(148, 132, 239, 40);
		add(tf_matkhau);

		tf_tennv = new JTextField();
		tf_tennv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_tennv.setColumns(10);
		tf_tennv.setBounds(148, 192, 239, 40);
		add(tf_tennv);

		tf_diachi = new JTextField();
		tf_diachi.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_diachi.setColumns(10);
		tf_diachi.setBounds(148, 312, 239, 40);
		add(tf_diachi);

		tf_sdt = new JTextField();
		tf_sdt.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(148, 372, 239, 40);
		add(tf_sdt);

		JComboBox cb_chucvu = new JComboBox();
		cb_chucvu.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		cb_chucvu.setModel(new DefaultComboBoxModel(new String[] { "Quản lý", "Nhân viên" }));
		cb_chucvu.setBounds(148, 252, 239, 40);
		add(cb_chucvu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 12, 689, 400);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null },
						{ null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, },
				new String[] { "M\u00E3 NV", "T\u00E0i kho\u1EA3n", "M\u1EADt kh\u1EA9u", "T\u00EAn NV",
						"Ch\u1EE9c v\u1EE5", "\u0110\u1ECBa ch\u1EC9", "SDT" }) {
			boolean[] columnEditables = new boolean[] { true, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		showData(NhanVienController.getAllNhanVien());

		tf_search = new JTextField();
		tf_search.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_search.setBounds(650, 480, 368, 40);
		add(tf_search);
		tf_search.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo tên Nhân viên:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNewLabel_1.setBounds(650, 442, 368, 40);
		add(lblNewLabel_1);
		

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					int selectedRow = table.getSelectedRow();
					tf_manv.setText(table.getValueAt(selectedRow, 0).toString());
					tf_taikhoan.setText(table.getValueAt(selectedRow, 1).toString());
					tf_matkhau.setText(table.getValueAt(selectedRow, 2).toString());
					tf_tennv.setText(table.getValueAt(selectedRow, 3).toString());
					cb_chucvu.setSelectedItem(table.getValueAt(selectedRow, 4).toString());
					tf_diachi.setText(table.getValueAt(selectedRow, 5).toString());
					tf_sdt.setText(table.getValueAt(selectedRow, 6).toString());
				}
			}
		});

		JPanel panelRefresh = new JPanel();
		panelRefresh.setBackground(new Color(51, 153, 153));
		panelRefresh.setBorder(null);
		panelRefresh.setBounds(10, 460, 150, 60);
		add(panelRefresh);
		panelRefresh.setLayout(null);
		panelRefresh.addMouseListener(new PanleButtonMouseAdpter(panelRefresh) {
			@Override
			public void mouseClicked(MouseEvent e) {
				NhanVienController nvController = new NhanVienController();
				showData(nvController.getAllNhanVien());
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
		panelAdd.setBounds(170, 460, 150, 60);
		add(panelAdd);
		panelAdd.addMouseListener(new PanleButtonMouseAdpter(panelAdd) {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String manv = tf_manv.getText().trim();        // Loại bỏ khoảng trắng thừa
		        String taikhoan = tf_taikhoan.getText().trim();
		        String matkhau = tf_matkhau.getText().trim();
		        String tennv = tf_tennv.getText().trim();
		        String chucvu = cb_chucvu.getSelectedItem().toString().trim();
		        String diachi = tf_diachi.getText().trim();
		        String sdt = tf_sdt.getText().trim();          // Loại bỏ khoảng trắng thừa

		        // Kiểm tra thông tin không được để trống
		        if (!manv.isEmpty() && !taikhoan.isEmpty() && !matkhau.isEmpty() && !tennv.isEmpty()
		                && !chucvu.isEmpty() && !diachi.isEmpty() && !sdt.isEmpty()) {

		            if (sdt.matches("0\\d{2}\\s\\d{4}\\s\\d{3}")) { 
		                NhanVien nv = new NhanVien();
		                nv.setMaNV(manv);
		                nv.setTaiKhoan(taikhoan);
		                nv.setMatKhau(matkhau);
		                nv.setTenNV(tennv);
		                nv.setChucVu(chucvu);
		                nv.setDiaChi(diachi);
		                nv.setSDT(sdt); // Không cần loại bỏ khoảng trắng, lưu theo định dạng nhập

		                NhanVienController nvController = new NhanVienController();
		                int check = nvController.Add(nv);
		                showData(nvController.getAllNhanVien());
		                if (check <= 0) {
		                    JOptionPane.showMessageDialog(null, "Mã nhân viên là duy nhất!", "Lỗi",
		                            JOptionPane.ERROR_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Thêm thành công!");
		                }
		            } else {
		                // Hiển thị lỗi nếu số điện thoại không hợp lệ
		                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ! Vui lòng nhập số theo định dạng: 097 8978 033.", "Lỗi",
		                        JOptionPane.ERROR_MESSAGE);
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
		panelUpdate.setBounds(330, 460, 150, 60);
		add(panelUpdate);
		panelUpdate.addMouseListener(new PanleButtonMouseAdpter(panelUpdate) {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String manv = tf_manv.getText().trim();       // Loại bỏ khoảng trắng thừa
		        String taikhoan = tf_taikhoan.getText().trim();
		        String matkhau = tf_matkhau.getText().trim();
		        String tennv = tf_tennv.getText().trim();
		        String chucvu = cb_chucvu.getSelectedItem().toString().trim();
		        String diachi = tf_diachi.getText().trim();
		        String sdt = tf_sdt.getText().trim();         // Loại bỏ khoảng trắng thừa

		        // Kiểm tra thông tin không được để trống
		        if (!manv.isEmpty() && !taikhoan.isEmpty() && !matkhau.isEmpty() && !tennv.isEmpty()
		                && !chucvu.isEmpty() && !diachi.isEmpty() && !sdt.isEmpty()) {

		            if (sdt.matches("0\\d{2}\\s\\d{4}\\s\\d{3}")) { 
		                NhanVien nv = new NhanVien();
		                nv.setMaNV(manv);
		                nv.setTaiKhoan(taikhoan);
		                nv.setMatKhau(matkhau);
		                nv.setTenNV(tennv);
		                nv.setChucVu(chucvu);
		                nv.setDiaChi(diachi);
		                nv.setSDT(sdt); // Giữ nguyên định dạng số điện thoại

		                NhanVienController nvController = new NhanVienController();
		                int check = nvController.Update(nv); // Cập nhật nhân viên
		                showData(nvController.getAllNhanVien());

		                if (check <= 0) {
		                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên này!", "Lỗi",
		                            JOptionPane.ERROR_MESSAGE);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Sửa thành công!");
		                }
		            } else {
		                // Hiển thị lỗi nếu số điện thoại không hợp lệ
		                JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ! Vui lòng nhập số theo định dạng: 097 8978 033.", "Lỗi",
		                        JOptionPane.ERROR_MESSAGE);
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Các thông tin không được để trống!", "Lỗi",
		                    JOptionPane.ERROR_MESSAGE);
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
		panelDelete.setBounds(490, 460, 150, 60);
		add(panelDelete);
		panelDelete.addMouseListener(new PanleButtonMouseAdpter(panelDelete) {
			@Override
			public void mouseClicked(MouseEvent e) {
				String manv = tf_manv.getText();
				String taikhoan = tf_taikhoan.getText();
				String matkhau = tf_matkhau.getText();
				String tennv = tf_tennv.getText();
				String chucvu = cb_chucvu.getSelectedItem().toString();
				String diachi = tf_diachi.getText();
				String sdt = tf_sdt.getText();

				if (!manv.equals("")) {
					NhanVien nv = new NhanVien();
					nv.setMaNV(manv);

					NhanVienController nvController = new NhanVienController();
					int check = nvController.Delete(nv);
					showData(nvController.getAllNhanVien());
					if (check <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Xóa thành công!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Mã nhân viên không được để trống!", "Lỗi",
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

		JPanel panelSearch = new JPanel();
		panelSearch.setBackground(new Color(51, 153, 153));
		panelSearch.setBounds(1023, 470, 50, 50);
		add(panelSearch);
		panelSearch.setLayout(null);
		panelSearch.addMouseListener(new PanleButtonMouseAdpter(panelSearch) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!tf_search.getText().equals("")) {
					NhanVien nv = new NhanVien();
					nv.setTenNV(tf_search.getText());
					showData(NhanVienController.findByName(nv));
					if (NhanVienController.findByName(nv).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(NhanVienController.getAllNhanVien());
						tf_search.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Thông tin tìm kiếm không được để trống!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JLabel lb_search = new JLabel("");
		lb_search.setHorizontalAlignment(SwingConstants.CENTER);
		lb_search.setBounds(0, 0, 50, 50);
		panelSearch.add(lb_search);

		lb_refresh.setIcon(new ImageIcon(img_refresh));
		lb_add.setIcon(new ImageIcon(img_add));
		lb_delete.setIcon(new ImageIcon(img_delete));
		lb_update.setIcon(new ImageIcon(img_update));
		lb_search.setIcon(new ImageIcon(img_search));

	}

	public void showData(List<NhanVien> nhanvien) {
		List<NhanVien> listnhanvien = new ArrayList<>();
		listnhanvien = nhanvien;
		DefaultTableModel tableModel;
		table.getModel();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		listnhanvien.forEach((NhanVien) -> {
			tableModel.addRow(new Object[] { NhanVien.getMaNV(), NhanVien.getTaiKhoan(), NhanVien.getMatKhau(),
					NhanVien.getTenNV(), NhanVien.getChucVu(), NhanVien.getDiaChi(), NhanVien.getSDT() });
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
