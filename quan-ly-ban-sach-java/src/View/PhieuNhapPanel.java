package View;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.PhieuNhapController;
import Model.PhieuNhap;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PhieuNhapPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_mapn;
	private JTextField tf_manv;
	private JTextField tf_mancc;
	private JTextField tf_masach;
	private JTextField tf_soluong;
	private JTextField tf_giasach;
	private JTextField tf_search;
	private JTextField tf_ngaynhap;

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
	public PhieuNhapPanel() {
		setBorder(new LineBorder(Color.WHITE, 3));
		setBackground(new Color(51, 153, 153));
		setBounds(0, 0, 1096, 573);
		setLayout(null);

		JLabel lblMPn = new JLabel("Mã PN:");
		lblMPn.setForeground(Color.WHITE);
		lblMPn.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMPn.setBounds(10, 11, 115, 40);
		add(lblMPn);

		JLabel lblMNv = new JLabel("Mã NV:");
		lblMNv.setForeground(Color.WHITE);
		lblMNv.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMNv.setBounds(10, 71, 115, 40);
		add(lblMNv);

		JLabel lblMNcc = new JLabel("Mã NCC:");
		lblMNcc.setForeground(Color.WHITE);
		lblMNcc.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMNcc.setBounds(10, 131, 115, 40);
		add(lblMNcc);

		JLabel lblMSch = new JLabel("Mã sách:");
		lblMSch.setForeground(Color.WHITE);
		lblMSch.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMSch.setBounds(10, 191, 115, 40);
		add(lblMSch);

		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setForeground(Color.WHITE);
		lblSLng.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSLng.setBounds(10, 251, 115, 40);
		add(lblSLng);

		JLabel lblGiSch = new JLabel("Giá sách:");
		lblGiSch.setForeground(Color.WHITE);
		lblGiSch.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblGiSch.setBounds(10, 311, 115, 40);
		add(lblGiSch);

		JLabel lblNgyNhp = new JLabel("Ngày nhập:");
		lblNgyNhp.setForeground(Color.WHITE);
		lblNgyNhp.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNgyNhp.setBounds(10, 371, 128, 40);
		add(lblNgyNhp);

		tf_mapn = new JTextField();
		tf_mapn.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_mapn.setColumns(10);
		tf_mapn.setBounds(148, 11, 239, 40);
		add(tf_mapn);

		tf_manv = new JTextField();
		tf_manv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_manv.setColumns(10);
		tf_manv.setBounds(148, 71, 239, 40);
		add(tf_manv);

		tf_mancc = new JTextField();
		tf_mancc.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_mancc.setColumns(10);
		tf_mancc.setBounds(148, 131, 239, 40);
		add(tf_mancc);

		tf_masach = new JTextField();
		tf_masach.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_masach.setColumns(10);
		tf_masach.setBounds(148, 191, 239, 40);
		add(tf_masach);

		tf_soluong = new JTextField();
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
		tf_soluong.setText("0");
		tf_soluong.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_soluong.setColumns(10);
		tf_soluong.setBounds(148, 252, 239, 40);
		add(tf_soluong);

		tf_giasach = new JTextField();
		tf_giasach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
					JOptionPane.showMessageDialog(null, "Chỉ được nhập số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		tf_giasach.setText("0");
		tf_giasach.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_giasach.setColumns(10);
		tf_giasach.setBounds(148, 312, 239, 40);
		add(tf_giasach);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 11, 689, 400);
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
				new String[] { "M\u00E3 PN", "M\u00E3 NV", "M\u00E3 NCC", "M\u00E3 s\u00E1ch",
						"S\u1ED1 l\u01B0\u1EE3ng", "Gi\u00E1 s\u00E1ch", "Ng\u00E0y nh\u1EADp" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, true, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		showData(PhieuNhapController.getAllPhieuNhap());

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
		tf_search.setBounds(650, 479, 368, 40);
		add(tf_search);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					int selectedRow = table.getSelectedRow();
					tf_mapn.setText(table.getValueAt(selectedRow, 0).toString());
					tf_manv.setText(table.getValueAt(selectedRow, 1).toString());
					tf_mancc.setText(table.getValueAt(selectedRow, 2).toString());
					tf_masach.setText(table.getValueAt(selectedRow, 3).toString());
					tf_soluong.setText(table.getValueAt(selectedRow, 4).toString());
					tf_giasach.setText(table.getValueAt(selectedRow, 5).toString());
					tf_ngaynhap.setText(table.getValueAt(selectedRow, 6).toString());
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm theo tháng:");
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
				PhieuNhapController pnController = new PhieuNhapController();
				showData(pnController.getAllPhieuNhap());
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
				String mapn = tf_mapn.getText();
				String manv = tf_manv.getText();
				String mancc = tf_mancc.getText();
				String masach = tf_masach.getText();
				int soluong = Integer.parseInt(tf_soluong.getText());
				int giasach = Integer.parseInt(tf_giasach.getText());
				String ngaynhap = tf_ngaynhap.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date textFieldAsDate = null;
				try {
					textFieldAsDate = sdf.parse(ngaynhap);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date date = java.sql.Date.valueOf(sdf.format(textFieldAsDate));

				if (!mapn.equals("") && !manv.equals("") && !mancc.equals("") && !masach.equals("") && soluong != 0
						&& giasach != 0) {
					PhieuNhap pn = new PhieuNhap();
					pn.setMaPN(mapn);
					pn.setMaNV(manv);
					pn.setMaNCC(mancc);
					pn.setMaSach(masach);
					pn.setSoLuong(soluong);
					pn.setGiaSach(giasach);
					pn.setNgayNhap(date);

					PhieuNhapController pnController = new PhieuNhapController();
					int check = pnController.Add(pn);
					showData(pnController.getAllPhieuNhap());
					if (check <= 0) {
						JOptionPane.showMessageDialog(null, "Mã phiếu nhập là duy nhất!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
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
		panelUpdate.setBounds(330, 459, 150, 60);
		add(panelUpdate);
		panelUpdate.addMouseListener(new PanleButtonMouseAdpter(panelUpdate) {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String mapn = tf_mapn.getText();
		        String manv = tf_manv.getText();
		        String mancc = tf_mancc.getText();
		        String masach = tf_masach.getText();
		        int soluong = Integer.parseInt(tf_soluong.getText());
		        int giasach = Integer.parseInt(tf_giasach.getText());
		        String ngaynhap = tf_ngaynhap.getText();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date textFieldAsDate = null;
		        try {
		            textFieldAsDate = sdf.parse(ngaynhap);
		        } catch (ParseException e1) {
		            e1.printStackTrace();
		        }
		        sdf = new SimpleDateFormat("yyyy-MM-dd");
		        java.sql.Date date = java.sql.Date.valueOf(sdf.format(textFieldAsDate));

		        if (!mapn.equals("") && !manv.equals("") && !mancc.equals("") && !masach.equals("") && soluong != 0
		                && giasach != 0) {
		            PhieuNhap pn = new PhieuNhap();
		            pn.setMaPN(mapn);
		            pn.setMaNV(manv);
		            pn.setMaNCC(mancc);
		            pn.setMaSach(masach);
		            pn.setSoLuong(soluong);
		            pn.setGiaSach(giasach);
		            pn.setNgayNhap(date);

		            PhieuNhapController pnController = new PhieuNhapController();
		            int check = pnController.Update(pn);
		            showData(pnController.getAllPhieuNhap());
		            if (check == -1) {
		                // Chỉ hiển thị thông báo lỗi nếu người dùng cố sửa số lượng
		                JOptionPane.showMessageDialog(null, "Không thể sửa số lượng vì phiếu nhập đã được sử dụng trong hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            } else if (check <= 0) {
		                // Phiếu nhập không tồn tại
		                JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu nhập này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            } else {
		                // Các trường còn lại được sửa thành công
		                JOptionPane.showMessageDialog(null, "Sửa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		            }}
		        }});





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
		        String mapn = tf_mapn.getText();

		        if (!mapn.equals("")) {
		            PhieuNhap pn = new PhieuNhap();
		            pn.setMaPN(mapn);

		            PhieuNhapController pnController = new PhieuNhapController();
		            int check = pnController.Delete(pn);

		            if (check == -1) {
		                JOptionPane.showMessageDialog(null, "Không thể xóa phiếu nhập này vì đã có hóa đơn bán sách liên quan!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            } else if (check == 0) {
		                JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu nhập này!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            } else {
		                showData(pnController.getAllPhieuNhap());  // Cập nhật lại dữ liệu sau khi xóa
		                JOptionPane.showMessageDialog(null, "Xoá thành công!");
		            }

		        } else {
		            JOptionPane.showMessageDialog(null, "Mã phiếu nhập không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
				if (tf_search.getText().equals("1")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_1(pn));
					if (PhieuNhapController.findByMonth_1(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("2")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_2(pn));
					if (PhieuNhapController.findByMonth_2(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("3")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_3(pn));
					if (PhieuNhapController.findByMonth_3(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("4")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_4(pn));
					if (PhieuNhapController.findByMonth_4(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("5")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_5(pn));
					if (PhieuNhapController.findByMonth_5(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("6")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_6(pn));
					if (PhieuNhapController.findByMonth_6(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
					}
				} else if (tf_search.getText().equals("7")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_7(pn));
					if (PhieuNhapController.findByMonth_7(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("8")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_8(pn));
					if (PhieuNhapController.findByMonth_8(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("9")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_9(pn));
					if (PhieuNhapController.findByMonth_9(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("10")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_10(pn));
					if (PhieuNhapController.findByMonth_10(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("11")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_11(pn));
					if (PhieuNhapController.findByMonth_11(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
						tf_search.setText("");
					}
				} else if (tf_search.getText().equals("12")) {
					PhieuNhap pn = new PhieuNhap();
					showData(PhieuNhapController.findByMonth_12(pn));
					if (PhieuNhapController.findByMonth_12(pn).size() <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy thông tin tháng này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						showData(PhieuNhapController.getAllPhieuNhap());
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
		lb_search.setHorizontalAlignment(SwingConstants.CENTER);
		lb_search.setBounds(0, 0, 50, 50);
		panelSearch.add(lb_search);

		tf_ngaynhap = new JTextField();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate nowDate = LocalDate.now();
		tf_ngaynhap.setText(nowDate.toString());
		tf_ngaynhap.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_ngaynhap.setColumns(10);
		tf_ngaynhap.setBounds(148, 371, 239, 40);
		add(tf_ngaynhap);

		lb_refresh.setIcon(new ImageIcon(img_refresh));
		lb_add.setIcon(new ImageIcon(img_add));
		lb_delete.setIcon(new ImageIcon(img_delete));
		lb_update.setIcon(new ImageIcon(img_update));
		lb_search.setIcon(new ImageIcon(img_search));
		
	}

	public void showData(List<PhieuNhap> phieunhap) {
		List<PhieuNhap> listphieunhap = new ArrayList<>();
		listphieunhap = phieunhap;
		DefaultTableModel tableModel;
		table.getModel();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		listphieunhap.forEach((PhieuNhap) -> {
			tableModel.addRow(new Object[] { PhieuNhap.getMaPN(), PhieuNhap.getMaNV(), PhieuNhap.getMaNCC(),
					PhieuNhap.getMaSach(), PhieuNhap.getSoLuong(), PhieuNhap.getGiaSach(), PhieuNhap.getNgayNhap() });
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
