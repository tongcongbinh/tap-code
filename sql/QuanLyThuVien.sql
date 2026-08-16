create user QuanLyThuVien identified by 123456;
grant connect, resource, dba to QuanLyThuVien;
grant create session to QuanLyThuVien;
grant unlimited tablespace to QuanLyThuVien;

create table NhaXuatBan(
    manhaxuatban nvarchar2(10) primary key not null,
    tennhaxuatban nvarchar2(40)
);

create table DanhMucTaiLieu(
    madanhmuc nvarchar2(10) primary key not null,
    tendanhmuc nvarchar2(30)
);

create table TaiLieu(
    matailieu nvarchar2(10) primary key not null,
    tentailieu nvarchar2(30) not null,
    madanhmuc nvarchar2(10),
    tacgia nvarchar2(40),
    manhaxuatban nvarchar2(10),
    namxuatban nvarchar2(10),
    soluongcon nvarchar2(20),
    tomtatnoidung nvarchar2(800),
    gia number,
    
    CONSTRAINT pk_NhaXuatBan FOREIGN KEY(manhaxuatban) REFERENCES NhaXuatBan(manhaxuatban),
    CONSTRAINT pk_DanhMucTaiLieu FOREIGN KEY(madanhmuc) REFERENCES DanhMucTaiLieu(madanhmuc)
);

create table DocGia(
    madocgia nvarchar2(10) primary key not null,
    matkhau nvarchar2(20) not null,
    tendangnhap nvarchar2(30) not null,
    tendocgia nvarchar2(50),
    ngaysinh nvarchar2(30),
    email nvarchar2(40),
    diachi nvarchar2(50),
    sodienthoai nvarchar2(15),
    trangthai nvarchar2(10)
);

create table ThuThu(
    mathuthu nvarchar2(10) primary key not null,
    matkhau nvarchar2(20),
    hoten nvarchar2(50),
    ngaysinh date,
    diachi nvarchar2(50),
    sodienthoai nvarchar2(15),
    email nvarchar2(40)
);

create table PhieuMuon(
    maphieumuon nvarchar2(10) primary key not null,
    madocgia nvarchar2(10) not null,    
    ngaymuon nvarchar2(30),
    songaymuon int,
    sotailieumuon int,
    trangthai nvarchar2(40),
    mathuthu nvarchar2(10),
    
    CONSTRAINT pk_DocGia FOREIGN KEY(madocgia) REFERENCES DocGia(madocgia),
    CONSTRAINT pk_ThuThu FOREIGN KEY(mathuthu) REFERENCES ThuThu(mathuthu)
);

create table ChiTietPhieuMuon(
    maphieumuon nvarchar2(10) primary key not null,
    matailieu nvarchar2(10) not null,
    ngaytra date,
    tienphat number,
    tinhtrangtailieu nvarchar2(20),
    
    CONSTRAINT pk_TaiLieu FOREIGN KEY(matailieu) REFERENCES TaiLieu(matailieu),
    CONSTRAINT pk_PhieuMuon FOREIGN KEY(maphieumuon) REFERENCES PhieuMuon(maphieumuon)
);

INSERT INTO NhaXuatBan(manhaxuatban, tennhaxuatban) VALUES (1, 'Kim Đồng');
INSERT INTO NhaXuatBan(manhaxuatban, tennhaxuatban) VALUES (2, 'Nhi Đồng');
INSERT INTO NhaXuatBan(manhaxuatban, tennhaxuatban) VALUES (3, 'Trẻ');
INSERT INTO NhaXuatBan(manhaxuatban, tennhaxuatban) VALUES (4, 'Giáo Dục');
INSERT INTO NhaXuatBan(manhaxuatban, tennhaxuatban) VALUES (5, 'Thanh Niên');

INSERT INTO DanhMucTaiLieu(madanhmuc, tendanhmuc) VALUES (1, 'Trinh thám');
INSERT INTO DanhMucTaiLieu(madanhmuc, tendanhmuc) VALUES (2, 'Tiểu thuyết');
INSERT INTO DanhMucTaiLieu(madanhmuc, tendanhmuc) VALUES (3, 'Ngôn tình');
INSERT INTO DanhMucTaiLieu(madanhmuc, tendanhmuc) VALUES (4, 'Truyện tranh');
INSERT INTO DanhMucTaiLieu(madanhmuc, tendanhmuc) VALUES (5, 'Khoa học');

