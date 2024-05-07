/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorys.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.ChiTietSP;
import models.Thongke;
import models.Users;
import repositorys.IThongkeRepository;
import utilconnext.DBConnection;

/**
 *
 * @author ADMIN
 */
public class ThongkeRepository implements IThongkeRepository{
private List<Thongke> lst ;
    public ThongkeRepository() {
        lst = new ArrayList<>();
    }

    @Override
    public int getbyday() {
        int box=0;
        String sql ="select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id\n" +
            "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and Day(c.NgayThanhToan) = Day(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                box=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    return box;
    }

    @Override
    public int getbyday(String date) {
        int box=0;
        String sql ="select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id\n" +
            "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and Day(c.NgayThanhToan) = ? and MONTH(c.NgayThanhToan) = MONTH(GETDATE()) and YEAR(c.NgayThanhToan) = YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    @Override
    public int getbymonth(String date) {
        int box =0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id\n"
                + "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and MONTH(c.NgayThanhToan) = ? AND YEAR(NGAYTHANHTOAN) =YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
               box=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    @Override
    public int getbyyear(String date) {
        int box = 0;
        String sql = "select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id\n"
                + "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and Year(c.NgayThanhToan) = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, date);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                box=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return box;
    }

    @Override
    public List<Thongke> getsp() {
        lst.removeAll(lst);
        String sql = "select a.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND DAY(NGAYTHANHTOAN) = DAY(GETDATE()) group by a.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ChiTietSP chiTietSP = new ChiTietSP();
                chiTietSP.setTen(rs.getString(1));
                lst.add(new Thongke(rs.getInt(2), chiTietSP));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    @Override
    public List<Thongke> getnv() {
        lst.removeAll(lst);
        String sql = "SELECT c.Ten,Sum(b.Soluong) as 'Số sản phẩm bán được' from HoaDon a join Users c on a.IdNV = c.Id\n"
                + "join HoaDonChiTiet b on a.Id = b.IdHD\n"
                + "where a.TinhTrang =1"
                + "group by c.Ten\n"
                + "Order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Users u = new Users();
                u.setTen(rs.getString(1));
                lst.add(new Thongke(u, rs.getInt(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    @Override
    public int getmonth1() {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon \n" +
"			where TinhTrang = 1 and MONTH(NgayThanhToan) = 1";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box= rs.getInt(1);
            }
                return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth2() {
        int box = 0;
                String sql = "select Sum(TongTien) from HoaDon \n" +
"			where TinhTrang = 1 and MONTH(NgayThanhToan) = 2";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth3() {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 3";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth4() {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 4";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth5() {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 5";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth6() {
        int box = 0;
       String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 6";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth7() {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 7";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth8() {
        int box = 0;
       String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 8";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth9() {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 9";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth10() {
        int box = 0;
       String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 10";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth11() {
        int box = 0;
String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 11";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getmonth12() {
        int box = 0;
        String sql = "select Sum(TongTien) from HoaDon \n"
                + "			where TinhTrang = 1 and MONTH(NgayThanhToan) = 12";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int gethdday() {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and DAY(NgayThanhToan) = DAY(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int gethdday(String date) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and DAY(NgayThanhToan) = ? and MONTH(NGAYTHANHTOAN) =MONTH(GETDATE()) AND YEAR(NGAYTHANHTOAN) =YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public int gethdmonth(String date) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and MONTH(NgayThanhToan) = ? and YEAR(NgayThanhToan) =YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public int gethdyear(String date) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and YEAR(NgayThanhToan) = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
   @Override
    public int getkhday() {
        int box = 0;
        String sql = "select COUNT(IdKH) from HoaDon where TinhTrang =1 and DAY(NgayThanhToan) = DAY(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
   @Override
    public int getkhday(String date) {
        int box = 0;
        String sql = "select COUNT(IdKH) from HoaDon where TinhTrang =1 and DAY(NgayThanhToan) = ? and MONTH(NGAYTHANHTOAN) =MONTH(GETDATE()) AND YEAR(NGAYTHANHTOAN) =YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
   @Override
    public int getkhmonth(String date) {
        int box = 0;
        String sql = "select COUNT(IdKH) from HoaDon where TinhTrang =1 and MONTH(NgayThanhToan) = ? AND YEAR(NGAYTHANHTOAN) =YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
   @Override
    public int getkhyear(String date) {
        int box = 0;
        String sql = "select COUNT(IdKH) from HoaDon where TinhTrang =1 and Year(NgayThanhToan) = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
       @Override
    public int getdtday() {
        int box =0;
        String sql ="select SUM(TongTien) from HoaDon where TinhTrang = 1 and Day(NgayThanhToan) = Day(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {                
                box=(int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    return box;
    }
       @Override
    public int getdtday(String date) {
        int box =0;
        String sql ="select SUM(TongTien) from HoaDon where TinhTrang = 1 and Day(NgayThanhToan) = ? and MONTH(NgayThanhToan) = MONTH(GETDATE()) and  YEAR(NGAYTHANHTOAN) = YEAR(GETDATE())";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                box=(int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    return box;
    }
       @Override
    public int getdtmonth(String date) {
        int box =0;
        String sql ="select SUM(TongTien) from HoaDon where TinhTrang = 1 and MONTH(NgayThanhToan) = ? and  YEAR(NGAYTHANHTOAN) = YEAR(GETDATE()) ";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                box=(int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    return box;
    }
       @Override
    public int getdtyear(String date) {
        int box =0;
        String sql ="select SUM(TongTien) from HoaDon where TinhTrang = 1 and YEAR(NgayThanhToan) = ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                box=(int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    return box;
    }
    @Override
    public List<Thongke> getspday(String date) {
        lst.removeAll(lst);
        String sql = "select a.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND DAY(NGAYTHANHTOAN) = ? AND MONTH(NGAYTHANHTOAN) = MONTH(GETDATE()) AND YEAR(NGAYTHANHTOAN)=YEAR(GETDATE()) group by a.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSP chiTietSP = new ChiTietSP();
                chiTietSP.setTen(rs.getString(1));
                lst.add(new Thongke(rs.getInt(2), chiTietSP));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }
    @Override
    public List<Thongke> getspmonth(String date) {
        lst.removeAll(lst);
        String sql = "select a.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND MONTH(NGAYTHANHTOAN) = ? AND YEAR(NGAYTHANHTOAN) = YEAR(GETDATE()) group by a.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSP chiTietSP = new ChiTietSP();
                chiTietSP.setTen(rs.getString(1));
                lst.add(new Thongke(rs.getInt(2), chiTietSP));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }
    @Override
    public List<Thongke> getspyear(String date) {
        lst.removeAll(lst);
        String sql = "select a.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND YEAR(NGAYTHANHTOAN) = ? group by a.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSP chiTietSP = new ChiTietSP();
                chiTietSP.setTen(rs.getString(1));
                lst.add(new Thongke(rs.getInt(2), chiTietSP));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }

    @Override
    public int getdtkhoang1(String date,String date1) {
 int box =0;
        String sql ="select SUM(TongTien) from HoaDon where TinhTrang = 1\n" +
            "  and MONTH(NgayThanhToan) between ? and ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                box=(int) rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    return box;
    }

        @Override
    public int getbykhoang1(String date,String date1) {
        int box=0;
        String sql ="select Sum(a.Soluong) as 'Số sản phẩm' from HoaDonChiTiet a join ChitietSP b on a.IdCTSP =b.id\n" +
            "join HoaDon c on a.IdHD = c.Id where TinhTrang = 1 and MONTH(c.NgayThanhToan) between ? and ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                box=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    return box;
    }

        @Override
    public int gethdkhoang1(String date,String date1) {
        int box = 0;
        String sql = "select COUNT(Id) from HoaDon where TinhTrang =1 and MONTH(NgayThanhToan) between ? and ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

       @Override
    public int getkhkhoang1(String date,String date1) {
        int box = 0;
        String sql = "select COUNT(IdKH) from HoaDon where TinhTrang =1 and MONTH(NgayThanhToan) between ? and ?";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                box = rs.getInt(1);
            }
            return box;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
           @Override
    public List<Thongke> getspkhoang(String date,String date1) {
        lst.removeAll(lst);
        String sql = "select a.Ten,Sum(b.Soluong) as 'đã bán được' from ChitietSP a join HoaDonChiTiet b on a.Id=b.IdCTSP JOIN HOADON c ON b.IDHD=c.ID "
                + "join KhuyenMai d on a.IdKM =d.Id where c.TinhTrang =1 AND MONTH(NGAYTHANHTOAN) BETWEEN ? AND ? AND YEAR(NGAYTHANHTOAN)=YEAR(GETDATE()) group by a.Ten order by Sum(b.Soluong) desc";
        try {
            Connection conn = DBConnection.openDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, date1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietSP chiTietSP = new ChiTietSP();
                chiTietSP.setTen(rs.getString(1));
                lst.add(new Thongke(rs.getInt(2), chiTietSP));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return lst;
    }
}
