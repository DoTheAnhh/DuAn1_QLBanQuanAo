/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys.imp;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ChatLieu;
import models.KhuyenMai;
import models.KichCo;
import models.MauSac;
import models.SanPham;
import repositorys.ISanPhamReposory;
import utilconnext.DBConnection;

/**
 *
 * @author hungh
 */
public class SanPhamRepostory implements ISanPhamReposory {

    @Override
    public List<SanPham> getListSanPham() throws SQLException {
        List<SanPham> getListSP = new ArrayList<>();

        String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , CL.Ten AS N'CHẤT LIỆU', SP.SoLuongTon ,SP.GiaBan FROM ChitietSP SP \n"
                + "JOIN MauSac MS ON SP.IdMauSac = MS.Id\n"
                + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN ChatLieu CL ON SP.IdCL = CL.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id";
        Connection conn = DBConnection.openDbConnection();
        Statement stt = conn.createStatement();

        ResultSet rs = stt.executeQuery(sql);
        while (rs.next()) {
            SanPham sp = new SanPham();
            sp.setMa(rs.getString(1));
            sp.setTen(rs.getString(2));

            MauSac ms = new MauSac();
            ms.setTen(rs.getString(3));
            sp.setMauSac(ms);
            KhuyenMai km = new KhuyenMai();
            km.setGiaTriGiam(rs.getDouble(4));
            km.setHinhThucKM(rs.getString(5));
            sp.setKhuenMai(km);
            KichCo ks = new KichCo();
            ks.setTen(rs.getString(6));
            sp.setKichCo(ks);
            ChatLieu cl = new ChatLieu();
            cl.setTen(rs.getString(7));
            sp.setChatLieu(cl);
            sp.setSoLuongTon(rs.getInt(8));
            sp.setGiaBan(rs.getDouble(9));
            getListSP.add(sp);
        }
        rs.close();
        stt.close();
        conn.close();
        return getListSP;
    }

