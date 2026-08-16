package Model;

import java.sql.Date;

public class PhieuNhap {
	private String MaPN, MaNV, MaNCC, MaSach;
	private int SoLuong, GiaSach;
	private Date NgayNhap;
	
	public String getMaPN() {
		return MaPN;
	}
	public void setMaPN(String maPN) {
		MaPN = maPN;
	}
	public String getMaNV() {
		return MaNV;
	}
	public void setMaNV(String maNV) {
		MaNV = maNV;
	}
	public String getMaNCC() {
		return MaNCC;
	}
	public void setMaNCC(String maNCC) {
		MaNCC = maNCC;
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
	public int getGiaSach() {
		return GiaSach;
	}
	public void setGiaSach(int giaSach) {
		GiaSach = giaSach;
	}
	public Date getNgayNhap() {
		return NgayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		NgayNhap = ngayNhap;
	}
}
