CREATE DATABASE QLCHVPP;
GO
USE QLCHVPP;
GO
-- Bảng quyền
CREATE TABLE Quyen (
    MaQuyen INT NOT NULL PRIMARY KEY,
    TenQuyen NVARCHAR(10)
);
GO

-- Bảng loại sản phẩm
CREATE TABLE LoaiSP (
    MaLoai INT NOT NULL PRIMARY KEY IDENTITY,
    TenLoai NVARCHAR(50)
);
GO

-- Bảng nhà cung cấp
CREATE TABLE NhaCungCap (
    MaNCC NVARCHAR(6) NOT NULL PRIMARY KEY,
    TenNCC NVARCHAR(50),
    DiaChi NVARCHAR(50),
    SDT NVARCHAR(10),
    CONSTRAINT UK_NhaCungCap_MaNCC UNIQUE (MaNCC)
);
GO

-- Bảng tài khoản
CREATE TABLE TaiKhoan (
    MaTK NVARCHAR(10) NOT NULL PRIMARY KEY,
    TaiKhoan NVARCHAR(20) UNIQUE,
    MatKhau NVARCHAR(15),
    MaQuyen INT,
    CONSTRAINT UK_TaiKhoan_MaTK UNIQUE (MaTK),
    CONSTRAINT FK_TaiKhoan_MaQuyen FOREIGN KEY (MaQuyen) REFERENCES Quyen(MaQuyen) ON DELETE CASCADE
);
GO

-- Bảng sản phẩm
CREATE TABLE SanPham (
    MaSP NVARCHAR(6) NOT NULL PRIMARY KEY,
    TenSP NVARCHAR(255),
    MaLoai INT,
    Gia INT,
    SoLuong INT,
    CONSTRAINT UK_SanPham_MaSP UNIQUE (MaSP),
    CONSTRAINT FK_SanPham_MaLoai FOREIGN KEY (MaLoai) REFERENCES LoaiSP(MaLoai) ON DELETE CASCADE
);
GO

-- Bảng nhân viên
CREATE TABLE NhanVien (
    MaNV NVARCHAR(6) NOT NULL PRIMARY KEY,
    MaTK NVARCHAR(10) UNIQUE,
    TenNV NVARCHAR(50),
    GioiTinh NVARCHAR(10),
    NgaySinh DATE,
    DiaChi NVARCHAR(255),
    SDT NVARCHAR(10),
    CONSTRAINT UK_NhanVien_MaNV UNIQUE (MaNV),
    CONSTRAINT FK_NhanVien_TaiKhoan FOREIGN KEY (MaTK) REFERENCES TaiKhoan(MaTK) ON DELETE CASCADE
);
GO

-- Bảng phiếu nhập
CREATE TABLE PhieuNhap (
    MaPN INT NOT NULL PRIMARY KEY IDENTITY,
    MaNV NVARCHAR(6),
    MaNCC NVARCHAR(6),
    NgayNhap DATE,
    TongTien INT,
    CONSTRAINT FK_PhieuNhap_MaNV FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON DELETE CASCADE,
    CONSTRAINT FK_PhieuNhap_MaNCC FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(MaNCC) ON DELETE CASCADE
);
GO

-- Bảng chi tiết phiếu nhập
CREATE TABLE PhieuNhapChiTiet (
    MaPNCT INT NOT NULL PRIMARY KEY IDENTITY,
    MaPN INT,
    MaSP NVARCHAR(6),
    SoLuong INT,
    ThanhTien INT,
	CONSTRAINT CHK_SoLuong_PN CHECK (SoLuong >= 0),
    CONSTRAINT FK_PhieuNhapChiTiet_MaPN FOREIGN KEY (MaPN) REFERENCES PhieuNhap(MaPN) ON DELETE CASCADE,
    CONSTRAINT FK_PhieuNhapChiTiet_MaSP FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP) ON DELETE CASCADE
);
GO

-- Bảng hóa đơn
CREATE TABLE HoaDon (
    MaHD INT NOT NULL PRIMARY KEY IDENTITY,
    MaNV NVARCHAR(6),
    NgayTaoHD DATE,
    TongTien INT,
    CONSTRAINT FK_HoaDon_MaNV FOREIGN KEY (MaNV) REFERENCES NhanVien(MaNV) ON DELETE CASCADE
);
GO

-- Bảng chi tiết hóa đơn
CREATE TABLE HoaDonChiTiet (
    MaHDCT INT NOT NULL PRIMARY KEY IDENTITY,
    MaHD INT,
    MaSP NVARCHAR(6),
    SoLuong INT,
    ThanhTien INT,
	CONSTRAINT CHK_SoLuong_HD CHECK (SoLuong >= 0), 
    CONSTRAINT FK_HoaDonChiTiet_MaHD FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD) ON DELETE CASCADE,
    CONSTRAINT FK_HoaDonChiTiet_MaSP FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP) ON DELETE CASCADE
);
GO

