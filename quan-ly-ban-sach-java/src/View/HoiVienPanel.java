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
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.HoiVienController;
import Model.HoiVien;

import javax.swing.JTable;

public class HoiVienPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_mahv;
	private JTextField tf_tenhv;
	private JTextField tf_diachi;
	private JTextField tf_sdt;
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
	private JTable table;

	/**
	 * Create the panel.
	 */
	public HoiVienPanel() {
		setBorder(new LineBorder(new Color(255, 255, 255), 3));
		setBackground(new Color(51, 153, 153));
		setBounds(0, 0, 1096, 573);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã HV:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNewLabel.setBounds(10, 12, 115, 40);
		add(lblNewLabel);

		JLabel lblTnNv = new JLabel("Tên HV:");
		lblTnNv.setForeground(Color.WHITE);
		lblTnNv.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblTnNv.setBounds(10, 112, 115, 40);
		add(lblTnNv);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setForeground(Color.WHITE);
		lblaCh.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblaCh.setBounds(10, 212, 115, 40);
		add(lblaCh);

		JLabel lblSdt = new JLabel("SDT:");
		lblSdt.setForeground(Color.WHITE);
		lblSdt.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSdt.setBounds(10, 312, 115, 40);
		add(lblSdt);

		tf_mahv = new JTextField();
		tf_mahv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_mahv.setColumns(10);
		tf_mahv.setBounds(10, 62, 299, 40);
		add(tf_mahv);

		tf_tenhv = new JTextField();
		tf_tenhv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_tenhv.setColumns(10);
		tf_tenhv.setBounds(10, 162, 299, 40);
		add(tf_tenhv);

		tf_diachi = new JTextField();
		tf_diachi.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_diachi.setColumns(10);
		tf_diachi.setBounds(10, 262, 299, 40);
		add(tf_diachi);

		tf_sdt = new JTextField();
		tf_sdt.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_sdt.setColumns(10);
		tf_sdt.setBounds(10, 362, 299, 40);
		add(tf_sdt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(319, 11, 767, 391);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, },
				new String[] { "M\u00E3 HV", "T\u00EAn HV", "\u0110\u1ECBa ch\u1EC9", "SDT" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		showData(HoiVienController.getAllHoiVien());

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					int selectedRow = table.getSelectedRow();
					tf_mahv.setText(table.getValueAt(selectedRow, 0).toString());
					tf_tenhv.setText(table.getValueAt(selectedRow, 1).toString());
					tf_diachi.setText(table.getValueAt(selectedRow, 2).toString());
					tf_sdt.setText(table.getValueAt(selectedRow, 3).toString());
				}
			}
		});

		tf_search = new JTextField();
		tf_search.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_search.setColumns(10);
		tf_search.setBounds(650, 479, 368, 40);
		add(tf_search);

		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo tên Hội viên:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNewLabel_1.setBounds(650, 441, 368, 40);
		add(lblNewLabel_1);

		JPanel panelRefresh = new JPanel();
		panelRefresh.setLayout(null);
		panelRefresh.setBorder(null);
		panelRefresh.setBackground(new Color(51, 153, 153));
		panelRefresh.setBounds(10, 459, 150, 60);
		add(panelRefresh);
		panelRefresh.addMouseListener(new PanleButtonMouseAdpter(panelRefresh) {
			@Override
			public void mouseClicked(MouseEvent e) {
				HoiVienController hvController = new HoiVienController();
				showData(hvController.getAllHoiVien());
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
		panelAdd.setBounds(170, 459, 150, 60);
		add(panelAdd);
		panelAdd.addMouseListener(new PanleButtonMouseAdpter(panelAdd) {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String mahv = tf_mahv.getText().trim();      // Loại bỏ khoảng trắng thừa
		        String tenhv = tf_tenhv.getText().trim();
		        String diachi = tf_diachi.getText().trim();
		        String sdt = tf_sdt.getText().trim();        // Loại bỏ khoảng trắng thừa

		        // Kiểm tra thông tin không được để trống
		        if (!mahv.isEmpty() && !tenhv.isEmpty() && !diachi.isEmpty() && !sdt.isEmpty()) {

		            if (sdt.matches("0\\d{2}\\s\\d{4}\\s\\d{3}")) { 
		                HoiVien hv = new HoiVien();
		                hv.setMaHV(mahv);
		                hv.setTenHV(tenhv);
		                hv.setDiaChi(diachi);
		                hv.setSDT(sdt); // Lưu số điện thoại đúng định dạng

		                HoiVienController hvController = new HoiVienController();
		                int check = hvController.Add(hv);
		                showData(hvController.getAllHoiVien());
		                
		                if (check <= 0) {
		                    JOptionPane.showMessageDialog(null, "Mã hội viên là duy nhất!", "Lỗi",
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
		panelUpdate.setBounds(330, 459, 150, 60);
		add(panelUpdate);
		panelUpdate.addMouseListener(new PanleButtonMouseAdpter(panelUpdate) {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String mahv = tf_mahv.getText().trim();      // Loại bỏ khoảng trắng thừa
		        String tenhv = tf_tenhv.getText().trim();
		        String diachi = tf_diachi.getText().trim();
		        String sdt = tf_sdt.getText().trim();        // Loại bỏ khoảng trắng thừa

		        // Kiểm tra thông tin không được để trống
		        if (!mahv.isEmpty() && !tenhv.isEmpty() && !diachi.isEmpty() && !sdt.isEmpty()) {

		            if (sdt.matches("0\\d{2}\\s\\d{4}\\s\\d{3}")) { 
		                HoiVien hv = new HoiVien();
		                hv.setMaHV(mahv);
		                hv.setTenHV(tenhv);
		                hv.setDiaChi(diachi);
		                hv.setSDT(sdt); // Giữ nguyên định dạng số điện thoại

		                HoiVienController hvController = new HoiVienController();
		                int check = hvController.Update(hv); // Cập nhật hội viên
		                showData(hvController.getAllHoiVien());

		                if (check <= 0) {
		                    JOptionPane.showMessageDialog(null, "Không tìm thấy hội viên này!", "Lỗi",
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
		panelDelete.setBounds(490, 459, 150, 60);
		add(panelDelete);
		panelDelete.addMouseListener(new PanleButtonMouseAdpter(panelDelete) {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mahv = tf_mahv.getText();
				String tenhv = tf_tenhv.getText();
				String diachi = tf_diachi.getText();
				String sdt = tf_sdt.getText();

				if (!mahv.equals("")) {
					HoiVien hv = new HoiVien();
					hv.setMaHV(mahv);

					HoiVienController hvController = new HoiVienController();
					int check = hvController.Delete(hv);
					showData(hvController.getAllHoiVien());
					if (check <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy hội viên này!", "Lỗi",
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

		JPanel panelSearch = new JPanel();
		panelSearch.setLayout(null);
		panelSearch.setBackground(new Color(51, 153, 153));
		panelSearch.setBounds(1023, 469, 50, 50);
		add(panelSearch);
		panelSearch.addMouseListener(new PanleButtonMouseAdpter(panelSearch) {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!tf_search.getText().equals("")) {
					HoiVien hv = new HoiVien();
					hv.setTenHV(tf_search.getText());
					showData(HoiVienController.findByName(hv));
					if (HoiVienController.findByName(hv).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy hội viên này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(HoiVienController.getAllHoiVien());
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

	public void showData(List<HoiVien> hoivien) {
		List<HoiVien> listhoivien = new ArrayList<>();
		listhoivien = hoivien;
		DefaultTableModel tableModel;
		table.getModel();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		listhoivien.forEach((HoiVien) -> {
			tableModel.addRow(
					new Object[] { HoiVien.getMaHV(), HoiVien.getTenHV(), HoiVien.getDiaChi(), HoiVien.getSDT() });
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
