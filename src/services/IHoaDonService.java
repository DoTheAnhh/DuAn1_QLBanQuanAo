/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.HoaDon;
import viewmodels.HoaDonCHiTietViewModel;


import viewmodels.HoaDonViewModel;

/**
 *
 * @author Win10
 */
public interface IHoaDonService {
public List<HoaDonViewModel> getAllHD();
public List<HoaDonCHiTietViewModel> getAllHDCT();
 List<HoaDonViewModel> TimID(String ID);
  Integer saveHD(HoaDonViewModel hoaDon, int idNV);

    Integer saveHDCT(HoaDonCHiTietViewModel hoaDonChiTiet, String MaSP, String MaHD);

    List<HoaDonViewModel> getListHD(int TrangThai);

    Integer getIdHD(String MaHD);

    List<HoaDonCHiTietViewModel> getListHoaDonChiTiet(String MaHD);

    Integer deleteSanPham(int idHD, int idSP);

    Integer updateSanPhamTrenGioHang(int idHD, int idSP, int SoLuong);

    Integer clearSanPhamTrenGioHang(int idHD);
    
    Integer getIDCTSP(int MaHD);
    
     Integer updateSoLuongGioHang(int SoLuong,String MaSP , String MaHD);
     
   
}