GO
-- Trigger tính lại Thành tiền của Phiếu nhập chi tiết sau khi Insert hoặc Update
CREATE OR ALTER TRIGGER ThanhTien_PN
ON PhieuNhapChiTiet
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE PNT
    SET ThanhTien = I.SoLuong * SP.Gia
    FROM PhieuNhapChiTiet AS PNT
    INNER JOIN inserted AS I ON PNT.MaPNCT = I.MaPNCT
    INNER JOIN SanPham AS SP ON SP.MaSP = I.MaSP;
END;
GO

-- Trigger tính lại Thành tiền của Phiếu nhập chi tiết sau khi Insert hoặc Update
CREATE OR ALTER TRIGGER ThanhTien_PN
ON PhieuNhapChiTiet
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE PNT
    SET ThanhTien = I.SoLuong * SP.Gia
    FROM PhieuNhapChiTiet AS PNT
    INNER JOIN inserted AS I ON PNT.MaPNCT = I.MaPNCT
    INNER JOIN SanPham AS SP ON SP.MaSP = I.MaSP;
END;
GO

-- Trigger tính lại Tổng tiền của Phiếu nhập sau khi Insert, Update hoặc Delete Phiếu nhập chi tiết
CREATE OR ALTER TRIGGER TongTien_PN
ON PhieuNhapChiTiet
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    UPDATE PN
    SET TongTien = ISNULL((
        SELECT SUM(PNT.ThanhTien)
        FROM PhieuNhapChiTiet AS PNT
        WHERE PNT.MaPN = PN.MaPN
    ), 0)
    FROM PhieuNhap AS PN
    INNER JOIN (
        SELECT DISTINCT MaPN FROM inserted
        UNION
        SELECT DISTINCT MaPN FROM deleted
    ) AS Changes ON PN.MaPN = Changes.MaPN;
END;
GO

-- Trigger tính lại Thành tiền của Hóa đơn chi tiết sau khi Insert hoặc Update
CREATE OR ALTER TRIGGER ThanhTien_HD
ON HoaDonChiTiet
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE HDC
    SET ThanhTien = I.SoLuong * SP.Gia
    FROM HoaDonChiTiet AS HDC
    INNER JOIN inserted AS I ON HDC.MaHDCT = I.MaHDCT
    INNER JOIN SanPham AS SP ON SP.MaSP = I.MaSP;
END;
GO

-- Trigger tính lại Tổng tiền của Hóa đơn sau khi Insert, Update hoặc Delete Hóa đơn chi tiết
CREATE OR ALTER TRIGGER TongTien_HD
ON HoaDonChiTiet
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    UPDATE HD
    SET TongTien = ISNULL((
        SELECT SUM(HDC.ThanhTien)
        FROM HoaDonChiTiet AS HDC
        WHERE HDC.MaHD = HD.MaHD
    ), 0)
    FROM HoaDon AS HD
    INNER JOIN (
        SELECT DISTINCT MaHD FROM inserted
        UNION
        SELECT DISTINCT MaHD FROM deleted
    ) AS Changes ON HD.MaHD = Changes.MaHD;
END;
GO

-- Chèn dữ liệu vào bảng Quyen
INSERT INTO Quyen (MaQuyen, TenQuyen)
VALUES (1, N'Admin'),
       (2, N'User');
GO
-- Chèn dữ liệu vào bảng LoaiSP
INSERT INTO LoaiSP (TenLoai)
VALUES (N'Bút'),
       (N'Giấy'),
       (N'Sách vở'),
       (N'Hồ sơ'),
       (N'Bìa'),
       (N'Dụng cụ văn phòng'),
       (N'Dụng cụ sửa chữa và bảo trì'),
       (N'Dụng cụ làm sạch và bảo dưỡng'),
       (N'Đồ chơi và Trang trí');
GO
-- Chèn dữ liệu vào bảng NhaCungCap
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
-- Chèn dữ liệu vào bảng TaiKhoan
INSERT INTO TaiKhoan (MaTK, TaiKhoan, MatKhau, MaQuyen)
VALUES
    ('TK001', 'admin01', '1234', 1),
    ('TK002', 'user01', '2345', 2),
    ('TK003', 'admin02', '3456', 1),
    ('TK004', 'user02', '4567', 2),
    ('TK005', 'admin03', '5678', 1),
    ('TK006', 'user03', '6789', 2),
    ('TK007', 'admin04', '7890', 1),
    ('TK008', 'user04', '8901', 2),
    ('TK009', 'admin05', '9012', 1),
    ('TK010', 'user05', '0123', 2);
