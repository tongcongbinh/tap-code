package View;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.HoaDonController;
import Model.HoaDon;

public class HoaDonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tf_mahd;
	private JTextField tf_manv;
	private JTextField tf_mahv;
	private JTextField tf_masach;
	private JTextField tf_soluong;
	private JTextField tf_ngaytaohd;
	
	private HDCTPanel hdctPanel;

	private Image img_refresh = new ImageIcon(QuanLyForm.class.getResource("/pictures/refresh.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_add = new ImageIcon(QuanLyForm.class.getResource("/pictures/add.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_update = new ImageIcon(QuanLyForm.class.getResource("/pictures/update.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_delete = new ImageIcon(QuanLyForm.class.getResource("/pictures/delete.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	private Image img_hdct = new ImageIcon(QuanLyForm.class.getResource("/pictures/detail.png")).getImage()
			.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
	
	private JTable table;
	
	

	/**
	 * Create the panel.
	 */
	public HoaDonPanel() {
		setBorder(new LineBorder(Color.WHITE, 3));
		setBackground(new Color(51, 153, 153));
		setBounds(0, 0, 1096, 573);
		setLayout(null);
		
		hdctPanel = new HDCTPanel();
		
		JLabel lblMPn = new JLabel("Mã HD:");
		lblMPn.setForeground(Color.WHITE);
		lblMPn.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMPn.setBounds(10, 11, 115, 40);
		add(lblMPn);
		
		JLabel lblMNv = new JLabel("Mã NV:");
		lblMNv.setForeground(Color.WHITE);
		lblMNv.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMNv.setBounds(10, 71, 115, 40);
		add(lblMNv);
		
		JLabel lblMNcc = new JLabel("Mã HV:");
		lblMNcc.setForeground(Color.WHITE);
		lblMNcc.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMNcc.setBounds(10, 131, 115, 40);
		add(lblMNcc);
		
		JLabel lblMSch = new JLabel("Mã sách:");
		lblMSch.setForeground(Color.WHITE);
		lblMSch.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblMSch.setBounds(520, 11, 115, 40);
		add(lblMSch);
		
		JLabel lblSLng = new JLabel("Số lượng:");
		lblSLng.setForeground(Color.WHITE);
		lblSLng.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblSLng.setBounds(520, 71, 115, 40);
		add(lblSLng);
		
		JLabel lblNgyNhp = new JLabel("Ngày tạo HD:");
		lblNgyNhp.setForeground(Color.WHITE);
		lblNgyNhp.setFont(new Font("Segoe UI", Font.BOLD, 23));
		lblNgyNhp.setBounds(520, 131, 163, 40);
		add(lblNgyNhp);
		
		tf_mahd = new JTextField();
		tf_mahd.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_mahd.setColumns(10);
		tf_mahd.setBounds(148, 11, 332, 40);
		add(tf_mahd);
		
		tf_manv = new JTextField();
		tf_manv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_manv.setColumns(10);
		tf_manv.setBounds(148, 71, 332, 40);
		add(tf_manv);
		
		tf_mahv = new JTextField();
		tf_mahv.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_mahv.setColumns(10);
		tf_mahv.setBounds(148, 131, 332, 40);
		add(tf_mahv);
		
		tf_masach = new JTextField();
		tf_masach.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_masach.setColumns(10);
		tf_masach.setBounds(693, 12, 393, 40);
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
		tf_soluong.setBounds(693, 72, 393, 40);
		add(tf_soluong);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 1076, 284);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 HD", "M\u00E3 NV", "M\u00E3 HV", "M\u00E3 s\u00E1ch", "S\u1ED1 l\u01B0\u1EE3ng", "Ng\u00E0y t\u1EA1o HD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		scrollPane.setViewportView(table);
		table.setRowHeight(30);
		showData(HoaDonController.getAllHoaDon());
		
		  table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
		        public void valueChanged(ListSelectionEvent event) {
		            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
		                int selectedRow = table.getSelectedRow();
		                tf_mahd.setText(getValueOrEmpty(table, selectedRow, 0));
		                tf_manv.setText(getValueOrEmpty(table, selectedRow, 1));
		                tf_mahv.setText(getValueOrEmpty(table, selectedRow, 2));
		                tf_masach.setText(getValueOrEmpty(table, selectedRow, 3));
		                tf_soluong.setText(getValueOrEmpty(table, selectedRow, 4));
		                tf_ngaytaohd.setText(getValueOrEmpty(table, selectedRow, 5));
		            }
		        }
		    
		        public static String getValueOrEmpty(JTable table, int row, int col) {
		            Object value = table.getValueAt(row, col);
		            return (value != null) ? value.toString() : "";
		        }
		    });
		
		JPanel panelRefresh = new JPanel();
		panelRefresh.setLayout(null);
		panelRefresh.setBorder(null);
		panelRefresh.setBackground(new Color(51, 153, 153));
		panelRefresh.setBounds(10, 488, 150, 60);
		add(panelRefresh);
		panelRefresh.addMouseListener(new PanleButtonMouseAdpter(panelRefresh) {
			@Override
			public void mouseClicked(MouseEvent e) {
				HoaDonController hdController = new HoaDonController();
				showData(hdController.getAllHoaDon());
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
		panelAdd.setBounds(170, 488, 150, 60);
		add(panelAdd);
		panelAdd.addMouseListener(new PanleButtonMouseAdpter(panelAdd) {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        String mahd = tf_mahd.getText();
		        String manv = tf_manv.getText();
		        String mahv = tf_mahv.getText();
		        String masach = tf_masach.getText();
		        int soluong = Integer.parseInt(tf_soluong.getText());
		        String ngaytaohd = tf_ngaytaohd.getText();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        java.util.Date textFieldAsDate = null;
		        try {
		            textFieldAsDate = sdf.parse(ngaytaohd);
		        } catch (ParseException e1) {
		            e1.printStackTrace();
		        }
		        sdf = new SimpleDateFormat("yyyy-MM-dd");
		        java.sql.Date date = java.sql.Date.valueOf(sdf.format(textFieldAsDate));

		        // Kiểm tra các thông tin đầu vào
		        if (!mahd.equals("") && !manv.equals("") && !masach.equals("") && soluong != 0) {
		            // Kiểm tra số lượng sách trong kho
		            int currentStock = HoaDonController.getBookQuantity(masach);
		            if (currentStock < soluong) {
		                // Thông báo lỗi nếu số lượng không đủ
		                JOptionPane.showMessageDialog(null, "Số lượng sách trong kho không đủ!", "Lỗi",
		                        JOptionPane.ERROR_MESSAGE);
		                return; // Kết thúc nếu số lượng không đủ
		            }

		            // Tiếp tục tạo hóa đơn nếu đủ số lượng
		            HoaDon hd = new HoaDon();
		            hd.setMaHD(mahd);
		            hd.setMaNV(manv);
		            hd.setMaHV(mahv);
		            hd.setMaSach(masach);
		            hd.setSoLuong(soluong);
		            hd.setNgayTaoHD(date);
		            if (mahv.equals("")) {
		                hd.setMaHV(null);
		            }
		            HoaDonController hdController = new HoaDonController();
		            int check = hdController.Add(hd);
		            showData(hdController.getAllHoaDon());
		            if (check <= 0) {
		                JOptionPane.showMessageDialog(null, "Mã hóa đơn là duy nhất!", "Lỗi",
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
		panelUpdate.setBounds(330, 488, 150, 60);
		add(panelUpdate);
		panelUpdate.addMouseListener(new PanleButtonMouseAdpter(panelUpdate) {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mahd = tf_mahd.getText();
				String manv = tf_manv.getText();
				String mahv = tf_mahv.getText();
				String masach = tf_masach.getText();
				int soluong = Integer.parseInt(tf_soluong.getText());
				String ngaytaohd = tf_ngaytaohd.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date textFieldAsDate = null;
				try {
					textFieldAsDate = sdf.parse(ngaytaohd);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date date = java.sql.Date.valueOf(sdf.format(textFieldAsDate));

				if (!mahd.equals("") && !manv.equals("") && !masach.equals("") && soluong != 0) {
					HoaDon hd = new HoaDon();
					hd.setMaHD(mahd);;
					hd.setMaNV(manv);
					hd.setMaHV(mahv);
					hd.setMaSach(masach);
					hd.setSoLuong(soluong);
					hd.setNgayTaoHD(date);
					if (mahv.equals("")) {
						hd.setMaHV(null);
					}
					HoaDonController hdController = new HoaDonController();
					int check = hdController.Update(hd);
					showData(hdController.getAllHoaDon());
					if (check <= 0) {
			            JOptionPane.showMessageDialog(null, "Số lượng sách trong kho không đủ!", "Lỗi",
			                    JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thành công!");
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
		panelDelete.setBounds(490, 488, 150, 60);
		add(panelDelete);
		panelDelete.addMouseListener(new PanleButtonMouseAdpter(panelDelete) {
			@Override
			public void mouseClicked(MouseEvent e) {
				String mahd = tf_mahd.getText();

				if (!mahd.equals("")) {
					HoaDon hd = new HoaDon();
					hd.setMaHD(mahd);

					HoaDonController hdController = new HoaDonController();
					int check = hdController.Delete(hd);
					showData(hdController.getAllHoaDon());
					if (check <= 0) {
						JOptionPane.showMessageDialog(null, "Không tìm thấy phiếu nhập này!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Xoá thành công!");
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
		
		tf_ngaytaohd = new JTextField();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate nowDate = LocalDate.now();
		tf_ngaytaohd.setText(nowDate.toString());
		tf_ngaytaohd.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		tf_ngaytaohd.setColumns(10);
		tf_ngaytaohd.setBounds(693, 132, 393, 40);
		add(tf_ngaytaohd);
				
		lb_refresh.setIcon(new ImageIcon(img_refresh));
		lb_add.setIcon(new ImageIcon(img_add));
		lb_delete.setIcon(new ImageIcon(img_delete));
		lb_update.setIcon(new ImageIcon(img_update));
		
	}
	public void showData(List<HoaDon> hoadon) {
		List<HoaDon> listhoadon = new ArrayList<>();
		listhoadon = hoadon;
		DefaultTableModel tableModel;
		table.getModel();
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		listhoadon.forEach((HoaDon) -> {
			tableModel.addRow(new Object[] { HoaDon.getMaHD(), HoaDon.getMaNV(), HoaDon.getMaHV(),
					HoaDon.getMaSach(), HoaDon.getSoLuong(), HoaDon.getNgayTaoHD()});
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
