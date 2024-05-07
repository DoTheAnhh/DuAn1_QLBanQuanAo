/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodels;

/**
 *
 * @author PC
 */
public class KhachHang02ViewMD {

    private int id;
    private String ten;
    private String tendem;
    private String ho;
    private String SDT;
    private String MaHD;
    private String ngayTao;
    private double dongia;
    private int trangthai;

    public KhachHang02ViewMD() {
    }

    public KhachHang02ViewMD(int id, String ten, String tendem, String ho, String SDT, String MaHD, String ngayTao, double dongia, int trangthai) {
        this.id = id;
        this.ten = ten;
        this.tendem = tendem;
        this.ho = ho;
        this.SDT = SDT;
        this.MaHD = MaHD;
        this.ngayTao = ngayTao;
        this.dongia = dongia;
        this.trangthai = trangthai;
       
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

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public Object[] toDataRow() {
        return new Object[]{id, ho + " " + tendem + " " + ten, SDT, MaHD, ngayTao, dongia, trangthai == 0 ? "chờ thanh toán" : "Đã Thanh Toán"};

    }
}