-- Chèn dữ liệu vào bảng SanPham
GO
INSERT INTO SanPham (MaSP, TenSP, MaLoai, Gia, SoLuong)
VALUES
    ('SP001', N'Bút bi Thiên Long', 1, 3000, 100),
    ('SP002', N'Bút bi Hồng Hà', 1, 3200, 150),
    ('SP003', N'Bút chì Thiên Long', 1, 2000, 200),
    ('SP004', N'Bút chì Hồng Hà', 1, 2200, 180),
    ('SP005', N'Giấy A4 Double A', 2, 50000, 300),
    ('SP006', N'Giấy A4 PaperOne', 2, 48000, 350),
    ('SP007', N'Giấy Note vàng', 2, 15000, 400),
    ('SP008', N'Giấy Note xanh', 2, 16000, 350),
    ('SP009', N'Sách vở Bút Chì', 3, 25000, 500),
    ('SP010', N'Sách vở Gáy Lò', 3, 26000, 450),
    ('SP011', N'Hồ sơ Giấy', 4, 12000, 600),
    ('SP012', N'Hồ sơ Nhựa', 4, 14000, 550),
    ('SP013', N'Bìa cứng', 5, 18000, 650),
    ('SP014', N'Bìa nhựa', 5, 20000, 700),
    ('SP015', N'Kéo cắt giấy', 6, 10000, 800),
    ('SP016', N'Dập ghim', 6, 15000, 750),
    ('SP017', N'Bút dạ quang', 6, 12000, 900),
    ('SP018', N'Tẩy bút chì', 6, 5000, 850),
    ('SP019', N'Tua vít', 7, 25000, 300),
    ('SP020', N'Cờ lê', 7, 22000, 350),
    ('SP021', N'Máy khoan', 7, 150000, 100),
    ('SP022', N'Kìm cắt', 7, 30000, 150),
    ('SP023', N'Nước rửa tay', 8, 40000, 500),
    ('SP024', N'Chổi quét nhà', 8, 15000, 450),
    ('SP025', N'Khăn lau', 8, 20000, 600),
    ('SP026', N'Nước lau sàn', 8, 50000, 550),
    ('SP027', N'Đồ chơi trẻ em', 9, 30000, 700),
    ('SP028', N'Trang trí sinh nhật', 9, 25000, 650),
    ('SP029', N'Trang trí Noel', 9, 50000, 600),
    ('SP030', N'Bóng bay', 9, 10000, 800),
    ('SP031', N'Kẹo mút', 9, 5000, 900),
    ('SP032', N'Súng nước', 9, 35000, 400),
    ('SP033', N'Đèn lồng', 9, 45000, 350),
    ('SP034', N'Màu vẽ', 6, 25000, 500),
    ('SP035', N'Giấy A4', 2, 30000, 500),
    ('SP036', N'Bút chì', 1, 5000, 800),
    ('SP037', N'Tập vở', 3, 20000, 400),
    ('SP038', N'Hồ sơ A4', 4, 15000, 300),
    ('SP039', N'Bìa hồ sơ', 5, 5000, 700),
    ('SP040', N'Kẹp file', 6, 8000, 600);
GO
-- Chèn dữ liệu vào bảng NhanVien
INSERT INTO NhanVien (MaNV, MaTK, TenNV, GioiTinh, NgaySinh, DiaChi, SDT)
VALUES
    ('NV001', 'TK001', N'Phạm Gia Minh', N'Nam', '1990-01-01', N'Hoàn Kiếm, Hà Nội', '0123456789'),
    ('NV002', 'TK002', N'Bùi Thảo Anh', N'Nữ', '1995-03-15', N'Đống Đa, Hà Nội', '0987654321'),
    ('NV003', 'TK003', N'Đào Tuấn Phong', N'Nam', '1985-05-20', N'Ba Đình, Hà Nội', '0369847521'),
    ('NV004', 'TK004', N'Lưu Trang Anh', N'Nữ', '1992-07-10', N'Tây Hồ, Hà Nội', '0932154876'),
    ('NV005', 'TK005', N'Lê Gia Bảo', N'Nam', '1988-09-25', N'Cầu Giấy, Hà Nội', '0657891234'),
    ('NV006', 'TK006', N'Nguyễn Yến Nhi', N'Nữ', '1993-11-30', N'Long Biên, Hà Nội', '0963258741'),
    ('NV007', 'TK007', N'Hà Duy Anh', N'Nam', '1996-02-05', N'Hai Bà Trưng, Hà Nội', '0987541236'),
    ('NV008', 'TK008', N'Trần Bảo Thy', N'Nữ', '1986-04-18', N'Hoàng Mai, Hà Nội', '0978563412'),
    ('NV009', 'TK009', N'Mai Tùng Bách', N'Nam', '1991-06-22', N'Thanh Xuân, Hà Nội', '0398452167'),
    ('NV010', 'TK010', N'Vũ Phương Thảo', N'Nữ', '1987-08-12', N'Nam Từ Liêm, Hà Nội', '0765987412');
GO
-- Chèn dữ liệu vào bảng PhieuNhap
INSERT INTO PhieuNhap (MaNV, MaNCC, NgayNhap)
VALUES
    ('NV001', 'NCC001', '2024-06-25'),
    ('NV002', 'NCC002', '2024-06-25'),
    ('NV003', 'NCC003', '2024-06-26'),
    ('NV004', 'NCC004', '2024-06-26'),
    ('NV005', 'NCC005', '2024-06-27'),
    ('NV006', 'NCC006', '2024-06-27'),
    ('NV007', 'NCC007', '2024-06-28'),
    ('NV008', 'NCC008', '2024-06-28'),
    ('NV009', 'NCC009', '2024-06-29'),
    ('NV010', 'NCC010', '2024-06-29');
