
-- Bảng Nhân Viên --
create table NhanVien(
	MaNV varchar(10) primary key not null,
	TaiKhoan varchar(25),
	MatKhau varchar(10),
	TenNV varchar(50),
	ChucVu varchar(25),
	DiaChi varchar(50),
	SDT varchar(15)
);

insert into NhanVien(MaNV, TaiKhoan, MatKhau, TenNV, ChucVu, DiaChi, SDT)
values  ('NV01', 'quanly1', '123', N'Bùi Thảo Anh', N'Quản lý', N'Quảng Ninh', '097 7666 035'),
		('NV02', 'quanly2', '123', N'Lưu Trang Anh', N'Quản lý', N'Hải Phòng', '090 8897 544'),
		('NV03', 'quanly3', '123', N'Phạm Gia Minh', N'Quản lý', N'Hà Nam', '091 3723 223'),
		('NV04', 'nhanvien4', '123', N'Nguyễn Yến Nhi', N'Nhân viên', N'Bắc Ninh', '090 9232 169'),
		('NV05', 'nhanvien5', '123', N'Vũ Thanh Huyền', N'Nhân viên', N'Hải Phòng', '091 8097 236'),
		('NV06', 'nhanvien6', '123', N'Trần Bảo Thy', N'Nhân viên', N'Nam Định', '090 3165 835'),
		('NV07', 'nhanvien7', '123', N'Đặng Gia Hân', N'Nhân viên', N'Hưng Yên', '090 3880 081'),
		('NV08', 'nhanvien8', '123', N'Mai Tùng Bách', N'Nhân viên', N'Vĩnh Phúc', '091 1146 866'),
		('NV09', 'nhanvien9', '123', N'Bùi Nam Khánh', N'Nhân viên', N'Bắc Ninh', ' 091 1149 688'),
		('NV10', 'nhanvien10', '123', N'Lê Gia Bảo', N'Nhân viên', N'Hải Dương', '097 9873 656'),
		('NV11', 'nhanvien11', '123', N'Đào Tuấn Phong', N'Nhân viên', N'Hà Nam', '090 6548 265'),
		('NV12', 'nhanvien12', '123', N'Hà Duy Anh', N'Nhân viên', N'Bắc Ninh', '096 4368 899'),
		('NV13', 'nhanvien13', '123', N'Đỗ Hoàng Mỹ', N'Nhân viên', N'Thái Bình', '093 2123 035'),
		('NV14', 'nhanvien14', '123', N'Vũ Phương Thảo', N'Nhân viên', N'Ninh Bình', '097 8978 035'),
		('NV15', 'nhanvien15', '123', N'Hoàng Nhật Mai', N'Nhân viên', N'Hà Nội', '092 1874 035');


-- Bảng Hội Viên --
create table HoiVien(
	MaHV varchar(10) primary key not null,
	TenHV varchar(50),
	DiaChi varchar(50),
	SDT varchar(15)
);
insert into HoiVien(MaHV, TenHV, DiaChi, SDT)
values  ('HV01', N'Lê Hồ Minh Duy', N'Hà Nội', '093 5456 384'),
		('HV02', N'Nguyễn Phúc Khang', N'Hà Nam', '099 1212 687'),
		('HV03', N'Tăng Du Linh', N'Hà Nội', '096 6545 353'),
		('HV04', N'Lê Thái Toàn', N'Hà Nội', '092 2345 556'),
		('HV05', N'Trần Diễm Tú', N'Hà Nội', '091 8971 223'),
		('HV06', N'Trần Trung Đức', N'Hải Dương', '095 3341 441'),
		('HV07', N'Lương Trọng Tín', N'Hải Dương', '094 2365 623'),
		('HV08', N'Phạm Huy Khang', N'Hà Nội', '097 6565 556'),
		('HV09', N'Vương Gia Huy', N'Hà Nam', '097 9999 781'),
		('HV10', N'Lưu Mạnh Quan', N'Bắc Ninh', '090 5464 223'),
		('HV11', N'Nguyễn Như Ý', N'Hà Nội', '098 7415 009'),
		('HV12', N'Trương Văn Bắc', N'Hà Nội', '091 3465 300'),
		('HV13', N'Nguyễn Trọng Hùng', N'Bắc Ninh', '092 6458 102'),
		('HV14', N'Phạm Đình Khôi', N'Hà Nội', '093 3939 350'),
		('HV15', N'Vũ Gia Vinh', N'Bắc Ninh', '095 7832 305');
					

-- Bảng Sách --
create table Sach(
	MaSach varchar(10) primary key not null,
	TenSach varchar(50),
	TacGia varchar(50),
	TheLoai varchar(50),
	NhaXuatBan varchar(50),
	Gia int,
	SoLuong int,
	ViTri varchar(50)
);

