/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorys.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.KhuyenMai;
import repositorys.IKhuyenmaiRepository;
import utilconnext.DBConnection;

/**
 *
 * @author ADMIN
 */
public class KhuyenmaiReponsitory implements IKhuyenmaiRepository {

    List<KhuyenMai> lstKm;

    public KhuyenmaiReponsitory() {
        lstKm = new ArrayList<>();
    }

    @Override
    public List<KhuyenMai> GetAll() {
        try {
            lstKm.removeAll(lstKm);
            String sql = "Select * from khuyenmai";
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lstKm.add(new KhuyenMai(rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(3), rs.getString(4), rs.getDouble(6), rs.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstKm;
    }

    @Override
    public boolean Add(KhuyenMai km) {
        String sql = "INSERT INTO KHUYENMAI(TEN,HINHTHUCKM,NGAYBATDAU,NGAYKETTHUC,GIATRIGIAM,TRANGTHAI) VALUES(?,?,?,?,?,?)";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, km.getTenKM());
            pstm.setString(2, km.getHinhThucKM());
            pstm.setString(3, km.getNgayBatDau());
            pstm.setString(4, km.getNgayKetThuc());
            pstm.setDouble(5, km.getGiaTriGiam());
            pstm.setDouble(6, 0);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean Update(KhuyenMai km, String id) {
        String sql = "UPDATE KHUYENMAI SET TEN=?,NGAYBATDAU=?,NGAYKETTHUC = ?,HINHTHUCKM=?,GIATRIGIAM =? WHERE ID = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, km.getTenKM());
            pstm.setString(2, km.getNgayBatDau());
            pstm.setString(3, km.getNgayKetThuc());
            pstm.setString(4, km.getHinhThucKM());
            pstm.setDouble(5, km.getGiaTriGiam());
            pstm.setString(6, id);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean Delete(String id) {
        String sql = "DELETE FROM KHUYENMAI WHERE ID = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String checktrung(String ten) {
        String sql = " SELECT TEN FROM KHUYENMAI WHERE TEN = ?";
        String box = null;
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, ten);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return box;
    }

    @Override
    public List<KhuyenMai> GetOnebyBD(String date) {
        try {
            lstKm.removeAll(lstKm);
            String sql = "Select Ten, Ngaybatdau, Ngayketthuc, HinhthucKM, Giatrigiam,Trangthai from KhuyenMai \n"
                    + "Where Ngaybatdau =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                lstKm.add(new KhuyenMai(rs.getString(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstKm;
    }

    @Override
    public List<KhuyenMai> GetOnebyKT(String date) {
        try {
            lstKm.removeAll(lstKm);
            String sql = "Select Ten, Ngaybatdau, Ngayketthuc, HinhthucKM, Giatrigiam,Trangthai from KhuyenMai \n"
                    + "Where NGAYKETTHUC =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                lstKm.add(new KhuyenMai(rs.getString(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstKm;
    }

    @Override
    public List<KhuyenMai> GetOnebyALL(String datedb, String datekt) {
        try {
            lstKm.removeAll(lstKm);
            String sql = "Select Ten, Ngaybatdau, Ngayketthuc, HinhthucKM, Giatrigiam,Trangthai from KhuyenMai \n"
                    + "Where Ngaybatdau =? and NGAYKETTHUC =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, datedb);
            pstm.setString(2, datekt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                lstKm.add(new KhuyenMai(rs.getString(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstKm;
    }

    @Override
    public List<KhuyenMai> GetOnebyten(String ten) {
        try {
            lstKm.removeAll(lstKm);
            String sql = "SELECT Ten,Ngaybatdau,Ngayketthuc,HinhthucKM,Giatrigiam,Trangthai from KhuyenMai\n"
                    + "where  Ten like ? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, ten);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                lstKm.add(new KhuyenMai(rs.getString(1), rs.getString(4), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstKm;
    }

    @Override
    public boolean UpdateTT() {
        String sql = "UPDATE KHUYENMAI SET TrangThai = 1,GIATRIGIAM =0 WHERE NGAYKETTHUC < GETDATE()";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean UpdateTT2() {
        String sql = "UPDATE KHUYENMAI SET TrangThai = 0 WHERE NGAYKETTHUC > GETDATE()";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<KhuyenMai> Getbyid(int id) {
        List<KhuyenMai> lst = new ArrayList<>();;
        try {

            String sql = "SELECT * FROM dbo.KhuyenMai WHERE Id = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lst.add(new KhuyenMai(rs.getString(1), rs.getString(2), rs.getString(5), rs.getString(3), rs.getString(4), rs.getDouble(6), rs.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public KhuyenMai getbyid(int id) {
        KhuyenMai km = new KhuyenMai();
        if (id == 0) {
            return km;
        }
        km = Getbyid(id).get(0);
        return km;
    }
}