GO
-- Chèn dữ liệu vào bảng PhieuNhapChiTiet
INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
VALUES
    (1, 'SP001', 15),
    (1, 'SP002', 10),
    (1, 'SP003', 18),
    (1, 'SP004', 12),
    (1, 'SP005', 8),
    (2, 'SP006', 15),
    (2, 'SP007', 20),
    (2, 'SP008', 18),
    (2, 'SP009', 10),
    (2, 'SP010', 12),
    (3, 'SP011', 15),
    (3, 'SP012', 10),
    (3, 'SP013', 18),
    (3, 'SP014', 12),
    (3, 'SP015', 8),
    (4, 'SP016', 15),
    (4, 'SP017', 20),
    (4, 'SP018', 18),
    (4, 'SP019', 10),
    (4, 'SP020', 12),
    (5, 'SP021', 15),
    (5, 'SP022', 10),
    (5, 'SP023', 18),
    (5, 'SP024', 12),
    (5, 'SP025', 8),
    (6, 'SP026', 15),
    (6, 'SP027', 20),
    (6, 'SP028', 18),
    (6, 'SP029', 10),
    (6, 'SP030', 12),
    (7, 'SP031', 15),
    (7, 'SP032', 10),
    (7, 'SP033', 18),
    (7, 'SP034', 12),
    (8, 'SP035', 8),
    (8, 'SP036', 15),
    (9, 'SP037', 20),
    (9, 'SP038', 18),
    (10, 'SP039', 10),
    (10, 'SP040', 12);
GO
-- Chèn dữ liệu vào bảng HoaDon
INSERT INTO HoaDon (MaNV, NgayTaoHD)
VALUES
    ('NV001', '2024-06-20'),
    ('NV002', '2024-06-21'),
    ('NV003', '2024-06-22'),
    ('NV004', '2024-06-23'),
    ('NV005', '2024-06-24'),
    ('NV006', '2024-06-25'),
    ('NV007', '2024-06-26'),
    ('NV008', '2024-06-27'),
    ('NV009', '2024-06-28'),
    ('NV010', '2024-06-29'),
    ('NV001', '2024-06-20'),
    ('NV002', '2024-06-21'),
    ('NV003', '2024-06-22'),
    ('NV004', '2024-06-23'),
    ('NV005', '2024-06-24');
GO
-- Chèn dữ liệu vào bảng HoaDonChiTiet
INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
VALUES
    (1, 'SP001', 5),
    (1, 'SP002', 3),
    (1, 'SP003', 2),
    (2, 'SP004', 4),
    (2, 'SP005', 6),
    (2, 'SP006', 7),
    (3, 'SP007', 8),
    (3, 'SP008', 9),
    (3, 'SP009', 10),
    (4, 'SP010', 11),
    (4, 'SP011', 12),
    (4, 'SP012', 13),
    (5, 'SP013', 14),
    (5, 'SP014', 15),
    (5, 'SP015', 16),
    (6, 'SP016', 17),
    (6, 'SP017', 18),
    (6, 'SP018', 19),
    (7, 'SP019', 20),
    (7, 'SP020', 19),
    (7, 'SP021', 18),
    (8, 'SP022', 17),
    (8, 'SP023', 16),
    (8, 'SP024', 15),
    (9, 'SP025', 14),
    (9, 'SP026', 13),
    (9, 'SP027', 12),
    (10, 'SP028', 11),
    (10, 'SP029', 10),
    (10, 'SP030', 9),
    (11, 'SP031', 8),
    (11, 'SP032', 7),
    (11, 'SP033', 6),
    (12, 'SP034', 5),
    (12, 'SP035', 4),
    (12, 'SP036', 3),
    (13, 'SP037', 2),
    (13, 'SP038', 1),
    (14, 'SP039', 2),
    (15, 'SP040', 3);
GO
-- Truy vấn 

SELECT * FROM Quyen;
SELECT * FROM LoaiSP;
SELECT * FROM NhaCungCap;
SELECT * FROM TaiKhoan;
SELECT * FROM SanPham;
SELECT * FROM NhanVien;
SELECT * FROM PhieuNhap;
SELECT * FROM PhieuNhapChiTiet;
SELECT * FROM HoaDon;
SELECT * FROM HoaDonChiTiet;

-- Proc NhaCungCap
-- Tạo kiểu dữ liệu bảng NCC_Type
CREATE TYPE NCC_Type AS TABLE (
	MaNCC NVARCHAR(6),
	TenNCC NVARCHAR(50),
	DiaChi NVARCHAR(50),
	SDT NVARCHAR(10)
);
GO

-- Tạo stored procedure themNCC
CREATE PROCEDURE ThemNhaCungCap
    @MaNCC NVARCHAR(6),
    @TenNCC NVARCHAR(50),
    @DiaChi NVARCHAR(50),
    @SDT NVARCHAR(10)
AS
BEGIN
    INSERT INTO NhaCungCap (MaNCC, TenNCC, DiaChi, SDT)
    VALUES (@MaNCC, @TenNCC, @DiaChi, @SDT);
END;
GO


GO
-- Tạo stored procedure suaNCC
CREATE PROCEDURE SuaNhaCungCap
    @MaNCC NVARCHAR(6),
    @TenNCC NVARCHAR(50),
    @DiaChi NVARCHAR(50),
    @SDT NVARCHAR(10)
