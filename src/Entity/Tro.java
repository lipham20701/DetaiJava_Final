package Entity;

public class Tro {

	private String matro;
	private String chunha;
	private String diachi;
	private String sdt;
	private NhanVien manv;
	public String getMatro() {
		return matro;
	}
	public void setMatro(String matro) {
		this.matro = matro;
	}
	public String getChunha() {
		return chunha;
	}
	public void setChunha(String chunha) {
		this.chunha = chunha;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public NhanVien getManv() {
		return manv;
	}
	public void setManv(NhanVien manv) {
		this.manv = manv;
	}
	public Tro(String matro, String chunha, String diachi, String sdt,NhanVien manv) {
		super();
		this.matro = matro;
		this.chunha = chunha;
		this.diachi = diachi;
		this.sdt = sdt;
		this.manv = manv;
	}
	@Override
	public String toString() {
		return "Tro [matro=" + matro + ", chunha=" + chunha + ", diachi=" + diachi + ", sdt=" + sdt + ", mssv="+", manv=" + manv + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matro == null) ? 0 : matro.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tro other = (Tro) obj;
		if (matro == null) {
			if (other.matro != null)
				return false;
		} else if (!matro.equals(other.matro))
			return false;
		return true;
	}
	public Tro(String matro) {
		super();
		this.matro = matro;
	}
	
   
    
	
	
}
