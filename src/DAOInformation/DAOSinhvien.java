package DAOInformation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import Entity.Khoa;
import Entity.SinhVien;
import Entity.Tro;
import connectDatabase.Database;

public class DAOSinhvien {
	ArrayList<SinhVien> dssv;
	DAOChutro dao_tro = new DAOChutro();
			
	public DAOSinhvien() {
		// TODO Auto-generated constructor stub
		dssv=new ArrayList<SinhVien>();
	}
       
       public boolean addSinhvien(SinhVien sv){
    	   Connection con = Database.getInstance().getConnection();
    	   PreparedStatement stmt = null;
    	   int n = 0;
		 try {
			stmt = con.prepareStatement("Insert Sinhvien values(?,?,?,?,?,?,?)");
			stmt.setString(1, sv.getMssv());
			stmt.setString(2, sv.getHoten());
			stmt.setString(3, sv.getGioitinh());
			stmt.setString(4,sv.getLop());
			stmt.setString(5, sv.getQuequan());
			stmt.setString(6, sv.getKhoa().getMaKhoa());
			stmt.setString(7, sv.gettro().getMatro());
			n=stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return n>0;
	}
       public ArrayList<SinhVien> doctubang(){
    	 
    	   try {
    		   Database.getInstance().connect();
    		   Connection conn=Database.getInstance().getConnection();
    		   String sql="Select * from SinhVien";
    		   Statement ps = conn.createStatement();
    		   ResultSet rs=ps.executeQuery(sql);
    		   while(rs.next()) {
    			   String masv=rs.getString(1);
    			   String tensv=rs.getString(2);
    			   String gt=rs.getString(3);
    			   String lop=rs.getString(4);
			   		String que=rs.getString(5);
			   		String makhoa=rs.getString(6);
			   		String matro = rs.getString(7);
			   		SinhVien sv=new SinhVien(masv,tensv,gt,lop,que,new Khoa(makhoa), new Tro(matro));
			   		dssv.add(sv);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	   return dssv;
       }
      public boolean update(SinhVien sv) {
		Connection con = Database.getInstance().getConnection();
    	  PreparedStatement ps = null;
    	  String sql = "Update Sinhvien set tensv = ?, Gioitinh = ?, Lop=? , Quequan = ?, maKhoa= ?, Matro = ? where Mssv = ?" ;
    	  int n=0;
    	  try {
    		ps = con.prepareStatement(sql);
			ps.setString(1, sv.getHoten());
			ps.setString(2, sv.getGioitinh());
			ps.setString(3, sv.getLop());
			ps.setString(4, sv.getQuequan());
			ps.setString(5, sv.getKhoa().getMaKhoa());
			ps.setString(6, sv.gettro().getMatro());
			ps.setString(7, sv.getMssv());
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	  return n>0;
      }
      public boolean delete(String Mssv){
    	  Connection conn=Database.getInstance().getConnection();
    	  PreparedStatement stmt = null;
    	  int n=0;
    	  try {
			stmt = conn.prepareStatement("Delete from Sinhvien where Mssv = ?");
			stmt.setString(1, Mssv);
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	  return n>0;
      }
      public ArrayList<SinhVien> timkiemSV (String masv) {
  	  	ArrayList<SinhVien> svtim = new ArrayList<SinhVien>();	
  	  try {
  		  
  		  Connection con = Database.getInstance().getConnection();
  		  String sql = "Select * from Sinhvien where mssv = '"+masv+"'";
  		  Statement stmt = con.createStatement();
  		  ResultSet rs = stmt.executeQuery(sql);
	  			while(rs.next()) {
	  				String mssv = rs.getString(1);
		  			String tensv = rs.getString(2);
		  			String gioitinh = rs.getString(3);
		  			String lop = rs.getString(4);
		  			String quequan = rs.getString(5);
		  			String khoa = rs.getString(6);
		  			String matro = rs.getString(7);
		  			SinhVien sv = new SinhVien(mssv, tensv, gioitinh, lop, quequan, new Khoa(khoa), new Tro(matro));
		  			svtim.add(sv);
	  			}
	  			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
  	  return svtim;
      }
      public ArrayList<SinhVien> timkiemSVKhoa(String maKhoa) {
    	  	ArrayList<SinhVien> dsduyet = new ArrayList<SinhVien>();	
    	  try {
    		  
    		  Connection con = Database.getInstance().getConnection();
    		  String sql = "select * from Sinhvien where maKhoa = '"+maKhoa+"'";
    		  Statement stmt = con.createStatement();
    		  ResultSet rs = stmt.executeQuery(sql);
	  			while(rs.next()) {
	  				String mssv = rs.getString(1);
		  			String tensv = rs.getString(2);
		  			String gioitinh = rs.getString(3);
		  			String lop = rs.getString(4);
		  			String quequan = rs.getString(5);
		  			String khoa = rs.getString(6);
		  			String matro = rs.getString(7);
		  			SinhVien sv = new SinhVien(mssv, tensv, gioitinh, lop, quequan, new Khoa(khoa), new Tro(matro));
		  			dsduyet.add(sv);
	  			}
	  			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	  return dsduyet;
      }
}
