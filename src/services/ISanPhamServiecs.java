/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.SanPham;
import viewmodels.SanPhamViewModel;

/**
 *
 * @author Admin
 */
public interface ISanPhamServiecs {
      List<SanPhamViewModel> getListSanPham();
      boolean updateSoLuongSP(String Masp, int SoLuong ) ;
      Integer getIdSanPham(String MaSP);
      List<SanPham> seachSanPham(String Ten);
      List<SanPham> seachBarCodeS(String barcode);
       List<SanPhamViewModel> locTheoDanhMucSP(String TenDanhMuc);
}
