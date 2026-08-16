CREATE DATABASE QLVPP;
GO

USE QLVPP;
GO
-- Tạo bảng --
CREATE TABLE SanPham (
    MaSP NVARCHAR(6) PRIMARY KEY,
    TenSP NVARCHAR(255),
    LoaiSP NVARCHAR(50),
    Gia INT,
    SoLuong INT,
    TrangThai NVARCHAR(15)
);
CREATE TABLE TaiKhoan (
    TaiKhoan NVARCHAR(20) PRIMARY KEY,
    MatKhau NVARCHAR(15),
    VaiTro NVARCHAR(10)
);

CREATE TABLE NhanVien (
    MaNV NVARCHAR(6) PRIMARY KEY,
	TaiKhoan NVARCHAR(20),
    TenNV NVARCHAR(50),
    GioiTinh NVARCHAR(10),
    NgaySinh DATE,
    DiaChi NVARCHAR(255),
    SDT NVARCHAR(10),
    TrangThai NVARCHAR(15),
	CONSTRAINT FK_TaiKhoan FOREIGN KEY (TaiKhoan) REFERENCES TaiKhoan(TaiKhoan) ON DELETE CASCADE
);

CREATE TABLE NhaCungCap (
    MaNCC NVARCHAR(6) PRIMARY KEY,
    TenNCC NVARCHAR(50),
    DiaChi NVARCHAR(50),
    SDT NVARCHAR(10)
);

CREATE TABLE PhieuNhap (
    MaPN INT IDENTITY(1,1) PRIMARY KEY,
    MaNV NVARCHAR(6),
    MaNCC NVARCHAR(6),
	NgayNhap DATE,
    TongTien INT,
    CONSTRAINT UC_MaNV_MaPN UNIQUE (MaNV, MaPN) -- Định nghĩa ràng buộc duy nhất
);

CREATE TABLE PhieuNhapChiTiet (
    MaPNCT INT IDENTITY(1,1) PRIMARY KEY,
    MaPN INT,
    MaSP NVARCHAR(6),
    SoLuong INT,
    ThanhTien INT,
    CONSTRAINT CHK_SoLuong CHECK (SoLuong >= 0), -- Ràng buộc kiểm tra SoLuong không âm
    CONSTRAINT FK_MaPN FOREIGN KEY (MaPN) REFERENCES PhieuNhap(MaPN) ON DELETE CASCADE -- Xóa các chi tiết khi phiếu nhập bị xóa
);

CREATE TABLE HoaDon (
    MaHD INT IDENTITY(1,1) PRIMARY KEY,
    MaNV NVARCHAR(6),
    NgayTaoHD DATETIME,
    TongTien INT,
    CONSTRAINT UC_MaNV_MaHD UNIQUE (MaNV, MaHD) -- Định nghĩa ràng buộc duy nhất
);

CREATE TABLE HoaDonChiTiet (
    MaHDCT INT IDENTITY(1,1) PRIMARY KEY,
    MaHD INT,
    MaSP NVARCHAR(6),
    SoLuong INT,
    ThanhTien INT,
    CONSTRAINT CHK_SoLuong_HD CHECK (SoLuong >= 0), -- Ràng buộc kiểm tra SoLuong không âm
    CONSTRAINT FK_MaHD FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD) ON DELETE CASCADE -- Xóa các chi tiết khi hóa đơn bị xóa
);

GO 
ALTER TABLE PhieuNhap ADD CONSTRAINT FK_MaNV_PN FOREIGN KEY (MaNV) REFERENCES NhanVien (MaNV) ON DELETE CASCADE;
ALTER TABLE PhieuNhap ADD CONSTRAINT FK_MaNCC_PN FOREIGN KEY (MaNCC) REFERENCES NhaCungCap (MaNCC) ON DELETE CASCADE;
ALTER TABLE HoaDon ADD CONSTRAINT FK_MaNV_HD FOREIGN KEY (MaNV) REFERENCES NhanVien (MaNV) ON DELETE CASCADE;
ALTER TABLE HoaDonChiTiet ADD CONSTRAINT FK_MaSP_HDCT FOREIGN KEY (MaSP) REFERENCES SanPham (MaSP) ON DELETE CASCADE; -- Xóa các chi tiết khi sản phẩm bị xóa

