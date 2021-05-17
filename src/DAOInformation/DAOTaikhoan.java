package DAOInformation;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entity.NhanVien;
import Entity.TaiKhoan;
import connectDatabase.Database;

public class DAOTaikhoan {
	public static NhanVien Dangnhap(String taikhoan, String matkhau) {
		String maNv = null;
		try {
			Connection con = Database.getInstance().getConnection();
			String sql = "select Manv from dbo.Taikhoan where taikoan = "+ taikhoan + " and matkhau = "+matkhau;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String manv = rs.getString(1);
				String tennv = rs.getString(2);
				String diachi = rs.getString(3);
				String sodienthoai = rs.getString(4);
				String taikhoan1 = rs.getString(5);
				NhanVien nv = new NhanVien(manv, tennv, diachi, sodienthoai, new TaiKhoan(taikhoan1));
				return nv;
			}
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