AS
BEGIN
    UPDATE NhaCungCap
    SET TenNCC = @TenNCC,
        DiaChi = @DiaChi,
        SDT = @SDT
    WHERE MaNCC = @MaNCC;
END;
GO

GO

-- Stored procedure xoaNhaCungCap
CREATE PROCEDURE xoaNhaCungCap
    @MaNCC NVARCHAR(6),
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM NhaCungCap
        WHERE MaNCC = @MaNCC;

        SET @mess = N'Xóa nhà cung cấp thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @MaNCC NVARCHAR(6) = 'NCC011'; 
	DECLARE @mess NVARCHAR(1000);
	EXEC xoaNhaCungCap @MaNCC, @mess OUTPUT;
	PRINT @mess; 
	SELECT * FROM NhaCungCap;
	*/
-- Tạo stored procedure searchNCC
CREATE PROCEDURE timkiemNhaCungCap
	@TenNCC NVARCHAR(50),
	@mess NVARCHAR(1000) OUTPUT
AS
BEGIN
	BEGIN TRY
		SELECT MaNCC, TenNCC, DiaChi, SDT
		FROM NhaCungCap
		WHERE TenNCC LIKE '%' + @TenNCC + '%';

		SET @mess = N'Tìm kiếm thành công';
	END TRY
	BEGIN CATCH
		SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
	END CATCH
END;
GO
	/* Test: ok
	DECLARE @TenNCC NVARCHAR(50) = N'Đ';
	DECLARE @mess NVARCHAR(1000);
	EXEC timkiemNhaCungCap @TenNCC, @mess OUTPUT;
	PRINT @mess;
	*/

-- Proc TaiKhoan
-- Tạo kiểu dữ liệu bảng TaiKhoan_Type
CREATE TYPE TaiKhoan_Type AS TABLE (
    MaTK NVARCHAR(10),
    TaiKhoan NVARCHAR(20),
    MatKhau NVARCHAR(15),
    MaQuyen INT
);
GO

-- Tạo stored procedure themTaiKhoan
CREATE PROCEDURE ThemTaiKhoan
    @MaTK NVARCHAR(10),
    @TaiKhoan NVARCHAR(20),
    @MatKhau NVARCHAR(15),
    @MaQuyen INT
AS
BEGIN
    INSERT INTO TaiKhoan (MaTK, TaiKhoan, MatKhau, MaQuyen)
    VALUES (@MaTK, @TaiKhoan, @MatKhau, @MaQuyen);
END;
GO

-- Tạo stored procedure suaTaiKhoan
CREATE PROCEDURE SuaTaiKhoan
    @MaTK NVARCHAR(10),
    @TaiKhoan NVARCHAR(20),
    @MatKhau NVARCHAR(15),
    @MaQuyen INT
AS
BEGIN
    UPDATE TaiKhoan
    SET TaiKhoan = @TaiKhoan,
        MatKhau = @MatKhau,
        MaQuyen = @MaQuyen
    WHERE MaTK = @MaTK;
END;
GO

-- Stored procedure xoaTaiKhoan
CREATE PROCEDURE xoaTaiKhoan
    @MaTK NVARCHAR(10),
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM TaiKhoan
        WHERE MaTK = @MaTK;

        SET @mess = N'Xóa tài khoản thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @maTK NVARCHAR(10) = 'TK011';
	DECLARE @mess_delete NVARCHAR(30);
	EXEC xoaTaiKhoan @maTK, @mess_delete OUTPUT;
	PRINT @mess_delete;
	SELECT * FROM TaiKhoan;
	*/
-- Tạo stored procedure searchTaiKhoan
CREATE PROCEDURE searchTaiKhoan
    @TaiKhoan NVARCHAR(20),
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        SELECT MaTK, TaiKhoan, MatKhau, MaQuyen
        FROM TaiKhoan
        WHERE TaiKhoan.TaiKhoan like '%' + @TaiKhoan + '%';

        SET @mess = N'Tìm kiếm tài khoản thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @TaiKhoan NVARCHAR(20) = 'username1';
	DECLARE @mess_search NVARCHAR(30);
	EXEC searchTaiKhoan @TaiKhoan, @mess_search OUTPUT;
	PRINT @mess_search;
	*/
-- Proc SanPham 
-- Tạo kiểu dữ liệu bảng SanPham_Type
CREATE TYPE SanPham_Type AS TABLE (
    MaSP NVARCHAR(6),
    TenSP NVARCHAR(255),
    MaLoai INT,
    Gia INT,
    SoLuong INT
);
GO

-- Tạo stored procedure themSanPham
CREATE PROCEDURE ThemSanPham
    @MaSP NVARCHAR(6),
    @TenSP NVARCHAR(255),
    @MaLoai INT,
    @Gia INT,
    @SoLuong INT
AS
BEGIN
    INSERT INTO SanPham (MaSP, TenSP, MaLoai, Gia, SoLuong)
    VALUES (@MaSP, @TenSP, @MaLoai, @Gia, @SoLuong);
END;
GO

