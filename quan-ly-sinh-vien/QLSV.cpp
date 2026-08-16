#include <stdio.h>
#include <string.h>
#define MaxSV 100
struct SinhVien
{
    char MSV[10];
    char HoTen[20];
    char GioiTinh[5];
    char NgaySinh[15];
    char Lop[5];
    float DiemToanCC;
    float DiemTriet;
    float DiemCSLT;
    float DiemTB;
};

void Menu()
{
    
    printf("\n MENU QUAN LY SINH VIEN KHOA CONG NGHE THONG TIN");
    printf("\n-==================================================-");
    printf("\n|  1. NHAP THONG TIN SINH VIEN.                   |");
    printf("\n|  2. XUAT DANH SACH SINH VIEN.                   |");
    printf("\n|  3. TINH DIEM TRUNG BINH SINH VIEN.             |");
    printf("\n|  4. SAP XEP SINH VIEN THEO THU TU TANG DAN CUA  |");
    printf("\n|     DIEM TRUNG BINH.                            |");
    printf("\n|  5. XEP LOAI SINH VIEN.                         |");
    printf("\n|  6. NHAP, XUAT DU LIEU SINH VIEN VAO FILE.      |");
    printf("\n|  0. Thoat.                                      |");
    printf("\n-==================================================-");
}

// 1.
void ThongTin(SinhVien &sv)
{
    printf("\nNhap Ma sinh vien: "); scanf("%s", &sv.MSV); fflush(stdin);
    printf("Nhap Ho va ten: "); gets(sv.HoTen); fflush(stdin);
    printf("Nhap Gioi tinh: "); scanf("%s", &sv.GioiTinh); fflush(stdin);
    printf("Nhap Ngay sinh: "); scanf("%s", &sv.NgaySinh); fflush(stdin);
    printf("Nhap Lop: "); scanf("%s", &sv.Lop); fflush(stdin);
    printf("Nhap Diem Toan cao cap: "); scanf("%f", &sv.DiemToanCC); fflush(stdin);
    printf("Nhap Diem Triet: "); scanf("%f", &sv.DiemTriet); fflush(stdin);
    printf("Nhap Diem Co so lap trinh C: "); scanf("%f", &sv.DiemCSLT); fflush(stdin);
    sv.DiemTB = (float) (sv.DiemToanCC + sv.DiemTriet + sv.DiemCSLT) / 3;
}

void NhapSV(SinhVien sv[], int n)
{
    printf("\n");
    printf("    \n=== NHAP THONG TIN SINH VIEN ===");
    for ( int i = 0; i < n; i++)
    {
        printf("\nNhap SV thu %d", i+1);
        ThongTin(sv[i]);
        fflush(stdin);
        printf("\n");
    } 
}

// 2.
void DuLieuSv1(SinhVien sv)
{
    printf("\n| %-9s | %-18s | %-9s | %-13s | %-9s | %-12.2f | %-10.2f | %-9.2f |", sv.MSV, sv.HoTen, sv.GioiTinh, sv.NgaySinh,
    sv.Lop, sv.DiemToanCC, sv.DiemTriet, sv.DiemCSLT);
}
void XuatSV(SinhVien sv[], int n)
{
    printf("\n========= XUAT THONG TIN SINH VIEN =========");
    printf("\n------------------------------------------------------------------------------------------------------------------");
    printf("\n|   Ma SV   |       Ho Ten       | Gioi Tinh |   Ngay Sinh   |    Lop    | Diem Toan CC | Diem Triet | Diem CSLT |");
    printf("\n|----------------------------------------------------------------------------------------------------------------|");
    for ( int i = 0; i < n; i++)
    {
        DuLieuSv1(sv[i]);
    }
    printf("\n------------------------------------------------------------------------------------------------------------------");
}

// 3.
void DuLieuSv2(SinhVien sv)
{
    printf("\n| %-9s | %-18s | %-9s | %-13s | %-9s | %-7.2f |", sv.MSV, sv.HoTen, sv.GioiTinh, sv.NgaySinh,
    sv.Lop, sv.DiemTB);
}

void XuatDiemTB(SinhVien sv[], int n)
{
    printf("\n========= TINH DIEM TRUNG BINH SINH VIEN =========");
    printf("\n------------------------------------------------------------------------------------");
    printf("\n|   Ma SV   |       Ho Ten       | Gioi Tinh |   Ngay Sinh   |    Lop    | Diem TB |");
    printf("\n|----------------------------------------------------------------------------------|");
    for ( int i = 0; i < n; i++)
    {
        DuLieuSv2(sv[i]);
    }
    printf("\n------------------------------------------------------------------------------------");
}

//4.
void SapXepSV(SinhVien sv[], int n)
{
    SinhVien tmp;
    for (int i = 0; i < n; i++)
    {
        for (int j = i+1; j < n; j++)
        {
            if (sv[i].DiemTB > sv[j].DiemTB)
            {
                tmp = sv[i];
                sv[i] =sv[j];
                sv[j] = tmp;
            }
            
        }
        
    }
    XuatDiemTB(sv, n);
}

// 5.
void DuLieuSv3(SinhVien sv)
{
    char XepLoai[15];
    if (sv.DiemTB >= 8)
    {
        strcpy(XepLoai, "Gioi");
    }
    else if (sv.DiemTB >= 6.5)
    {
        strcpy(XepLoai, "Kha");
    }
    else if (sv.DiemTB >= 5)
    {
        strcpy(XepLoai, "Trung binh");
    }
    else
    {
        strcpy(XepLoai, "Yeu");
    }
    printf("\n| %-9s | %-18s | %-9s | %-13s | %-9s | %-12s |", sv.MSV, sv.HoTen, sv.GioiTinh, sv.NgaySinh,
    sv.Lop, XepLoai);
}

void XepLoaiSV(SinhVien sv[], int n)
{
    printf("\n========= XEP LOAI SINH VIEN =========");
    printf("\n-----------------------------------------------------------------------------------------");
    printf("\n|   Ma SV   |       Ho Ten       | Gioi Tinh |   Ngay Sinh   |    Lop    |   Xep loai   |");
    printf("\n|---------------------------------------------------------------------------------------|");
    for ( int i = 0; i < n; i++)
    {
        DuLieuSv3(sv[i]);
    }
    printf("\n-----------------------------------------------------------------------------------------");
}

// 6.

int main(){
    int n;
    struct SinhVien sv[MaxSV];
    int LuaChon;
    do
    {
        printf("\nNhap so sinh vien: ");
        scanf("%d", &n);
        printf("\n");
        fflush(stdin);
    } while (n < 1 || n > MaxSV);

    while (true)
    {
        Menu();
        printf("\nNhap lua chon: ");
        scanf("%d", &LuaChon);
        switch (LuaChon)
        {
        case 1:
            NhapSV(sv, n);
            break;
        case 2:
            XuatSV(sv, n);
            break;
        case 3:
            XuatDiemTB(sv, n);
            break;
        case 4:
            SapXepSV(sv, n);
            break;
        case 5:
            XepLoaiSV(sv, n);
            break;
        case 6:
            printf("Sorry I can't do it :(\n");
            break;
        case 0:
            printf("Ban da thoat chuong trinh!");
            return 0;
        default:
            printf("Khong co chuc nang nay!\n");
            break;
        }
    }
}
