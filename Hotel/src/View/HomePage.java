package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.KhachHang;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import Control.HomePageController;
import java.awt.Font; 

public class HomePage {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private HomePageController controller;
	private JTextField textFieldSearch;
	private JTextField textFieldSearch_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	

	/**
	 * Create the application.
	 */
	
	public HomePage() {
		initialize();
		controller = new HomePageController((DefaultTableModel) table.getModel());
		DefaultTableModel model = (DefaultTableModel) table.getModel(); // Create a new DefaultTableModel object
		for (KhachHang kh : controller.getList()) {
			model.addRow(new Object[] {kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getKieuPhong(), kh.getSoPhong(), kh.getNgayThue(), });
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	
	 
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table.setName("");
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(296, 130, 580, 483);
		frame.getContentPane().add(table);
	
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(10, 140, 276, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Mã khách hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 100, 267, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblKiuPhng = new JLabel("Tên khách hàng");
		lblKiuPhng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKiuPhng.setBounds(10, 180, 276, 30);
		frame.getContentPane().add(lblKiuPhng);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 220, 276, 30);
		frame.getContentPane().add(textField_1);
		
		JLabel lblSPhng = new JLabel("Kiểu phòng");
		lblSPhng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSPhng.setBounds(10, 260, 267, 30);
		frame.getContentPane().add(lblSPhng);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Đơn", "Đôi", "Vip"}));
		comboBox_1.setBounds(10, 300, 101, 30);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblGiTin = new JLabel("Ngày đặt");
		lblGiTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGiTin.setBounds(10, 340, 276, 30);
		frame.getContentPane().add(lblGiTin);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_4.setColumns(10);
		textField_4.setBounds(10, 380, 267, 30);
		frame.getContentPane().add(textField_4);
		
		JButton btnAdd = new JButton("Thêm");
		JButton btnSửa = new JButton("Sửa");
		JButton btnXóa = new JButton("Xóa");
		JButton btnSave = new JButton("Lưu");
		JButton btnXa = new JButton("Reset");
		JButton btnHyB = new JButton("Hủy Bỏ");


		btnAdd.setVisible(true);
		btnSửa.setVisible(true);
		btnXóa.setVisible(true);
		btnSave.setVisible(false);
		btnXa.setVisible(false);
		btnHyB.setVisible(false);


		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = new KhachHang();
				kh.setMaKhachHang(Integer.parseInt(textField.getText()));
				kh.setTenKhachHang(textField_1.getText());
				kh.setKieuPhong(comboBox_1.getSelectedItem().toString());
				controller.setSoPhongAndKieuPhong(kh);		
				try {
					kh.setNgayThue(new SimpleDateFormat("dd/MM/yyyy").parse(textField_4.getText()));
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				controller.add(kh);
			}
		});
		btnAdd.setBounds(10, 420, 85, 30);
		frame.getContentPane().add(btnAdd);
		
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				comboBox_1.setSelectedItem("Đơn"); 
				textField_4.setText("");
			}
		});
		btnXa.setBounds(105, 460, 85, 30);
		frame.getContentPane().add(btnXa);
		
		JLabel lblNewLabel_1 = new JLabel("Thông tin khách hàng");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(296, 10, 267, 53);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnXóa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = table.getSelectedRow();
				controller.delete(selectedIndex);
			}
		});
		btnXóa.setBounds(200, 420, 85, 30);
		frame.getContentPane().add(btnXóa);
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHang kh = new KhachHang();
				kh.setMaKhachHang(Integer.parseInt(textField.getText()));
				kh.setTenKhachHang(textField_1.getText());
				kh.setKieuPhong(comboBox_1.getSelectedItem().toString());
				controller.setSoPhongAndKieuPhong(kh);				
				try {
					kh.setNgayThue(new SimpleDateFormat("dd/MM/yyyy").parse(textField_4.getText()));
				} catch (ParseException ex) {
					ex.printStackTrace();
				}
				int selectedIndex = table.getSelectedRow();
				controller.update(kh, selectedIndex);
			}
		});
		btnSave.setBounds(10, 460, 85, 30);
		frame.getContentPane().add(btnSave);
		

		btnSửa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd.setVisible(false);
				btnSửa.setVisible(false);
				btnXóa.setVisible(false);
				btnSave.setVisible(true);
				btnXa.setVisible(true);
				btnHyB.setVisible(true);

			}
		});
		btnSửa.setBounds(105, 420, 85, 30);
		frame.getContentPane().add(btnSửa);
		
		btnHyB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdd.setVisible(true);
				btnSửa.setVisible(true);
				btnXóa.setVisible(true);
				btnSave.setVisible(false);
				btnXa.setVisible(false);
				btnHyB.setVisible(false);
			}
		});
		btnHyB.setBounds(200, 460, 85, 30);
		frame.getContentPane().add(btnHyB);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(412, 74, 151, 30);
		frame.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Tìm Kiếm Theo Tên");
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		btnSearch.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
        	String keyword = textFieldSearch.getText();
        	ArrayList<KhachHang> searchResults = controller.search(keyword);
        	model.setRowCount(0);
        	for (KhachHang kh : searchResults) {
            	model.addRow(new Object[]{
                	kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getKieuPhong(), kh.getSoPhong(), kh.getNgayThue()
            	});
        		}
    		}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnSearch.setBounds(296, 73, 106, 29);
		frame.getContentPane().add(btnSearch);
		
				JButton btnSearch_1 = new JButton("Tìm Kiếm Theo Mã");
				btnSearch_1.addActionListener(new ActionListener() {
		    		public void actionPerformed(ActionEvent e) {
		        	String keyword = textFieldSearch_1.getText();
		        	ArrayList<KhachHang> searchResults = controller.searchByMaKhachHang(Integer.parseInt(keyword));
		        	model.setRowCount(0);
		        	for (KhachHang kh : searchResults) {
		            	model.addRow(new Object[]{
		                	kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getKieuPhong(), kh.getSoPhong(), kh.getNgayThue()
		            	});
		        		}
		    		}
				});
				btnSearch_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
				btnSearch_1.setBounds(610, 75, 106, 29);
		frame.getContentPane().add(btnSearch_1);
		
		textFieldSearch_1 = new JTextField();
		textFieldSearch_1.setColumns(10);
		textFieldSearch_1.setBounds(725, 74, 151, 30);
		frame.getContentPane().add(textFieldSearch_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mã khách hàng");
		lblNewLabel_2.setBounds(296, 113, 106, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tên khách hàng");
		lblNewLabel_2_1.setBounds(394, 113, 106, 13);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Kiểu phòng");
		lblNewLabel_2_2.setBounds(490, 113, 106, 13);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Số phòng");
		lblNewLabel_2_3.setBounds(587, 113, 106, 13);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Ngày đặt");
		lblNewLabel_2_4.setBounds(684, 113, 106, 13);
		frame.getContentPane().add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_4_1 = new JLabel("Giá");
		lblNewLabel_2_4_1.setBounds(780, 113, 106, 13);
		frame.getContentPane().add(lblNewLabel_2_4_1);

		
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					textField.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
					textField_1.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					comboBox_1.setSelectedItem(table.getValueAt(table.getSelectedRow(), 2).toString());
					Object dateObject = table.getValueAt(table.getSelectedRow(), 4);
					if (dateObject instanceof Date) {
						textField_4.setText(new SimpleDateFormat("dd/MM/yyyy").format((Date) dateObject)); // Cast to Date
					}
				}
			}
		});


	}

	
	
	public JFrame getFrame() {
		return frame;
	}
}
