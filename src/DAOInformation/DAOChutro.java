package DAOInformation;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import Entity.Khoa;
import Entity.NhanVien;
import Entity.SinhVien;
import Entity.Sinhvien_Tro;
import Entity.Tro;
import connectDatabase.Database;

public class DAOChutro {
      ArrayList<Tro> dao_tro;
      public DAOChutro() {
    	  dao_tro = new ArrayList<Tro>();
      }
      public boolean addChutro(Tro tro) {
    	  Connection conn=Database.getInstance().getConnection();
    	  String sql="insert into NhaTro values(?,?,?,?,?)";
    	  PreparedStatement ps=null;
    	  try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, tro.getMatro());
			ps.setString(2, tro.getDiachi());
			ps.setString(3, tro.getChunha());
			ps.setString(4, tro.getSdt());
			ps.setString(5, tro.getManv().getMaNV());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();

		}
			return false;
      }
      public ArrayList<Tro> getListTro() {
    	  try {
    		 
    		 Connection conn=Database. getInstance().getConnection();
    	  String sql="Select * from NhaTro";
			Statement stmt = conn.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			while(rs.next()) {
				String matro=rs.getString("matro");
				String diachi=rs.getString("diachi");
				String chunha=rs.getString("chunha");
				String sdt=rs.getString("sdt");
				String manv=rs.getString("Manv");
                dao_tro.add(new Tro(matro,diachi,chunha,sdt,new NhanVien(manv)));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dao_tro;
	}
      public ArrayList<SinhVien> doctubangJoin(){
    	  ArrayList<SinhVien> listsv = new ArrayList<SinhVien>();
    	  try {
     		 
     	Connection conn=Database. getInstance().getConnection();
     	  String sql="Select * from Sinhvien SV Join NhaTro NT ON SV.Matro = NT.MaTro ";
     	  Statement stmt = conn.createStatement();
     	  ResultSet rs= stmt.executeQuery(sql);
 			while(rs.next()) {
 				String mssv=rs.getString(1);
				String tensv=rs.getString(2);
				String gioitinh=rs.getString(3);
				String lop = rs.getString(4);
                String quequan=rs.getString(5);
                String makhoa = rs.getString(6);
                String matro = rs.getString(7);
                String diachi = rs.getString(9);
                String chunha = rs.getString(10);
                String sdt = rs.getString(11);
                String manv = rs.getString(12);
                SinhVien sv = new SinhVien(mssv,tensv,gioitinh,lop,quequan,new Khoa(makhoa), new Tro(matro, chunha, diachi, sdt, new NhanVien(manv)));
                listsv.add(sv);
 			}
 		} catch (SQLException e) {
 			// TODO: handle exception
 			e.printStackTrace();
 		}
 		return listsv;
      }
      public boolean update(Tro tro){
    	  Connection conn= Database.getInstance().getConnection();
    	 PreparedStatement ps= null;
    	 String sql = "update NhaTro set DiaChi=?, ChuNha=?,SDT=?,Manv=? where MaTro=? ";
    	  int n=0;
    	  try {
    		 ps = conn.prepareStatement(sql); 
			ps.setString(1, tro.getDiachi());
			ps.setString(2, tro.getChunha());
			ps.setString(3, tro.getSdt());
			ps.setString(4, tro.getManv().getMaNV());
			ps.setString(5, tro.getMatro());
			n=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	  return n>0;
      }
      public boolean delete(String maTro)  {
    	  Connection connection= Database.getInstance().getConnection();
    	 PreparedStatement stmt = null;
    	  int n=0;
    	  try {
    	 stmt = connection.prepareStatement("delete from NhaTro where MaTro=?");
			stmt.setString(1, maTro);
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	  return n>0;
      }
      public ArrayList<SinhVien> timkiemSv_tro(String tim) {
    	  ArrayList<SinhVien> dssv = new ArrayList<SinhVien>();
    	  try {
    		Connection conn = Database.getInstance().getConnection();
    		String sql = "select * from Sinhvien sv join NhaTro n on sv.Matro=n.MaTro where sv.Mssv like '%"+tim+"%'or sv.Tensv like '%"+tim+"%'or n.Diachi like '%"+tim+"%'or n.SDT like '%"+tim+"%'";
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String mssv=rs.getString(1);
				String tensv=rs.getString(2);
				String gioitinh=rs.getString(3);
				String lop = rs.getString(4);
                String quequan=rs.getString(5);
                String makhoa = rs.getString(6);
                String matro = rs.getString(7);
                String diachi = rs.getString(9);
                String chunha = rs.getString(10);
                String sdt = rs.getString(11);
                String manv = rs.getString(12);
                SinhVien sv = new SinhVien(mssv,tensv,gioitinh,lop,quequan,new Khoa(makhoa), new Tro(matro, chunha, diachi, sdt, new NhanVien(manv)));
                dssv.add(sv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  return dssv;
      }
      public ArrayList<Tro> timkiemtro(String mssv, String diachi, String sodienthoai) {
    	  try {
    		 Connection conn = Database.getInstance().getConnection();
      		String sql = "Select * from Sinhvien SV Join NhaTro NT ON SV.Matro = NT.MaTro where Mssv = '"+mssv+"' and diachi = '"+diachi+"' and SDT = '"+sodienthoai+"'";
      		Statement stmt = conn.createStatement();
      		ResultSet rs = stmt.executeQuery(sql);
  			while(rs.next()) {
  				String matro=rs.getString(7);
  				String diachi1=rs.getString(8);
  				String chunha=rs.getString(9);
                String sdt=rs.getString(10);
                String manv=rs.getString(11);
                dao_tro.add(new Tro(matro, chunha, diachi1, sdt,new NhanVien(manv)));
  			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	  return dao_tro;
      }
}