GO
-- Tạo các Trigger --
CREATE TRIGGER ThanhTien_PN
ON PhieuNhapChiTiet
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE PhieuNhapChiTiet
    SET ThanhTien = inserted.SoLuong * (SELECT Gia FROM SanPham WHERE SanPham.MaSP = inserted.MaSP)
    FROM PhieuNhapChiTiet
    INNER JOIN inserted ON PhieuNhapChiTiet.MaPNCT = inserted.MaPNCT;
END;

GO

CREATE TRIGGER TongTien_PN
ON PhieuNhapChiTiet
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    UPDATE PhieuNhap
    SET TongTien = (SELECT SUM(ThanhTien) FROM PhieuNhapChiTiet WHERE PhieuNhapChiTiet.MaPN = PhieuNhap.MaPN)
    WHERE MaPN IN (SELECT MaPN FROM inserted UNION SELECT MaPN FROM deleted);
END;

GO

CREATE TRIGGER ThanhTien_HD
ON HoaDonChiTiet
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE HoaDonChiTiet
    SET ThanhTien = inserted.SoLuong * (SELECT Gia FROM SanPham WHERE SanPham.MaSP = inserted.MaSP)
    FROM HoaDonChiTiet
    INNER JOIN inserted ON HoaDonChiTiet.MaHDCT = inserted.MaHDCT;
END;

GO

CREATE TRIGGER TongTien_HD
ON HoaDonChiTiet
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    UPDATE HoaDon
    SET TongTien = (SELECT SUM(ThanhTien) FROM HoaDonChiTiet WHERE HoaDonChiTiet.MaHD = HoaDon.MaHD)
    WHERE MaHD IN (SELECT MaHD FROM inserted UNION SELECT MaHD FROM deleted);
END;

GO


