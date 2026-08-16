package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.HDCTController;
import Controller.ThongKeController;
import Model.HDCT;
import Model.Sach;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;

public class ThongKePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField tf_doanhthu;
	/**
	 * Create the panel.
	 */
	public ThongKePanel() {
		setBackground(new Color(51, 153, 153));
		setBorder(new LineBorder(Color.WHITE, 3));
		setBounds(0, 0, 1096, 573);
		setLayout(null);
		
		tf_doanhthu = new JTextField();
		tf_doanhthu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		tf_doanhthu.setBounds(936, 132, 150, 56);
		add(tf_doanhthu);
		tf_doanhthu.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(153, 204, 255), 3));
		panel.setBackground(new Color(51, 153, 153));
		panel.setBounds(10, 199, 356, 324);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 336, 369);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "Gi\u00E1"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(153, 204, 255), 3));
		panel_1.setBackground(new Color(51, 153, 153));
		panel_1.setBounds(376, 132, 356, 391);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 336, 369);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "Gi"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(175);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(20);
		table_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(153, 204, 255), 3));
		panel_2.setBackground(new Color(51, 153, 153));
		panel_2.setBounds(742, 199, 344, 324);
		add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 324, 302);
		panel_2.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"M\u00E3 HD", "T\u00EAn NV", "Ng\u00E0y t\u1EA1o HD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(15);
		table_2.getColumnModel().getColumn(1).setResizable(false);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(30);
		table_2.getColumnModel().getColumn(2).setResizable(false);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane_2.setViewportView(table_2);
		
		table.setRowHeight(50);
		table_1.setRowHeight(50);
		table_2.setRowHeight(50);
		
		JLabel lblNewLabel = new JLabel("SÁCH BÁN CHẠY");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNewLabel.setBounds(80, 38, 208, 72);
		add(lblNewLabel);
		
		JLabel lblSchBnChy = new JLabel("SÁCH TỒN KHO");
		lblSchBnChy.setForeground(Color.WHITE);
		lblSchBnChy.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchBnChy.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSchBnChy.setBounds(376, 38, 356, 72);
		add(lblSchBnChy);
		
		JLabel lblSchBnChy_1 = new JLabel("DOANH THU THÁNG");
		lblSchBnChy_1.setForeground(Color.WHITE);
		lblSchBnChy_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchBnChy_1.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSchBnChy_1.setBounds(750, 38, 346, 72);
		add(lblSchBnChy_1);
		
		lblSchBnChy.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Khi click vào lblSchBnChy, lấy danh sách sách bán chạy và hiển thị trên bảng
		        List<Sach> sachTonKho = ThongKeController.getSachTonKho(); // Lấy dữ liệu sách bán chạy
		        showData1(sachTonKho); // Hiển thị dữ liệu vào bảng
		    }
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if (comboBox.getSelectedIndex() == 1) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_1(hdct));
					if (HDCTController.findByMonth_1(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast1());
				} else if (comboBox.getSelectedIndex() == 2) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_2(hdct));
					if (HDCTController.findByMonth_2(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast2());
				} else if (comboBox.getSelectedIndex() == 3) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_3(hdct));
					if (HDCTController.findByMonth_3(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast3());
				} else if (comboBox.getSelectedIndex() == 4) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_4(hdct));
					if (HDCTController.findByMonth_4(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast4());
				} else if (comboBox.getSelectedIndex() == 5) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_5(hdct));
					if (HDCTController.findByMonth_5(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast5());
				} else if (comboBox.getSelectedIndex() == 6) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_6(hdct));
					if (HDCTController.findByMonth_6(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast6());
				} else if (comboBox.getSelectedIndex() == 7) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_7(hdct));
					if (HDCTController.findByMonth_7(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast7());
				} else if (comboBox.getSelectedIndex() == 8) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_8(hdct));
					if (HDCTController.findByMonth_8(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast8());
				} else if (comboBox.getSelectedIndex() == 9) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_9(hdct));
					if (HDCTController.findByMonth_9(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast9());
				} else if (comboBox.getSelectedIndex() == 10) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_10(hdct));
					if (HDCTController.findByMonth_10(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast10());
				} else if (comboBox.getSelectedIndex() == 11) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_11(hdct));
					if (HDCTController.findByMonth_11(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast11());
				} else if (comboBox.getSelectedIndex() == 12) {
					HDCT hdct = new HDCT();
					showData2(HDCTController.findByMonth_12(hdct));
					if (HDCTController.findByMonth_12(hdct).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData2(HDCTController.getAllHoaDon());
						comboBox.setSelectedIndex(0);
					}
					tf_doanhthu.setText(ThongKeController.findCast12());
				} else if (comboBox.getSelectedIndex() == 0) {
					
				}
			}
		});
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Lấy giá trị tháng được chọn từ JComboBox
		        int selectedIndex = comboBox_1.getSelectedIndex();
		        
		        if (selectedIndex == 0) {
		            // Nếu chọn "Chọn tháng" thì không làm gì cả
		            return;
		        }

		        // Lấy tháng từ selectedIndex (Tháng 1 có chỉ số là 1, Tháng 2 có chỉ số là 2...)
		        int selectedMonth = selectedIndex; 

		        // Gọi phương thức để lấy danh sách sách bán chạy theo tháng đã chọn
		        List<Sach> sachBanChay = ThongKeController.getSachBanChay(selectedMonth);

		        // Hiển thị kết quả lên bảng
		        showData(sachBanChay);
		    }
		});
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Chọn tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		comboBox.setBounds(742, 130, 184, 56);
		add(comboBox);
		
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Chọn tháng", "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
		comboBox_1.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		comboBox_1.setBounds(90, 130, 198, 56);
		add(comboBox_1);
	}
	
	public void showData(List<Sach> sach) {
	    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	    tableModel.setRowCount(0); // Xóa tất cả các hàng hiện tại

	    // Thêm dữ liệu vào bảng
	    for (Sach s : sach) {
	        tableModel.addRow(new Object[] { s.getMaSach(), s.getTenSach(), s.getGia() });
	    }
	}
	public void showData1(List<Sach> sach) {
		List<Sach> listsach = new ArrayList<>();
		listsach = sach;
		DefaultTableModel tableModel;
		table_1.getModel();
		tableModel = (DefaultTableModel) table_1.getModel();
		tableModel.setRowCount(0);
		listsach.forEach((Sach) -> {
			tableModel.addRow(new Object[] { Sach.getMaSach(), Sach.getTenSach(), Sach.getGia() });
		});
	}
	
	public void showData2(List<HDCT> hdct) {
		List<HDCT> listhoadon = new ArrayList<>();
		listhoadon = hdct;
		DefaultTableModel tableModel;
		table_2.getModel();
		tableModel = (DefaultTableModel) table_2.getModel();
		tableModel.setRowCount(0);
		listhoadon.forEach((HDCT) -> {
			tableModel.addRow(new Object[] { HDCT.getMaHD(), HDCT.getTenNV(), HDCT.getNgayTaoHD() });
		});

	}
}
