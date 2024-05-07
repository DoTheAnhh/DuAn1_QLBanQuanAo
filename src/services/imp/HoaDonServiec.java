/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.imp;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.HoaDon;
import models.HoaDonChiTiet;
import models.KhachHang;
import models.SanPham;
import models.User;
import repositorys.IHoaDonRepostory;
import repositorys.ISanPhamReposory;
import repositorys.imp.HoaDonRepostory;
import repositorys.imp.SanPhamRepostory;
import services.IHoaDonServiec;
import services.IKhachHangService;
import viewmodels.HoaDonCHiTietViewModel;
import viewmodels.HoaDonViewModel;
import viewmodels.SanPhamViewModel;
import services.ISanPhamServiecs;

/**
 *
 * @author Admin
 */
public class HoaDonServiec implements IHoaDonServiec {

    private IHoaDonRepostory hoadon1 = new HoaDonRepostory();
    private IHoaDonRepostory hoadon2 = new HoaDonRepostory();
    private List<HoaDonViewModel> getListHD;
    private IHoaDonRepostory hoaDonRepostory;
    private List<HoaDonCHiTietViewModel> getListHDCT;
    private IKhachHangService khachHangService;
    private ISanPhamServiecs sanISamPhamServiecs;

    public HoaDonServiec() {
        getListHD = new ArrayList<>();
        getListHDCT = new ArrayList<>();
        hoaDonRepostory = new HoaDonRepostory();
        sanISamPhamServiecs = new SanPhamServiec();
        khachHangService = new khahangsvImpl();

    }

    @Override
    public List<HoaDonViewModel> getAllHD() {
        List<HoaDonViewModel> list01 = new ArrayList<>();

        List<HoaDon> list = hoadon1.GetAllHD();
        for (HoaDon x : list) {
            list01.add(new HoaDonViewModel(x.getKhachHang(),x.getUser(),x.getMa(),x.getNgayTao() ,x.getNgayThanhToan() ,x.getTinhTrang(),x.getGhichu(),x.getTongTien()));
//            HoaDonViewModel hd = new HoaDonViewModel();
//            hd.setKh(x.getKhachHang());
//            hd.setUs(x.getUser());
//            hd.setMa(x.getMa());
//            hd.setNgayTao(x.getNgayTao());
//            hd.setNgayThanhToan(x.getNgayThanhToan());
//            hd.setTinhTrang(x.getTinhTrang());
//            hd.setGhiChu(x.getGhichu());
        }
        return list01;

    }

    @Override
    public List<HoaDonViewModel> getTimHDTen(String Ten) {
        List<HoaDonViewModel> list04 = new ArrayList<>();

        List<HoaDon> Listtm = hoaDonRepostory.getHDTen(Ten);
        for (HoaDon x : Listtm) {
            list04.add(new HoaDonViewModel(x.getKhachHang(),x.getUser(),x.getMa(),x.getNgayTao() ,x.getNgayThanhToan() ,x.getTinhTrang(),x.getGhichu(),x.getTongTien()));
//            HoaDonViewModel hd = new HoaDonViewModel();
//            hd.setKh(x.getKhachHang());
//            hd.setUs(x.getUser());
//            hd.setMa(x.getMa());
//            hd.setNgayTao(x.getNgayTao());
//            hd.setNgayThanhToan(x.getNgayThanhToan());
//            hd.setTinhTrang(x.getTinhTrang());
//            hd.setGhiChu(x.getGhichu());
        }
        return list04;
    }

    @Override
    public List<HoaDonCHiTietViewModel> getAllHDCT() {
        List<HoaDonCHiTietViewModel> list02 = new ArrayList<>();

        List<HoaDonChiTiet> List00 = hoadon2.GetAllHDCT();
        for (HoaDonChiTiet c : List00) {
            list02.add(new HoaDonCHiTietViewModel(c.getSoluong(), c.getDonGia(), c.getHaoDon(), c.getSanPham()));
        }
        return list02;
    }

    @Override
    public List<HoaDonCHiTietViewModel> gettimma(String ma) {

        List<HoaDonCHiTietViewModel> list03 = new ArrayList<>();

        List<HoaDonChiTiet> Listtm = hoadon2.gettimma(ma);
        for (HoaDonChiTiet t : Listtm) {
            list03.add(new HoaDonCHiTietViewModel(t.getSoluong(), t.getDonGia(), t.getHaoDon(), t.getSanPham()));
        }
        return list03;
    }

    @Override
    public List<HoaDonViewModel> getTimHDTrangThai(int TrangThai) {
        List<HoaDonViewModel> list05 = new ArrayList<>();

        List<HoaDon> Listtm = hoaDonRepostory.getHDTrangThai(TrangThai);
        for (HoaDon x : Listtm) {
             list05.add(new HoaDonViewModel(x.getKhachHang(),x.getUser(),x.getMa(),x.getNgayTao() ,x.getNgayThanhToan() ,x.getTinhTrang(),x.getGhichu(),x.getTongTien()));
            HoaDonViewModel hd = new HoaDonViewModel();
            hd.setKh(x.getKhachHang());
            hd.setUs(x.getUser());
            hd.setMa(x.getMa());
            hd.setNgayTao(x.getNgayTao());
            hd.setNgayThanhToan(x.getNgayThanhToan());
            hd.setTinhTrang(x.getTinhTrang());
            hd.setGhiChu(x.getGhichu());
        }
        return list05;
    }

