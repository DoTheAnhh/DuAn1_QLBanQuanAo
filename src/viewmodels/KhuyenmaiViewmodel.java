/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodels;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class KhuyenmaiViewmodel {
    private String TenKM,HinhThucKM,NgayBatDau,NgayKetThuc;
    private Double GiaTriGiam;
    private int Trangthai;

    public KhuyenmaiViewmodel() {
    }

    public KhuyenmaiViewmodel(String TenKM, String HinhThucKM, String NgayBatDau, String NgayKetThuc, Double GiaTriGiam,int Trangthai) {
        this.TenKM = TenKM;
        this.HinhThucKM = HinhThucKM;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.GiaTriGiam = GiaTriGiam;
        this.Trangthai = Trangthai;
    }

    public int getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(int Trangthai) {
        this.Trangthai = Trangthai;
    }
    
    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public String getHinhThucKM() {
        return HinhThucKM;
    }

    public void setHinhThucKM(String HinhThucKM) {
        this.HinhThucKM = HinhThucKM;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public Double getGiaTriGiam() {
        return GiaTriGiam;
    }

    public void setGiaTriGiam(Double GiaTriGiam) {
        this.GiaTriGiam = GiaTriGiam;
    }

    @Override
    public String toString() {
        return  TenKM;
    }
    
}
