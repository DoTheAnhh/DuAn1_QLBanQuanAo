/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.imp;

import java.util.ArrayList;
import java.util.List;
import models.KhachHang;
import repositorys.imp.KhachHangRPImpl;
import viewmodels.KhachHangViewMD;
import repositorys.IKhachHangReposytory;
import services.IKhachHangService;
import viewmodels.KhachHang02ViewMD;

/**
 *
 * @author PC
 */
public class khahangsvImpl implements IKhachHangService {

    private IKhachHangReposytory khachHang01 = new KhachHangRPImpl();

    @Override
    public List<KhachHangViewMD> getall() {
        List<KhachHangViewMD> list01 = new ArrayList<>();

        List<KhachHang> list = khachHang01.getall();
        for (KhachHang x : list) {
            list01.add(new KhachHangViewMD(x.getId(), x.getTen(), x.getTendem(), x.getHo(), x.getGioitinh(), x.getNgaysinh(), x.getEmail(), x.getSdt(), x.getDiemthuong()));
        }
        return list01;

    }

    @Override
    public String add(KhachHang khachHang) {
        boolean addkhachhang = khachHang01.add(khachHang);
        if (addkhachhang) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(int id, KhachHang khachHang) {
        boolean updatehoadon = khachHang01.update(id, khachHang);
        if (updatehoadon) {
            return "Sửa thành công id = " + id;
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(int id) {
        boolean deleteCuaHang = khachHang01.delete(id);
        if (deleteCuaHang) {
            return "Xóa thành công id = " + id;
        }
        return "Xóa thất bại";
    }

    @Override
    public List<KhachHangViewMD> GetTK(String SDT) {
        List<KhachHang> kh = khachHang01.GetTK(SDT);
        List<KhachHangViewMD> list01 = new ArrayList<>();
        for (KhachHang x : kh) {
            list01.add(new KhachHangViewMD(x.getId(), x.getTen(), x.getTendem(), x.getHo(), x.getGioitinh(), x.getNgaysinh(), x.getEmail(), x.getSdt(), x.getDiemthuong()));
        }
        return list01;

    }

    @Override
    public List<KhachHang02ViewMD> getall01() {
        return khachHang01.getall02();

    }

    @Override
    public List<KhachHang> TenDiemKhachHang(String SDT) {
        return khachHang01.SeachTheoSDT(SDT);
    }

    @Override
    public Integer updateDiemKhachHang(String SDT, int diem) {
        return khachHang01.updateDiemKhachHang(SDT, diem);
    }

    @Override
    public List<KhachHang02ViewMD> GetTKTheoIDKH(int ID) {
        return khachHang01.GetTKTheoIDKH(ID);

    }

    @Override
    public String kiemtra(String mail) {
        return khachHang01.kiemtra(mail);

    }

    @Override
    public String kiemtrasdt(String sdt) {
        return khachHang01.kiemtrasdt(sdt);

    }

}