-- Tạo stored procedure suaSanPham
CREATE PROCEDURE SuaSanPham
    @MaSP NVARCHAR(6),
    @TenSP NVARCHAR(255),
    @MaLoai INT,
    @Gia INT,
    @SoLuong INT
AS
BEGIN
    UPDATE SanPham
    SET TenSP = @TenSP,
        MaLoai = @MaLoai,
        Gia = @Gia,
        SoLuong = @SoLuong
    WHERE MaSP = @MaSP;
END;
GO

-- Tạo stored procedure xoaSanPham
CREATE PROCEDURE xoaSanPham
    @MaSP NVARCHAR(6),
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM SanPham
        WHERE MaSP = @MaSP;

        SET @mess = N'Xóa sản phẩm thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* test: ok
	DECLARE @maSP NVARCHAR(6) = 'SP051';
	DECLARE @mess_xoa NVARCHAR(1000)
	EXEC xoaSanPham @maSP, @mess_xoa OUTPUT;
	PRINT @mess_xoa;
	SELECT * FROM SanPham
	*/
-- Tạo stored procedure timKiemSanPham
CREATE PROCEDURE timKiemSanPham
    @TenSP NVARCHAR(255),
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        SELECT MaSP, TenSP, MaLoai, Gia, SoLuong
        FROM SanPham
        WHERE TenSP LIKE '%' + @TenSP + '%';

        SET @mess = N'Tìm kiếm sản phẩm thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* test: ok
	DECLARE @tenSP NVARCHAR(255) = 'K';
	DECLARE @mess_tim NVARCHAR(1000);
	EXEC timKiemSanPham @tenSP, @mess_tim OUTPUT;
	PRINT @mess_tim;
	*/


-- Proc NhanVien
-- Tạo kiểu dữ liệu bảng NhanVien_Type
CREATE TYPE NhanVien_Type AS TABLE (
    MaNV NVARCHAR(6),
    MaTK NVARCHAR(10),
    TenNV NVARCHAR(50),
    GioiTinh NVARCHAR(10),
    NgaySinh DATE,
    DiaChi NVARCHAR(255),
    SDT NVARCHAR(10)
);
GO

-- Tạo stored procedure themNhanVien
CREATE PROCEDURE ThemNhanVien
    @MaNV NVARCHAR(6),
    @MaTK NVARCHAR(10),
    @TenNV NVARCHAR(50),
    @GioiTinh NVARCHAR(10),
    @NgaySinh DATE,
    @DiaChi NVARCHAR(255),
    @SDT NVARCHAR(10)
AS
BEGIN
    INSERT INTO NhanVien (MaNV, MaTK, TenNV, GioiTinh, NgaySinh, DiaChi, SDT)
    VALUES (@MaNV, @MaTK, @TenNV, @GioiTinh, @NgaySinh, @DiaChi, @SDT);
END;
GO

-- Tạo stored procedure suaNhanVien
CREATE PROCEDURE SuaNhanVien
    @MaNV NVARCHAR(6),
    @MaTK NVARCHAR(10),
    @TenNV NVARCHAR(50),
    @GioiTinh NVARCHAR(10),
    @NgaySinh DATE,
    @DiaChi NVARCHAR(255),
    @SDT NVARCHAR(10)
AS
BEGIN
    UPDATE NhanVien
    SET MaTK = @MaTK,
        TenNV = @TenNV,
        GioiTinh = @GioiTinh,
        NgaySinh = @NgaySinh,
        DiaChi = @DiaChi,
        SDT = @SDT
    WHERE MaNV = @MaNV;
END;
GO

-- Tạo stored procedure xoaNhanVien
CREATE PROCEDURE xoaNhanVien
    @MaNV NVARCHAR(6),
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM NhanVien
        WHERE MaNV = @MaNV;

        SET @mess = N'Xóa nhân viên thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* test: ok
	DECLARE @maNV NVARCHAR(6) = 'NV011';
	DECLARE @mess_xoa NVARCHAR(1000);
	EXEC xoaNhanVien @maNV, @mess_xoa OUTPUT;
	PRINT @mess_xoa;
	SELECT * FROM NhanVien
	*/
-- Tạo stored procedure timKiemNhanVien
CREATE PROCEDURE timKiemNhanVien
    @TenNV NVARCHAR(50),
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        SELECT MaNV, MaTK, TenNV, GioiTinh, NgaySinh, DiaChi, SDT
        FROM NhanVien
        WHERE TenNV LIKE '%' + @TenNV + '%';

        SET @mess = N'Tìm kiếm nhân viên thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* test: ok
	DECLARE @tenNV NVARCHAR(50) = 'n';
	DECLARE @mess_tim NVARCHAR(1000);
	EXEC timKiemNhanVien @tenNV, @mess_tim OUTPUT;
	PRINT @mess_tim;
	*/

-- Proc PhieuNhap
-- Tạo kiểu dữ liệu bảng PhieuNhap_Type
CREATE TYPE PhieuNhap_Type AS TABLE (
	MaPN INT,
    MaNV NVARCHAR(6),
    MaNCC NVARCHAR(6),
    NgayNhap DATE
);
GO

