package DAOInformation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.Khoa;
import connectDatabase.Database;

public class DAOKhoa {
	ArrayList<Khoa> dskhoa ;
	public DAOKhoa() {
		// TODO Auto-generated constructor stub
		dskhoa = new ArrayList<Khoa>();
	}
	public ArrayList<Khoa> doctubang(){
		try {
			Connection con = Database.getInstance().getConnection();
			String sql="Select * from Khoa";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String makhoa = rs.getString(1);
				String tenkhoa = rs.getString(2);
				Khoa khoa = new Khoa(makhoa, tenkhoa);
				dskhoa.add(khoa);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dskhoa;
	}
}
