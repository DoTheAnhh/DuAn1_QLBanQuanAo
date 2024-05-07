/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.util.List;
import models.KhachHang;
import viewmodels.KhachHang02ViewMD;

/**
 *
 * @author PC
 */
public interface IKhachHangReposytory {

    List<KhachHang> getall();

    List<KhachHang02ViewMD> getall02();

    boolean add(KhachHang khachhang);

    boolean update(int id, KhachHang khachhang);

    boolean delete(int id);

    public List<KhachHang> GetTK(String SDT);

    public List<KhachHang02ViewMD> GetTKTheoIDKH(int ID);

    List<KhachHang> SeachTheoSDT(String SDT);

    Integer updateDiemKhachHang(String SDT, int diem);

    String kiemtra(String mail);

    String kiemtrasdt(String sdt);

}