-- Tạo stored procedure themPhieuNhap
CREATE PROCEDURE ThemPhieuNhap
    @MaNV NVARCHAR(6),
    @MaNCC NVARCHAR(6),
    @NgayNhap DATE
AS
BEGIN
    INSERT INTO PhieuNhap (MaNV, MaNCC, NgayNhap)
    VALUES (@MaNV, @MaNCC, @NgayNhap);
END;
GO

-- Tạo stored procedure suaPhieuNhap
CREATE PROCEDURE SuaPhieuNhap
    @MaPN INT,
    @MaNV NVARCHAR(6),
    @MaNCC NVARCHAR(6),
    @NgayNhap DATE
AS
BEGIN
    UPDATE PhieuNhap
    SET MaNV = @MaNV,
        MaNCC = @MaNCC,
        NgayNhap = @NgayNhap
    WHERE MaPN = @MaPN;
END;
GO


-- Tạo stored procedure xoaPhieuNhap
CREATE PROCEDURE xoaPhieuNhap
    @MaPN INT,
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM PhieuNhap
        WHERE MaPN = @MaPN;

        SET @mess = N'Xóa phiếu nhập thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* test: ok
	DECLARE @maPN INT = 11;
	DECLARE @mess_xoa NVARCHAR(1000);
	EXEC xoaPhieuNhap @maPN, @mess_xoa OUTPUT;
	PRINT @mess_xoa;
	SELECT * FROM PhieuNhap
	*/
-- Tạo stored procedure timKiemPhieuNhap
CREATE PROCEDURE timKiemPhieuNhap
    @MaPN INT,
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        SELECT MaPN, MaNV, MaNCC, NgayNhap
        FROM PhieuNhap
        WHERE MaPN = @MaPN;

        SET @mess = N'Tìm kiếm phiếu nhập thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* test: ok
	DECLARE @maPN INT = 1;
	DECLARE @mess_tim NVARCHAR(1000);
	EXEC timKiemPhieuNhap @maPN, @mess_tim OUTPUT;
	PRINT @mess_tim;
	*/
-- Proc PhieuNhapChiTiet
-- Tạo kiểu dữ liệu bảng PhieuNhapChiTiet_Type
CREATE TYPE PhieuNhapChiTiet_Type AS TABLE (
    MaPN INT,
    MaSP NVARCHAR(6),
    SoLuong INT
);
GO

-- Stored procedure themPhieuNhapChiTiet
-- Tạo kiểu dữ liệu bảng PhieuNhapChiTiet_Type
CREATE TYPE PhieuNhapChiTiet_Type AS TABLE (
	MaPNCT INT,
    MaPN INT,
    MaSP NVARCHAR(6),
    SoLuong INT
);
GO

-- Stored procedure themPhieuNhapChiTiet
CREATE PROCEDURE ThemPhieuNhapChiTiet
    @MaPN INT,
    @MaSP NVARCHAR(6),
    @SoLuong INT
AS
BEGIN
    INSERT INTO PhieuNhapChiTiet (MaPN, MaSP, SoLuong)
    VALUES (@MaPN, @MaSP, @SoLuong);
END;
GO

-- Tạo stored procedure suaPhieuNhapChiTiet
CREATE PROCEDURE SuaPhieuNhapChiTiet
    @MaPNCT INT,
    @MaPN INT,
    @MaSP NVARCHAR(6),
    @SoLuong INT
AS
BEGIN
    UPDATE PhieuNhapChiTiet
    SET MaPN = @MaPN,
        MaSP = @MaSP,
        SoLuong = @SoLuong
    WHERE MaPNCT = @MaPNCT;
END;
GO

-- Stored procedure xoaPhieuNhapChiTiet
CREATE PROCEDURE xoaPhieuNhapChiTiet
    @MaPNCT INT,
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM PhieuNhapChiTiet
        WHERE MaPNCT = @MaPNCT;

        SET @mess = N'Xóa chi tiết phiếu nhập thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @maPNCT INT = 43;
	DECLARE @mess_xoa NVARCHAR(1000);
	EXEC xoaPhieuNhapChiTiet @maPNCT, @mess_xoa OUTPUT;
	PRINT @mess_xoa;
	SELECT * FROM PhieuNhapChiTiet
	*/
-- Stored procedure timKiemPhieuNhapChiTiet
CREATE PROCEDURE timKiemPhieuNhapChiTiet
    @MaPN INT,
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        SELECT MaPNCT, MaPN, MaSP, SoLuong
        FROM PhieuNhapChiTiet
        WHERE MaPN = @MaPN;

        SET @mess = N'Tìm kiếm chi tiết phiếu nhập thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @maPN INT = 1;
	DECLARE @mess_tim NVARCHAR(1000); -- Biến lưu thông báo từ stored procedure
	EXEC timKiemPhieuNhapChiTiet @maPN, @mess_tim OUTPUT;
	PRINT @mess_tim;
	*/

-- Proc HoaDon
-- Tạo kiểu dữ liệu bảng HoaDon_Type
CREATE TYPE HoaDon_Type AS TABLE (
    MaHD INT,
	MaNV NVARCHAR(6),
    NgayTaoHD DATE
);
GO

