package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.HDCT;
import Model.HoaDon;
import Model.PhieuNhap;

public class HDCTController {
	public static List<HDCT> getAllHoaDon() {

		List<HDCT> hoadonList = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach";
		try {
			java.sql.Connection conn = Connection.getConnection();

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();

				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hoadonList.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoadonList;
	}
	public static List<HDCT> findByMonth_1(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 1";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	
	public static List<HDCT> findByMonth_2(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 2";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	
	public static List<HDCT> findByMonth_3(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 3";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	
	public static List<HDCT> findByMonth_4(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 4";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	
	public static List<HDCT> findByMonth_5(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 5";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	
	public static List<HDCT> findByMonth_6(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 6";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	public static List<HDCT> findByMonth_7(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 7";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	public static List<HDCT> findByMonth_8(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 8";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	public static List<HDCT> findByMonth_9(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 9";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	public static List<HDCT> findByMonth_10(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 10";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	public static List<HDCT> findByMonth_11(HDCT hdctlist) {

		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 11";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HDCT hdct = new HDCT();
				
				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	public static List<HDCT> findByMonth_12(HDCT hdctlist) {
		HDCT hdct = new HDCT();
		
		List<HDCT> hdctListByMonth = new ArrayList<HDCT>();
		String sql = "SELECT hd.MaHD, nv.TenNV, s.TenSach, hd.SoLuong, (hd.Soluong * s.Gia) as TongTien, hd.ngaytaohd FROM (hoadon as hd INNER JOIN nhanvien as nv on hd.MaNV = nv.MaNV) INNER JOIN sach as s on hd.MaSach = s.MaSach\r\n"
				+ "WHERE month(hd.ngaytaohd) = 12";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				hdct.setMaHD(rs.getString("mahd"));
				hdct.setTenNV(rs.getString("tennv"));
				hdct.setSoLuong(rs.getInt("soluong"));
				hdct.setTenSach(rs.getString("tensach"));
				hdct.setTongTien(rs.getInt("tongtien"));
				hdct.setNgayTaoHD(rs.getDate("ngaytaohd"));
				
				hdctListByMonth.add(hdct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hdctListByMonth;
	}
	
}
