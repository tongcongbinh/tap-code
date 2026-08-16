package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.PreparableStatement;

import Model.NhanVien;
import View.LoginForm;
import View.NhanVienForm;
import View.QuanLyForm;

public class LoginController extends Controller.Connection{
	public boolean login(NhanVien nhanvien) {
		Connection connection = Controller.Connection.getConnection();
		LoginForm loginForm = new LoginForm();
		NhanVienForm nvform = new NhanVienForm();
		QuanLyForm qlform = new QuanLyForm();
		
		String sql = "select * from nhanvien where TaiKhoan = ? and MatKhau = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, nhanvien.getTaiKhoan());
			ps.setString(2, nhanvien.getMatKhau());
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String role = rs.getString("ChucVu");
				if (role.equals("Nhân viên")) {
					JOptionPane.showMessageDialog(null, "Nhân viên đăng nhập thành công!");
					nvform.setVisible(true);
					loginForm.setVisible(false);
				} else if (role.equals("Quản lý")) {
					JOptionPane.showMessageDialog(null, "Quản lý đăng nhập thành công!");
					qlform.setVisible(true);
					loginForm.setVisible(false);
				} else {
					return false;
				}	
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
		}

		return false;
	}
}
