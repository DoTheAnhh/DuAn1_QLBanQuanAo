/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodels;

import models.ChatLieu;
import models.DanhMucSP;
import models.KhuyenMai;
import models.KichCo;
import models.MauSac;
import models.NSX;
import models.ThuongHieu;

/**
 *
 * @author vieta
 */
public class ChiTietSPViewModel {

    private String ma;
    private String ten;
    private NSX nsx;
    private MauSac mausac;
    private DanhMucSP danhmuc;
    private KichCo kichco;
    private ChatLieu chatlieu;
    private ThuongHieu thuonghieu;
    private KhuyenMai khuyenmai;
    private int soluongton;
    private Double gianhap;
    private Double giaban;
    private String mota;
    private String qrcode;

    public ChiTietSPViewModel() {
    }

    public ChiTietSPViewModel(String ma, String ten, NSX nsx, MauSac mausac, DanhMucSP danhmuc, KichCo kichco, ChatLieu chatlieu, ThuongHieu thuonghieu, KhuyenMai khuyenmai, int soluongton, Double gianhap, Double giaban, String mota, String qrcode) {
        this.ma = ma;
        this.ten = ten;
        this.nsx = nsx;
        this.mausac = mausac;
        this.danhmuc = danhmuc;
        this.kichco = kichco;
        this.chatlieu = chatlieu;
        this.thuonghieu = thuonghieu;
        this.khuyenmai = khuyenmai;
        this.soluongton = soluongton;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.mota = mota;
        this.qrcode = qrcode;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public NSX getNsx() {
        return nsx;
    }

    public void setNsx(NSX nsx) {
        this.nsx = nsx;
    }

    public MauSac getMausac() {
        return mausac;
    }

    public void setMausac(MauSac mausac) {
        this.mausac = mausac;
    }

    public DanhMucSP getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(DanhMucSP danhmuc) {
        this.danhmuc = danhmuc;
    }

    public KichCo getKichco() {
        return kichco;
    }

    public void setKichco(KichCo kichco) {
        this.kichco = kichco;
    }

    public ChatLieu getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(ChatLieu chatlieu) {
        this.chatlieu = chatlieu;
    }

    public ThuongHieu getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(ThuongHieu thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public KhuyenMai getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(KhuyenMai khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public Double getGianhap() {
        return gianhap;
    }

    public void setGianhap(Double gianhap) {
        this.gianhap = gianhap;
    }

    public Double getGiaban() {
        return giaban;
    }

    public void setGiaban(Double giaban) {
        this.giaban = giaban;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

}
