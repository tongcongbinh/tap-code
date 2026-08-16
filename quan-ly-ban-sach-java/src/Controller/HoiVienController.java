package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.HoiVien;

public class HoiVienController {
	public static List<HoiVien> getAllHoiVien() {
		List<HoiVien> hoivienList = new ArrayList<HoiVien>();
		String sql = "select * from hoivien";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HoiVien hoivien = new HoiVien();

				hoivien.setMaHV(rs.getString("mahv"));
				hoivien.setTenHV(rs.getString("tenhv"));
				hoivien.setDiaChi(rs.getString("diachi"));
				hoivien.setSDT(rs.getString("sdt"));

				hoivienList.add(hoivien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoivienList;
	}

	public static int Add(HoiVien hv) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;

		String sql = "insert into hoivien (mahv, tenhv, diachi, sdt) values ( ?, ?, ?, ?);";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hv.getMaHV());
			ps.setString(2, hv.getTenHV());
			ps.setString(3, hv.getDiaChi());
			ps.setString(4, hv.getSDT());

			action = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return action;
	}
	
	public static int Update(HoiVien hv) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;

		String sql = "UPDATE hoivien SET tenhv = ?, diachi = ?, sdt = ?  WHERE mahv = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, hv.getTenHV());
			ps.setString(2, hv.getDiaChi());
			ps.setString(3, hv.getSDT());
			ps.setString(4, hv.getMaHV());

			action = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return action;
	}
	
	public static int Delete(HoiVien hv) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;
		try {
			String sql = "DELETE FROM hoivien WHERE mahv = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hv.getMaHV());
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
	
	public static List<HoiVien> findByName(HoiVien hoivienname) {

		List<HoiVien> hoivienListByName = new ArrayList<HoiVien>();
		String sql = "SELECT * FROM hoivien WHERE lower(tenhv) like '%"+ hoivienname.getTenHV()+"%' or upper(tenhv) like '%"+ hoivienname.getTenHV()+"%'";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HoiVien nhanvien = new HoiVien();

				nhanvien.setMaHV(rs.getString("mahv"));
				nhanvien.setTenHV(rs.getString("tenhv"));
				nhanvien.setDiaChi(rs.getString("diachi"));
				nhanvien.setSDT(rs.getString("sdt"));

				hoivienListByName.add(nhanvien);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoivienListByName;
	}
}
