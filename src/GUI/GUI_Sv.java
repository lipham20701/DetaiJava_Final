package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAOInformation.DAOChutro;
import DAOInformation.DAOKhoa;
import DAOInformation.DAOSinhvien;
import Entity.Khoa;
import Entity.NhanVien;
import Entity.SinhVien;
import Entity.Tro;
import connectDatabase.Database;

public class GUI_Sv extends JFrame implements ActionListener, MouseListener {
	DAOSinhvien dao_sv = new DAOSinhvien();
	DAOChutro dao_tro = new DAOChutro();
	private JTable table;
	private JTextField txtmssv, txthoten, txtlop, txtquequan,txtKhoa,txtMatro;
	private String[] option = {"Nam","Nữ"};
	public String[] optionKhoa= {"Reset"};
	private JComboBox<Object> optionlist, optionlistDuyet;
	private JLabel lblmssv,lblhoten,lblgioitinh,lbllop,lblquequan,lbltitle;
	private JButton btnthem, btnxoa, btnxoarong,btnluu,btnsearch,btnsua,btnduyet;
	private DefaultTableModel tablemodel;
	String[] cols = {"MSSV","Họ Và Tên","Giới Tính","Lớp","Quê Quán","Khoa - Ngành học","Mã Trọ"};
	public GUI_Sv() {
		// TODO Auto-generated constructor stub
		setSize(800, 700);
		setResizable(false); 
		addControl();
	}
	private void addControl(){
		JPanel pnborder = new JPanel();
		pnborder.setLayout(new BorderLayout());
		//North
		JPanel pnorth = new JPanel();
		pnorth.add(lbltitle = new JLabel("THÔNG TIN SINH VIÊN"));
		Font fp = new Font("Arial", Font.BOLD, 30);
		lbltitle.setFont(fp);
		lbltitle.setForeground(Color.BLUE);
		pnborder.add(pnorth, BorderLayout.NORTH);
		//Center
				JPanel pcenter, p1, p2, p3, p4;
				pcenter = new JPanel();
				p1 = new JPanel();
				p2 = new JPanel();
				p3 = new JPanel();
				p4 = new JPanel();
				
				p1.add(lblmssv = new JLabel("Mã Sinh viên:"));
				p1.add(txtmssv = new JTextField(60));
				pcenter.add(p1);
				
				p2.add(lblhoten = new JLabel("Họ và tên:"));
				p2.add(txthoten = new JTextField(48));
				
				p2.add(lblgioitinh = new JLabel("Giới tính:"));
				optionlist = new JComboBox<Object>(option);
				p2.add(optionlist);
				pcenter.add(p2);
				
				p3.add(lbllop = new JLabel("Lớp:"));
				p3.add(txtlop = new JTextField(60));
				pcenter.add(p3);
				
				p4.add(lblquequan = new JLabel("Quê quán:"));
				p4.add(txtquequan = new JTextField(60));
				pcenter.add(p4);
				
				JPanel p5 = new JPanel();
				JLabel lblKhoa = new JLabel("Khoa");
				txtKhoa = new JTextField(60);
				p5.add(lblKhoa);
				p5.add(txtKhoa);
				pcenter.add(p5);
				
				
				JPanel p51 = new JPanel();
				JLabel lblMatro = new JLabel("Mã Trọ");
				txtMatro = new JTextField(60);
				p51.add(lblMatro);
				p51.add(txtMatro);
				pcenter.add(p51);
				
				JPanel p6 = new JPanel();
				JLabel lblDuyet = new JLabel("Duyệt sinh viên theo Khoa");
				optionlistDuyet = new JComboBox<Object>(optionKhoa);
				p6.add(lblDuyet);
				p6.add(optionlistDuyet);
				p6.add(btnduyet = new JButton("Duyệt"));
				pcenter.add(p6);
				
				tablemodel = new DefaultTableModel(cols, 0);
				table = new JTable(tablemodel);
				JScrollPane scroll;
				table.setRowHeight(20);
				scroll = new JScrollPane(table);
				scroll.setPreferredSize(new Dimension(700,500));
				pcenter.add(scroll);
				pnborder.add(pcenter, BorderLayout.CENTER);
				

				

				lblhoten.setPreferredSize(lblmssv.getPreferredSize());
				lbllop.setPreferredSize(lblhoten.getPreferredSize());
				lblquequan.setPreferredSize(lbllop.getPreferredSize());
				
				//south
				JPanel psouth = new JPanel();
				psouth.setBorder(BorderFactory.createTitledBorder(""));
				psouth.add(btnthem = new JButton("Thêm"));
				psouth.add(Box.createHorizontalStrut(10));
				psouth.add(btnxoarong = new JButton("Xóa rỗng"));
				psouth.add(Box.createHorizontalStrut(10));
				psouth.add(btnxoa = new JButton("Xóa"));
				psouth.add(Box.createHorizontalStrut(10));
//				psouth.add(btnluu = new JButton("Lưu"));
				psouth.add(Box.createHorizontalStrut(10));
				psouth.add(btnsua = new JButton("Sửa"));
				pnborder.add(psouth, BorderLayout.SOUTH);
				add(pnborder);
				//Set Prefer
				lblhoten.setPreferredSize(lblmssv.getPreferredSize());
				lbllop.setPreferredSize(lblmssv.getPreferredSize());
				lblquequan.setPreferredSize(lblmssv.getPreferredSize());
				lblKhoa.setPreferredSize(lblmssv.getPreferredSize());
				lblMatro.setPreferredSize(lblmssv.getPreferredSize());
				
				//
				
				btnthem.addActionListener(this);
				btnxoa.addActionListener(this);
				btnxoarong.addActionListener(this);
				btnsua.addActionListener(this);
//				btnluu.addActionListener(this);
				table.addMouseListener(this);
				btnduyet.addActionListener(this);
				Database.getInstance().connect();
				updateTabledata();
				updateCbxKhoa();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj.equals(btnthem)) {
			if(kiemTraNhapLieu() && kiemTraTrungMa()) {
				SinhVien sv = reverFromTextField();
				if(dao_sv.addSinhvien(sv)) {
					
					tablemodel.addRow(new Object[] {txtmssv.getText(), txthoten.getText(),optionlist.getSelectedItem().toString(),txtlop.getText(),txtquequan.getText(),new Khoa(txtKhoa.getText()).getMaKhoa(),new Tro(txtMatro.getText()).getMatro()});
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					xoarong();
				}else {
					JOptionPane.showMessageDialog(this, "Thêm không thành công");
					int hoi = JOptionPane.showConfirmDialog(this, "Mã trọ không tồn tại!\n Bạn có muốn thêm Trọ?", "Warnning", JOptionPane.YES_NO_CANCEL_OPTION);
					if(hoi == JOptionPane.YES_OPTION) {
						new GUI_Tro().setVisible(true);
					}
				}
			}
		}
		else if(obj.equals(btnxoarong)) {
			xoarong();
		}
		else if(obj.equals(btnluu)) {
			luudata();
		}
		else if(obj.equals(btnxoa)) {
//			xoasv();
			int hoinhac = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xóa Sinh viên này?","Warnning",JOptionPane.YES_NO_CANCEL_OPTION);
			if(hoinhac == JOptionPane.YES_OPTION) {
				xoasv();
			}
		}
		else if(obj.equals(btnsua)) {
			suasv();
		}else if(obj.equals(btnduyet)) {
			lietkesvKhoa();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtmssv.setText(table.getValueAt(row, 0).toString());
		txthoten.setText(table.getValueAt(row, 1).toString());
		optionlist.setSelectedItem(table.getValueAt(row, 2).toString());
		txtlop.setText(table.getValueAt(row, 3).toString());
		txtquequan.setText(table.getValueAt(row, 4).toString());
		txtKhoa.setText(table.getValueAt(row, 5).toString());
		txtMatro.setText(table.getValueAt(row, 6).toString());
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public boolean kiemTraNhapLieu() {
		
		String masv = txtmssv.getText().trim();
		String hoTen = txthoten.getText().trim();
		String gioitinh = (String) optionlist.getSelectedItem();
		String lop = txtlop.getText().trim();
		String quequan = txtquequan.getText().trim();
		String khoa = txtKhoa.getText().trim();
		if (masv.equals("") || !masv.matches("(A|B)\\d{3}")) {
			JOptionPane.showMessageDialog(this, "Bắt đầu bằng A or B theo sau là 3 số.");
			return false;
		}
		if (hoTen.equals("") || !hoTen.matches("\\w+(\\s\\w+)*")) {
			JOptionPane.showMessageDialog(this, "tên gồm 1 hoặc nhiều từ cách nhau bởi khoảng trắng");
			return false;
		}
		if (lop.equals("") || !lop.matches("^([A-Z]{4}\\d{2}[A-Z])$")) {
			JOptionPane.showMessageDialog(this, "Lớp có định dạng DHTH15I");
			return false;
		}
		if (quequan.equals("") || !quequan.matches("([A-Za-z]+\\s*)+$")) {
			JOptionPane.showMessageDialog(this, "Quê quán bao gồm 1 hoặc nhiều từ");
			return false;
		}
		if (khoa.equals("") || !khoa.matches("([A-Za-z]+\\s*)+$")) {
			JOptionPane.showMessageDialog(this, "Khoa bao gồm 1 chuỗi kí tự không bao gồm số và khoảng cách");
			return false;
		}
		return true;
	}
	private void xoarong() {
		txtmssv.setText("");
		txthoten.setText("");
		txtlop.setText("");
		txtquequan.setText("");
		txtKhoa.setText("");
	}
	public boolean kiemTraTrungMa() {
		ArrayList<SinhVien> dsSV = dao_sv.doctubang();
		SinhVien sv = new SinhVien(txtmssv.getText(), txthoten.getText(),optionlist.getSelectedItem().toString(),txtlop.getText(),txtquequan.getText(),new Khoa(txtKhoa.getText()),new Tro(txtMatro.getText())); 
		if (dsSV.contains(sv)) {
			JOptionPane.showMessageDialog(this, "Trùng mã");
			return false;
		} else
			return true;
	}
	private void updateTabledata() {
		// TODO Auto-generated method stub
		DAOSinhvien ds = new DAOSinhvien();
		List<SinhVien> list = ds.doctubang();
		for(SinhVien sv : list) {
			String [] rowdata = {sv.getMssv(),sv.getHoten(),sv.getGioitinh(),sv.getLop(),sv.getQuequan(),sv.getKhoa().getMaKhoa(),sv.gettro().getMatro()};
			tablemodel.addRow(rowdata);
			
		}
		table.setModel(tablemodel);
	}
	private void luudata() {
		SinhVien sv = reverFromTextField();
		dao_sv.addSinhvien(sv);
	}
	private SinhVien reverFromTextField() {
		String mssv = txtmssv.getText();
		String hoten = txthoten.getText();
		String gioitinh = optionlist.getSelectedItem().toString();
		String lop = txtlop.getText();
		String quequan =txtquequan.getText();
		String khoa = txtKhoa.getText();
		String matro = txtMatro.getText();
		return new SinhVien(mssv, hoten, gioitinh, lop, quequan, new Khoa(khoa), new Tro(matro));
	}
	private void xoasv() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row >= 0) {
			String masv = (String) table.getValueAt(row, 0);
			if(dao_sv.delete(masv)) {
				tablemodel.removeRow(row);
				xoarong();
			}	
		}	
		
	}
	private void updateCbxKhoa() {
		DAOKhoa dao_khoa = new DAOKhoa();
		List<Khoa> listkhoa = dao_khoa.doctubang();
		for(Khoa khoa : listkhoa ) {
			optionlistDuyet.addItem(khoa.getMaKhoa());
			
		}
	}
	private void lietkesvKhoa() {
		tablemodel.setRowCount(0);
		String makhoa = (String) optionlistDuyet.getSelectedItem();
		if(makhoa.equals("Reset")) {
			updateTabledata();
		}else {
			List<SinhVien> dssvt = dao_sv.timkiemSVKhoa(makhoa);
			for(SinhVien sv : dssvt) {
				String[] rowData = {sv.getMssv(),sv.getHoten(),optionlist.getSelectedItem().toString(),sv.getLop(),sv.getQuequan(),sv.getKhoa().getMaKhoa(),sv.gettro().getMatro()};
				tablemodel.addRow(rowData);
				
			}
			table.setModel(tablemodel);
		}
	}
	private void suasv() {
		int row = table.getSelectedRow();
		if(row >= 0) {
			SinhVien sv = reverFromTextField();
			if(dao_sv.update(sv) && kiemTraNhapLieu()) {
				int yes = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật sinh viên này?", "Warnning", JOptionPane.YES_NO_CANCEL_OPTION);
				if(yes == JOptionPane.YES_OPTION) {
//				yes = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật sinh viên này?", "Warnning", JOptionPane.YES_NO_CANCEL_OPTION);
				table.setValueAt(txthoten.getText(), row, 1);
				table.setValueAt(optionlist.getSelectedItem().toString(), row, 2);
				table.setValueAt(txtlop.getText(), row, 3);
				table.setValueAt(txtquequan.getText(), row, 4);
				table.setValueAt(txtKhoa.getText(), row, 5);
				table.setValueAt(txtMatro.getText(), row, 6);
				JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!");
				}
			}
		}
	}
	public static void main(String[] args) {
		new GUI_Sv().setVisible(true);
		
	}
}
