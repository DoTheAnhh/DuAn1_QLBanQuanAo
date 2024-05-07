/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.util.List;
import models.SanPham;

/**
 *
 * @author Admin
 */
public interface ISanPhamReposory {
     List<SanPham> getListSanPham() throws Exception;
    boolean updateSoLuongSP(String Masp , int SoLuong) ;
    Integer getIdSanPham(String MaSP);
    List<SanPham> seachSanPham(String Ten);
    List<SanPham> seachBarCode(String barcode);
  List<SanPham> locTheoDanhMucSP(String TenDanhMuc);
    
}
