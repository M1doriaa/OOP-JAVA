package Control;

import javax.swing.table.DefaultTableModel;
import Model.KhachHang;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.text.SimpleDateFormat;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;



public class HomePageController {
    private ArrayList<KhachHang> list;
    private DefaultTableModel model;
    private static Map<String, Integer> roomCount = new HashMap<>();


    public HomePageController(DefaultTableModel model) {
        this.list = new ArrayList<>();
        this.model = model;
        File xmlFile = new File("src/KhachHang.xml");
        if(xmlFile.exists()) {
            readFromXmlFile(xmlFile);
        } else {
            writeToXmlFile();
        }
    }


    private void readFromXmlFile(File xmlFile) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("KhachHang");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element khachHangElement = (Element) node;
                    KhachHang kh = new KhachHang();
                    kh.setMaKhachHang(Integer.parseInt(khachHangElement.getElementsByTagName("MaKhachHang").item(0).getTextContent()));
                    kh.setTenKhachHang(khachHangElement.getElementsByTagName("TenKhachHang").item(0).getTextContent());
                    kh.setKieuPhong(khachHangElement.getElementsByTagName("KieuPhong").item(0).getTextContent());
                    kh.setSoPhong(Integer.parseInt(khachHangElement.getElementsByTagName("SoPhong").item(0).getTextContent()));
                    kh.setNgayThue(new SimpleDateFormat("yyyy-MM-dd").parse(khachHangElement.getElementsByTagName("NgayThue").item(0).getTextContent()));
                    kh.setGiaPhong(Double.parseDouble(khachHangElement.getElementsByTagName("GiaTien").item(0).getTextContent()));
                    list.add(kh);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void writeToXmlFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("KhachHangs");
            doc.appendChild(rootElement);
            for(KhachHang kh : list) {
                Element khachHangElement = doc.createElement("KhachHang");
                Element maKhachHangElement = doc.createElement("MaKhachHang");
                maKhachHangElement.appendChild(doc.createTextNode(String.valueOf(kh.getMaKhachHang())));
                khachHangElement.appendChild(maKhachHangElement);
                Element tenKhachHangElement = doc.createElement("TenKhachHang");
                tenKhachHangElement.appendChild(doc.createTextNode(kh.getTenKhachHang()));
                khachHangElement.appendChild(tenKhachHangElement);
                rootElement.appendChild(khachHangElement);
                Element kieuPhongElement = doc.createElement("KieuPhong");
                kieuPhongElement.appendChild(doc.createTextNode(kh.getKieuPhong()));
                khachHangElement.appendChild(kieuPhongElement);
                Element soPhongElement = doc.createElement("SoPhong");
                soPhongElement.appendChild(doc.createTextNode(String.valueOf(kh.getSoPhong())));
                khachHangElement.appendChild(soPhongElement);
                Element ngayThueElement = doc.createElement("NgayThue");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String ngayThueString = dateFormat.format(kh.getNgayThue());
                ngayThueElement.appendChild(doc.createTextNode(ngayThueString));
                khachHangElement.appendChild(ngayThueElement);
                Element giaTienElement = doc.createElement("GiaTien");
                giaTienElement.appendChild(doc.createTextNode(String.valueOf(kh.getGiaPhong())));
                khachHangElement.appendChild(giaTienElement);

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/KhachHang.xml"));
            transformer.transform(source, result);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<KhachHang> getList() {
        return list;
    }

    public void setGiaPhongBasedOnKieuPhong(KhachHang kh) {
        String kieuPhong = kh.getKieuPhong();
        double giaPhong;
        switch (kieuPhong) {
            case "Đơn":
                giaPhong = 100.0;
                break;
            case "Đôi":
                giaPhong = 200.0;
                break;
            case "Vip":
                giaPhong = 500.0;
                break;
            default:
                giaPhong = 0.0; 
                break;
        }
        kh.setGiaPhong(giaPhong);
    }


    public void setSoPhongAndKieuPhong(KhachHang kh) {
        String kieuPhong = kh.getKieuPhong();
        int soPhong = roomCount.getOrDefault(kieuPhong, 0) + 1;
    
        switch (kieuPhong) {
            case "Đơn":
                if (soPhong > 10) {
                    kieuPhong = "Đôi";
                    soPhong = roomCount.getOrDefault(kieuPhong, 0) + 1; 
                }
                break;
            case "Đôi":
                if (soPhong > 10) {
                    kieuPhong = "Vip";
                    soPhong = roomCount.getOrDefault(kieuPhong, 0) + 1; 
                }
                break;
            case "Vip":
                if (soPhong > 10) {
                    kieuPhong = "Đơn";
                    soPhong = roomCount.getOrDefault(kieuPhong, 0) + 1; 
                }
                break;
        }
    
        roomCount.put(kieuPhong, soPhong);
    
        kh.setKieuPhong(kieuPhong);
        kh.setSoPhong(soPhong);
    }

    

    public void add(KhachHang kh) {
        setGiaPhongBasedOnKieuPhong(kh);
        list.add(kh);
        show(kh);
        writeToXmlFile();
    }

    public void show(KhachHang kh) {
        model.addRow(new Object[]{
            kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getKieuPhong(), kh.getSoPhong(), kh.getNgayThue(), kh.getGiaPhong()
        });
    }

    public void update(KhachHang kh, int selectedIndex) {
        setGiaPhongBasedOnKieuPhong(kh);
        if (selectedIndex >= 0 && selectedIndex < list.size()) {
            list.set(selectedIndex, kh);
            model.removeRow(selectedIndex);
            model.insertRow(selectedIndex, new Object[]{
                kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getKieuPhong(), kh.getSoPhong(), kh.getNgayThue(), kh.getGiaPhong()
            });
            writeToXmlFile();
        } 
    }
    public void delete(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < list.size()) {
            list.remove(selectedIndex);
            model.removeRow(selectedIndex);
            writeToXmlFile();
        } 
    }

    public ArrayList<KhachHang> search(String keyword) {
        ArrayList<KhachHang> result = new ArrayList<>();
    
        for (KhachHang kh : list) {
            if (kh.getTenKhachHang().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(kh);
            }
        }
    
        return result;
    }

    public ArrayList<KhachHang> searchByMaKhachHang(Integer maKhachHang) {
        ArrayList<KhachHang> result = new ArrayList<>();
    
        for (KhachHang kh : list) {
            if (Integer.valueOf(kh.getMaKhachHang()).equals(maKhachHang)) {
                result.add(kh);
            }
        }
    
        return result;
    }

    

    
}

