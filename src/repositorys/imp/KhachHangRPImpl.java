/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.KhachHang;
import models.KhuyenMai;
import utilconnext.DBConnection;
import repositorys.IKhachHangReposytory;
import viewmodels.KhachHang02ViewMD;
import viewmodels.KhachHangViewMD;

/**
 *
 * @author PC
 */
public class KhachHangRPImpl implements IKhachHangReposytory {

    @Override
    public List<KhachHang> getall() {
        String query = "SELECT [Id]\n"
                + "      ,[Ten]\n"
                + "      ,[TenDem]\n"
                + "      ,[Ho]\n"
                + "      ,[Gioitinh]\n"
                + "      ,[NgaySinh]\n"
                + "      ,[Email]\n"
                + "      ,[Sdt]\n"
                + "      ,[Diemthuong]\n"
                + "  FROM [dbo].[KhachHang]";
        try ( Connection con = DBConnection.openDbConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            List<KhachHang> listSP = new ArrayList<>();
            while (rs.next()) {
                KhachHang khachhang = new KhachHang();
                khachhang.setId(rs.getInt(1));
                khachhang.setTen(rs.getString(2));
                khachhang.setTendem(rs.getString(3));
                khachhang.setHo(rs.getString(4));
                khachhang.setGioitinh(rs.getInt(5));
                khachhang.setNgaysinh(rs.getDate(6));
                khachhang.setEmail(rs.getString(7));
                khachhang.setSdt(rs.getString(8));
                khachhang.setDiemthuong(rs.getInt(9));

                listSP.add(khachhang);
            }
            return listSP;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public boolean add(KhachHang khachhang) {
        int check = 0;
        String sql = "INSERT INTO [dbo].[KhachHang]\n"
                + "           ([Ten]\n"
                + "           ,[TenDem]\n"
                + "           ,[Ho]\n"
                + "           ,[Gioitinh]\n"
                + "           ,[NgaySinh]\n"
                + "           ,[Email]\n"
                + "           ,[Sdt]\n)"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try ( Connection con = DBConnection.openDbConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, khachhang.getTen());
            ps.setObject(2, khachhang.getTendem());
            ps.setObject(3, khachhang.getHo());
            ps.setObject(4, khachhang.getGioitinh());
            ps.setObject(5, khachhang.getNgaysinh());
            ps.setObject(6, khachhang.getEmail());
            ps.setObject(7, khachhang.getSdt());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean update(int id, KhachHang khachhang) {
        int check = 0;
        String sql = "UPDATE [dbo].[KhachHang]\n"
                + "   SET [Ten] =?\n"
                + "      ,[TenDem] = ?\n"
                + "      ,[Ho] =?\n"
                + "      ,[Gioitinh] = ?\n"
                + "      ,[NgaySinh] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Sdt] = ?\n"
                + "      ,[Diemthuong] = ?\n"
                + " WHERE id = ?";
        try ( Connection con = DBConnection.openDbConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, khachhang.getTen());
            ps.setObject(2, khachhang.getTendem());
            ps.setObject(3, khachhang.getHo());
            ps.setObject(4, khachhang.getGioitinh());
            ps.setObject(5, khachhang.getNgaysinh());
            ps.setObject(6, khachhang.getEmail());
            ps.setObject(7, khachhang.getSdt());
            ps.setObject(8, khachhang.getDiemthuong());
            ps.setObject(9, id);

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public boolean delete(int id) {
        int check = 0;
        String sql = "DELETE FROM [dbo].[KhachHang]\n"
                + "      WHERE id = ?";
        try ( Connection con = DBConnection.openDbConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    @Override
    public List<KhachHang> GetTK(String SDT) {
        List<KhachHang> listkh = new ArrayList<>();
        try {
            listkh.removeAll(listkh);
            String sql = "SELECT [Id]\n"
                    + "      ,[Ten]\n"
                    + "      ,[TenDem]\n"
                    + "      ,[Ho]\n"
                    + "      ,[Gioitinh]\n"
                    + "      ,[NgaySinh]\n"
                    + "      ,[Email]\n"
                    + "      ,[Sdt]\n"
                    + "      ,[Diemthuong]\n"
                    + "  FROM [dbo].[KhachHang]\n"
                    + "  where sdt like ? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + SDT + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang khachhang = new KhachHang();
                khachhang.setId(rs.getInt(1));
                khachhang.setTen(rs.getString(2));
                khachhang.setTendem(rs.getString(3));
                khachhang.setHo(rs.getString(4));
                khachhang.setGioitinh(rs.getInt(5));
                khachhang.setNgaysinh(rs.getDate(6));
                khachhang.setEmail(rs.getString(7));
                khachhang.setSdt(rs.getString(8));
                khachhang.setDiemthuong(rs.getInt(9));

                listkh.add(khachhang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listkh;
    }

    @Override
    public List<KhachHang02ViewMD> getall02() {
        String query = "SELECT dbo.KhachHang.Id, dbo.KhachHang.Ten, dbo.KhachHang.TenDem, dbo.KhachHang.Ho, dbo.KhachHang.Sdt, dbo.HoaDon.Ma, dbo.HoaDon.NgayTao, dbo.HoaDon.TinhTrang, dbo.HoaDon.TongTien\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id";
        try ( Connection con = DBConnection.openDbConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            List<KhachHang02ViewMD> listSP = new ArrayList<>();
            while (rs.next()) {
                KhachHang02ViewMD khachhang = new KhachHang02ViewMD();
                khachhang.setId(rs.getInt(1));
                khachhang.setTen(rs.getString(2));
                khachhang.setTendem(rs.getString(3));
                khachhang.setHo(rs.getString(4));
                khachhang.setSDT(rs.getString(5));
                khachhang.setMaHD(rs.getString(6));
                khachhang.setNgayTao(rs.getString(7));
                khachhang.setTrangthai(rs.getInt(8));
                khachhang.setDongia(rs.getDouble(9));

                listSP.add(khachhang);
            }
            return listSP;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<KhachHang> SeachTheoSDT(String SDT) {
        List<KhachHang> getList = new ArrayList<>();
        try {
            String sql = "SELECT Ten , TenDem , Ho , Diemthuong FROM KhachHang where Sdt = ?";
            Connection con = DBConnection.openDbConnection();
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, SDT);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setTen(rs.getString(3) + " " + rs.getString(2) + " " + rs.getString(1));
                kh.setDiemthuong(rs.getInt(4));
//               kh.setId(rs.getInt(5));
                getList.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }

    @Override
    public Integer updateDiemKhachHang(String SDT, int diem) {
        int rs = 0;
        try {
            String sql = "update KhachHang set DiemThuong = ? where Sdt = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, diem);
            pr.setString(2, SDT);
            rs = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return rs;
    }

    @Override
    public List<KhachHang02ViewMD> GetTKTheoIDKH(int ID) {
        List<KhachHang02ViewMD> listkh = new ArrayList<>();
        try {
            listkh.removeAll(listkh);
            String sql = "SELECT dbo.KhachHang.Id, dbo.KhachHang.Ten, dbo.KhachHang.TenDem, dbo.KhachHang.Ho, dbo.KhachHang.Sdt, dbo.HoaDon.Ma, dbo.HoaDon.NgayTao, dbo.HoaDon.TinhTrang, dbo.HoaDon.TongTien\n"
                    + "FROM     dbo.HoaDon INNER JOIN\n"
                    + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id where dbo.KhachHang.id = ? ";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, ID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhachHang02ViewMD khachhang = new KhachHang02ViewMD();
                khachhang.setId(rs.getInt(1));
                khachhang.setTen(rs.getString(2));
                khachhang.setTendem(rs.getString(3));
                khachhang.setHo(rs.getString(4));
                khachhang.setSDT(rs.getString(5));
                khachhang.setMaHD(rs.getString(6));
                khachhang.setNgayTao(rs.getString(7));
                khachhang.setTrangthai(rs.getInt(8));
                khachhang.setDongia(rs.getDouble(9));

                listkh.add(khachhang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhuyenmaiReponsitory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listkh;
    }

    @Override
    public String kiemtra(String mail) {
        String sql = "SELECT EMAIL FROM KhachHang WHERE EMAIL = ?";
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
    public String kiemtrasdt(String sdt) {
        String sql = "SELECT SDT FROM KhachHang WHERE SDT = ? ";
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
}
