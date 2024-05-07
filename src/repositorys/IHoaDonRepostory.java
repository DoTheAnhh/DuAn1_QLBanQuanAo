/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.util.List;
import models.HoaDon;
import models.HoaDonChiTiet;
    
/**
 *
 * @author Admin
 */
public interface IHoaDonRepostory {

    public List<HoaDon> GetAllHD();    
    
    public List<HoaDon> getHDTen(String Ten);
    
    public List<HoaDon> getHDTrangThai(int TrangThai);
    
    public List<HoaDonChiTiet> GetAllHDCT();
    
    public List<HoaDonChiTiet> gettimma(String ma);
     
    public List<HoaDon> GetTimNTT(String NgayTT);
///
    Integer insertHoaDon(HoaDon hd, Integer idNV);

    Integer insertHoaDonChiTiet(HoaDonChiTiet hdct);

    List<HoaDon> getListHD(int TrangThai);

    Integer getIdHD(String MaHD);

    List<HoaDonChiTiet> getListHoaDonChiTiet(String MaHD);

    Integer deleteSanPham(int idHD, int idSP);

    Integer updateSanPhamTrenGioHang(int idHD, int idSP, int SoLuong);

    Integer clearSanPhamTrenGioHang(int idHD);

    Integer getIDCTSP(int MaHD);

    Integer updateSoLuongGioHang(int SoLuong,String MaSP , String MaHD);
    
    Integer updateTrangThaiHoaDon(HoaDon hd);
    
    Integer updateHoaDonKhachHang(int Ma, String MaHD);
    
        List<HoaDon> getKhachHang(String MaHD);

}