insert into Sach(MaSach, TenSach, TacGia, TheLoai, NhaXuatBan, Gia, SoLuong, ViTri)
values  ('S01', N'Những chiến sĩ com lê ra trận', N'Minh Tâm',N'Lịch sử',N'Dân Trí', 66000, 135 ,N'Kệ sách 2, Tầng 1'),
		('S02', N'A! Tết là đây!', N'Quyên Thái', N'Truyện tranh',N'Kim Đồng', 72000, 79, N'Kệ sách 5, Tầng 1'),
		('S03', N'Cha giàu, cha nghèo', N' Robert Kiyosaki', N'Kinh tế',N'Trẻ', 135000, 86, N'Kệ sách 4, Tầng 2'),
		('S04', N'Bộ truyện Doraemon', N'Fujiko Fujio', N'Truyện tranh',N'Kim Đồng', 280000, 160, N'Kệ sách 5, Tầng 2'),
		('S05', N'Cô bé Matilda', N'Roald Dahl', N'Văn học nước ngoài',N'Kim Đồng', 100000, 142, N'Kệ sách 4, Tầng 1'),
		('S06', N'Hai vạn dặm dưới biển',N'Jules Verne',N'Văn học nước ngoài',N'Văn Học', 75000, 79, N'Kệ sách 4, Tầng 1'),
		('S07', N'Nhà giả kim', N'Paulo Coelho', N'Tiểu thuyết',N'Kim Đồng', 76000, 163, N'Kệ sách 1, Tầng 1'),
		('S08', N'Huệ tím', N'Hermann Hesse', N'Văn học nước ngoài',N'Trẻ', 40000, 69, N'Kệ sách 1, Tầng 2'),
		('S09', N'Cá voi và hồ nước', N'Thái Trí Hằng', N'Tiểu thuyết',N'Dân Trí', 100000, 166, N'Kệ sách 3, Tầng 1'),
		('S10', N'Không gia đình', N'Hector Malot', N'Tiểu thuyết',N'Tri Thức', 136000, 88, N'Kệ sách 2, Tầng 2'),
		('S11', N'Thao túng tâm lý ', N'shannon thomas ', N'Tâm Lý ',N'NXB dân trí ',125000 ,12 , N'Kệ sách 7, Tầng 3'),
		('S12', N'Giao Tiếp Đỉnh Cấp ', N'Lương Hiền ', N'Văn Hóa Xã Hội ',N'Thanh Niên', 70000,190, N'Kệ sách 5, Tầng 3'),
		('S13', N'Sỹ Số Lớp vắng 0', N'emma Hạ My', N'truyện',N'Dân Trí',82000 ,200 , N'Kệ sách 3, Tầng 3'),
		('S14', N'Đứa Trẻ Hiểu Truyện Thường Không Có Kẹo Ăn ', N'Nguyên Anh', N'Văn Hóa Xã Hội ',N'Nhà Xuất Bản Văn Học',111000 ,90 , N'Kệ sách 4, Tầng 3'),
		('S15', N'999 lá thư cho chính mình', N'Miêu công tử', N'văn hóa xã hội ',N'Thanh Niên',74000 ,123 , N'Kệ sách 7, Tầng 3'),
		('S16', N'Thôi Miên Bằng Ngôn Từ', N'Joe Vitale', N'Tâm Lý',N'Hồng Đức',150000 ,200 , N'Kệ sách 2, Tầng 3'),
		('S17', N'Sự Thông Minh Trong Hài Hước', N'Lý Thế Cường', N'Tâm Lý',N'Hồng Đức',81000 ,108 , N'Kệ sách 6, Tầng 2'),
		('S18', N'Kỹ Năng Phát Triển Bản Thân', N'Kwon min Chan', N'Văn Hóa Xã Hội',N'Dân Trí',200000 ,23 , N'Kệ sách 7, Tầng 1'),
		('S19', N'Tiền Đẻ Ra Tiền', N'Duncan Bannatyne', N'Khoa học công nghệ – Kinh tế',N'Hồng Đức', 85000,30 , N'Kệ sách 6, Tầng 1'),
		('S20', N'Tư Duy Về Tiền Bạc', N'Jonathan clements', N' Khoa học công nghệ – Kinh tế',N'Thanh Niên',65000 , 500, N'Kệ sách 6, Tầng 2'),
		('S21', N'Đừng Để Nỗi Sợ ẢNh Hưởng Bạn', N'Helen Odessky', N'Tâm Lý',N'Thanh Niên',70000 , 60, N'Kệ sách 7, Tầng 2'),
		('S22', N'Hấp Dẫn Trong Tình Yêu', N'Caterina Yolanie', N'Tình Cảm',N'NXB Thanh Niên',120000 , 50, N'Kệ sách 6, Tầng 2'),
		('S23', N'Năng Lượng Chữa Lành', N'Phoebe Garnsworthy', N'Tâm lý',N'Thanh Niên', 69000, 40, N'Kệ sách 6 , Tầng 3'),
		('S24', N'Sức Mạnh Của Sự Tử Tế', N'Robij Koval', N'Văn hóa xã hội',N'Phương Nam',140000 , 60, N'Kệ sách 4, Tầng 2'),
		('S25', N'Điềm Tĩnh Và Nóng Giận', N'Tạ Quốc Tế', N'Tâm Lý',N'Thanh Niên',70000 ,200 , N'Kệ sách 5, Tầng 2');

