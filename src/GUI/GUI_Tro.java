package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAOInformation.DAOChutro;
import Entity.NhanVien;
import Entity.SinhVien;
import Entity.Tro;
import connectDatabase.Database;


public class GUI_Tro extends JFrame implements ActionListener,MouseListener{

	private JLabel lblmatro, lblchunha, lbldiachi, lblsdt, lbltitle,lblmanv;
	private JTextField txtmatro, txtchunha, txtdiachi, txtsdt, txtMess,txtmanv;
	private JButton btnthem, btnsua, btnxoarong, btnxoa;
	private JTable table;
	private DefaultTableModel tablemodel;
	DAOChutro dstro=new DAOChutro();
	
	public GUI_Tro() {
		

		JPanel pnborder = new JPanel();
		pnborder.setLayout(new BorderLayout());
		
		//north
		JPanel pnorth = new JPanel();
		pnorth.add(lbltitle = new JLabel("THÔNG TIN NHÀ TRỌ"));
		Font fp = new Font("Arial", Font.BOLD, 30);
		lbltitle.setFont(fp);
		lbltitle.setForeground(Color.decode("#40E0D0"));
		pnborder.add(pnorth, BorderLayout.NORTH);
		
		//center
		JPanel pcenter, p1, p2, p3, p4,p5, pc;
		pcenter = new JPanel();
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		pc = new JPanel();
		p5= new JPanel();
		//pc.setBorder(BorderFactory.createTitledBorder(""));
		pc.setLayout(new BoxLayout(pc, BoxLayout.Y_AXIS));
		//pc.setPreferredSize(new Dimension(0, 180));
		//pc.setLayout(null);
		
		p1.add(lblmatro = new JLabel("Mã chủ nhà: "));
		p1.add(txtmatro = new JTextField(50));
		pc.add(p1);
		
		p2.add(lblchunha = new JLabel("Tên chủ nhà: "));
		p2.add(txtchunha = new JTextField(50));
		pc.add(p2);
		
		p3.add(lbldiachi = new JLabel("Địa chỉ: "));
		p3.add(txtdiachi = new JTextField(50));
		pc.add(p3);
		
		p4.add(lblsdt = new JLabel("Số điện thoại: "));
		p4.add(txtsdt = new JTextField(50));
		pc.add(p4);
		p5.add(lblmanv=new JLabel("Mã nhân viên"));
		p5.add(txtmanv= new JTextField(50));
		pc.add(p5);
		pcenter.add(pc);
		

		lblmanv.setPreferredSize(lblsdt.getPreferredSize());
		lbldiachi.setPreferredSize(lblsdt.getPreferredSize());
		lblchunha.setPreferredSize(lblsdt.getPreferredSize());
		lblmatro.setPreferredSize(lblsdt.getPreferredSize());
		
		
		pc.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));
		
		String[] cols = {"Mã trọ","Tên chủ nhà","Địa chỉ","Số điện thoại","Mã nhân viên"};
		tablemodel = new DefaultTableModel(cols, 0);
		table = new JTable(tablemodel);
		JScrollPane scroll;
		table.setRowHeight(20);
		scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(680,225));
		pcenter.add(scroll);
		pnborder.add(pcenter, BorderLayout.CENTER);
		
		//south
		JPanel psouth = new JPanel();
		psouth.setBorder(BorderFactory.createTitledBorder(""));
		psouth.add(btnthem = new JButton("Thêm"));
		psouth.add(Box.createHorizontalStrut(10));
		psouth.add(btnsua = new JButton("Cập nhật"));
		psouth.add(Box.createHorizontalStrut(10));
		psouth.add(btnxoa = new JButton("Xóa"));
		psouth.add(Box.createHorizontalStrut(10));
		psouth.add(btnxoarong = new JButton("Xóa rỗng"));
		psouth.add(Box.createHorizontalStrut(10));
		pnborder.add(psouth, BorderLayout.SOUTH);
		add(pnborder);
		
		Database.getInstance().connect();
		updateTableData();
		
		
		btnthem.addActionListener(this);
		btnsua.addActionListener(this);
		btnxoarong.addActionListener(this);
		btnxoa.addActionListener(this);
		table.addMouseListener(this);
		setLocation(100,100);
		setSize(700,500);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		txtmatro.setText(table.getValueAt(row, 0).toString());
		txtchunha.setText(table.getValueAt(row, 1).toString());
		txtdiachi.setText(table.getValueAt(row, 2).toString());
		txtsdt.setText(table.getValueAt(row, 3).toString());
		txtmanv.setText(table.getValueAt(row, 4).toString());
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

	

	
	/*private void XoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();

	}*/
	
	public void updateTableData() {

		ArrayList<Tro> list= new DAOChutro().getListTro();
		//XoaHetDuLieuTrenTableModel();
		for (Tro t : list) {
		String[] row= {t.getMatro(),t.getDiachi(),t.getChunha(),t.getSdt(),t.getManv().getMaNV()};
		tablemodel.addRow(row);
		}
		table.setModel(tablemodel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnthem)) {		
					if(validData()&&kiemtratrungma()) {
						Tro tro = revertTroFromTextfields();
						if(dstro.addChutro(tro)) {
							String [] rowData = {txtmatro.getText(), txtchunha.getText(), txtdiachi.getText(), txtsdt.getText(), new NhanVien(txtmanv.getText()).getMaNV()};					
							tablemodel.addRow(rowData);
							JOptionPane.showMessageDialog(this, "Thêm thành công!!");
							Xoarong();
						}else
							JOptionPane.showMessageDialog(this, "Thêm không thành công, Hãy thử lại!!");
					}
				}
		else if(o.equals(btnxoarong)) {
			Xoarong();
		}
		else if(o.equals(btnsua)) {
			sua();
		}
		}
	
	
	private void sua() {
		// TODO Auto-generated method stub
		int row=table.getSelectedRow();
		if(row>=0) {
			Tro tro=revertTroFromTextfields();
			if(dstro.update(tro) && validData()) {
				int yes = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật Phòng trọ này?", "Warnning", JOptionPane.YES_NO_CANCEL_OPTION);
				if(yes == JOptionPane.YES_OPTION) { 
					table.setValueAt(txtdiachi.getText(), row, 1);
					table.setValueAt(txtchunha.getText(), row, 2);
					table.setValueAt(txtsdt.getText(), row, 3);
					table.setValueAt(txtmanv.getText(), row, 4);
					JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công!!!");
				}

			}
		}
	}

	public void luuTro()   {
		// TODO Auto-generated method stub
		   Tro tro=revertTroFromTextfields();
			if(new DAOChutro().addChutro(tro)) {
				//Object[] row= {Integer.parseInt(txtmatro.getText()),txtdiachi.getText(),txtchunha.getText(),txtsdt.getText()};
				//tablemodel.addRow(row);
				JOptionPane.showMessageDialog(this, "Lưu thành công!");
			} else {
				JOptionPane.showMessageDialog(this, "Lưu thất bại!");
			}
		
	}

	public void Xoarong() {
		txtmatro.setText("");
		txtchunha.setText("");
		txtdiachi.setText("");
		txtsdt.setText("");
		txtmanv.setText("");
	}
	
	
	public Tro revertTroFromTextfields() {
		String matro = txtmatro.getText().toString();
		String chunha = txtchunha.getText().toString();
		String diachi = txtdiachi.getText().toString();
		String sdt = txtsdt.getText().toString();
		String manv=txtmanv.getText().toString();
		return new Tro(matro,chunha,diachi,sdt,new NhanVien(manv));
	}
	
	public void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}
	
	public boolean validData() {
		String matro = txtmatro.getText().trim();
		String chunha = txtchunha.getText().trim();
		String diachi = txtdiachi.getText().trim();
		String sdt = txtsdt.getText().trim();
		
		if(matro.length() == 0) {
			showMessage("Mã trọ không được để trống", txtmatro);
			txtmatro.requestFocus();
				return false;
		} else if(!(matro.length() > 0 && matro.matches("^[A-Z]\\d{3}"))) {
			showMessage("Lỗi, mã bắt đầu bằng chữ cái in hoa", txtmatro);
			return false;
		}
		
		if(chunha.length() == 0) {
			showMessage("Tên chủ nhà không được để trống", txtchunha);
			txtchunha.requestFocus();
			return false;
		} else if(!(chunha.length() > 0 && chunha.matches("([a-zA-Z]+\\s*)+"))) {
			showMessage("Lỗi, tên chủ nhà phải là ký tự chuỗi", txtchunha);
			return false;
		}
		
		if(diachi.length() == 0) {
			showMessage("Địa chỉ không được để trống", txtdiachi);
			txtdiachi.requestFocus();
			return false;
		} else if(!(diachi.length() > 0 && diachi.matches("([a-zA-Z0-9]+\\s*)+"))) {
			showMessage("Lỗi,Địa chỉ phải là ký tự chuỗi", txtdiachi);
			return false;
		}
		
		if(sdt.length() == 0) {
			showMessage("Số điện thoại không được để trống", txtsdt);
			txtsdt.requestFocus();
			return false;
		} else if(!(sdt.length() > 0 && sdt.matches("^\\d{9}$"))) {
			showMessage("Lỗi, số điện thoại có 9 số xxx xxx xxx", txtsdt);
			return false;
		}
		
		return true;
	}
	private boolean kiemtratrungma() {
		// TODO Auto-generated method stub
		ArrayList<Tro> dsTro = dstro.getListTro();
		Tro tro = new Tro(txtmatro.getText(), txtchunha.getText(), txtdiachi.getText(), txtsdt.getText(), new NhanVien(txtmanv.getText()));
		if(dsTro.contains(tro)) {
			JOptionPane.showMessageDialog(this, "Trùng mã Trọ");
			return false;
			}else 
				return true;
		}


}