-- Stored procedure themHoaDon
CREATE PROCEDURE ThemHoaDon
    @MaNV NVARCHAR(6),
    @NgayTaoHD DATE
AS
BEGIN
    INSERT INTO HoaDon (MaNV, NgayTaoHD)
    VALUES (@MaNV, @NgayTaoHD);
END;
GO

-- Tạo stored procedure suaHoaDon
CREATE PROCEDURE SuaHoaDon
    @MaHD INT,
    @MaNV NVARCHAR(6),
    @NgayTaoHD DATE
AS
BEGIN
    UPDATE HoaDon
    SET MaNV = @MaNV,
        NgayTaoHD = @NgayTaoHD
    WHERE MaHD = @MaHD;
END;
GO

-- Stored procedure xoaHoaDon
CREATE PROCEDURE xoaHoaDon
    @MaHD INT,
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM HoaDon
        WHERE MaHD = @MaHD;

        SET @mess = N'Xóa hóa đơn thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @maHD INT = 17;
	DECLARE @mess_xoa NVARCHAR(1000);
	EXEC xoaHoaDon @maHD, @mess_xoa OUTPUT;
	PRINT @mess_xoa;
	SELECT * FROM HoaDon
	*/
-- Stored procedure timKiemHoaDon
CREATE PROCEDURE timKiemHoaDon
    @MaHD INT,
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        SELECT MaHD, MaNV, NgayTaoHD
        FROM HoaDon
        WHERE MaHD = @MaHD;

        SET @mess = N'Tìm kiếm hóa đơn thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @maHD INT = 1;
	DECLARE @mess_tim NVARCHAR(1000);
	EXEC timKiemHoaDon @maHD, @mess_tim OUTPUT;
	PRINT @mess_tim;
	*/

-- Proc HoaDonChiTiet
-- Tạo kiểu dữ liệu bảng HoaDonChiTiet_Type
CREATE TYPE HoaDonChiTiet_Type AS TABLE (
	MaHDCT INT,
    MaHD INT,
    MaSP NVARCHAR(6),
    SoLuong INT
);
GO

-- Stored procedure themHoaDonChiTiet
CREATE PROCEDURE ThemHoaDonChiTiet
    @MaHD INT,
    @MaSP NVARCHAR(6),
    @SoLuong INT
AS
BEGIN
    INSERT INTO HoaDonChiTiet (MaHD, MaSP, SoLuong)
    VALUES (@MaHD, @MaSP, @SoLuong);
END;
GO

-- Tạo stored procedure suaHoaDonChiTiet
CREATE PROCEDURE SuaHoaDonChiTiet
    @MaHDCT INT,
    @MaHD INT,
    @MaSP NVARCHAR(6),
    @SoLuong INT
AS
BEGIN
    UPDATE HoaDonChiTiet
    SET MaHD = @MaHD,
        MaSP = @MaSP,
        SoLuong = @SoLuong
    WHERE MaHDCT = @MaHDCT;
END;
GO

-- Stored procedure xoaHoaDonChiTiet
CREATE PROCEDURE xoaHoaDonChiTiet
    @MaHDCT INT,
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        DELETE FROM HoaDonChiTiet
        WHERE MaHDCT = @MaHDCT;

        SET @mess = N'Xóa chi tiết hóa đơn thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @maHDCT INT = 42;
	DECLARE @mess_xoa NVARCHAR(1000);
	EXEC xoaHoaDonChiTiet @maHDCT, @mess_xoa OUTPUT;
	PRINT @mess_xoa;
	SELECT * FROM HoaDonChiTiet
	*/
-- Stored procedure timKiemHoaDonChiTiet
CREATE PROCEDURE timKiemHoaDonChiTiet
    @MaHD INT,
    @mess NVARCHAR(1000) OUTPUT
AS
BEGIN
    BEGIN TRY
        SELECT MaHDCT, MaHD, MaSP, SoLuong
        FROM HoaDonChiTiet
        WHERE MaHD = @MaHD;

        SET @mess = N'Tìm kiếm chi tiết hóa đơn thành công';
    END TRY
    BEGIN CATCH
        SET @mess = N'Lỗi: ' + ERROR_MESSAGE();
    END CATCH
END;
GO
	/* Test: ok
	DECLARE @maHD INT = 1;
	DECLARE @mess_tim NVARCHAR(1000);
	EXEC timKiemHoaDonChiTiet @maHD, @mess_tim OUTPUT;
	PRINT @mess_tim;
	*/

CREATE PROCEDURE GetMonthlyTotals
    @Month INT
AS
BEGIN
    -- Total amount of invoices
    SELECT COALESCE(SUM(TongTien), 0) AS TotalInvoice
    FROM HoaDon
    WHERE MONTH(NgayTaoHD) = @Month;

    -- Total amount of receipts
    SELECT COALESCE(SUM(TongTien), 0) AS TotalReceipt
    FROM PhieuNhap
    WHERE MONTH(NgayNhap) = @Month;
END;
GO

DECLARE @Month INT = 6;  -- Tháng muốn lấy dữ liệu, ở đây là tháng 6

-- Chạy stored procedure
EXEC GetMonthlyTotals @Month;




