package Entity;

public class TaiKhoan {
   private NhanVien maNV;
   private String taiKhoan;
   private String matKhau;
public NhanVien getMaNV() {
	return maNV;
}
public void setMaNV(NhanVien maNV) {
	this.maNV = maNV;
}
public String getTaiKhoan() {
	return taiKhoan;
}
public void setTaiKhoan(String taiKhoan) {
	this.taiKhoan = taiKhoan;
}
public String getMatKhau() {
	return matKhau;
}
public void setMatKhau(String matKhau) {
	this.matKhau = matKhau;
}
public TaiKhoan(NhanVien maNV, String taiKhoan, String matKhau) {
	super();
	this.maNV = maNV;
	this.taiKhoan = taiKhoan;
	this.matKhau = matKhau;
}
public TaiKhoan(String taiKhoan) {
	super();
	this.taiKhoan = taiKhoan;
}
@Override
public String toString() {
	return "TaiKhoan [maNV=" + maNV + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + "]";
}
    
}