INSERT INTO TaiLieu(matailieu, tentailieu, madanhmuc, tacgia, manhaxuatban, namxuatban, soluongcon, tomtatnoidung, gia)
    VALUES (1, 'Harry Potter', 2, 'J.K.',1 ,2006, 100,'Sách nổi bật', 150000);
INSERT INTO TaiLieu(matailieu, tentailieu, madanhmuc, tacgia, manhaxuatban, namxuatban, soluongcon, tomtatnoidung, gia)
    VALUES (2, '10 vạn câu hỏi vì sao', 5, 'Trí Nguy?n',1 ,2003, 200,'Sách bán chạy', 100000);
INSERT INTO TaiLieu(matailieu, tentailieu, madanhmuc, tacgia, manhaxuatban, namxuatban, soluongcon, tomtatnoidung, gia)
    VALUES (3, 'Chú cuội', 4, 'Phát Hoà',4 ,2008, 50,'Sách bán chạy', 75000);
INSERT INTO TaiLieu(matailieu, tentailieu, madanhmuc, tacgia, manhaxuatban, namxuatban, soluongcon, tomtatnoidung, gia)
    VALUES (4, 'Connan', 1, 'Aoyama',2 ,2006, 299,'Sách bán chạy', 45000);
INSERT INTO TaiLieu(matailieu, tentailieu, madanhmuc, tacgia, manhaxuatban, namxuatban, soluongcon, tomtatnoidung, gia)
    VALUES (5, 'Khó Dỗ Dành', 3, 'Trúc Dĩ',3 ,2010, 2,'Sách mới ra mắt', 600000);

INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (1, 'doc1', 'a', 'Nhung', '09/11/2003', 'nhung@gmail.com', 'Hà Nội', '023111', 'Online');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (2, 'doc2', 'b', 'Hùng', '02/08/2003', 'hung@gmail.com', 'Bắc Ninh', '1322', 'Offline');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (3, 'doc3', 'c', 'Giang', '02/02/2003', 'giang@gmail.com', 'Hà Nội', '4465465', 'Online');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (4, 'doc4', 'd', 'Anh', '15/07/2003', 'anh@gmail.com', 'Hà Nội', '787923', 'Offline');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (5, 'doc5', 'e', 'Hải', '19/03/2003', 'hai@gmail.com', 'Bắc Ninh', '544564', 'Online');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (6, 'doc6', 'f', 'An', '19/03/2003', 'an@gmail.com', 'Hà Nội', '3236569', 'Online');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (7, 'doc7', 'g', 'Nam', '19/03/2003', 'nam@gmail.com', 'Hà Nội', '7454721', 'Online');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (8, 'doc8', 'h', 'Phong', '19/03/2003', 'phong@gmail.com', 'Bắc Ninh', '1552187', 'Online');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (9, 'doc9', 'i', 'Vân', '19/03/2003', 'van@gmail.com', 'Hà Nội', '3321145', 'Online');
INSERT INTO DocGia(madocgia, tendangnhap, matkhau, tendocgia, ngaysinh, email, diachi, sodienthoai, trangthai)
    VALUES (10, 'doc10', 'j', 'Trang', '19/03/2003', 'trang@gmail.com', 'Hà Nội', '5644654', 'Offline');

INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (1, 'q', 'Lê Quân', '02/November/2003', 'Bắc Ninh', '6544564', 'lequan@gmail.com');
INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (2, 'w', 'Phan Anh', '12/January/2003', 'Hà Nội', '446564', 'phananh@gmail.com');
INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (3, 'e', 'Nguyễn Linh', '22/September/2003', 'Bắc Ninh', '7978', 'nguyenlinh@gmail.com');
INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (4, 'r', 'Hạ Hoa', '05/July/2003', 'Hà Nội', '6544564', 'hahoa@gmail.com');
INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (5, 't', 'Minh Quang', '15/November/2003', 'Hà Nội', '664451', 'minhquang@gmail.com');
    INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (6, 'y', 'Xuân Anh', '12/November/2003', 'Bắc Ninh', '5456452', 'xuananh@gmail.com');
INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (7, 'u', 'Ngô Hồng', '21/January/2003', 'Hà Nội', '781245', 'ngohong@gmail.com');
INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (8, 'i', 'Nguyễn Ánh', '06/September/2003', 'Bắc Ninh', '361245', 'nguyenanh@gmail.com');
INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (9, 'o', 'Liên Hoa', '18/July/2003', 'Hà Nội', '56452231', 'lienhoa@gmail.com');
INSERT INTO ThuThu(mathuthu, matkhau, hoten, ngaysinh, diachi, sodienthoai, email)
    VALUES (10, 'p', 'Minh Trúc', '22/November/2003', 'Hà Nội', '2187912', 'minhtruc@gmail.com');

INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (1, 2, '22/09/2023', 2, 1, 'Đã trả', 5);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (2, 3, '20/09/2023', 5, 1, 'Chưa trả', 4);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (3, 5, '10/09/2023', 4, 1, 'Chưa trả', 1);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (4, 4, '20/09/2023', 3, 1, 'Đã trả', 3);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (5, 1, '10/09/2023', 9, 1, 'Chưa trả', 2);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (6, 6, '22/09/2023', 2, 2, 'Đã trả', 6);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (7, 8, '21/09/2023', 5, 1, 'Chưa trả', 8);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (8, 9, '13/09/2023', 4, 3, 'Chưa trả', 10);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (9, 10, '23/09/2023', 3, 2, 'Đã trả', 8);
INSERT INTO PhieuMuon(maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon, trangthai, mathuthu)
    VALUES (10, 2, '26/09/2023', 9, 2, 'Chưa trả', 7);

INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (1, 3, '19/September/2023', 0, 'Nguyên vẹn');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (2, 5, '21/September/2023', 30000, 'Thiệt hại');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (3, 4, '22/September/2023', 0, 'Nguyên vẹn');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (4, 1, '22/September/2023', 20000, 'Thiệt hại');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (5, 2, '15/September/2023', 0, 'Nguyên vẹn');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (6, 2, '19/September/2023', 0, 'Nguyên vẹn');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (7, 3, '21/September/2023', 30000, 'Thiệt hại');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (8, 4, '22/September/2023', 0, 'Nguyên vẹn');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (9, 5, '22/September/2023', 20000, 'Thiệt hại');
INSERT INTO ChiTietPhieuMuon(maphieumuon, matailieu, ngaytra, tienphat, tinhtrangtailieu)
    VALUES (10, 6, '15/September/2023', 40000, 'Thiệt hại');

SELECT * FROM NhaXuatBan;
SELECT * FROM DanhMucTaiLieu;
SELECT * FROM TaiLieu;
SELECT * FROM DocGia;
SELECT * FROM ThuThu;
SELECT * FROM PhieuMuon;
SELECT * FROM ChiTietPhieuMuon;

//Truy van 1: 
SELECT * FROM ThuThu where diachi = 'Hà Nội';

//Truy van 2: 
SELECT maphieumuon, madocgia, ngaymuon, songaymuon, sotailieumuon FROM PhieuMuon where trangthai = 'Đã trả';

//Truy van 3: 
SELECT * FROM TaiLieu where Gia > 100000;

//Truy van 4: 
SELECT * FROM DocGia where madocgia in
(select madocgia from DocGia where trangthai = 'Offline');

//Truy van 5: 
SELECT matailieu, tienphat FROM ChiTietPhieuMuon where maphieumuon in
(select maphieumuon from ChiTietPhieuMuon where tinhtrangtailieu = 'Thiệt hại');

//Truy van 6: 
SELECT * FROM TaiLieu where matailieu in
(select matailieu from TaiLieu where gia < 100000);

//Truy van 7: 
SELECT PhieuMuon.maphieumuon, DocGia.tendocgia, PhieuMuon.ngaymuon, PhieuMuon.sotailieumuon, PhieuMuon.trangthai
FROM PhieuMuon inner join DocGia on PhieuMuon.madocgia = DocGia.madocgia;

//Truy van 8: 
SELECT  TaiLieu.matailieu, TaiLieu.tentailieu, DanhMucTaiLieu.tendanhmuc, NhaXuatBan.tennhaxuatban, TaiLieu.soluongcon, TaiLieu.gia
FROM (TaiLieu inner join NhaXuatBan on TaiLieu.manhaxuatban = NhaXuatBan.manhaxuatban)
inner join DanhMucTaiLieu on TaiLieu.madanhmuc = DanhMucTaiLieu.madanhmuc;
//Truy van 9: 
SELECT count(*) as SoNguoiOHaNoi
FROM docgia where diachi = 'Hà Nội'
//Truy van 10: 
SELECT tentailieu, count(*) 
FROM TaiLieu
GROUP BY tentailieu HAVING gia > 50000;
//Truy van 11: 

//Truy van 12: 




