package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthUI;

import Model.HDCT;
import Model.Sach;

public class ThongKeController {
	public static List<Sach> getSachBanChay(int month) {
	    List<Sach> sachList = new ArrayList<>();
	    
	    // Truy vấn để lấy danh sách sách bán chạy từ bảng hóa đơn theo tháng
	    String sql = "SELECT s.masach, s.tensach, s.gia, SUM(hd.soluong) AS totalSold " +
	                 "FROM hoadon AS hd " +
	                 "INNER JOIN sach AS s ON hd.masach = s.masach " +
	                 "WHERE MONTH(hd.ngaytaohd) = ? " +
	                 "GROUP BY s.masach, s.tensach, s.gia " +
	                 "ORDER BY totalSold DESC " +
	                 "LIMIT 5"; // Giới hạn lấy top 5 sách bán chạy theo tháng

	    try {
	        java.sql.Connection conn = Connection.getConnection();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, month);  // Set tháng vào truy vấn
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Sach sach = new Sach();
	            
	            // Lấy thông tin sách từ kết quả truy vấn
	            sach.setMaSach(rs.getString("masach"));
	            sach.setTenSach(rs.getString("tensach"));
	            sach.setGia(rs.getInt("gia"));

	            sachList.add(sach);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return sachList;
	}


	
	public static List<Sach> getSachTonKho() {

		List<Sach> sachList = new ArrayList<Sach>();
		String sql = "select masach, tensach, gia from sach where soluong > 100";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Sach sach = new Sach();

				sach.setMaSach(rs.getString("masach"));
				sach.setTenSach(rs.getString("tensach"));
				sach.setGia(rs.getInt("gia"));

				sachList.add(sach);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sachList;
	}
	
	public static String findCast1() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 1";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}

	public static String findCast2() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 2";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast3() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 3";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast4() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 4";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast5() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 5";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast6() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 6";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast7() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 7";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast8() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 8";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast9() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 9";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast10() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 10";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast11() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 11";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	public static String findCast12() {
		String sql = "SELECT sum(hd.Soluong * s.Gia) as doanhthu FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach "
				+ "WHERE month(hd.ngaytaohd) = 12";
		String doanhthu = "";
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				
				doanhthu = rs.getString("doanhthu");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doanhthu;
	}
	
}
