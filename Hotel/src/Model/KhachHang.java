package Model;

import java.util.Date;

public class KhachHang {
    private int maKhachHang;
    private String tenKhachHang;
    private String kieuPhong;
    private int soPhong;
    private Date ngayThue;
    private double giaPhong;

    public KhachHang() {
    }

    public KhachHang(int maKhachHang, String tenKhachHang, String kieuPhong, int soPhong, Date ngayThue, double giaPhong) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.kieuPhong = kieuPhong;
        this.soPhong = soPhong;
        this.ngayThue = ngayThue;
        this.giaPhong = giaPhong;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getKieuPhong() {
        return kieuPhong;
    }

    public void setKieuPhong(String kieuPhong) {
        this.kieuPhong = kieuPhong;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
        
    }

    public double getGiaPhong() {
        return this.giaPhong;
    }
}

