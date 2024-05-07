package repositorys.imp;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.HoaDon;
import models.HoaDonChiTiet;
import models.KhachHang;
import models.KhuyenMai;
import models.SanPham;
import models.User;
import repositorys.IHoaDonRepostory;
import utilconnext.DBConnection;

/**
 *
 * @author Admin
 */
public class HoaDonRepostory implements IHoaDonRepostory {


   @Override
    public List<HoaDon> GetAllHD() {
        String query = "SELECT a.MA,b.TEN,c.TEN,NGAYTAO,NGAYTHANHTOAN,TinhTrang,GHICHU,a.tongTien FROM HOADON a JOIN USERS b ON a.IDNV = b.ID "
                + "JOIN KHACHHANG c ON a.IDKH = c.ID";
        try (Connection con = DBConnection.openDbConnection();
                PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();
            List<HoaDon> listSP = new ArrayList<>();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMa(rs.getString(1));
                hoadon.setGhichu(rs.getString(7));
                hoadon.setNgayTao(rs.getDate(4));
                hoadon.setNgayThanhToan(rs.getDate(5));
                hoadon.setTinhTrang(rs.getInt(6));
                hoadon.setTongTien(rs.getDouble(8));
                User u = new User();
                u.setTen(rs.getString(2));
                hoadon.setUser(u);

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString(3));
                hoadon.setKhachHang(khachHang);

                listSP.add(hoadon);
            }
            return listSP;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<HoaDonChiTiet> GetAllHDCT() {
        String query = "SELECT c.Ma, b.MA,b.TEN,a.soluong,DonGia FROM HOADONCHITIET a JOIN ChitietSP b ON a.IdCTSP = b.Id"
                + " JOIN HOADON c ON a.IDHD = c.ID";
        try (Connection con = DBConnection.openDbConnection();
                PreparedStatement ps = con.prepareStatement(query);) {
            ResultSet rs = ps.executeQuery();

            List<HoaDonChiTiet> listHDCT = new ArrayList<>();
            while (rs.next()) {
                HoaDonChiTiet hoadonCT = new HoaDonChiTiet();
                hoadonCT.setSoluong(rs.getInt(4));
                hoadonCT.setDonGia(rs.getDouble(5));

                SanPham SP = new SanPham();
                SP.setMa(rs.getString(2));
                SP.setTen(rs.getString(3));
                hoadonCT.setSanPham(SP);

                HoaDon hd = new HoaDon();
                hd.setMa(rs.getString(1));
                hoadonCT.setHaoDon(hd);

                listHDCT.add(hoadonCT);
            }
            return listHDCT;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public List<HoaDonChiTiet> gettimma(String ma) {

        List<HoaDonChiTiet> getList = new ArrayList<>();
        try {
            String sql = "SELECT c.Ma, b.MA,b.TEN,a.soluong,DonGia FROM HOADONCHITIET a JOIN ChitietSP b ON a.IdCTSP = b.Id"
                    + " JOIN HOADON c ON a.IDHD = c.ID "
                    + "WHERE c.Ma =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, ma);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hoadonCT = new HoaDonChiTiet();
                hoadonCT.setSoluong(rs.getInt(4));
                hoadonCT.setDonGia(rs.getDouble(5));

                SanPham SP = new SanPham();
                SP.setMa(rs.getString(2));
                SP.setTen(rs.getString(3));
                hoadonCT.setSanPham(SP);

                HoaDon hd = new HoaDon();
                hd.setMa(rs.getString(1));
                hoadonCT.setHaoDon(hd);
                getList.add(hoadonCT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }

    @Override
    public List<HoaDon> getHDTen(String Ten) {
        List<HoaDon> getList = new ArrayList<>();
        try {
            String sql = "SELECT a.MA,b.TEN,c.TEN,NGAYTAO,NGAYTHANHTOAN,TinhTrang,GHICHU FROM HOADON a JOIN USERS b ON a.IDNV = b.ID \n"
                    + "                JOIN KHACHHANG c ON a.IDKH = c.ID\n"
                    + "                WHERE c.Ten like ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, Ten);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMa(rs.getString(1));
                hoadon.setGhichu(rs.getString(7));
                hoadon.setNgayTao(rs.getDate(4));
                hoadon.setNgayThanhToan(rs.getDate(5));
                hoadon.setTinhTrang(rs.getInt(6));

                User u = new User();
                u.setTen(rs.getString(2));
                hoadon.setUser(u);

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString(3));
                hoadon.setKhachHang(khachHang);

                getList.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;

    }

    @Override
    public List<HoaDon> getHDTrangThai(int TrangThai) {
        List<HoaDon> getList = new ArrayList<>();
        try {
            String sql = "SELECT a.MA,b.TEN,c.TEN,NGAYTAO,NGAYTHANHTOAN,TinhTrang,GHICHU,a.tongTien FROM HOADON a JOIN USERS b ON a.IDNV = b.ID \n"
                    + "                             JOIN KHACHHANG c ON a.IDKH = c.ID\n"
                    + "							 where a.TinhTrang = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, TrangThai);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMa(rs.getString(1));
                hoadon.setGhichu(rs.getString(7));
                hoadon.setNgayTao(rs.getDate(4));
                hoadon.setNgayThanhToan(rs.getDate(5));
                hoadon.setTinhTrang(rs.getInt(6));
                hoadon.setTongTien(rs.getDouble(8));
                User u = new User();
                u.setTen(rs.getString(2));
                hoadon.setUser(u);

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString(3));
                hoadon.setKhachHang(khachHang);

                getList.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }
    

    @Override
    public List<HoaDon> GetTimNTT(String NgayTT) {
       List<HoaDon> getList = new ArrayList<>();
        try {
            String sql = "SELECT a.MA,b.TEN,c.TEN,NGAYTAO,NGAYTHANHTOAN,TinhTrang,GHICHU ,a.tongTien FROM HOADON a JOIN USERS b ON a.IDNV = b.ID \n"
                    + "JOIN KHACHHANG c ON a.IDKH = c.ID\n"
                    + "where a.NGAYTHANHTOAN = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, NgayTT);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMa(rs.getString(1));
                hoadon.setGhichu(rs.getString(7));
                hoadon.setNgayTao(rs.getDate(4));
                hoadon.setNgayThanhToan(rs.getDate(5));
                hoadon.setTinhTrang(rs.getInt(6));
                hoadon.setTongTien(rs.getDouble(8));
                User u = new User();
                u.setTen(rs.getString(2));
                hoadon.setUser(u);

                KhachHang khachHang = new KhachHang();
                khachHang.setTen(rs.getString(3));
                hoadon.setKhachHang(khachHang);

                getList.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }
    
/////////////////////////////////////////////////
/////////////////////////////////////////////////
     @Override
    public Integer insertHoaDon(HoaDon hd, Integer idNV) {
        int result = 0;
        try {
            String sql = "insert into HoaDon (Ma ,IdNV, NgayTao , NgayThanhToan , TinhTrang) values(? , ? ,? ,? ,?)";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, hd.getMa());
            pr.setInt(2, idNV);
            pr.setDate(3, hd.getNgayTao());
            pr.setDate(4, hd.getNgayThanhToan());
            pr.setInt(5, hd.getTinhTrang());
            result = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    @Override
    public Integer insertHoaDonChiTiet(HoaDonChiTiet hdct) {

        int result = 0;
        try {
            String sql = "insert into HoaDonChiTiet (IdHD ,idCTSP, Soluong , Dongia ) values(? , ? ,? ,?)";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(2, hdct.getSanPham().getId());
            pr.setInt(1, hdct.getHaoDon().getId());
            pr.setInt(3, hdct.getSoluong());
            pr.setDouble(4, hdct.getDonGia());

            result = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    @Override
    public List<HoaDon> getListHD(int TrangThai) {
        List<HoaDon> getListGD = new ArrayList<>();
        try {
            String sql = "SELECT HD.Ma , HD.NgayTao , NV.Ten , HD.TinhTrang , NV.TenDem , NV.Ho FROM HoaDon HD JOIN Users NV ON HD.IdNV = NV.Id WHERE HD.TinhTrang = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, TrangThai);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();

                hd.setMa(rs.getString(1));
                hd.setNgayTao(rs.getDate(2));
                User uesr = new User();
                uesr.setTen(rs.getString(6)+" "+rs.getString(5)+" " + rs.getString(3));
                hd.setUser(uesr);
                hd.setTinhTrang(rs.getInt(4));
                getListGD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getListGD;
    }

    @Override
    public Integer getIdHD(String MaHD) {
        Integer idHD = 0;
        try {
            String sql = "select id from HoaDon where Ma = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, MaHD);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                idHD = rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return idHD;
    }

    @Override
    public List<HoaDonChiTiet> getListHoaDonChiTiet(String MaHD) {
        List<HoaDonChiTiet> getList = new ArrayList<>();
        try {
            String sql = "SELECT SP.Ma , SP.Ten , HDCT.Dongia , HDCT.Soluong , KM.Giatrigiam , KM.HinhthucKM ,HDCT.IdCTSP  FROM HoaDon HD JOIN HoaDonChiTiet HDCT ON HD.Id = HDCT.IdHD\n"
                    + "                    JOIN ChitietSP SP ON SP.Id = HDCT.IdCTSP join KhuyenMai km ON SP.IdKM = KM.Id "
                    + "  WHERE HD.Ma =?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, MaHD);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                     HoaDonChiTiet hdct = new HoaDonChiTiet();
                KhuyenMai km = new KhuyenMai();
                km.setGiaTriGiam(rs.getDouble(5));
                km.setHinhThucKM(rs.getString(6));

                SanPham sp = new SanPham();
                sp.setTen(rs.getString(2));
                sp.setMa(rs.getString(1));
                sp.setId(rs.getInt(7));
              
                sp.setKhuenMai(km);
                hdct.setSanPham(sp);
                hdct.setSoluong(rs.getInt(4));
                hdct.setDonGia(rs.getDouble(3));
                getList.add(hdct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }

    @Override
    public Integer deleteSanPham(int idHD, int idSP) {
        int rs = 0;
        try {
            String sql = "DELETE FROM HoaDonChiTiet WHERE IdHD = ? AND IdCTSP = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, idHD);
            pr.setInt(2, idSP);
            rs = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return rs;
    }

    @Override
    public Integer updateSanPhamTrenGioHang(int idHD, int idSP, int SoLuong) {
        int rs = 0;
        try {
            String sql = "UPDATE HoaDonChiTiet SET Soluong = ? WHERE IdHD = ? AND IdCTSP = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(2, idHD);
            pr.setInt(3, idSP);
            pr.setInt(1, SoLuong);
            rs = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return rs;
    }

    @Override
    public Integer clearSanPhamTrenGioHang(int idHD) {
        int rs = 0;
        try {
            String sql = "DELETE FROM HoaDonChiTiet WHERE IdHD = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, idHD);

            rs = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return rs;
    }

    @Override
    public Integer getIDCTSP(int MaHD) {
        int idCTSP = 0;
        try {
            String sql = "select idCTSP from HoaDonChiTiet where idHD = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, MaHD);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                idCTSP = rs.getInt(1);
                System.out.println(idCTSP);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return idCTSP;
    }

    @Override
    public Integer updateSoLuongGioHang(int SoLuong, String MaSP, String MaHD) {
        int rs = 0;
        try {
            String sql = "UPDATE HoaDonChiTiet SET Soluong = ? WHERE IdHD IN (SELECT ID FROM HoaDon WHERE MA = ?) AND IdCTSP IN (SELECT ID FROM ChitietSP WHERE MA = ?)";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, SoLuong);
            pr.setString(2, MaHD);
            pr.setString(3, MaSP);
            rs = pr.executeUpdate();
            if (rs > 0) {
                System.out.println("thành công");
            } else {
                System.out.println("thất bại");
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return rs;
    }

    @Override
    public Integer updateTrangThaiHoaDon(HoaDon hd) {
   int rs = 0;
        try {
            String sql = "update HoaDon set TinhTrang = ? , Ghichu = ? ,NgayThanhToan = ? ,tongTien = ?  where Ma = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, hd.getTinhTrang());
            pr.setString(2, hd.getGhichu());
            pr.setDate(3, hd.getNgayThanhToan());
            pr.setString(5, hd.getMa());
            pr.setDouble(4, hd.getTongTien());

            rs = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return rs;
    }

    @Override
    public Integer updateHoaDonKhachHang(int Ma  ,String MaHD) {
          int rs = 0;
        try {
            String sql = "UPDATE HoaDon SET idKH = ? WHERE Ma = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, Ma);
            pr.setString(2, MaHD);
          
            rs = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return rs;
    }

    @Override
    public List<HoaDon> getKhachHang(String MaHD) {
         List<HoaDon> getList = new ArrayList<>();
        try {
            String sql = "SELECT kh.Ho , kh.TenDem , kh.Ten , kh.sdt , kh.DiemThuong FROM HoaDon hd join KhachHang kh on hd.idKH = kh.id where hd.Ma =?";

            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, MaHD);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {

                KhachHang kh = new KhachHang();
                kh.setTen(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                kh.setSdt(rs.getString(4));
                kh.setDiemthuong(rs.getInt(5));
                HoaDon hd = new HoaDon();
                hd.setKhachHang(kh);

                getList.add(hd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }
    
    public List<HoaDon> getKhachHangg(String MaHD) {
         List<HoaDon> getList = new ArrayList<>();
        try {
            String sql = "SELECT kh.Ho , kh.TenDem , kh.Ten , kh.sdt , kh.DiemThuong FROM HoaDon hd join KhachHang kh on hd.idKH = kh.id where hd.Ma =?";

            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setString(1, MaHD);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setTen(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                kh.setSdt(rs.getString(4));
                kh.setDiemthuong(rs.getInt(5));
                HoaDon hd = new HoaDon();
                hd.setKhachHang(kh);

                getList.add(hd);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList;
    }

    
    public Integer updateHoaDonKhacchHang(int Ma  ,String MaHD) {
          int rs = 0;
        try {
            String sql = "UPDATE HoaDon SET idKH = ? WHERE Ma = ?";
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pr = conn.prepareStatement(sql);
            pr.setInt(1, Ma);
            pr.setString(2, MaHD);
            rs = pr.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return rs;
    }
    public static void main(String[] args) {
        System.out.println(new HoaDonRepostory().getKhachHang("HD631353181").toString());
//         System.out.println(new HoaDonRepostory().getListHoaDonChiTiet("HD-2055145").toString());


    }

   
  

    

}