-- Insert data --
INSERT INTO SanPham (MaSP, TenSP, LoaiSP, Gia, SoLuong, TrangThai) VALUES 
('SP001', N'Bút bi', N'Bút', 5000, 100, N'Active'),
('SP002', N'Bút chì', N'Bút', 3000, 200, N'Active'),
('SP003', N'Giấy A4', N'Giấy', 50000, 500, N'Active'),
('SP004', N'Giấy ghi chú', N'Giấy', 20000, 300, N'Active'),
('SP005', N'Sổ tay', N'Sách vở', 20000, 150, N'Active'),
('SP006', N'Tập hồ sơ', N'Hồ sơ', 10000, 250, N'InActive'),
('SP007', N'Túi đựng hồ sơ', N'Hồ sơ', 15000, 300, N'Active'),
('SP008', N'Bìa cứng', N'Bìa', 7000, 200, N'InActive'),
('SP009', N'Bìa kẹp', N'Bìa', 25000, 150, N'Active'),
('SP010', N'Kẹp giấy', N'Dụng cụ văn phòng', 1000, 1000, N'InActive'),
('SP011', N'Bút lông', N'Dụng cụ văn phòng', 12000, 400, N'Active'),
('SP012', N'Keo dán', N'Dụng cụ văn phòng', 8000, 150, N'InActive'),
('SP013', N'Máy tính cầm tay', N'Dụng cụ văn phòng', 150000, 50, N'Active'),
('SP014', N'Thước kẻ', N'Dụng cụ văn phòng', 5000, 300, N'Active'),
('SP015', N'Băng dính', N'Dụng cụ văn phòng', 10000, 200, N'Active'),
('SP016', N'Tẩy bảng', N'Dụng cụ sửa chữa và bảo trì', 12000, 100, N'Active'),
('SP017', N'Bảng trắng', N'Dụng cụ sửa chữa và bảo trì', 500000, 50, N'Active'),
('SP018', N'Túi zip', N'Dụng cụ làm sạch và bảo dưỡng', 5000, 500, N'InActive'),
('SP019', N'Kẹp ghim', N'Dụng cụ làm sạch và bảo dưỡng', 2000, 800, N'Active'),
('SP020', N'Bảng tin', N'Đồ chơi và Trang trí', 200000, 30, N'InActive'),
('SP021', N'Bút màu', N'Bút', 6000, 200, N'Active'),
('SP022', N'Giấy A3', N'Giấy', 75000, 200, N'InActive'),
('SP023', N'Giấy note', N'Giấy', 25000, 500, N'Active'),
('SP024', N'Sổ tay ghi chú', N'Sách vở', 22000, 150, N'InActive'),
('SP025', N'Tập hồ sơ màu', N'Hồ sơ', 12000, 250, N'Active'),
('SP026', N'Túi đựng hồ sơ màu', N'Hồ sơ', 18000, 300, N'Active'),
('SP027', N'Bìa nhựa', N'Bìa', 9000, 200, N'InActive'),
('SP028', N'Bìa kẹp màu', N'Bìa', 27000, 150, N'Active'),
('SP029', N'Kẹp giấy màu', N'Dụng cụ văn phòng', 1500, 1000, N'InActive'),
('SP030', N'Bút lông màu', N'Dụng cụ văn phòng', 14000, 400, N'Active'),
('SP031', N'Keo dán giấy', N'Dụng cụ văn phòng', 8500, 150, N'InActive'),
('SP032', N'Máy tính khoa học', N'Dụng cụ văn phòng', 200000, 50, N'Active'),
('SP033', N'Thước kẻ nhựa', N'Dụng cụ văn phòng', 5500, 300, N'Active'),
('SP034', N'Băng dính màu', N'Dụng cụ văn phòng', 12000, 200, N'Active'),
('SP035', N'Tẩy mực', N'Dụng cụ sửa chữa và bảo trì', 15000, 100, N'Active'),
('SP036', N'Bảng đen', N'Dụng cụ sửa chữa và bảo trì', 550000, 50, N'InActive'),
('SP037', N'Túi zip lớn', N'Dụng cụ làm sạch và bảo dưỡng', 6000, 500, N'Active'),
('SP038', N'Kẹp ghim lớn', N'Dụng cụ làm sạch và bảo dưỡng', 2500, 800, N'InActive'),
('SP039', N'Bảng tin màu', N'Đồ chơi và Trang trí', 250000, 30, N'Active'),
('SP040', N'Thuốc lau kính', N'Dụng cụ làm sạch và bảo dưỡng', 15000, 200, N'InActive');

GO


INSERT INTO TaiKhoan (TaiKhoan, MatKhau, VaiTro)
VALUES
    ('user001', '1234', 'Admin'),
    ('user002', '2345', 'User'),
    ('user003', '3456', 'User'),
    ('user004', '4567', 'User'),
    ('user005', '5678', 'User'),
    ('user006', '6789', 'User'),
    ('user007', '7890', 'User'),
    ('user008', '8901', 'User'),
    ('user009', '9012', 'User'),
    ('user010', '0123', 'User');
GO
N'Phạm Gia Minh', N'Nam', '1990-01-01', N'Hoàn Kiếm, Hà Nội', '0123456789'
N'Bùi Thảo Anh', N'Nữ', '1995-03-15', N'Đống Đa, Hà Nội', '0987654321',
N'Đào Tuấn Phong', N'Nam', '1985-05-20', N'Ba Đình, Hà Nội', '0369847521'
 N'Lưu Trang Anh', N'Nữ', '1992-07-10', N'Tây Hồ, Hà Nội', '0932154876',
 N'Lê Gia Bảo', N'Nam', '1988-09-25', N'Cầu Giấy, Hà Nội', '0657891234'
  N'Nguyễn Yến Nhi', N'Nữ', '1993-11-30', N'Long Biên, Hà Nội', '0963258741',
   N'Hà Duy Anh', N'Nam', '1996-02-05', N'Hai Bà Trưng, Hà Nội','0987541236'
    N'Trần Bảo Thy', N'Nữ', '1986-04-18', N'Hoàng Mai, Hà Nội', '0978563412',
	 N'Mai Tùng Bách', N'Nam', '1991-06-22', N'Thanh Xuân, Hà Nội', '0398452167',
	  N'Vũ Phương Thảo', N'Nữ', '1987-08-12', N'Nam Từ Liêm, Hà Nội', '0765987412',


