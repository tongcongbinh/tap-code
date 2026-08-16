package Model;

import java.sql.Date;

public class HDCT {
	private Date NgayTaoHD;
	public Date getNgayTaoHD() {
		return NgayTaoHD;
	}
	public void setNgayTaoHD(Date ngayTaoHD) {
		NgayTaoHD = ngayTaoHD;
	}
	private String MaHD, TenNV, TenSach;
	public String getTenSach() {
		return TenSach;
	}
	public void setTenSach(String tenSach) {
		TenSach = tenSach;
	}
	private int SoLuong, TongTien;
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String maHD) {
		MaHD = maHD;
	}
	public String getTenNV() {
		return TenNV;
	}
	public void setTenNV(String tenNV) {
		TenNV = tenNV;
	}
	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	public int getTongTien() {
		return TongTien;
	}
	public void setTongTien(int tongTien) {
		TongTien = tongTien;
	}
	
}