-- Bảng Nhà Cung Cấp --
create table NhaCungCap(
	MaNCC varchar(10) primary key not null,
	TenNhaCC varchar(50),
	DiaChi varchar(50),
	Email varchar(50)
);

insert into NhaCungCap(MaNCC, TenNhaCC, DiaChi, Email)
values  ('NCC01', N'Nhà sách cá Chép', N'Hà Nội', 'nhasachcachep@gmail.com'),
		('NCC02', N'Nhà sách Tiền Phong', N'Hà Nội', 'nhasachtienphong.com.vn@gmail.com'),
		('NCC03', N'Nhà sách EBook ', N'Hà Nội', 'nhasach.ebook@gmail.com'),
		('NCC04', N'Nhà xuất bản Kim Đồng', N'Hà Nội', 'cskh_online@nxbkimdong.com.vn'),
		('NCC05', N'Nhà xuất bản Trẻ', N'TP.HCM', 'hopthubandoc@nxbtre.com.vn'),
		('NCC06', N'Nhà xuất bản Tổng hợp Thành phố Hồ Chí Minh', N'TP.HCM', 'nstonghop@gmail'),
		('NCC07', N'Nhà xuất bản Thời đại', N'Hà Nội', 'nxbtd@gmail.com'),
		('NCC08', N'Nhà xuất bản Khoa học xã hội', N'Hà Nội', '	nxbkhxh@gmail.com'),
		('NCC09', N'Nhà xuất bản Giáo dục Việt Nam', N'Hà Nội', 'nxbgd@moet.edu.vn'),
		('NCC10', N'Nhà xuất bản Phương Đông', N'TP.HCM', 'nxbpd@gmail.com');
		

-- Bảng Phiếu Nhập --
create table PhieuNhap(
	MaPN varchar(10) primary key not null,
	MaNV varchar(10),
	MaNCC varchar(10),
	MaSach varchar(10),
	SoLuong int,
	GiaSach int,
	NgayNhap date,

	foreign key (MaNV) references NhanVien(MaNV),
	foreign key (MaNCC) references NhaCungCap(MaNCC),
	foreign key (MaSach) references Sach(MaSach)
);

insert into PhieuNhap(MaPN, MaNV, MaNCC, MaSach, SoLuong, GiaSach, NgayNhap)
values  ('PN01', 'NV01', 'NCC02', 'S05', 100, 80000, '2024-06-19'),
		('PN02', 'NV08', 'NCC01', 'S03', 75, 90000, '2024-07-22'),
		('PN03', 'NV11', 'NCC03', 'S04', 25, 60000, '2024-08-26'),
		('PN04', 'NV10', 'NCC06', 'S02', 68, 85000, '2024-09-15'),
		('PN05', 'NV04', 'NCC08', 'S01', 75, 65000, '2024-09-22'),
		('PN06', 'NV02', 'NCC05', 'S06', 90, 75000, '2024-08-30'),
		('PN07', 'NV15', 'NCC05', 'S09', 45,90000, '2024-07-25'),
		('PN08', 'NV12', 'NCC02', 'S07', 35, 100000, '2024-07-12'),
		('PN09', 'NV14', 'NCC01', 'S10', 78, 69000, '2024-08-11'),
		('PN10', 'NV02', 'NCC07', 'S08', 99, 72000, '2024-07-27'),
		('PN11', 'NV01', 'NCC03', 'S01', 120, 66000, '2024-06-23'),
		('PN12', 'NV06', 'NCC04', 'S02', 5, 72000, '2024-07-22'),
		('PN13', 'NV15', 'NCC01', 'S03', 4, 135000, '2024-08-26'),
		('PN14', 'NV13', 'NCC02', 'S04', 22, 280000, '2024-09-15'),
		('PN15', 'NV07', 'NCC04', 'S05', 22, 100000, '2024-01-22'),
		('PN16', 'NV04', 'NCC05', 'S06', 29, 75000, '2024-02-16'),
		('PN17', 'NV11', 'NCC06', 'S08', 50,40000, '2024-03-25'),
		('PN18', 'NV10', 'NCC07', 'S09', 100, 100000, '2024-04-12'),
		('PN19', 'NV12', 'NCC08', 'S17', 88, 81000, '2024-05-11'),
		('PN20', 'NV05', 'NCC08', 'S07', 39, 76000, '2024-07-27'),
		('PN21', 'NV03', 'NCC09', 'S08', 10, 40000, '2024-06-19'),
		('PN22', 'NV09', 'NCC10', 'S11', 10, 125000, '2024-12-22'),
		('PN23', 'NV10', 'NCC01', 'S05', 5, 100000, '2024-11-26'),
		('PN24', 'NV12', 'NCC03', 'S06', 18, 75000, '2024-10-15'),
		('PN25', 'NV05', 'NCC04', 'S21', 10, 70000, '2024-03-22'),
		('PN26', 'NV06', 'NCC06', 'S19', 9, 85000, '2024-09-30'),
		('PN27', 'NV14', 'NCC07', 'S17', 5,81000, '2024-05-25'),
		('PN28', 'NV12', 'NCC02', 'S15', 12, 74000, '2024-02-12'),
		('PN29', 'NV13', 'NCC05', 'S22', 6, 120000, '2024-04-11'),
		('PN30', 'NV08', 'NCC06', 'S25', 89, 70000, '2024-07-27');


