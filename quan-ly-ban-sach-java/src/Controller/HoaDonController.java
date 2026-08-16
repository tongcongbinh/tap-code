package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.HoaDon;
import Model.PhieuNhap;

public class HoaDonController {
	public static List<HoaDon> getAllHoaDon() {

		List<HoaDon> hoadonList = new ArrayList<HoaDon>();
		String sql = "select * from hoadon";

		try {
			java.sql.Connection conn = Connection.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HoaDon hoadon = new HoaDon();

				hoadon.setMaHD(rs.getString("mahd"));
				hoadon.setMaNV(rs.getString("manv"));
				hoadon.setMaHV(rs.getString("mahv"));
				hoadon.setMaSach(rs.getString("masach"));
				hoadon.setSoLuong(rs.getInt("soluong"));
				hoadon.setNgayTaoHD(rs.getDate("ngaytaohd"));

				hoadonList.add(hoadon);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoadonList;
	}

	public static int Add(HoaDon hd) {
	    java.sql.Connection conn = Connection.getConnection();
	    int action = 0;

	    String sql = "INSERT INTO hoadon (mahd, manv, mahv, masach, soluong, ngaytaohd) VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        // Bắt đầu một giao dịch (transaction)
	        conn.setAutoCommit(false);

	        // Kiểm tra số lượng sách hiện có trong kho
	        String sqlCheckStock = "SELECT soluong FROM sach WHERE masach = ?";
	        PreparedStatement psCheckStock = conn.prepareStatement(sqlCheckStock);
	        psCheckStock.setString(1, hd.getMaSach());
	        ResultSet rs = psCheckStock.executeQuery();

	        int currentStock = 0;
	        if (rs.next()) {
	            currentStock = rs.getInt("soluong");
	        }

	        // Nếu số lượng muốn mua lớn hơn số lượng hiện có, trả về lỗi
	        if (hd.getSoLuong() > currentStock) {
	            System.out.println("Số lượng sách trong kho không đủ để tạo hóa đơn.");
	            return 0; // Trả về 0 để báo lỗi
	        }

	        // Thêm hóa đơn
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, hd.getMaHD());
	        ps.setString(2, hd.getMaNV());
	        ps.setString(3, hd.getMaHV());
	        ps.setString(4, hd.getMaSach());
	        ps.setInt(5, hd.getSoLuong());
	        ps.setDate(6, hd.getNgayTaoHD());

	        action = ps.executeUpdate();

	        // Nếu thêm hóa đơn thành công, cập nhật số lượng sách trong kho
	        if (action > 0) {
	            String sqlUpdateBookQuantity = "UPDATE sach SET soluong = soluong - ? WHERE masach = ?";
	            PreparedStatement psUpdateBook = conn.prepareStatement(sqlUpdateBookQuantity);
	            psUpdateBook.setInt(1, hd.getSoLuong()); // Giảm số lượng theo số lượng hóa đơn
	            psUpdateBook.setString(2, hd.getMaSach());
	            psUpdateBook.executeUpdate();
	        }

	        // Xác nhận giao dịch (commit)
	        conn.commit();

	    } catch (Exception e) {
	        try {
	            // Nếu có lỗi, hủy bỏ giao dịch (rollback)
	            conn.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
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


	public static int Update(HoaDon hd) {
	    java.sql.Connection conn = Connection.getConnection();
	    int action = 0;

	    try {
	        // Lấy thông tin cũ của hóa đơn trước khi cập nhật
	        String sqlGetOldInfo = "SELECT masach, soluong FROM hoadon WHERE mahd = ?";
	        PreparedStatement psGetOldInfo = conn.prepareStatement(sqlGetOldInfo);
	        psGetOldInfo.setString(1, hd.getMaHD());
	        ResultSet rs = psGetOldInfo.executeQuery();

	        String oldMaSach = null;
	        int oldQuantity = 0;

	        if (rs.next()) {
	            oldMaSach = rs.getString("masach");
	            oldQuantity = rs.getInt("soluong");
	        }

	        int newQuantity = hd.getSoLuong();
	        String newMaSach = hd.getMaSach();

	        // Kiểm tra số lượng sách trong kho
	        String sqlCheckStock = "SELECT soluong FROM sach WHERE masach = ?";
	        PreparedStatement psCheckStock = conn.prepareStatement(sqlCheckStock);
	        psCheckStock.setString(1, newMaSach);
	        ResultSet rsStock = psCheckStock.executeQuery();
	        int availableStock = 0;
	        if (rsStock.next()) {
	            availableStock = rsStock.getInt("soluong");
	        }
//	        System.out.println(availableStock);
	        int quantityDifference = newMaSach.equals(oldMaSach) ? newQuantity - oldQuantity : newQuantity;
	        
//	        System.out.println(quantityDifference);
	        if (quantityDifference > availableStock) {
	            return 0; // Không cho phép sửa
	        }

	        // Cập nhật hóa đơn
	        String sqlUpdate = "UPDATE hoadon SET manv = ?, mahv = ?, masach = ?, soluong = ?, ngaytaohd = ? WHERE mahd = ?";
	        PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);

	        psUpdate.setString(1, hd.getMaNV());
	        psUpdate.setString(2, hd.getMaHV());
	        psUpdate.setString(3, newMaSach);
	        psUpdate.setInt(4, newQuantity);
	        psUpdate.setDate(5, hd.getNgayTaoHD());
	        psUpdate.setString(6, hd.getMaHD());

	        action = psUpdate.executeUpdate();

	        // Nếu cập nhật thành công, điều chỉnh số lượng sách trong kho
	        if (action > 0) {
	            if (newMaSach.equals(oldMaSach)) {
	                String sqlUpdateBook = "UPDATE sach SET soluong = soluong - ? WHERE masach = ?";
	                PreparedStatement psUpdateBook = conn.prepareStatement(sqlUpdateBook);
	                psUpdateBook.setInt(1, quantityDifference);
	                psUpdateBook.setString(2, newMaSach);
	                psUpdateBook.executeUpdate();
	            } else {
	                // Cộng lại số lượng cho mã sách cũ
	                String sqlUpdateOldBook = "UPDATE sach SET soluong = soluong + ? WHERE masach = ?";
	                PreparedStatement psUpdateOldBook = conn.prepareStatement(sqlUpdateOldBook);
	                psUpdateOldBook.setInt(1, oldQuantity);
	                psUpdateOldBook.setString(2, oldMaSach);
	                psUpdateOldBook.executeUpdate();

	                // Trừ số lượng cho mã sách mới
	                String sqlUpdateNewBook = "UPDATE sach SET soluong = soluong - ? WHERE masach = ?";
	                PreparedStatement psUpdateNewBook = conn.prepareStatement(sqlUpdateNewBook);
	                psUpdateNewBook.setInt(1, newQuantity);
	                psUpdateNewBook.setString(2, newMaSach);
	                psUpdateNewBook.executeUpdate();
	            }
	        }

	    } catch (Exception e) {
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

	public static int Delete(HoaDon hd) {
	    java.sql.Connection conn = Connection.getConnection();
	    int action = 0;

	    try {
	        // Lấy thông tin mã sách và số lượng sách từ hóa đơn trước khi xóa
	        String sqlGetOldInfo = "SELECT masach, soluong FROM hoadon WHERE mahd = ?";
	        PreparedStatement psGetOldInfo = conn.prepareStatement(sqlGetOldInfo);
	        psGetOldInfo.setString(1, hd.getMaHD());
	        ResultSet rs = psGetOldInfo.executeQuery();

	        String maSach = null;
	        int quantityToRestore = 0;

	        if (rs.next()) {
	            maSach = rs.getString("masach");
	            quantityToRestore = rs.getInt("soluong");
	        }

	        // Xóa hóa đơn
	        String sqlDelete = "DELETE FROM hoadon WHERE mahd = ?";
	        PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
	        psDelete.setString(1, hd.getMaHD());
	        action = psDelete.executeUpdate();

	        // Nếu xóa thành công, cập nhật lại số lượng sách trong kho
	        if (action > 0 && maSach != null) {
	            String sqlUpdateBook = "UPDATE sach SET soluong = soluong + ? WHERE masach = ?";
	            PreparedStatement psUpdateBook = conn.prepareStatement(sqlUpdateBook);
	            psUpdateBook.setInt(1, quantityToRestore);
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


	
	public static int getBookQuantity(String masach) {
	    int quantity = 0;
	    java.sql.Connection conn = Connection.getConnection();
	    try {
	        String sql = "SELECT soluong FROM sach WHERE masach = ?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, masach);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            quantity = rs.getInt("soluong");
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
	    return quantity;
	}

}
