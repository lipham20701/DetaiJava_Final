package Entity;

public class NhanVien {
      private String maNV;
      private String tenNV;
      private String diaChi;
      private String soDienthoai;
      private TaiKhoan taiKhoan;
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDienthoai() {
		return soDienthoai;
	}
	public void setSoDienthoai(String soDienthoai) {
		this.soDienthoai = soDienthoai;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public NhanVien(String maNV, String tenNV, String diaChi, String soDienthoai, TaiKhoan taiKhoan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.diaChi = diaChi;
		this.soDienthoai = soDienthoai;
		this.taiKhoan = taiKhoan;
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", diaChi=" + diaChi + ", soDienthoai=" + soDienthoai
				+ ", taiKhoan=" + taiKhoan + "]";
	}
      
      
}
