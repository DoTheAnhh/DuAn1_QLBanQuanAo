/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodels;

import java.util.Date;

/**
 *
 * @author PC
 */
public class KhachHangViewMD {

    private int id;
    private String ten;
    private String tendem;
    private String ho;
    private int gioitinh;
    private Date ngaysinh;
    private String email;
    private String sdt;
    private int diemthuong;

    public KhachHangViewMD() {
    }

    public KhachHangViewMD(int id, String ten, String tendem, String ho, int gioitinh, Date ngaysinh, String email, String sdt, int diemthuong) {
        this.id = id;
        this.ten = ten;
        this.tendem = tendem;
        this.ho = ho;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.email = email;
        this.sdt = sdt;
        this.diemthuong = diemthuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTendem() {
        return tendem;
    }

    public void setTendem(String tendem) {
        this.tendem = tendem;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public int getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(int gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

   

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getDiemthuong() {
        return diemthuong;
    }

    public void setDiemthuong(int diemthuong) {
        this.diemthuong = diemthuong;
    }

    public Object[] toDataRow() {
        return new Object[]{id, ho + " " + tendem + " " + ten, gioitinh == 0 ? "Nam" : "Nữ", ngaysinh, sdt, email, diemthuong};

    }

}
