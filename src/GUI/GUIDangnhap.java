package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DAOInformation.DAOTaikhoan;
import Entity.NhanVien;
public class GUIDangnhap extends JFrame implements ActionListener {
	private JPanel gui_main = new JPanel(new BorderLayout());
	private NhanVien nhanvien;
	private JButton btnDangnhap, btnHuy;
	private JLabel lblthongbao,lblTK,lblMK,lblkhoangtrong;
	private JTextField txtTK;
	private JPasswordField txtMK;
	private JPanel pnSouth = new JPanel();
	public GUIDangnhap() {
		// TODO Auto-generated constructor stub
		setSize(600,400);
		setResizable(false);
		setLocationRelativeTo(null);
		addControl();
	}
	private void addControl() {
		// TODO Auto-generated method stub
		Dimension expectedDimension = new Dimension(200, 150);
		JPanel pnNorth = new JPanel();
		JLabel lbltitle = new JLabel("ĐĂNG NHẬP");
		Font fb = new Font("Time new Roman",Font.BOLD, 24);
		lbltitle.setFont(fb);
		pnNorth.add(lbltitle);
		gui_main.add(pnNorth,BorderLayout.NORTH);
		//
		JPanel pnCen = new JPanel();
		JPanel pnTk = new JPanel();
		JLabel lblTK = new JLabel("Tên đăng nhập ");
		JLabel lblMK = new JLabel("Mật khẩu ");
		txtTK = new JTextField();
		txtMK = new JPasswordField();
		pnTk.add(lblTK);
		pnTk.add(txtTK);
		pnCen.add(pnTk);
		JPanel pnMK = new JPanel();
		pnMK.add(lblMK);
		pnMK.add(txtMK);
		pnCen.add(pnMK);
		lblTK.setPreferredSize(new Dimension(100, 30));
		lblMK.setPreferredSize(lblTK.getPreferredSize());
		txtTK.setPreferredSize(new Dimension(300, 30));
	    txtMK.setPreferredSize(new Dimension(300, 30));
	    //
	    txtTK.setBackground(Color.decode("#c4c4c4"));
	    txtMK.setBackground(Color.decode("#c4c4c4"));
	    gui_main.add(pnCen,BorderLayout.CENTER);	
		lblthongbao = new JLabel("Sai tên Đăng nhập hoặc Mật khẩu");
		btnDangnhap = new JButton("Đăng nhập");
		btnHuy = new JButton("Hủy");
		btnHuy.setPreferredSize(btnDangnhap.getPreferredSize());
		JPanel pnbutton = new JPanel();
		pnbutton.add(btnDangnhap);
		pnbutton.add(btnHuy);
		pnCen.add(pnbutton);
		add(gui_main);
		btnDangnhap.addActionListener(this);
	}
	public JPanel getGUIdangnhap(){
		return this.gui_main;
	}
	public NhanVien getNhanvien(){
		return this.nhanvien;
	}
	public static void main(String[] args) {
		new GUIDangnhap().setVisible(true);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(DAOTaikhoan.Dangnhap(txtTK.getText(), txtMK.getText())== null) {
			pnSouth.add(lblthongbao);
			gui_main.revalidate();
			gui_main.repaint();
		}
		else {
			nhanvien = DAOTaikhoan.Dangnhap(txtTK.getText(), txtTK.getText());
			pnSouth.removeAll();
			gui_main.revalidate();
			gui_main.repaint();
		}
		 Component source = (Component) e.getSource();
		    while (source.getParent() != null) {            
		        source = source.getParent();
		    }

		    if (source instanceof ActionListener) {
		      ((ActionListener) source).actionPerformed(e);
		    }
		  }

	
}
