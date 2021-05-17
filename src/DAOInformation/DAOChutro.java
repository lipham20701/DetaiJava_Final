package DAOInformation;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import Entity.NhanVien;
import Entity.Tro;
import GUI.GUI_Tro;
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
			System.err.println(e);

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
			System.err.println(e);
		}
		return dao_tro;
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
			System.err.println(e);
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
			System.err.println(e);
		}
    	  return n>0;
      }
      public ArrayList<Tro> timkiemthongtin(String tim) {
    	  try {
    		Connection conn = Database.getInstance().getConnection();
    		String sql = "select * from NhaTro n join Sinhvien sv  on sv.Matro=n.MaTro where sv.Mssv = '%"+tim+"%'"+"or ";
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String matro=rs.getString(1);
				String diachi=rs.getString(2);
				String chunha=rs.getString(3);
                String sdt=rs.getString(4);
                String manv=rs.getString(5);
                dao_tro.add(new Tro(matro, chunha, diachi, sdt,new NhanVien(manv)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
    	  return dao_tro;
      }
      public ArrayList<Tro> timkiemtro(String mssv) {
    	  try {
    		 Connection conn = Database.getInstance().getConnection();
      		String sql = "Select * from Sinhvien SV Join NhaTro NT ON SV.Matro = NT.MaTro where Mssv = '"+mssv+"'";
      		Statement stmt = conn.createStatement();
      		ResultSet rs = stmt.executeQuery(sql);
  			while(rs.next()) {
  				String matro=rs.getString(1);
  				String diachi=rs.getString(2);
  				String chunha=rs.getString(3);
                String sdt=rs.getString(4);
                String manv=rs.getString(5);
                dao_tro.add(new Tro(matro, chunha, diachi, sdt,new NhanVien(manv)));
  			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println(e);
		}
    	  return dao_tro;
      }
    
}

