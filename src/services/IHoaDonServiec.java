/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.HoaDon;
import models.HoaDonChiTiet;
import viewmodels.HoaDonCHiTietViewModel;
import viewmodels.HoaDonViewModel;

/**
 *
 * @author Admin
 */
public interface IHoaDonServiec {

    public List<HoaDonViewModel> getAllHD();

    public List<HoaDonViewModel> getTimHDTen(String Ten);

    public List<HoaDonViewModel> getTimHDTrangThai(int TrangThai);
    
    public List<HoaDonViewModel> GetTimNTT(String NgayTT);

    public List<HoaDonCHiTietViewModel> getAllHDCT();

    public List<HoaDonCHiTietViewModel> gettimma(String ma);
    
///////
    
    Integer saveHD(HoaDonViewModel hoaDon, int idNV);

    Integer saveHDCT(HoaDonCHiTietViewModel hoaDonChiTiet, String MaSP, String MaHD);

    List<HoaDonViewModel> getListHD(int TrangThai);

    Integer getIdHD(String MaHD);

    List<HoaDonCHiTietViewModel> getListHoaDonChiTiet(String MaHD);

    Integer deleteSanPham(int idHD, int idSP);

    Integer updateSanPhamTrenGioHang(int idHD, int idSP, int SoLuong);

    Integer clearSanPhamTrenGioHang(int idHD);

    Integer getIDCTSP(int MaHD);

    Integer updateSoLuongGioHang(int SoLuong, String MaSP, String MaHD);

    Integer updateTrangThaiHoaDon(HoaDonViewModel hd );
    Integer updateHoaDonKhachHang(int Ma  ,String MaHD);
       List<HoaDon> getKhachHang(String MaHD);
}