INSERT INTO NhanVien (MaNV, TaiKhoan, TenNV, GioiTinh, NgaySinh, DiaChi, SDT, TrangThai)
VALUES
    ('NV001', 'user001',N'Phạm Gia Minh', N'Nam', '1990-01-01', N'Hoàn Kiếm, Hà Nội', '0123456789', N'Active'),
    ('NV002', 'user002', N'Bùi Thảo Anh', N'Nữ', '1995-03-15', N'Đống Đa, Hà Nội', '0987654321', N'Active'),
    ('NV003', 'user003', N'Đào Tuấn Phong', N'Nam', '1985-05-20', N'Ba Đình, Hà Nội', '0369847521', N'Active'),
    ('NV004', 'user004', N'Lưu Trang Anh', N'Nữ', '1992-07-10', N'Tây Hồ, Hà Nội', '0932154876', N'Active'),
    ('NV005', 'user005', N'Lê Gia Bảo', N'Nam', '1988-09-25', N'Cầu Giấy, Hà Nội', '0657891234', N'Active'),
    ('NV006', 'user006', N'Nguyễn Yến Nhi', N'Nữ', '1993-11-30', N'Long Biên, Hà Nội', '0963258741', N'Active'),
    ('NV007', 'user007', N'Hà Duy Anh', N'Nam', '1996-02-05', N'Hai Bà Trưng, Hà Nội', '0987541236', N'Active'),
    ('NV008', 'user008', N'Trần Bảo Thy', N'Nữ', '1986-04-18', N'Hoàng Mai, Hà Nội', '0978563412', N'Active'),
    ('NV009', 'user009',  N'Mai Tùng Bách', N'Nam', '1991-06-22', N'Thanh Xuân, Hà Nội', '0398452167', N'Active'),
    ('NV010', 'user010', N'Vũ Phương Thảo', N'Nữ', '1987-08-12', N'Nam Từ Liêm, Hà Nội', '0765987412', N'Active');
GO

INSERT INTO NhaCungCap (MaNCC, TenNCC, DiaChi, SDT)
VALUES
    ('NCC001', N'Nhà cung cấp Thiên Long', N'Trung Hoà, Hà Nội', '0987100200'),
    ('NCC002', N'Nhà cung cấp Văn Phòng Xanh', N'Trương Định, Hà Nội', '0976200310'),
    ('NCC003', N'Nhà cung cấp Hồng Hà', N'Hồng Hà, Hà Nội', '0915300420'),
    ('NCC004', N'Nhà cung cấp Thăng Long', N'Ngọc Thụy, Hà Nội', '0932400530'),
    ('NCC005', N'Nhà cung cấp Văn Phòng Việt', N'Phú Thượng, Hà Nội', '0967500640'),
    ('NCC006', N'Nhà cung cấp Sao Mai', N'Yên Hòa, Hà Nội', '0978600750'),
    ('NCC007', N'Nhà cung cấp Đông Á', N'Trần Duy Hưng, Hà Nội', '0989700860'),
    ('NCC008', N'Nhà cung cấp Đại Dương', N'Kim Giang, Hà Nội', '0910800970'),
    ('NCC009', N'Nhà cung cấp Hòa Bình', N'Linh Đàm, Hà Nội', '0932901080'),
    ('NCC010', N'Nhà cung cấp Việt Tiến', N'Thượng Đình, Hà Nội', '0943101190');
