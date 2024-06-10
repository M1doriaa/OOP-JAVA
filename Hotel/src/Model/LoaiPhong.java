package Model;

public class LoaiPhong {
    private String kieuPhong;
    private double giaPhong;

    public LoaiPhong() {
    }

    public LoaiPhong(String kieuPhong, double giaPhong) {
        this.kieuPhong = kieuPhong;
        this.giaPhong = giaPhong;
    }

    public String getKieuPhong() {
        return kieuPhong;
    }

    public void setKieuPhong(String kieuPhong) {
        this.kieuPhong = kieuPhong;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

}