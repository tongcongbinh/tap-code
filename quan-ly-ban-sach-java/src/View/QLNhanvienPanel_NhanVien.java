package View;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.NhanVienController;
import Model.NhanVien;
import javax.swing.DefaultComboBoxModel;

public class QLNhanvienPanel_NhanVien extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_manv;
	private JTextField tf_tennv;
	private JTextField tf_diachi;
	private JTextField tf_sdt;
	private JTextField tf_search;

	private Image img_search = new ImageIcon(QuanLyForm.class.getResource("/pictures/search.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_refresh = new ImageIcon(QuanLyForm.class.getResource("/pictures/refresh.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private JTable table;

	/**
	 * Create the panel.
	 */
	public QLNhanvienPanel_NhanVien() {
		setBorder(new LineBorder(new Color(255, 255, 255), 3));
		setBackground(new Color(51, 153, 153));
		setBounds(0, 0, 1096, 573);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã NV:");
		lblNewLabel.setBounds(10, 11, 115, 40);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
		add(lblNewLabel);

		JLabel lblTiKhon = new JLabel("Tên NV:");
		lblTiKhon.setBounds(10, 101, 115, 40);
		lblTiKhon.setForeground(Color.WHITE);
		lblTiKhon.setFont(new Font("Segoe UI", Font.BOLD, 23));
		add(lblTiKhon);

		JLabel lblChcV = new JLabel("Chức vụ:");
		lblChcV.setBounds(10, 191, 115, 40);
		lblChcV.setForeground(Color.WHITE);
		lblChcV.setFont(new Font("Segoe UI", Font.BOLD, 23));
		add(lblChcV);

		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setBounds(10, 281, 115, 40);
		lblaCh.setForeground(Color.WHITE);
		lblaCh.setFont(new Font("Segoe UI", Font.BOLD, 23));
		add(lblaCh);

		JLabel lblSdt = new JLabel("SDT:");
		lblSdt.setBounds(10, 371, 115, 40);
		lblSdt.setForeground(Color.WHITE);
		lblSdt.setFont(new Font("Segoe UI", Font.BOLD, 23));
		add(lblSdt);

		tf_manv = new JTextField();
		tf_manv.setBounds(148, 11, 239, 40);
		tf_manv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_manv.setColumns(10);
		add(tf_manv);

		tf_tennv = new JTextField();
		tf_tennv.setBounds(148, 101, 239, 40);
		tf_tennv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_tennv.setColumns(10);
		add(tf_tennv);

		tf_diachi = new JTextField();
		tf_diachi.setBounds(148, 281, 239, 40);
		tf_diachi.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_diachi.setColumns(10);
		add(tf_diachi);

		tf_sdt = new JTextField();
		tf_sdt.setBounds(148, 371, 239, 40);
		tf_sdt.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_sdt.setColumns(10);
		add(tf_sdt);

		JComboBox cb_chucvu = new JComboBox();
		cb_chucvu.setModel(new DefaultComboBoxModel(new String[] {"Quản lý", "Nhân viên"}));
		cb_chucvu.setBounds(148, 191, 239, 40);
		cb_chucvu.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		add(cb_chucvu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 11, 689, 400);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "M\u00E3 NV", "T\u00EAn NV", "Ch\u1EE9c v\u1EE5", "\u0110\u1ECBa ch\u1EC9", "SDT" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		showData(NhanVienController.getAllNhanVien());

		tf_search = new JTextField();
		tf_search.setBounds(555, 485, 368, 40);
		tf_search.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_search.setColumns(10);
		add(tf_search);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					int selectedRow = table.getSelectedRow();
					tf_manv.setText(table.getValueAt(selectedRow, 0).toString());
					tf_tennv.setText(table.getValueAt(selectedRow, 1).toString());
					cb_chucvu.setSelectedItem(table.getValueAt(selectedRow, 2).toString());
					tf_diachi.setText(table.getValueAt(selectedRow, 3).toString());
					tf_sdt.setText(table.getValueAt(selectedRow, 4).toString());
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo tên Nhân viên:");
		lblNewLabel_1.setBounds(555, 447, 368, 40);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 23));
		add(lblNewLabel_1);

		JPanel panelRefresh = new JPanel();
		panelRefresh.setBounds(250, 465, 150, 60);
		panelRefresh.setLayout(null);
		panelRefresh.setBorder(null);
		panelRefresh.setBackground(new Color(51, 153, 153));
		add(panelRefresh);
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

		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(928, 475, 50, 50);
		panelSearch.setLayout(null);
		panelSearch.setBackground(new Color(51, 153, 153));
		add(panelSearch);
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
			tableModel.addRow(new Object[] { NhanVien.getMaNV(), NhanVien.getTenNV(), NhanVien.getChucVu(),
					NhanVien.getDiaChi(), NhanVien.getSDT() });
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
