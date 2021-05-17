package DAOInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.NhanVien;
import Entity.TaiKhoan;
import connectDatabase.Database;

public class DAONhanvien {
	ArrayList<NhanVien> dsnv;
	public DAONhanvien() {
		// TODO Auto-generated constructor stub
		dsnv = new ArrayList<NhanVien>();
	}
	public ArrayList<NhanVien> getlistNhanvien() throws SQLException{
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "Select * from Nhanvien";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String manv = rs.getString(1);
				String tennv = rs.getString(2);
				String diachi = rs.getString(3);
				String sodienthoai = rs.getString(4);
				String taikhoan = rs.getString(5);
				NhanVien nv = new NhanVien(manv, tennv, diachi, sodienthoai, new TaiKhoan(taikhoan));
				dsnv.add(nv);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsnv;
	}
}
