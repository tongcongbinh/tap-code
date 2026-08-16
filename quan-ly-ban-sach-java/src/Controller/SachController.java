package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.Sach;

public class SachController {
	public static List<Sach> getAllSach() {

		List<Sach> sachList = new ArrayList<Sach>();
		String sql = "select * from sach";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Sach sach = new Sach();

				sach.setMaSach(rs.getString("masach"));
				sach.setTenSach(rs.getString("tensach"));
				sach.setTacGia(rs.getString("tacgia"));
				sach.setTheLoai(rs.getString("theloai"));
				sach.setNhaXuatBan(rs.getString("nhaxuatban"));
				sach.setGia(rs.getInt("gia"));
				sach.setSoLuong(rs.getInt("soluong"));
				sach.setViTri(rs.getString("vitri"));

				sachList.add(sach);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sachList;
	}

	public static int Add(Sach sach) {
	    java.sql.Connection conn = Connection.getConnection();
	    int action = 0;

	    // Thêm sách mới vào bảng với số lượng mặc định là 0
	    String sql = "INSERT INTO sach (masach, tensach, tacgia, theloai, nhaxuatban, gia, soluong, vitri) VALUES (?, ?, ?, ?, ?, ?, 0, ?)";

	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, sach.getMaSach());
	        ps.setString(2, sach.getTenSach());
	        ps.setString(3, sach.getTacGia());
	        ps.setString(4, sach.getTheLoai());
	        ps.setString(5, sach.getNhaXuatBan());
	        ps.setInt(6, sach.getGia());
	        ps.setString(7, sach.getViTri());

