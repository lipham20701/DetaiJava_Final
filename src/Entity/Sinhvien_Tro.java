package Entity;

public class Sinhvien_Tro extends SinhVien {
	private Tro tro;

	public Tro getTro() {
		return tro;
	}

	public void setTro(Tro tro) {
		this.tro = tro;
	}

	public Sinhvien_Tro(String mssv, String hoten, String gioitinh, String lop, String quequan, Entity.Khoa khoa,
			Tro tro, Tro tro2) {
		super(mssv, hoten, gioitinh, lop, quequan, khoa, tro);
		tro = tro2;
	}
	
}
