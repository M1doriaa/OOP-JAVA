package Model;

public class Phong {
    private String kieuPhong;
    private int soPhong;
    private boolean trangThai;

    public Phong() {
    }

    public Phong( String kieuPhong, int soPhong, boolean trangThai) {
        this.kieuPhong = kieuPhong;
        this.soPhong = soPhong;
        this.trangThai = trangThai;
    }

    public String getKieuPhong() {
        return kieuPhong;
    }

    public void setKieuPhong(String kieuPhong) {
        this.kieuPhong = kieuPhong;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }
}

