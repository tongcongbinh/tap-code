create database QuanLyKhachSan
go
use QuanLyKhachSan
go
-- Bảng Quyền --
create table Quyen(
	MaQuyen int primary key,
	TenQuyen nvarchar(30),
);

insert into Quyen(MaQuyen,TenQuyen)
values (1,'Admin'), (2, N'Nhân Viên')

go
-- Bảng Đăng Nhập --
create table DangNhap(
	MaNguoiDung nvarchar(10) primary key,
	TaiKhoan nvarchar(30),
	MatKhau nvarchar(30),
	MaQuyen int,

	foreign key (MaQuyen) references Quyen(MaQuyen),
);

insert into DangNhap(MaNguoiDung,TaiKhoan,MatKhau, MaQuyen)
values ('1','admin','123', 1), ('2','nhanvien','321', 2)
drop table DangNhap
go
-- Kiểm tra đăng nhập --
create proc proc_login
@user nvarchar(30),
@pass nvarchar(30),
@quyen int
as
begin
	select * from DangNhap where TaiKhoan = @user and MatKhau = @pass
end
-- Tìm kiếm mật khẩu --
create proc proc_search_pass
@user nvarchar(30)
as
begin
	select MatKhau from DangNhap where TaiKhoan = @user
end
-- Bảng Khách hàng -- 
create table KhachHang(
	SoCMND nvarchar(15) primary key,
	HoTen nvarchar(50),
	Tuoi int,
	GioiTinh nvarchar(10),
	QueQuan nvarchar(50),
);

insert into KhachHang(SoCMND, HoTen, Tuoi, GioiTinh, QueQuan)
values  ('035203003672',N'Ngô Gia Lâm',18,N'Nam',N'Hà Nội'),
		('035392005612',N'Hoàng Hải Long',23,N'Nam',N'Hải Dương'),
		('035506005913',N'Phan Viết Hải',22,N'Nam',N'Hà Nam'),
		('035332009912',N'Lâm Hoàng Anh',19,N'Nam',N'Bắc Ninh'),
		('035130004112',N'Đỗ Linh Anh',26,N'Nữ',N'Thái Bình'),
		('035921001192',N'Lê Ngọc Toàn',25,N'Nam',N'Hà Nội'),
		('035315005321',N'Ngô An Nam',28,N'Nam',N'Thái Nguyên'),
		('035225002201',N'Phan Hoàng Nam',21,N'Nam',N'Hà Nội'),
		('035198000992',N'Lê Minh Thư',22,N'Nữ','Hà Nam'),
		('035199001357',N'Đoàn Hải Hậu',26,N'Nam',N'Bình Dương')
-- Bảng Phòng --
create table Phong(
	MaPhong int primary key,
	TenPhong nvarchar(20),
	Tang int,
	SoNguoiToiDa int,
	GiaPhong int,
	TinhTrang nvarchar(20),
);

insert into Phong(MaPhong, TenPhong, Tang, SoNguoiToiDa, GiaPhong, TinhTrang)
values  (1, N'Phòng 201', 2, 1, 400000, N'Trống'),
		(2, N'Phòng 202', 2, 1, 400000, N'Trống'),
		(3, N'Phòng 203', 2, 1, 400000, N'Trống'),
		(4, N'Phòng 204', 2, 1, 400000, N'Trống'),
		(5, N'Phòng 205', 2, 1, 400000, N'Trống'),
		(6, N'Phòng 301', 3, 2, 600000, N'Trống'),
		(7, N'Phòng 302', 3, 2, 600000, N'Trống'),
		(8, N'Phòng 303', 3, 2, 600000, N'Trống'),
		(9, N'Phòng 304', 3, 2, 600000, N'Trống'),
		(10, N'Phòng 305', 3, 2, 500000, N'Trống'),
		(11, N'Phòng 402', 4, 4, 1200000, N'Trống'),
		(12, N'Phòng 402', 4, 4, 1200000, N'Trống'),
		(13, N'Phòng 402', 4, 4, 1200000, N'Trống'),
		(14, N'Phòng 402', 4, 4, 1200000, N'Trống'),
		(15, N'Phòng 402', 4, 4, 1200000, N'Trống'),
		(16, N'Phòng 501', 5, 2, 800000, N'Trống'),
		(17, N'Phòng 502', 5, 2, 800000, N'Trống'),
		(18, N'Phòng 503', 5, 2, 800000, N'Trống'),
		(19, N'Phòng 504', 5, 2, 800000, N'Trống'),
		(20, N'Phòng 505', 5, 2, 800000, N'Trống')
