package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import Entity.NhanVien;
import connectDatabase.Database;
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
		super("Form đăng nhập");
		setSize(550,350);
		setResizable(false);
		setLocationRelativeTo(null);
		addControl();
		
	}
	private void addControl() {
		// TODO Auto-generated method stub
		Dimension expectedDimension = new Dimension(200, 150);
		JPanel pnNorth = new JPanel();
		JLabel lbltitle = new JLabel("ĐĂNG NHẬP");
		Font fb = new Font("Time new Roman",Font.BOLD, 36);
		lbltitle.setFont(fb);
		lbltitle.setForeground(Color.decode("#40E0D0"));
		pnNorth.add(lbltitle);
		gui_main.add(pnNorth,BorderLayout.NORTH);
		
		//
		JPanel pnCen = new JPanel();
		JPanel pnTk = new JPanel();
		JLabel lblTK = new JLabel("Tên đăng nhập ");
		JLabel lblMK = new JLabel("Mật khẩu ");
		txtTK = new JTextField();
		txtMK = new JPasswordField();
		lblMK.setForeground(Color.decode("#1E90FF"));
		lblTK.setForeground(Color.decode("#1E90FF"));
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
	    txtTK.setBackground(Color.decode("#E0FFFF"));
	    txtMK.setBackground(Color.decode("#E0FFFF"));
	    gui_main.add(pnCen,BorderLayout.CENTER);	
		lblthongbao = new JLabel("Sai tên Đăng nhập hoặc Mật khẩu");
		btnDangnhap = new JButton("Đăng nhập");
		btnHuy = new JButton("Hủy");
		btnDangnhap.setForeground(Color.decode("#1E90FF"));
		btnHuy.setForeground(Color.decode("#1E90FF"));
		//btnDangnhap.setBackground(Color.decode("#FFEFD5"));
		//btnHuy.setBackground(Color.decode("#FFEFD5"));
		btnHuy.setPreferredSize(btnDangnhap.getPreferredSize());
		
		JPanel pnbutton = new JPanel();
		pnbutton.add(btnDangnhap);
		pnbutton.add(btnHuy);
		pnCen.add(pnbutton);
		add(gui_main);
		btnDangnhap.addActionListener(this);
		btnHuy.addActionListener(this);
		Database.getInstance().connect();
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	   Object obj=e.getSource();
			   if(obj.equals(btnDangnhap)) {
				   checklogin();
			   }
			   else if(obj.equals(btnHuy)) {
				   System.exit(EXIT_ON_CLOSE);
			   }
	    }
     private void checklogin() {
    	   if((txtTK.getText()).equals("")) {
   	    	JOptionPane.showMessageDialog(this, "Vui lòng điền tài khoản!");
   	    }
   	    else if((txtMK.getText()).equals("")) {
   	    	JOptionPane.showMessageDialog(this, "Vui lòng điền mật khẩu!");
   	    }
   	    else {
   	    	try {
   	    		Connection con = Database.getInstance().getConnection();
   				String sql = "select Manv from dbo.Taikhoan where TK = ? and MK = ?";
   				PreparedStatement ps=con.prepareStatement(sql);
   				ps.setString(1, txtTK.getText());
   				ps.setString(2, txtMK.getText());
   				ResultSet rs=ps.executeQuery();
   				if(rs.next()) {
   					
   					GUI_main main=new GUI_main();
   					main.setLocationRelativeTo(null);
   					main.setVisible(true);
   					
   				}
   				else {
   					JOptionPane.showMessageDialog(this, "Đăng nhập thất bại! Kiểm tra lại tài khoản/mật khẩu!");
   					txtTK.requestFocus();
   				}
   	    	}catch(Exception e1) {
   					System.out.println(e1);
   				}
   	    	}
     }
        

}