    @Override
    public List<HoaDonViewModel> GetTimNTT(String NgayTT) {
        List<HoaDonViewModel> list07 = new ArrayList<>();

        List<HoaDon> Listtm = hoaDonRepostory.GetTimNTT(NgayTT);
        for (HoaDon x : Listtm) {
             list07.add(new HoaDonViewModel(x.getKhachHang(),x.getUser(),x.getMa(),x.getNgayTao() ,x.getNgayThanhToan() ,x.getTinhTrang(),x.getGhichu(),x.getTongTien()));
            HoaDonViewModel hd = new HoaDonViewModel();
            hd.setKh(x.getKhachHang());
            hd.setUs(x.getUser());
            hd.setMa(x.getMa());
            hd.setNgayTao(x.getNgayTao());
            hd.setNgayThanhToan(x.getNgayThanhToan());
            hd.setTinhTrang(x.getTinhTrang());
            hd.setGhiChu(x.getGhichu());
        }
        return list07;
    }
///////////////////////

    @Override
    public Integer saveHD(HoaDonViewModel hoaDon, int idNV) {
        HoaDon hd = new HoaDon();
        hd.setMa(hoaDon.getMa());
        hd.setNgayTao(hoaDon.getNgayTao());
        hd.setTinhTrang(0);

        Integer isInsert = hoaDonRepostory.insertHoaDon(hd, idNV);
        return isInsert;
    }

    @Override
    public Integer saveHDCT(HoaDonCHiTietViewModel hoaDonChiTiet, String MaSP, String MaHD) {
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        hdct.setSoluong(hoaDonChiTiet.getSoluong());
        hdct.setDonGia(hoaDonChiTiet.getDonGia());
        SanPham sp = new SanPham();
        Integer idSP = sanISamPhamServiecs.getIdSanPham(MaSP);
        sp.setId(idSP);
        hdct.setSanPham(sp);
        HoaDon hd = new HoaDon();
        Integer idHD = hoaDonRepostory.getIdHD(MaHD);
        System.out.println(idHD);
        hd.setId(idHD);
        hdct.setHaoDon(hd);
        Integer isHDCT = hoaDonRepostory.insertHoaDonChiTiet(hdct);
        return isHDCT;
    }

    @Override
    public List<HoaDonViewModel> getListHD(int TrangThai) {
        List<HoaDon> list = hoaDonRepostory.getListHD(TrangThai);
        for (HoaDon hoaDon : list) {
            HoaDonViewModel hd = new HoaDonViewModel();
            hd.setMa(hoaDon.getMa());
            hd.setNgayTao(hoaDon.getNgayTao());
            hd.setTinhTrang(hoaDon.getTinhTrang());
            hd.setUs(hoaDon.getUser());
            getListHD.add(hd);
        }
        return getListHD;
    }

    @Override
    public Integer getIdHD(String MaHD) {
        return hoaDonRepostory.getIdHD(MaHD);
    }

    @Override
    public List<HoaDonCHiTietViewModel> getListHoaDonChiTiet(String MaHD) {
        getListHDCT = new ArrayList<>();
        List<HoaDonChiTiet> list = hoaDonRepostory.getListHoaDonChiTiet(MaHD);
        for (HoaDonChiTiet hoaDonChiTiet : list) {
            HoaDonCHiTietViewModel hd = new HoaDonCHiTietViewModel();
            hd.setDonGia(hoaDonChiTiet.getDonGia());
            hd.setSoluong(hoaDonChiTiet.getSoluong());
            hd.setSanPham(hoaDonChiTiet.getSanPham());
//            HoaDon hoaDon = new HoaDon();
//            hoaDon.setKhachHang(hoaDonChiTiet.getHaoDon().getKhachHang());
//            hd.setHaDon(hoaDon);
            getListHDCT.add(hd);
        }
        return getListHDCT;
    }

    @Override
    public Integer deleteSanPham(int idHD, int idSP) {
        return hoaDonRepostory.deleteSanPham(idHD, idSP);
    }

    @Override
    public Integer updateSanPhamTrenGioHang(int idHD, int idSP, int SoLuong) {
        return hoaDonRepostory.updateSanPhamTrenGioHang(idHD, idSP, SoLuong);
    }

    @Override
    public Integer clearSanPhamTrenGioHang(int idHD) {
        return hoaDonRepostory.clearSanPhamTrenGioHang(idHD);
    }

    @Override
    public Integer getIDCTSP(int MaHD) {
        return hoaDonRepostory.getIDCTSP(MaHD);
    }

    @Override
    public Integer updateSoLuongGioHang(int SoLuong, String MaSP, String MaHD) {
        return hoaDonRepostory.updateSoLuongGioHang(SoLuong, MaSP, MaHD);

    }

    @Override
    public Integer updateTrangThaiHoaDon(HoaDonViewModel hd) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setGhichu(hd.getGhiChu());
        hoaDon.setNgayThanhToan(hd.getNgayThanhToan());
        hoaDon.setMa(hd.getMa());
        hoaDon.setTongTien(hd.getTongTien());
        hoaDon.setTinhTrang(1);
        return hoaDonRepostory.updateTrangThaiHoaDon(hoaDon);
    }

    @Override
    public Integer updateHoaDonKhachHang(int Ma, String MaHD) {
        return hoaDonRepostory.updateHoaDonKhachHang(Ma, MaHD);
    }

    @Override
    public List<HoaDon> getKhachHang(String MaHD) {
        return hoaDonRepostory.getKhachHang(MaHD);
    }

}