-- Bảng Dịch vụ --
create table DichVu(
	TenDV nvarchar(30) primary key,
	GiaDV int
);
insert into DichVu(TenDV, GiaDV)
values  (N'Giặt ủi', 100000),
		(N'Spa', 250000),
		(N'Suất ăn sáng', 80000),
		(N'Suất ăn trưa', 150000),
		(N'Suất ăn tối', 150000),
		(N'Buffet', 200000),
		(N'Bida', 80000),
		(N'Bể bơi', 100000),
		(N'Bar', 150000)
-- Bảng Đặt phòng --
create table DatPhong(
	MaDatPhong int primary key,
	SoCMND nvarchar(15),
	MaPhong int,
	NgayDatPhong datetime,
	NgayTraPhong datetime,

	foreign key (SoCMND) references KhachHang(SoCMND),
	foreign key (MaPhong) references Phong(MaPhong),
);

insert into DatPhong(MaDatPhong, SoCMND, MaPhong, NgayDatPhong, NgayTraPhong)
values  (1,'035203003672',2,'2023-04-17','2023-04-19'),
		(2,'035506005913',4,'2023-03-17','2023-03-21'),
		(3,'035130004112',6,'2023-03-18','2023-03-22'),
		(4,'035315005321',6,'2023-02-21','2023-02-25'),
		(5,'035225002201',8,'2023-04-03','2023-04-09'),
		(6,'035198000992',2,'2023-01-17','2023-01-25'),
		(7,'035198000992',2,'2023-01-17','2023-01-25'),
		(8,'035198000992',8,'2023-01-17','2023-01-25'),
		(9,'035198000992',4,'2023-01-17','2023-01-25'),
		(10,'035198000992',10,'2023-01-17','2023-01-25'),
		(11,'035198000992',12,'2023-01-17','2023-01-25'),
		(12,'035198000992',12,'2023-01-17','2023-01-25'),
		(13,'035198000992',10,'2023-01-17','2023-01-25'),
		(14,'035198000992',2,'2023-01-17','2023-01-25'),
		(15,'035198000992',8,'2023-01-17','2023-01-25'),
		(16,'035198000992',6,'2023-01-17','2023-01-25'),
		(17,'035198000992',4,'2023-01-17','2023-01-25'),
		(18,'035198000992',14,'2023-01-17','2023-01-25'),
		(19,'035198000992',2,'2023-01-17','2023-01-25')
select DATEDIFF(DAY, NgayDatPhong, NgayTraPhong) as ngaydat from DatPhong 
-- Bảng Hóa đơn --
create table HoaDon(
	MaHD int primary key,
	NgayTaoHD datetime,
	SoCMND nvarchar(15),
	MaDatPhong int,

	foreign key (SoCMND) references KhachHang(SoCMND),
	foreign key (MaDatPhong) references DatPhong(MaDatPhong),
);
insert into HoaDon
values  (1, '2023-03-16', '035203003672', 2),
		(2, '2023-03-25', '035198000992', 4),
		(3, '2023-02-15', '035506005913', 6),
		(4, '2023-04-19', '035315005321', 8),
		(5, '2023-03-16', '035130004112', 10),
		(6, '2023-03-16', '035198000992', 5),
		(7, '2023-03-16', '035506005913', 9),
		(8, '2023-03-16', '035225002201', 16),
		(9, '2023-03-16', '035199001357', 18)
select *from HoaDon
select HD.MaHD, HD.NgayTaoHD, KH.HoTen, HD.SoCMND, P.TenPhong, DATEDIFF(DAY, NgayDatPhong, NgayTraPhong) as SoNgaySD, P.GiaPhong * DATEDIFF(DAY, NgayDatPhong, NgayTraPhong) as TienPhong
from (((HoaDon as HD inner join KhachHang as KH on HD.SoCMND = KH.SoCMND)
inner join DatPhong as DP on HD.MaDatPhong = DP.MaDatPhong)
inner join Phong as P on DP.MaPhong = P.MaPhong)

