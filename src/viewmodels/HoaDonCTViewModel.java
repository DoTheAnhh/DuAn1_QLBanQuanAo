/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewmodels;



/**
 *
 * @author Win10
 */
public class HoaDonCTViewModel {
    private Integer idHoaDon;
    private Integer idCTSP;
    private int Soluong;
    private Double DonGia;
    private Double DonKhiGiam;

    public HoaDonCTViewModel() {
    }

    public HoaDonCTViewModel(Integer idHoaDon, Integer idCTSP, int Soluong, Double DonGia, Double DonKhiGiam) {
        this.idHoaDon = idHoaDon;
        this.idCTSP = idCTSP;
        this.Soluong = Soluong;
        this.DonGia = DonGia;
        this.DonKhiGiam = DonKhiGiam;
    }

    public Integer getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Integer idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Integer getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(Integer idCTSP) {
        this.idCTSP = idCTSP;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

    public Double getDonGia() {
        return DonGia;
    }

    public void setDonGia(Double DonGia) {
        this.DonGia = DonGia;
    }

    public Double getDonKhiGiam() {
        return DonKhiGiam;
    }

    public void setDonKhiGiam(Double DonKhiGiam) {
        this.DonKhiGiam = DonKhiGiam;
    }

    @Override
    public String toString() {
        return "HoaDonCTViewModel{" + "idHoaDon=" + idHoaDon + ", idCTSP=" + idCTSP + ", Soluong=" + Soluong + ", DonGia=" + DonGia + ", DonKhiGiam=" + DonKhiGiam + '}';
    }
   
}
