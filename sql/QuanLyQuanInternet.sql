CREATE DATABASE QuanLyQuanInternet
GO
USE QuanLyQuanInternet
GO
CREATE TABLE KhachHang(
	MaKH varchar(10) primary key,
	TenKH nvarchar(30),
	TaiKhoan varchar(20),
	MatKhau varchar(15),
);

CREATE TABLE MayTinh(
	MaMay varchar(10) primary key,
	TenMay nvarchar(20),
	GiaMay int,
	TinhTrang nvarchar(20),
);

CREATE TABLE DichVu(
	MaDV varchar(10) primary key,
	TenDV nvarchar(30),
	DonGiaDV int,
);

CREATE TABLE KH_Order(
	MaOrder varchar(10) primary key,
	MaKH varchar(10),
	MaMay varchar(10),
	GioBatDau time,
	GioKetThuc time,
	MaDV varchar(10),
	SoLuongDV int,

	foreign key (MaKH) references KhachHang(MaKH),
	foreign key (MaMay) references MayTinh(MaMay),
	foreign key (MaDV) references DichVu(MaDV),
);

CREATE TABLE HoaDon(
	MaHD varchar(10) primary key,
	MaOrder varchar(10) unique,
	NgayTao datetime,

	foreign key (MaOrder) references KH_Order(MaOrder),
);

insert into KhachHang(MaKH, TenKH, TaiKhoan, MatKhau)
values  ('KH01',N'Hồ Hải Anh','haianh123','0123'),
		('KH02',N'Phan Viết Hải','haiphan1999','hai99'),
		('KH03',N'Hoàng Thanh Long','longquannet','longtinh'),
		('KH04',N'Đỗ Mai Anh','maianhtoi','emula'),
		('KH05',N'Ngô Hoàng Sơn','soncute1234','venti'),
		('KH06',N'Nguyễn An Khang','khanggne','khangmia'),
		('KH07',N'Đoàn Thành An','annnnn','anna')

insert into MayTinh(MaMay, TenMay, GiaMay, TinhTrang)
values  ('01','PC-01','5000',N'Trống'),
		('02','PC-02','5000',N'Đang sử dụng'),
		('03','PC-03','6000',N'Đang sử dụng'),
		('04','PC-04','7000',N'Trống'),
		('05','PC-05','8000',N'Trống'),
		('06','PC-06','6000',N'Đang sử dụng'),
		('07','PC-07','5000',N'Lỗi')

insert into DichVu(MaDV, TenDV, DonGiaDV)
values  ('DV01',N'Nước cam','10000'),
		('DV02',N'Coca','10000'),
		('DV03',N'Pepsi','10000'),
		('DV04',N'Cơm gà','250000'),
		('DV05',N'Đùi gà nấm','25000'),
		('DV06',N'Mì tôm trứng','15000'),
		('DV07',N'Cơm rang thập cẩm','25000'),
		('DV08',N'Trứng luộc','5000'),
		('DV09',N'Cơm trộn','30000')

insert into KH_Order(MaOrder, MaKH, MaMay, GioBatDau, GioKetThuc, MaDV, SoLuongDV)
values  ('OD01','KH01','01','12:12:20','14:22:00','DV06','3'),
		('OD02','KH02','03','12:00:00','15:30:00','DV07','2'),
		('OD03','KH06','05','13:00:00','16:40:00','DV04','4'),
		('OD04','KH07','01','11:00:00','12:00:00','DV02','3'),
		('OD05','KH02','02','15:30:00','16:00:00','DV09','1'),
		('OD06','KH03','03','12:00:00','13:00:00','DV01','1'),
		('OD07','KH05','04','12:00:00','12:00:00','DV02','3'),
		('OD08','KH04','05','12:00:00','12:00:00','DV05','2'),
		('OD09','KH01','06','10:30:00','12:00:00','DV01','4')
		



insert into HoaDon(MaHD,MaOrder,NgayTao)
values  ('HD01','OD01','03/22/2023'),
		('HD02','OD02','03/22/2023'),
		('HD03','OD03','03/22/2023'),
		('HD04','OD04','03/22/2023'),
		('HD05','OD05','03/22/2023'),
		('HD06','OD06','03/22/2023'),
		('HD07','OD07','03/22/2023'),
		('HD08','OD08','03/22/2023'),
		('HD09','OD09','03/22/2023')




