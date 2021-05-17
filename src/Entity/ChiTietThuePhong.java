package Entity;

public class ChiTietThuePhong {
      private SinhVien mssv;
      private Tro matro;
      private String ngayvao;
      private Double sotienthue;
      
	public ChiTietThuePhong(SinhVien mssv) {
		super();
		this.mssv = mssv;
	}
	public ChiTietThuePhong(SinhVien mssv, Tro matro, String ngayvao, Double sotienthue) {
		super();
		this.mssv = mssv;
		this.matro = matro;
		this.ngayvao = ngayvao;
		this.sotienthue = sotienthue;
	}
	public SinhVien getMssv() {
		return mssv;
	}
	public void setMssv(SinhVien mssv) {
		this.mssv = mssv;
	}
	public Tro getMatro() {
		return matro;
	}
	public void setMatro(Tro matro) {
		this.matro = matro;
	}
	public String getNgayvao() {
		return ngayvao;
	}
	public void setNgayvao(String ngayvao) {
		this.ngayvao = ngayvao;
	}
	public Double getSotienthue() {
		return sotienthue;
	}
	public void setSotienthue(Double sotienthue) {
		this.sotienthue = sotienthue;
	}
	@Override
	public String toString() {
		return "ChiTietThuePhong [mssv=" + mssv + ", matro=" + matro + ", ngayvao=" + ngayvao + ", sotienthue="
				+ sotienthue + "]";
	}
      
}
