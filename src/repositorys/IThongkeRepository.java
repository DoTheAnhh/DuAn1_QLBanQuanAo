/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorys;

import java.util.List;
import models.Thongke;

/**
 *
 * @author ADMIN
 */
public interface IThongkeRepository {
    public int getbyday();
    public int gethdday();
    public int gethdday(String date);
    public int gethdmonth(String date);
    public int gethdyear(String date);
    public int getkhday();
    public int getkhday(String date);
    public int getkhmonth(String date);
    public int getkhyear(String date);
    public int getbyday(String date);
    public int getbymonth(String date);
    public int getbyyear(String date);
    public List<Thongke> getsp();
    public List<Thongke> getnv();
    public int getmonth1();
    public int getmonth2();
    public int getmonth3();
    public int getmonth4();
    public int getmonth5();
    public int getmonth6();
    public int getmonth7();
    public int getmonth8();
    public int getmonth9();
    public int getmonth10();
    public int getmonth11();
    public int getmonth12();
    public int getdtday();
    public int getdtday(String date);
    public int getdtmonth(String date);
    public int getdtyear(String date);
    public List<Thongke> getspday(String date);
    public List<Thongke> getspmonth(String date);
    public List<Thongke> getspyear(String date);
    public int getdtkhoang1(String date,String date1);
    public int getkhkhoang1(String date,String date1);
    public int gethdkhoang1(String date,String date1);
    public int getbykhoang1(String date,String date1);
    public List<Thongke> getspkhoang(String date,String date1);
}
