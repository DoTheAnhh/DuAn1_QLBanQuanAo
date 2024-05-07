/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys.imp;

import java.util.ArrayList;
import java.util.List;
import models.Users;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Chucvu;
import utilconnext.DBConnection;
import repositorys.IUsersReposytory;

/**
 *
 * @author hungh
 */
public class UsersReposytory implements IUsersReposytory {

    final String SQL_SELECT_BY_TK = "SELECT Users.id,Users.Ten,TenDem,Ho,NgaySinh,Gioitinh,Sdt,IdCV,TaiKhoan,MatKhau,Email,ChucVu.Ten,TrangThai FROM Users join ChucVu on ChucVu.Id = Users.IdCV where Taikhoan = ?";

    @Override
    public List<Users> getAllNhanVien() {
        List<Users> nvv = new ArrayList<>();
        String sql = "SELECT Users.id,Users.Ten,TenDem,Ho,NgaySinh,Gioitinh,Sdt,IdCV,TaiKhoan,MatKhau,Email,ChucVu.Ten,TrangThai FROM Users join ChucVu \n"
                + "on ChucVu.Id = Users.IdCV";

        ResultSet rs = null;
        try {
            rs = DBConnection.getDataFromQuery(sql);
            while (rs.next()) {
                Chucvu cvv = new Chucvu(rs.getString(8), rs.getString(12));
                String id = rs.getString(1);
                String ten = rs.getString(2);
                String tendem = rs.getString(3);
                String ho = rs.getString(4);
                String ngaysinh = rs.getString(5);
                Integer gioitinh = rs.getInt(6);
                String sdt = rs.getString(7);
                String tk = rs.getString(9);
                String mk = rs.getString(10);
                String email = rs.getString(11);
                Integer tt = rs.getInt(13);

                nvv.add(new Users(id, ten, tendem, ho, ngaysinh, gioitinh, sdt, tk, mk, email, cvv, tt));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersReposytory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nvv;
    }

    @Override
    public boolean add(Users nv) {
        String sql = "INSERT INTO Users(Ten,TenDem,Ho,NgaySinh,Gioitinh,Sdt,IdCV,TaiKhoan,MatKhau,Email,TrangThai)VALUES(?,?,?,?,?,?,?,?,?,?,? )";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nv.getTen());
            pstm.setString(2, nv.getTendem());
            pstm.setString(3, nv.getHo());
            pstm.setString(4, nv.getNgaysinh());
            pstm.setInt(5, nv.getGioitinh());
            pstm.setString(6, nv.getSdt());
            pstm.setObject(7, nv.getChucVu().getId());
            pstm.setString(8, nv.getTk());
            pstm.setString(9, nv.getMk());
            pstm.setString(10, nv.getEmail());
            pstm.setInt(11, nv.getTT());
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Users us, String id) {
        String sql = "UPDATE Users\n"
                + "set Ten = ? ,\n"
                + "TenDem = ?,\n"
                + "Ho = ?,\n"
                + "NgaySinh = ?,\n"
                + "Gioitinh = ?,\n"
                + "Sdt = ?,\n"
                + "IdCV = ?,\n"
                + "TaiKhoan = ?,\n"
                + "MatKhau = ?,\n"
                + "Email = ?,\n"
                + "TrangThai = ?\n"
                + "where Id = ?";

        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, us.getTen());
            pstm.setString(2, us.getTendem());
            pstm.setString(3, us.getHo());
            pstm.setString(4, us.getNgaysinh());
            pstm.setInt(5, us.getGioitinh());
            pstm.setString(6, us.getSdt());
            pstm.setObject(7, us.getChucVu().getId());
            pstm.setString(8, us.getTk());
            pstm.setString(9, us.getMk());
            pstm.setString(10, us.getEmail());
            pstm.setInt(11, us.getTT());
            pstm.setString(12, id);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Users> SearchNVV(String Ten) {
        List<Users> nvv = new ArrayList<>();
        String sql = " SELECT Users.Ho,Users.TenDem,Users.Ten ,NgaySinh,Gioitinh,Sdt,IdCV,TaiKhoan,MatKhau,Email,TrangThai,ChucVu.Ten FROM Users join ChucVu on ChucVu.Id = Users.IdCV Where CAST(Ho+' '+TenDem+' '+Users.Ten as nvarchar) like ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setNString(1, "%" + Ten + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                nvv.add(new Users(rs.getString(3), rs.getString(2), rs.getString(1), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(8), rs.getString(9), rs.getString(10), new Chucvu(rs.getString(7), rs.getString(12)), rs.getInt(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersReposytory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nvv;
    }

    @Override
    public boolean delete(String id) {
        String sql = "delete from Users where Id = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMK(Users us, String mail) {
        String sql = "UPDATE Users SET MatKhau = ? WHERE TaiKhoan = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, us.getMk());
            pstm.setString(2, mail);
            pstm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String kiemtra(String mail) {
        String sql = "SELECT EMAIL FROM Users WHERE EMAIL = ?";
        String box = null;
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, mail);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getString(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Users getUserbytk(String tk) {
        Users x= new Users();
        try {
            x = getdataQuery(SQL_SELECT_BY_TK, tk).get(0);
        } catch (Exception e) { 
            x = new Users();
        }
        return x;
    }

    private List<Users> getdataQuery(String SQL, Object... arvg) {
        List<Users> lst = new ArrayList<>();

        try {
            ResultSet rs = DBConnection.getDataFromQuery(SQL, arvg);
            while (rs.next()) {
                Chucvu cvv = new Chucvu(rs.getString(8), rs.getString(12));
                String id = rs.getString(1);
                String ten = rs.getString(2);
                String tendem = rs.getString(3);
                String ho = rs.getString(4);
                String ngaysinh = rs.getString(5);
                Integer gioitinh = rs.getInt(6);
                String sdt = rs.getString(7);
                String tk = rs.getString(9);
                String mk = rs.getString(10);
                String email = rs.getString(11);
                Integer tt = rs.getInt(13);

                lst.add(new Users(id, ten, tendem, ho, ngaysinh, gioitinh, sdt, tk, mk, email, cvv, tt));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersReposytory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lst;
    }

    @Override
    public String kiemtrasdt(String sdt) {
        String sql = "SELECT SDT FROM Users WHERE SDT = ?";
        String box = null;
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, sdt);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getString(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String kiemtratk(String tk) {
    String sql = "SELECT TAIKHOAN FROM Users WHERE TAIKHOAN = ?";
        String box = null;
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, tk);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box = rs.getString(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
