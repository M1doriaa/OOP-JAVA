package Control;

import javax.swing.table.DefaultTableModel;

import Model.KhachHang;

public class TestReadXml {
    public static void main(String[] args) {
        // Create a new DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();

        // Create a new HomePageController with the model
        HomePageController controller = new HomePageController(model);


        // Print the data to the console
        for (KhachHang kh : controller.getList()) {
            System.out.println("MaKhachHang: " + kh.getMaKhachHang());
            System.out.println("TenKhachHang: " + kh.getTenKhachHang());
            System.out.println("KieuPhong: " + kh.getKieuPhong());
            System.out.println("SoPhong: " + kh.getSoPhong());
            System.out.println("NgayThue: " + kh.getNgayThue());
            System.out.println("-------------------");
        }
    }
}
