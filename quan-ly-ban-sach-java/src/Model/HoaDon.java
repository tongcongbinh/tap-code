package Model;

import java.sql.Date;

public class HoaDon {
	private String MaHD, MaNV, MaHV, MaSach;
	private int SoLuong;
	private Date NgayTaoHD;
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String maHD) {
		MaHD = maHD;
	}
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public String getMaHV() {
		return MaHV;
	}
	public void setMaHV(String maHV) {
		MaHV = maHV;
	}
	public String getMaSach() {
		return MaSach;
	}
	public void setMaSach(String maSach) {
		MaSach = maSach;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public Date getNgayTaoHD() {
		return NgayTaoHD;
	}
	public void setNgayTaoHD(Date ngayTaoHD) {
		NgayTaoHD = ngayTaoHD;
	}
}
