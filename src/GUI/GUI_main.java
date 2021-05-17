package GUI;

import javax.swing.*;

import DAOInformation.DAOChutro;
import DAOInformation.DAOSinhvien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class GUI_main extends JFrame implements ActionListener  {
	JMenuBar menubar = new JMenuBar();
	JMenu svMenu, phongMenu;
	JMenuItem xemttsv, xoasv, timsv,capnhatsv,xemttphong,capnhatphong,xoaphong;
	DAOSinhvien dao_sv = new DAOSinhvien();
	DAOChutro dao_tro = new DAOChutro();

	public GUI_main() {
		// TODO Auto-generated constructor stub
		setSize(700,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addControl();
		
		setJMenuBar(menubar);
	}
	private void addControl() {
		// TODO Auto-generated method stub
		JPanel pCen = new JPanel();
		JLabel lblCen = new JLabel("QUẢN LÍ THÔNG TIN Ở TRỌ CỦA SINH VIÊN");
		lblCen.setForeground(Color.orange);
		Font fb = new Font("Time new Roman", Font.BOLD, 25);
		lblCen.setFont(fb);
		pCen.add(lblCen);
		add(pCen,BorderLayout.CENTER);
		///menu sinh vien
		svMenu = new JMenu("Sinh viên");
		xemttsv = new JMenuItem("Danh sách sinh viên");
//		xoasv = new JMenuItem("Xóa sinh viên");
		timsv = new JMenuItem("Tìm sinh viên");
		capnhatsv = new JMenuItem("Cập nhật");
		svMenu.add(xemttsv);
//		svMenu.add(xoasv);
		svMenu.add(timsv);
		svMenu.add(capnhatsv);
		menubar.add(svMenu);
		/// menu Phong tro
		phongMenu = new JMenu("Phòng trọ");
		phongMenu.add(xemttphong = new JMenuItem("Danh sách Phòng trọ"));
//		phongMenu.add(xoaphong = new JMenuItem("Xóa Phòng trọ"));
		phongMenu.add(capnhatphong = new JMenuItem("Cập nhật Phòng trọ"));
		menubar.add(phongMenu);
		///action
		xemttsv.addActionListener(this);
		timsv.addActionListener(this);
		xemttphong.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(xemttsv)) {

			new GUI_Sv().setVisible(true);
		}
		else if(obj.equals(timsv)) {
			new TimKiemSinhVien().setVisible(true);
		}
//		else if(obj.equals(xemttphong)) {
//			new GUI_Tro(dstro).setVisible(true);
//		}
//		else if(obj.equals(xemttphong)) {
//			new GUI_Tro(dstro).setVisible(true);
//		}
	}
}