GO

INSERT INTO PhieuNhap (MaNV, MaNCC, NgayNhap)
VALUES
    ('NV001', 'NCC001', '2024-06-01'),
    ('NV002', 'NCC002', '2024-06-02'),
    ('NV003', 'NCC003', '2024-06-03'),
    ('NV004', 'NCC004', '2024-06-04'),
    ('NV005', 'NCC005', '2024-06-05'),
    ('NV006', 'NCC006', '2024-06-06'),
    ('NV007', 'NCC007', '2024-06-07'),
    ('NV008', 'NCC008', '2024-06-08'),
    ('NV009', 'NCC009', '2024-06-09'),
    ('NV010', 'NCC010', '2024-06-10'),
    ('NV001', 'NCC002', '2024-06-11'),
    ('NV002', 'NCC003', '2024-06-11'),
    ('NV003', 'NCC004', '2024-06-12'),
    ('NV004', 'NCC005', '2024-06-12'),
    ('NV005', 'NCC006', '2024-06-13'),
    ('NV006', 'NCC006', '2024-06-13'),
    ('NV007', 'NCC007', '2024-06-14'),
    ('NV008', 'NCC008', '2024-06-14'),
    ('NV009', 'NCC009', '2024-06-15'),
    ('NV010', 'NCC010', '2024-06-15');

GO

INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (1, 'SP001', 10),
    (1, 'SP002', 20);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (2, 'SP003', 15),
    (2, 'SP004', 5),
    (2, 'SP005', 10);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (3, 'SP006', 15),
    (3, 'SP007', 20),
    (3, 'SP008', 5),
    (3, 'SP009', 10);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (4, 'SP010', 8),
    (4, 'SP011', 12),
    (4, 'SP012', 6),
    (4, 'SP013', 4);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (5, 'SP014', 10),
    (5, 'SP015', 15),
    (5, 'SP016', 5),
    (5, 'SP017', 2);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (6, 'SP018', 20),
    (6, 'SP019', 25),
    (6, 'SP020', 15),
    (6, 'SP021', 10);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (7, 'SP022', 10),
    (7, 'SP023', 20),
    (7, 'SP024', 15);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (8, 'SP025', 5),
    (8, 'SP026', 10);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (9, 'SP027', 15),
    (9, 'SP028', 20),
    (9, 'SP029', 25);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (10, 'SP030', 5),
    (10, 'SP031', 10),
    (10, 'SP032', 15),
    (10, 'SP033', 20);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (11, 'SP034', 10),
    (11, 'SP035', 15);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (12, 'SP036', 20),
    (12, 'SP037', 25),
    (12, 'SP038', 15),
    (12, 'SP039', 22),
	(12, 'SP040', 10);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (13, 'SP001', 10),
    (13, 'SP002', 20);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (14, 'SP003', 15),
    (14, 'SP004', 5),
    (14, 'SP005', 10);
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (15, 'SP006', 15),
    (15, 'SP007', 20),
    (15, 'SP008', 5),
    (15, 'SP009', 10);

GO

INSERT INTO HoaDon (MaNV, NgayTaoHD)
VALUES 
    ('NV003', '2024-06-11 08:30:00'),
    ('NV004', '2024-06-11 10:15:00'),
    ('NV005', '2024-06-11 13:20:00'),
    ('NV006', '2024-06-11 15:45:00'),
    ('NV007', '2024-06-12 09:00:00'),
    ('NV008', '2024-06-12 11:30:00'),
    ('NV009', '2024-06-12 14:00:00'),
    ('NV010', '2024-06-12 16:45:00'),
    ('NV001', '2024-06-13 08:45:00'),
    ('NV002', '2024-06-13 12:15:00');
GO

INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (1, 'SP001', 5),
    (1, 'SP002', 7),
    (1, 'SP003', 9),
    (1, 'SP004', 6),
    (1, 'SP005', 8);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (2, 'SP006', 6),
    (2, 'SP007', 8),
    (2, 'SP008', 5),
    (2, 'SP009', 7);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (3, 'SP010', 7),
    (3, 'SP011', 5),
    (3, 'SP012', 6),
    (3, 'SP013', 9);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (4, 'SP014', 5),
    (4, 'SP015', 7),
    (4, 'SP016', 8),
    (4, 'SP017', 6),
    (4, 'SP018', 9);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (5, 'SP019', 6),
    (5, 'SP020', 8),
    (5, 'SP021', 5),
    (5, 'SP022', 7),
    (5, 'SP023', 4),
    (5, 'SP024', 6);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (6, 'SP025', 5),
    (6, 'SP026', 6),
    (6, 'SP027', 8),
    (6, 'SP028', 7),
    (6, 'SP029', 3),
    (6, 'SP030', 9),
    (6, 'SP031', 4);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (7, 'SP032', 9),
    (7, 'SP033', 7),
    (7, 'SP034', 5),
    (7, 'SP035', 6),
    (7, 'SP036', 3);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (8, 'SP037', 8),
    (8, 'SP038', 5),
    (8, 'SP039', 7),
    (8, 'SP040', 4),
    (8, 'SP001', 6),
    (8, 'SP002', 9);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (9, 'SP003', 7),
    (9, 'SP004', 5),
    (9, 'SP005', 6),
    (9, 'SP006', 8),
    (9, 'SP007', 3),
    (9, 'SP008', 9),
    (9, 'SP009', 4);
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES 
    (10, 'SP010', 5),
    (10, 'SP011', 6),
    (10, 'SP012', 8),
    (10, 'SP013', 3),
    (10, 'SP014', 9),
    (10, 'SP015', 4);



--  Bút, Giấy, Sách vở, Dụng cụ văn phòng, Hồ sơ, Bìa, Dụng cụ sửa chữa và bảo trì, Dụng cụ làm sạch và bảo dưỡng, Đồ chơi và Trang trí
GO

select * from SanPham where TrangThai = 'Active'
select * from NhaCungCap
select * from NhanVien where TrangThai = 'Active'

select * from PhieuNhap
select * from PhieuNhapChiTiet
select * from HoaDon
select * from HoaDonChiTiet

select sum(TongTien) as tien from HoaDon where MONTH(NgayTaoHD) = 6
select sum(TongTien) as tien from PhieuNhap where MONTH(NgayNhap) = 6

select HoaDon.MaHD, NhanVien.TenNV, SanPham.TenSP, HoaDonChiTiet.SoLuong, HoaDonChiTiet.ThanhTien, HoaDon.NgayTaoHD, HoaDon.TongTien
from HoaDon inner join HoaDonChiTiet on HoaDon.MaHD = HoaDonChiTiet.MaHD
inner join SanPham on HoaDonChiTiet.MaSP = SanPham.MaSP
inner join NhanVien on HoaDon.MaNV = NhanVien.MaNV
where HoaDon.MaHD = 5

select PhieuNhap.MaPN, NhanVien.TenNV, NhaCungCap.TenNCC, SanPham.TenSP, PhieuNhapChiTiet.SoLuong, PhieuNhapChiTiet.ThanhTien, PhieuNhap.NgayNhap, PhieuNhap.TongTien
from PhieuNhap inner join PhieuNhapChiTiet 
on PhieuNhap.MaPN = PhieuNhapChiTiet.MaPN
inner join SanPham on PhieuNhapChiTiet.MaSP = SanPham.MaSP
inner join NhaCungCap on PhieuNhap.MaNCC = NhaCungCap.MaNCC
inner join NhanVien on PhieuNhap.MaNV = NhanVien.MaNV
where PhieuNhap.MaPN = 5

-- INSERT INTO HoaDon (MaNV, NgayTaoHD) VALUES ('NV010', GETDATE()); 