package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.NhaCungCap;


public class NhaCungCapController {
	public static List<NhaCungCap> getAllNCC() {
		List<NhaCungCap> nccList = new ArrayList<NhaCungCap>();
		String sql = "select * from nhacungcap";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();

				ncc.setMaNCC(rs.getString("mancc"));
				ncc.setTenNhaCC(rs.getString("tennhacc"));
				ncc.setDiaChi(rs.getString("diachi"));
				ncc.setEmail(rs.getString("email"));

				nccList.add(ncc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nccList;
	}

	public static int Add(NhaCungCap ncc) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;

		String sql = "INSERT INTO nhacungcap (mancc, tennhacc, diachi, email) VALUES ( ?, ?, ?, ?)";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, ncc.getMaNCC());
			ps.setString(2, ncc.getTenNhaCC());
			ps.setString(3, ncc.getDiaChi());
			ps.setString(4, ncc.getEmail());

			action = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return action;
	}
	
	public static int Update(NhaCungCap ncc) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;

		String sql = "UPDATE nhacungcap SET tennhacc = ?, diachi = ?, email = ? WHERE mancc = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, ncc.getTenNhaCC());
			ps.setString(2, ncc.getDiaChi());
			ps.setString(3, ncc.getEmail());
			ps.setString(4, ncc.getMaNCC());
			
			action = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return action;
	}
	
	public static int Delete(NhaCungCap ncc) {
		java.sql.Connection conn = Connection.getConnection();
		int action = 0;
		try {
			String sql = "DELETE FROM nhacungcap WHERE mancc = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ncc.getMaNCC());
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
	
	public static List<NhaCungCap> findByName(NhaCungCap nccname) {

		List<NhaCungCap> nccListByName = new ArrayList<NhaCungCap>();
		String sql = "SELECT * FROM nhacungcap WHERE lower(tennhacc) like '%"+ nccname.getTenNhaCC()+"%' or upper(tennhacc) like '%"+ nccname.getTenNhaCC()+"%'";
		
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();

				ncc.setMaNCC(rs.getString("mancc"));
				ncc.setTenNhaCC(rs.getString("tennhacc"));
				ncc.setDiaChi(rs.getString("diachi"));
				ncc.setEmail(rs.getString("email"));

				nccListByName.add(ncc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nccListByName;
	}
}
