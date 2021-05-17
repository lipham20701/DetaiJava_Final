package GUI;

import java.awt.Color;
import java.util.Base64.Decoder;

public class Running {
	public static void main(String[] args) {
<<<<<<< HEAD
		new GUI_main().setVisible(true);
		String a ="as";
		String sql = "select * from Sinhvien sv join NhaTro n on sv.Matro=n.MaTro where sv.Mssv like '%"+a+"%'or sv.Tensv like '%"+a+"%'";
		System.out.println(sql);
=======
		GUIDangnhap login=new GUIDangnhap();
		login.setVisible(true);
>>>>>>> master
	}
}