	        action = ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return action;
	}

	
	// Phương thức cập nhật trong SachController, không thay đổi gì
	public static int Update(Sach sach) {
	    java.sql.Connection conn = Connection.getConnection();
	    int action = 0;

	    // Cập nhật SQL, bỏ qua SoLuong
	    String sql = "UPDATE sach SET TenSach = ?, TacGia = ?, TheLoai = ?, NhaXuatBan = ?, Gia = ?, ViTri = ? WHERE MaSach = ?";

	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);

	        // Set các trường cần cập nhật
	        ps.setString(1, sach.getTenSach());
	        ps.setString(2, sach.getTacGia());
	        ps.setString(3, sach.getTheLoai());
	        ps.setString(4, sach.getNhaXuatBan());
	        ps.setInt(5, sach.getGia());
	        ps.setString(6, sach.getViTri());
	        ps.setString(7, sach.getMaSach());

	        // Thực thi cập nhật
	        action = ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return action;
	}


	
	public static Sach getSachByMaSach(String masach) {
	    Sach sach = null;
	    String sql = "SELECT * FROM sach WHERE MaSach = ?";  // Truy vấn lấy sách theo mã sách

	    try {
	        // Kết nối với cơ sở dữ liệu
	        java.sql.Connection conn = Connection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, masach);  // Gắn mã sách vào câu lệnh SQL

	        ResultSet rs = ps.executeQuery();

	        // Nếu có kết quả, tạo đối tượng Sach và gán giá trị từ ResultSet
	        if (rs.next()) {
	            sach = new Sach();
	            sach.setMaSach(rs.getString("MaSach"));
	            sach.setTenSach(rs.getString("TenSach"));
	            sach.setTacGia(rs.getString("TacGia"));
	            sach.setTheLoai(rs.getString("TheLoai"));
	            sach.setNhaXuatBan(rs.getString("NhaXuatBan"));
	            sach.setGia(rs.getInt("Gia"));
	            sach.setSoLuong(rs.getInt("SoLuong"));
	            sach.setViTri(rs.getString("ViTri"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return sach;  // Trả về đối tượng Sach hoặc null nếu không tìm thấy
	}

	
	public static int Delete(Sach sach) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;
		try {
			 String sql = "DELETE FROM sach WHERE MaSach = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sach.getMaSach());
			action = ps.executeUpdate();

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
	
	public static List<Sach> findByCostIndex_1(Sach sachcost) {

		List<Sach> nhanvienListByName = new ArrayList<Sach>();
		String sql = "SELECT * FROM sach where gia >= 0 and gia < 100000";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sach sach = new Sach();

				sach.setMaSach(rs.getString("masach"));
				sach.setTenSach(rs.getString("tensach"));
				sach.setTacGia(rs.getString("tacgia"));
				sach.setTheLoai(rs.getString("theloai"));
				sach.setNhaXuatBan(rs.getString("nhaxuatban"));
				sach.setGia(rs.getInt("gia"));
				sach.setSoLuong(rs.getInt("soluong"));
				sach.setViTri(rs.getString("vitri"));

				nhanvienListByName.add(sach);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanvienListByName;
	}
	
	public static List<Sach> findByCostIndex_2(Sach sachcost) {

		List<Sach> nhanvienListByName = new ArrayList<Sach>();
		String sql = "SELECT * FROM sach where gia >= 100000 and gia <= 200000";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sach sach = new Sach();

				sach.setMaSach(rs.getString("masach"));
				sach.setTenSach(rs.getString("tensach"));
				sach.setTacGia(rs.getString("tacgia"));
				sach.setTheLoai(rs.getString("theloai"));
				sach.setNhaXuatBan(rs.getString("nhaxuatban"));
				sach.setGia(rs.getInt("gia"));
				sach.setSoLuong(rs.getInt("soluong"));
				sach.setViTri(rs.getString("vitri"));

				nhanvienListByName.add(sach);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanvienListByName;
	}
	
	public static List<Sach> findByCostIndex_3(Sach sachcost) {

		List<Sach> nhanvienListByName = new ArrayList<Sach>();
		String sql = "SELECT * FROM sach where gia >= 200000 and gia <= 500000";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sach sach = new Sach();

				sach.setMaSach(rs.getString("masach"));
				sach.setTenSach(rs.getString("tensach"));
				sach.setTacGia(rs.getString("tacgia"));
				sach.setTheLoai(rs.getString("theloai"));
				sach.setNhaXuatBan(rs.getString("nhaxuatban"));
				sach.setGia(rs.getInt("gia"));
				sach.setSoLuong(rs.getInt("soluong"));
				sach.setViTri(rs.getString("vitri"));

				nhanvienListByName.add(sach);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanvienListByName;
	}
	
	public static List<Sach> findByCostIndex_4(Sach sachcost) {

		List<Sach> nhanvienListByName = new ArrayList<Sach>();
		String sql = "SELECT * FROM sach where gia >= 500000 and gia <= 1000000";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sach sach = new Sach();

				sach.setMaSach(rs.getString("masach"));
				sach.setTenSach(rs.getString("tensach"));
				sach.setTacGia(rs.getString("tacgia"));
				sach.setTheLoai(rs.getString("theloai"));
				sach.setNhaXuatBan(rs.getString("nhaxuatban"));
				sach.setGia(rs.getInt("gia"));
				sach.setSoLuong(rs.getInt("soluong"));
				sach.setViTri(rs.getString("vitri"));

				nhanvienListByName.add(sach);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanvienListByName;
	}
	
	public static List<Sach> findByCostIndex_5(Sach sachcost) {

		List<Sach> nhanvienListByName = new ArrayList<Sach>();
		String sql = "SELECT * FROM sach where gia >= 1000000";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sach sach = new Sach();

				sach.setMaSach(rs.getString("masach"));
				sach.setTenSach(rs.getString("tensach"));
				sach.setTacGia(rs.getString("tacgia"));
				sach.setTheLoai(rs.getString("theloai"));
				sach.setNhaXuatBan(rs.getString("nhaxuatban"));
				sach.setGia(rs.getInt("gia"));
				sach.setSoLuong(rs.getInt("soluong"));
				sach.setViTri(rs.getString("vitri"));

				nhanvienListByName.add(sach);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanvienListByName;
	}
}
