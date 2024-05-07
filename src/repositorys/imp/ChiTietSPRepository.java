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
import models.ChiTietSP;
import repositorys.IChiTietSPRepository;
import utilconnext.DBConnection;

/**
 *
 * @author vieta
 */
public class ChiTietSPRepository implements IChiTietSPRepository {

    final String SQL_SELECT_ALL = "SELECT Ma,Ten,IdNsx,IdMauSac,IdDMuc,IdKC,IdCL,IdTH,IdKM,SoLuongTon,GiaNhap,GiaBan,MoTa,QrCode FROM dbo.ChitietSP WHERE SoLuongTon > 0";
    final String SQL_SELECT_BY_MA = "SELECT Ma,Ten,IdNsx,IdMauSac,IdDMuc,IdKC,IdCL,IdTH,IdKM,SoLuongTon,GiaNhap,GiaBan,MoTa,QrCode FROM dbo.ChitietSP WHERE Ma = ?";
    final String SQL_SELECT_BY_SL = "SELECT Ma,Ten,IdNsx,IdMauSac,IdDMuc,IdKC,IdCL,IdTH,IdKM,SoLuongTon,GiaNhap,GiaBan,MoTa,QrCode FROM dbo.ChitietSP WHERE SoLuongTon = 0";
    final String SQL_SELECT_BY_TEN = "SELECT Ma,Ten,IdNsx,IdMauSac,IdDMuc,IdKC,IdCL,IdTH,IdKM,SoLuongTon,GiaNhap,GiaBan,MoTa,QrCode FROM dbo.ChitietSP WHERE SoLuongTon > 0 AND ten LIKE ?";
    final String SQL_INSERT = "INSERT INTO dbo.ChitietSP\n"
            + "( Ma, Ten, IdNsx, IdMauSac, IdDMuc, IdKC, IdCL, IdTH, MoTa, SoLuongTon, GiaNhap, GiaBan, QrCode)\n"
            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    final String SQL_UPDATE = "UPDATE dbo.ChitietSP SET Ma = ?, Ten = ?, IdNsx = ?, IdMauSac = ?, IdDMuc = ?, IdKC = ?, IdCL = ?, IdTH = ?, MoTa = ?, SoLuongTon = ?, GiaNhap = ?, GiaBan = ? WHERE Ma = ?";
    final String SQL_DELETE = "DELETE dbo.ChitietSP WHERE Ma = ?";

    @Override
    public List<ChiTietSP> getAll() {
        return getdataquery(SQL_SELECT_ALL);
    }

    @Override
    public List<ChiTietSP> getlistbyTen(String ten) {
        return getdataquery(SQL_SELECT_BY_TEN, ten);
    }

    private List<ChiTietSP> getdataquery(String SQL, Object... arvg) {
        List<ChiTietSP> lst = new ArrayList<>();
        try {
            ResultSet rl = DBConnection.getDataFromQuery(SQL, arvg);
//            Ma,Ten,IdNsx,IdMauSac,IdDMuc,IdKC,IdCL,IdTH,IdKM,SoLuongTon,GiaNhap,GiaBan,MoTa,QrCode
            while (rl.next()) {
                lst.add(new ChiTietSP(
                        rl.getNString(1),
                        rl.getNString(2),
                        rl.getInt(3),
                        rl.getInt(4),
                        rl.getInt(5),
                        rl.getInt(6),
                        rl.getInt(7),
                        rl.getInt(8),
                        rl.getInt(9),
                        rl.getInt(10),
                        rl.getDouble(11),
                        rl.getDouble(12),
                        rl.getString(13),
                        rl.getString(14)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSPRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;

    }

    @Override
    public int insert(ChiTietSP x) {
        return DBConnection.ExcuteQuery(SQL_INSERT, x.getMa(), x.getTen(), x.getIdnsx(), x.getIdmausac(), x.getIddanhmuc(), x.getIdkc(), x.getIdcl(), x.getIdth(), x.getMota(), x.getSoluongton(), x.getGianhap(), x.getGiaban(), x.getQrcode());
    }

    @Override
    public int update(ChiTietSP x, String Ma) {
        return DBConnection.ExcuteQuery(SQL_UPDATE, x.getMa(), x.getTen(), x.getIdnsx(), x.getIdmausac(), x.getIddanhmuc(), x.getIdkc(), x.getIdcl(), x.getIdth(), x.getMota(), x.getSoluongton(), x.getGianhap(), x.getGiaban(), Ma);
    }

    @Override
    public int delete(String ma) {
        return DBConnection.ExcuteQuery(SQL_DELETE, ma);
    }

    @Override
    public List<ChiTietSP> getSPhet() {
        return getdataquery(SQL_SELECT_BY_SL);
    }

    @Override
    public List<ChiTietSP> check(String maSP) {
        return getdataquery(SQL_SELECT_BY_MA, maSP);
    }
    @Override
    public Date checkngay(String id) {
        Date time = null;
        try {
            String sql = "Select Ngaybatdau from khuyenmai where id =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                time = rs.getDate(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return time;
    }
    @Override
    public Date checkngay2(String id) {
        Date time = null;
        try {
            String sql = "Select ngayketthuc from khuyenmai where id =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                time = rs.getDate(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return time;
    }
    ////
       @Override
    public List<ChiTietSP> GetAll() {
        try {
            List<ChiTietSP> lst = new ArrayList<>();
            String sql = "Select Ma,Ten from ChitietSP";
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ChiTietSP sP = new ChiTietSP();
                sP.setMa(rs.getString(1));
                sP.setTen(rs.getString(2));
                lst.add(sP);
            }
                    return lst;
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
       @Override
    public boolean Update(String ma,String id) {
        try {
            String sql = "Update Chitietsp set idkm = ? Where Ma = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, ma);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