-- Bảng Hóa Đơn --
create table HoaDon(
	MaHD varchar(10) primary key not null,
	MaNV varchar(10),
	MaHV varchar(10),
	MaSach varchar(10),
	SoLuong int,
	NgayTaoHD date,

	foreign key (MaNV) references NhanVien(MaNV),
	foreign key (MaHV) references HoiVien(MaHV),
	foreign key (MaSach) references Sach(MaSach)
);

insert into HoaDon(MaHD, MaNV, MaHV, MaSach, SoLuong, NgayTaoHD)
values  ('HD01', 'NV03', null, 'S08', 2, '2024-07-22'),
		('HD02', 'NV09', 'HV01', 'S02', 1, '2024-08-25'),
		('HD03', 'NV08', 'HV04', 'S03', 1, '2024-09-27'),
		('HD04', 'NV10', null, 'S04', 3, '2024-07-13'),
		('HD05', 'NV11', null, 'S10', 1, '2024-06-26'),
		('HD06', 'NV12', 'HV06', 'S09', 1, '2024-09-28'),
		('HD07', 'NV06', 'HV03', 'S01', 2, '2024-06-15'),
		('HD08', 'NV05', null, 'S01', 4, '2024-06-22'),
		('HD09', 'NV04', 'HV09', 'S02', 1, '2024-08-21'),
		('HD10', 'NV10', 'HV04', 'S05', 2, '2024-07-13'),
		('HD11', 'NV03', null, 'S02', 2, '2024-02-22'),
		('HD12', 'NV09', 'HV03', 'S04', 1, '2024-03-25'),
		('HD13', 'NV08', 'HV02', 'S09', 1, '2024-04-27'),
		('HD14', 'NV10', null, 'S06', 3, '2024-05-13'),
		('HD15', 'NV11', null, 'S19', 1, '2024-06-26'),
		('HD16', 'NV12', 'HV08', 'S21', 1, '2024-07-28'),
		('HD17', 'NV06', 'HV01', 'S08', 2, '2024-08-15'),
		('HD18', 'NV05', null, 'S17', 4, '2024-09-22'),
		('HD19', 'NV04', 'HV07', 'S22', 1, '2024-10-21'),
		('HD20', 'NV10', 'HV02', 'S17', 2, '2024-11-13'),
		('HD21', 'NV09', null, 'S04', 2, '2024-12-22'),
		('HD22', 'NV09', 'HV07', 'S03', 1, '2024-03-25'),
		('HD23', 'NV02', 'HV02', 'S08', 1, '2024-02-27'),
		('HD24', 'NV03', null, 'S05', 3, '2024-06-13'),
		('HD25', 'NV02', null, 'S19', 1, '2024-09-26'),
		('HD26', 'NV11', 'HV09', 'S04', 1, '2024-01-28'),
		('HD27', 'NV13', 'HV02', 'S07', 2, '2024-08-15'),
		('HD28', 'NV12', null, 'S01', 4, '2024-11-22'),
		('HD29', 'NV08', 'HV03', 'S02', 1, '2024-02-21'),
		('HD30', 'NV10', 'HV07', 'S05', 2, '2024-07-13');
