package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAOInformation.DAOSinhvien;
import Entity.SinhVien;
import connectDatabase.Database;
public class TimKiemSinhVien extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lbltukhoa;
	private JTextField txttukhoa;
	private JButton btntim, btntrolai,btnxoa;
	private DefaultTableModel tablemodel;
	private JTable table;

	DAOSinhvien daosv = new DAOSinhvien();
	String[] cols = {"MSSV","Họ Và Tên","Giới Tính","Lớp","Quê Quán","Khoa"};
	public TimKiemSinhVien() {
		
		
		JPanel pnborder = new JPanel();
		pnborder.setLayout(new BorderLayout());
		
		JPanel pnorth = new JPanel();
		JLabel lblTitle = new JLabel("TÌM KIẾM THÔNG TIN SINH VIÊN");
		Font fb = new Font("Arial",Font.BOLD, 18);
		lblTitle.setFont(fb);
		pnorth.add(lblTitle);
		pnborder.add(pnorth, BorderLayout.NORTH);
		
		JPanel pnCen = new JPanel();
		pnCen.add(lbltukhoa = new JLabel("Nhập mã sinh viên cần tìm: "));
		pnCen.add(txttukhoa = new JTextField(30));
		JPanel pnbtn = new JPanel();
		pnbtn.add(btntim = new JButton("Tìm"));
		pnbtn.add(btntrolai = new JButton("Trở lại"));
		pnbtn.add(btnxoa = new JButton("Xóa"));
		btnxoa.setPreferredSize(btntrolai.getPreferredSize());
		btntim.setPreferredSize(btntrolai.getPreferredSize());
		pnCen.add(pnbtn);
		pnborder.add(pnCen, BorderLayout.CENTER);
		JPanel psouth = new JPanel();

		tablemodel = new DefaultTableModel(cols, 0);
		table = new JTable(tablemodel);
		JScrollPane scroll;
		table.setRowHeight(20);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(580,200));
		psouth.add(scroll);
		pnborder.add(psouth, BorderLayout.SOUTH);
		add(pnborder);
		btntim.addActionListener(this);
		btntrolai.addActionListener(this);
		btnxoa.addActionListener(this);
		setLocation(100,100);
		setSize(600,400);
		Database.getInstance().connect();
		updateTabledata();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj =e.getSource();
		if(obj.equals(btntim)) {
			timsv();
		}
		else if(obj.equals(btntrolai)) {
			tablemodel.setRowCount(0);
			updateTabledata();
		}
		else if(obj.equals(btnxoa))
		{
			int hoinhac = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xóa Sinh viên này?","Warnning",JOptionPane.YES_NO_CANCEL_OPTION);
			if(hoinhac == JOptionPane.YES_OPTION) {
				xoasv();
				updateTabledata();
			}
		}
	}
	private void updateTabledata() {
		// TODO Auto-generated method stub
		DAOSinhvien ds = new DAOSinhvien();
		List<SinhVien> list = ds.doctubang();
		for(SinhVien sv : list) {
			String [] rowdata = {sv.getMssv(),sv.getHoten(),sv.getGioitinh(),sv.getLop(),sv.getQuequan(),sv.getKhoa().getMaKhoa()};
			tablemodel.addRow(rowdata);
			
		}
		table.setModel(tablemodel);
	}
	private void timsv() {
		// TODO Auto-generated method stub
		tablemodel.setRowCount(0);
		String matim = txttukhoa.getText();
		if(txttukhoa.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập mã tìm kiếm!!");
			updateTabledata();
		}
		else if(daosv.timkiemSV(matim) != null){
			List<SinhVien> listsv = daosv.timkiemSV(matim);
			for(SinhVien sv : listsv) {
				String[] rowData = {sv.getMssv(),sv.getHoten(),sv.getGioitinh(),sv.getLop(),sv.getQuequan(),sv.getKhoa().getMaKhoa()};
				tablemodel.addRow(rowData);
			}
			table.setModel(tablemodel);
			
		}
	}
	private void xoasv() {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row >= 0) {
			String masv = (String) table.getValueAt(row, 0);
			if(daosv.delete(masv)) {
				tablemodel.removeRow(row);
				txttukhoa.setText("");
				JOptionPane.showMessageDialog(this, "Đã xóa Thành công!!");
			}	
		}	
		
	}
	public static void main(String[] args) {
		new TimKiemSinhVien().setVisible(true);
	}
}
