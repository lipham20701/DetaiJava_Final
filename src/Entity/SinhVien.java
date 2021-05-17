package Entity;

public class SinhVien {
	
	private String mssv;
	private String hoten;
	private String gioitinh;
	private String lop;
	private String quequan;
	private Khoa Khoa;
	private Tro tro;
	
	public SinhVien(String mssv, String hoten, String gioitinh, String lop, String quequan, Khoa khoa, Tro tro) {
		super();
		this.mssv = mssv;
		this.hoten = hoten;
		this.gioitinh = gioitinh;
		this.lop = lop;
		this.quequan = quequan;
		this.Khoa = khoa;
		this.tro = tro;
	}
    
	
	public SinhVien(String mssv) {
		super();
		this.mssv = mssv;
	}
	public SinhVien() {
		
	}

	public String getMssv() {
		return mssv;
	}

	public void setMssv(String mssv) {
		this.mssv = mssv;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	public String getQuequan() {
		return quequan;
	}

	public void setQuequan(String quequan) {
		this.quequan = quequan;
	}

	public Khoa getKhoa() {
		return Khoa;
	}

	public void setmaKhoa(Khoa khoa) {
		this.Khoa = khoa;
	}
	
	public Tro gettro() {
		return tro;
	}


	public void settro(Tro tro) {
		this.tro = tro;
	}


	public void setKhoa(Khoa khoa) {
		Khoa = khoa;
	}


	@Override
	public boolean equals(Object obj) {
		
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		if(mssv != other.mssv)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SinhVien [mssv=" + mssv + ", hoten=" + hoten + ", gioitinh=" + gioitinh + ", lop=" + lop + ", quequan="
				+ quequan + ", khoa=" + Khoa + "]";
	}
	
	

}
