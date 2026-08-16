package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.PhieuNhap;


public class PhieuNhapController {
	private boolean quantityUnchanged;
	public static List<PhieuNhap> getAllPhieuNhap() {

		List<PhieuNhap> phieunhapList = new ArrayList<PhieuNhap>();
		String sql = "select * from phieunhap";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap phieunhap = new PhieuNhap();

				phieunhap.setMaPN(rs.getString("mapn"));
				phieunhap.setMaNV(rs.getString("manv"));
				phieunhap.setMaNCC(rs.getString("mancc"));
				phieunhap.setMaSach(rs.getString("masach"));
				phieunhap.setSoLuong(rs.getInt("soluong"));
				phieunhap.setGiaSach(rs.getInt("giasach"));
				phieunhap.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapList.add(phieunhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapList;
	}

	public static int Add(PhieuNhap pn) {
	    java.sql.Connection conn = Connection.getConnection();
	    int action = 0;

	    String sqlInsertPhieuNhap = "INSERT INTO phieunhap (mapn, manv, mancc, masach, soluong, giasach, ngaynhap) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    String sqlUpdateBookQuantity = "UPDATE sach SET soluong = soluong + ? WHERE masach = ?";

	    try {
	        // Bắt đầu giao dịch
	        conn.setAutoCommit(false);

	        // Thêm phiếu nhập vào bảng phieunhap
	        PreparedStatement psInsert = conn.prepareStatement(sqlInsertPhieuNhap);
	        psInsert.setString(1, pn.getMaPN());
	        psInsert.setString(2, pn.getMaNV());
	        psInsert.setString(3, pn.getMaNCC());
	        psInsert.setString(4, pn.getMaSach());
	        psInsert.setInt(5, pn.getSoLuong());
	        psInsert.setInt(6, pn.getGiaSach());
	        psInsert.setDate(7, pn.getNgayNhap());

	        action = psInsert.executeUpdate();

	        // Cập nhật số lượng sách trong bảng sach
	        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdateBookQuantity);
	        psUpdate.setInt(1, pn.getSoLuong());
	        psUpdate.setString(2, pn.getMaSach());

	        psUpdate.executeUpdate();

	        // Xác nhận giao dịch
	        conn.commit();

	    } catch (Exception e) {
	        // Nếu có lỗi, hủy giao dịch
	        try {
	            conn.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        e.printStackTrace();
	    } finally {
	        // Đảm bảo đặt lại chế độ tự động cam kết
	        try {
	            conn.setAutoCommit(true);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return action;
	}

	
	public int Update(PhieuNhap pn) {
	    java.sql.Connection conn = Connection.getConnection();
	    int action = 0;
	    boolean hasInvoice = false;

	    try {
	        // Lấy số lượng hiện tại từ phiếu nhập
	        String sqlGetOldQuantity = "SELECT soluong FROM phieunhap WHERE mapn = ?";
	        PreparedStatement psGetOldQuantity = conn.prepareStatement(sqlGetOldQuantity);
	        psGetOldQuantity.setString(1, pn.getMaPN());
	        ResultSet rs = psGetOldQuantity.executeQuery();

	        int oldQuantity = 0;
	        if (rs.next()) {
	            oldQuantity = rs.getInt("soluong");
	        }

	        // Kiểm tra xem phiếu nhập này đã được sử dụng trong hóa đơn chưa
	        String sqlCheckInvoice = "SELECT COUNT(*) AS count FROM hoadon WHERE masach = ?";
	        PreparedStatement psCheckInvoice = conn.prepareStatement(sqlCheckInvoice);
	        psCheckInvoice.setString(1, pn.getMaSach());
	        ResultSet rsCheckInvoice = psCheckInvoice.executeQuery();

	        if (rsCheckInvoice.next() && rsCheckInvoice.getInt("count") > 0) {
	            hasInvoice = true;
	        }

	        // Nếu đã được sử dụng trong hóa đơn và số lượng thay đổi, không cho phép sửa
	        if (hasInvoice && pn.getSoLuong() != oldQuantity) {
	            return -1; // Lỗi vì không thể sửa số lượng
	        }

	        // Tiến hành cập nhật phiếu nhập
	        String sqlUpdate = "UPDATE phieunhap SET manv = ?, mancc = ?, masach = ?, soluong = ?, giasach = ?, ngaynhap = ? WHERE mapn = ?";
	        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);

	        psUpdate.setString(1, pn.getMaNV());
	        psUpdate.setString(2, pn.getMaNCC());
	        psUpdate.setString(3, pn.getMaSach());
	        psUpdate.setInt(4, pn.getSoLuong());
	        psUpdate.setInt(5, pn.getGiaSach());
	        psUpdate.setDate(6, pn.getNgayNhap());
	        psUpdate.setString(7, pn.getMaPN());

	        action = psUpdate.executeUpdate();

	        // Nếu số lượng thay đổi, cập nhật lại số lượng tồn kho trong bảng sách
	        if (pn.getSoLuong() != oldQuantity) {
	            int difference = pn.getSoLuong() - oldQuantity;
	            String sqlUpdateStock = "UPDATE sach SET soluong = soluong + ? WHERE masach = ?";
	            PreparedStatement psUpdateStock = conn.prepareStatement(sqlUpdateStock);
	            psUpdateStock.setInt(1, difference);
	            psUpdateStock.setString(2, pn.getMaSach());

	            psUpdateStock.executeUpdate();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return action;
	}



	public static int Delete(PhieuNhap pn) {
	    java.sql.Connection conn = Connection.getConnection();
	    int action = 0;

	    try {
	        // Lấy thông tin mã sách và số lượng sách từ phiếu nhập trước khi xóa
	        String sqlGetOldInfo = "SELECT masach, soluong FROM phieunhap WHERE mapn = ?";
	        PreparedStatement psGetOldInfo = conn.prepareStatement(sqlGetOldInfo);
	        psGetOldInfo.setString(1, pn.getMaPN());
	        ResultSet rs = psGetOldInfo.executeQuery();

	        int oldQuantity = 0;
	        String maSach = null;
	        if (rs.next()) {
	            oldQuantity = rs.getInt("soluong");
	            maSach = rs.getString("masach");
	        }

	        // Kiểm tra xem sách của phiếu nhập này có nằm trong bất kỳ hóa đơn nào không
	        if (maSach != null) {
	            String sqlCheckInvoice = "SELECT COUNT(*) AS count FROM hoadon WHERE masach = ?";
	            PreparedStatement psCheckInvoice = conn.prepareStatement(sqlCheckInvoice);
	            psCheckInvoice.setString(1, maSach);
	            ResultSet rsCheckInvoice = psCheckInvoice.executeQuery();

	            if (rsCheckInvoice.next() && rsCheckInvoice.getInt("count") > 0) {
	                // Nếu có hóa đơn chứa sách của phiếu nhập này, không cho phép xóa
	                return -1; // Trả về -1 để báo rằng không thể xóa vì có hóa đơn
	            }
	        }

	        // Xóa phiếu nhập
	        String sql = "DELETE FROM phieunhap WHERE mapn = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, pn.getMaPN());
	        action = ps.executeUpdate();

	        // Điều chỉnh số lượng sách trong kho nếu phiếu nhập đã được xóa thành công
	        if (action > 0 && maSach != null) {
	            String sqlUpdateBookQuantity = "UPDATE sach SET soluong = soluong - ? WHERE masach = ?";
	            PreparedStatement psUpdateBook = conn.prepareStatement(sqlUpdateBookQuantity);
	            psUpdateBook.setInt(1, oldQuantity);
	            psUpdateBook.setString(2, maSach);
	            psUpdateBook.executeUpdate();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return action;
	}


	
	public static List<PhieuNhap> findByMonth_1(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 1";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_2(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 2";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_3(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 3";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_4(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 4";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_5(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 5";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_6(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 6";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_7(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 7";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_8(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 8";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_9(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 9";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_10(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 10";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_11(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 11";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
	public static List<PhieuNhap> findByMonth_12(PhieuNhap phieunhap) {

		List<PhieuNhap> phieunhapListByMonth = new ArrayList<PhieuNhap>();
		String sql = "SELECT * FROM phieunhap WHERE month(NgayNhap) = 12";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				PhieuNhap pn = new PhieuNhap();

				pn.setMaPN(rs.getString("mapn"));
				pn.setMaNV(rs.getString("manv"));
				pn.setMaNCC(rs.getString("mancc"));
				pn.setMaSach(rs.getString("masach"));
				pn.setSoLuong(rs.getInt("soluong"));
				pn.setGiaSach(rs.getInt("giasach"));
				pn.setNgayNhap(rs.getDate("ngaynhap"));

				phieunhapListByMonth.add(pn);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phieunhapListByMonth;
	}
}
