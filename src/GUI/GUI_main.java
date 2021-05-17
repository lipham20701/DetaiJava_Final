package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import DAOInformation.DAOChutro;
import DAOInformation.DAOSinhvien;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.sql.SQLException;
public class GUI_main extends JFrame implements ActionListener  {
	JMenuBar menubar = new JMenuBar();
	JMenu svMenu, phongMenu;
	JMenuItem xemttsv, xoasv, timsv,capnhatsv,xemttphong,capnhatphong,xoaphong;
	DAOSinhvien dao_sv = new DAOSinhvien();
	DAOChutro dao_tro = new DAOChutro();

	public GUI_main() {
		// TODO Auto-generated constructor stub   
		setSize(720,600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addPnorth();
		IMG();
		addPcenter();
		setJMenuBar(menubar);
		
	}
	
	
	public void IMG() {
		setLayout(new FlowLayout());
		
		try {
			BufferedImage img = ImageIO.read(new File("img/Logo_IUH.png"));
			ImageIcon icon = new ImageIcon(img.getScaledInstance(300, 150, Image.SCALE_SMOOTH));
			JLabel lbl = new JLabel(icon);
			add(new JLabel(icon));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPcenter() {
		JPanel pnborder = new JPanel();
		pnborder.setLayout(new BorderLayout());
		
		Font fb = new Font("Time new Roman", Font.BOLD, 20);
		Font fb2 = new Font("Time new Roman", Font.BOLD, 30);
		JPanel pc = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		pc.setLayout(new BoxLayout(pc, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("BÁO CÁO ĐỀ TÀI");
		title.setFont(fb2);
		JPanel pntrong = new JPanel();
		pntrong.setPreferredSize(new Dimension(5, 40));
		JLabel mon = new JLabel("MÔN: LẬP TRÌNH HƯỚNG SỰ KIỆN JAVA");
		mon.setFont(fb);
		pc.add(Box.createHorizontalStrut(50));
		JLabel detai = new JLabel("ĐỀ TÀI: QUẢN LÝ THÔNG TIN Ở TRỌ CỦA SINH VIÊN");
		detai.setFont(fb);
		p4.add(pntrong);
		p4.add(title);
		p5.add(mon);
		p6.add(detai);
		title.setPreferredSize(detai.getPreferredSize());
		p4.setPreferredSize(p6.getPreferredSize());
		p5.setPreferredSize(p6.getPreferredSize());
		pc.add(p4); pc.add(p5); pc.add(p6);
		pnborder.add(pc,BorderLayout.CENTER);
		add(pnborder);
		
	}
	
	
	private void addPnorth() {
		
		// North
		JPanel pn = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
		JLabel lblc1 = new JLabel("BỘ CÔNG THƯƠNG");
		JLabel lblc2 = new JLabel("TRƯỜNG ĐẠI HỌC CÔNG NGHIỆP TP.HCM");
		JLabel lblc3 = new JLabel("KHOA CÔNG NGHỆ THÔNG TIN");

		Font fb = new Font("Time new Roman", Font.BOLD, 20);
		lblc1.setFont(fb); 
		lblc2.setFont(fb);
		lblc3.setFont(fb);
		p1.add(lblc1);
		p2.add(lblc2);
		p3.add(lblc3);
		p1.setPreferredSize(p2.getPreferredSize());
		p3.setPreferredSize(p2.getPreferredSize());
		pn.add(p1); pn.add(p2); pn.add(p3);
		
		add(pn,BorderLayout.NORTH);
		
		
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
