package Entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAOInformation.DAOChutro;

import connectDatabase.Database;

public class GUI_TimTroSinhVien extends JFrame implements ActionListener {
        private JButton btntim,btnxoatrang,btnduyet,btntrolai;
        private JTextField txtDiachi,txtchunha,txtSDT,txtMa,txttukhoa;
	private	JLabel lblTitle;
        private JLabel lbldiachi,lblchunha,lblsdt,lblMa,lbltukhoa;
        private JTable table;
        private DefaultTableModel tablemodel;
        DAOChutro dao_chutro = new DAOChutro();
        public GUI_TimTroSinhVien() {
        	super("TimkiemthongtinTro");
        	setSize(700,450);
        	setDefaultCloseOperation(EXIT_ON_CLOSE);
        	setLocationRelativeTo(null);
        	setResizable(false);
        	//
        	
        	JPanel pnBorder=new JPanel();
        	pnBorder.setLayout(new BorderLayout());
        	add(pnBorder);
        	JPanel pnNorth=new JPanel();
        	pnNorth.add(lblTitle=new JLabel("Tìm kiếm trọ sinh viên"));
        	lblTitle.setForeground(Color.ORANGE);
        	Font fp= new Font("Times New Roman", Font.BOLD, 35);
        	lblTitle.setFont(fp);
        	pnBorder.add(pnNorth,BorderLayout.NORTH);
        	//
        	JPanel pnCenter= new JPanel();
        	
        	JPanel pn1,pn2,pn3,pn0,pnduyet;
        	pnduyet = new JPanel();
        	pn1=new JPanel();
        	pn2= new JPanel();
        	pn3= new JPanel();
        	pn0=new JPanel();
        	pnduyet.add(lbltukhoa = new JLabel("Tìm kiếm"));
        	pnduyet.add(txttukhoa = new JTextField(25));
        	pnduyet.add(btnduyet = new JButton("Duyệt"));
        	pnCenter.add(pnduyet);
        	
//        	pn0.add(lblMa=new JLabel("Nhập mã sinh viên"));
//        	pn0.add(txtMa= new JTextField(50));
//        	pnCenter.add(pn0);
////        	pn1.add(lbldiachi=new JLabel("Nhập địa chỉ    "));
//        	pn1.add(txtDiachi=new JTextField(50));
//        	
//        	pnCenter.add(pn1);
//        	pn2.add(lblchunha= new JLabel("Nhập chủ nhà   "));
//        	pn2.add(txtchunha= new JTextField(50));
//        	pnCenter.add(pn2);
//        	pn3.add(lblsdt= new JLabel("Nhập số điện thoại"));
//        	pn3.add(txtSDT= new JTextField(50));
//        	pnCenter.add(pn3);
//        	lblchunha.setPreferredSize(lblsdt.getPreferredSize());
//        	lbldiachi.setPreferredSize(lblsdt.getPreferredSize());
        	//lblMa.setPreferredSize(lblsdt.getPreferredSize());
        	//lbltukhoa.setPreferredSize(lblMa.getPreferredSize());
        	JPanel pnButton= new JPanel();
        	//pnButton.add(btntim=new JButton("Tìm"));
        	pnButton.add(Box.createHorizontalStrut(5));
        	pnButton.add(btnxoatrang=new JButton("Xoá trắng"));
        	pnButton.add(Box.createHorizontalStrut(5));
        	pnButton.add(btntrolai=new JButton("Trở lại"));
        	pnCenter.add(pnButton);
        	//table
        	String[] cols = {"Mã sinh viên","Họ tên","Mã trọ","Tên chủ nhà","Địa chỉ","Số điện thoại"};
    		tablemodel = new DefaultTableModel(cols, 0);
    		table = new JTable(tablemodel);
    		JScrollPane scroll;
    		table.setRowHeight(20);
    		scroll = new JScrollPane(table);
    		scroll.setPreferredSize(new Dimension(680,225));
    		pnCenter.add(scroll);
        	pnBorder.add(pnCenter,BorderLayout.CENTER);
        	///
        	//btntim.addActionListener(this);
        	btnxoatrang.addActionListener(this);
        	btnduyet.addActionListener(this);
        	btntrolai.addActionListener(this);
        	Database.getInstance().connect();
        	updateTable();
        }
        private void updateTable() {
			// TODO Auto-generated method stub
        	ArrayList<SinhVien> list= new DAOChutro().doctubangJoin();
    		//XoaHetDuLieuTrenTableModel();
    		for (SinhVien t : list) {
    		String[] row= {t.getMssv(),t.getHoten(),t.gettro().getMatro(),t.gettro().getChunha(),t.gettro().getDiachi(),t.gettro().getSdt()};
    		tablemodel.addRow(row);
    		}
    		table.setModel(tablemodel);
		}
		public static void main(String[] args) {
			new GUI_TimTroSinhVien().setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj=e.getSource();
//			String timma=txtMa.getText();
//			String timdc=txtDiachi.getText();
//			String timcn=txtchunha.getText();
//			String timsdt=txtSDT.getText();
			
				if(obj.equals(btnduyet)) {
				tablemodel.setRowCount(0);
				String tim = txttukhoa.getText();
					if(tim.length()==0) {
					updateTable();
					}else {
					List<SinhVien> dssv = dao_chutro.timkiemSv_tro(tim);
					for(SinhVien sv : dssv) {
						String[] rowData= {sv.getMssv(),sv.getHoten(),sv.gettro().getMatro(),sv.gettro().getChunha(),sv.gettro().getDiachi(),sv.gettro().getSdt()};
						tablemodel.addRow(rowData);
					}
					table.setModel(tablemodel);
			}
			}else if(obj.equals(btntrolai)) {
				tablemodel.setRowCount(0);
				updateTable();
			}
				
			else if(obj.equals(btnxoatrang)) {
				xoatrang();
			}
		}
		private void xoatrang() {
			// TODO Auto-generated method stub
			txttukhoa.setText("");
		}
		
}
