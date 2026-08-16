package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Statement;

import Model.NhanVien;

public class NhanVienController {
	public static List<NhanVien> getAllNhanVien() {

		List<NhanVien> nhanvienList = new ArrayList<NhanVien>();
		String sql = "select * from nhanvien";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				NhanVien nhanvien = new NhanVien();

				nhanvien.setMaNV(rs.getString("manv"));
				nhanvien.setTaiKhoan(rs.getString("taikhoan"));
				nhanvien.setMatKhau(rs.getString("matkhau"));
				nhanvien.setTenNV(rs.getString("tennv"));
				nhanvien.setChucVu(rs.getString("chucvu"));
				nhanvien.setDiaChi(rs.getString("diachi"));
				nhanvien.setSDT(rs.getString("sdt"));

				nhanvienList.add(nhanvien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanvienList;
	}

	public static int Add(NhanVien nv) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;

		String sql = "insert into nhanvien (manv, taikhoan, matkhau, tennv, chucvu, diachi, sdt) values ( ?, ?, ?, ?, ?, ?, ?);";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nv.getMaNV());
			ps.setString(2, nv.getTaiKhoan());
			ps.setString(3, nv.getMatKhau());
			ps.setString(4, nv.getTenNV());
			ps.setString(5, nv.getChucVu());
			ps.setString(6, nv.getDiaChi());
			ps.setString(7, nv.getSDT());

			action = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return action;
	}

	
	public static int Update(NhanVien nv) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;

		String sql = "UPDATE nhanvien SET taikhoan = ?, matkhau = ?, tennv = ?, chucvu = ?, diachi = ?, sdt = ?  WHERE manv = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, nv.getTaiKhoan());
			ps.setString(2, nv.getMatKhau());
			ps.setString(3, nv.getTenNV());
			ps.setString(4, nv.getChucVu());
			ps.setString(5, nv.getDiaChi());
			ps.setString(6, nv.getSDT());
			ps.setString(7, nv.getMaNV());

			action = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return action;
	}
	
	public static int Delete(NhanVien nv) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;
		try {
			String sql = "delete from nhanvien where manv = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nv.getMaNV());
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
	
	public static List<NhanVien> findByName(NhanVien nhanvienname) {

		List<NhanVien> nhanvienListByName = new ArrayList<NhanVien>();
		String sql = "SELECT * FROM nhanvien WHERE lower(tennv) like '%"+ nhanvienname.getTenNV()+"%' or upper(tennv) like '%"+ nhanvienname.getTenNV()+"%'";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				NhanVien nhanvien = new NhanVien();

				nhanvien.setMaNV(rs.getString("manv"));
				nhanvien.setTaiKhoan(rs.getString("taikhoan"));
				nhanvien.setMatKhau(rs.getString("matkhau"));
				nhanvien.setTenNV(rs.getString("tennv"));
				nhanvien.setChucVu(rs.getString("chucvu"));
				nhanvien.setDiaChi(rs.getString("diachi"));
				nhanvien.setSDT(rs.getString("sdt"));

				nhanvienListByName.add(nhanvien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nhanvienListByName;
	}
}