    @Override
    public boolean updateSoLuongSP(String Masp, int SoLuong) {
        try {
            String sql = "UPDATE ChitietSP SET SoLuongTon = ? WHERE MA = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, SoLuong);
            pr.setString(2, Masp);
            pr.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer getIdSanPham(String MaSP) {
        Integer idSP = 0;
        try {
            String sql = "select id from ChitietSP where Ma = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, MaSP);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                idSP = rs.getInt(1);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return idSP;
    }

    @Override
    public List<SanPham> seachSanPham(String Ten) {
        List<SanPham> getListSP = new ArrayList<>();
        try {

            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , CL.Ten AS N'CHẤT LIỆU', SP.SoLuongTon ,SP.GiaBan FROM ChitietSP SP \n"
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id\n"
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN ChatLieu CL ON SP.IdCL = CL.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id WHERE SP.Ten LIKE ? or SP.QrCode =? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%" + Ten + "%");
            pr.setString(2, Ten);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));
                MauSac ms = new MauSac();
                ms.setTen(rs.getString(3));
                sp.setMauSac(ms);
                KhuyenMai km = new KhuyenMai();
                km.setGiaTriGiam(rs.getDouble(4));
                km.setHinhThucKM(rs.getString(5));
                sp.setKhuenMai(km);
                KichCo ks = new KichCo();
                ks.setTen(rs.getString(6));
                sp.setKichCo(ks);
                ChatLieu cl = new ChatLieu();
                cl.setTen(rs.getString(7));
                sp.setChatLieu(cl);
                sp.setSoLuongTon(rs.getInt(8));
                sp.setGiaBan(rs.getDouble(9));
                getListSP.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getListSP;
    }

    @Override
    public List<SanPham> seachBarCode(String barcode) {
        List<SanPham> getListSP = new ArrayList<>();
        try {

            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , CL.Ten AS N'CHẤT LIỆU', SP.SoLuongTon ,SP.GiaBan ,SP.id FROM ChitietSP SP \n"
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id\n"
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN ChatLieu CL ON SP.IdCL = CL.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id WHERE SP.QrCode like ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%" + barcode + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));
                sp.setId(rs.getInt(10));

                MauSac ms = new MauSac();
                ms.setTen(rs.getString(3));
                sp.setMauSac(ms);
                KhuyenMai km = new KhuyenMai();
                km.setGiaTriGiam(rs.getDouble(4));
                km.setHinhThucKM(rs.getString(5));
                sp.setKhuenMai(km);
                KichCo ks = new KichCo();
                ks.setTen(rs.getString(6));
                sp.setKichCo(ks);
                ChatLieu cl = new ChatLieu();
                cl.setTen(rs.getString(7));
                sp.setChatLieu(cl);
                sp.setSoLuongTon(rs.getInt(8));
                sp.setGiaBan(rs.getDouble(9));
                getListSP.add(sp);
            }
            return getListSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SanPham> locTheoDanhMucSP(String TenDanhMuc) {
        List<SanPham> getListSP = new ArrayList<>();
        try {

            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , CL.Ten AS N'CHẤT LIỆU', SP.SoLuongTon ,SP.GiaBan   FROM ChitietSP SP \n"
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id\n"
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN ChatLieu CL ON SP.IdCL = CL.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id JOIN DanhMucSP DM ON SP.idDMuc = DM.ID WHERE DM.Ten =  ? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, TenDanhMuc);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));

                MauSac ms = new MauSac();
                ms.setTen(rs.getString(3));
                sp.setMauSac(ms);
                KhuyenMai km = new KhuyenMai();
                km.setGiaTriGiam(rs.getDouble(4));
                km.setHinhThucKM(rs.getString(5));
                sp.setKhuenMai(km);
                KichCo ks = new KichCo();
                ks.setTen(rs.getString(6));
                ChatLieu cl = new ChatLieu();
                cl.setTen(rs.getString(7));
                sp.setChatLieu(cl);
                sp.setSoLuongTon(rs.getInt(8));
                sp.setGiaBan(rs.getDouble(9));
                getListSP.add(sp);
            }
            return getListSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public List<SanPham> seachBarCodee(String barcode) {
        List<SanPham> getListSP = new ArrayList<>();
        try {

            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , CL.Ten AS N'CHẤT LIỆU', SP.SoLuongTon ,SP.GiaBan ,SP.id FROM ChitietSP SP \n"
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id\n"
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN ChatLieu CL ON SP.IdCL = CL.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id WHERE SP.QrCode like ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, "%" + barcode + "%");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));
                sp.setId(rs.getInt(3));
                ChatLieu cl = new ChatLieu();
                cl.setTen(rs.getString(4));
                sp.setChatLieu(cl);
                sp.setSoLuongTon(rs.getInt(5));
                sp.setGiaBan(rs.getDouble(6));
                getListSP.add(sp);
            }
            return getListSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPham> locTheoDanhMucSPham(String TenDanhMuc) {
        List<SanPham> getListSP = new ArrayList<>();
        try {

            String sql = "SELECT SP.MA , SP.TEN , MS.Ten AS N'Màu Sắc' ,KM.Giatrigiam  , KM.HinhthucKM , KC.Ten AS N'Kích Cỡ' , CL.Ten AS N'CHẤT LIỆU', SP.SoLuongTon ,SP.GiaBan   FROM ChitietSP SP \n"
                    + "JOIN MauSac MS ON SP.IdMauSac = MS.Id\n"
                    + "JOIN KichCo KC ON SP.IdKC = KC.Id JOIN ChatLieu CL ON SP.IdCL = CL.Id JOIN KhuyenMai KM ON SP.IdKM = KM.Id JOIN DanhMucSP DM ON SP.idDMuc = DM.ID WHERE DM.Ten =  ? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, TenDanhMuc);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMa(rs.getString(1));
                sp.setTen(rs.getString(2));
                ChatLieu cl = new ChatLieu();
                cl.setTen(rs.getString(7));
                sp.setChatLieu(cl);
                sp.setSoLuongTon(rs.getInt(8));
                sp.setGiaBan(rs.getDouble(9));
                getListSP.add(sp);
            }
            return getListSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
