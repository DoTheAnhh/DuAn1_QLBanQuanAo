/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.sql.Date;
import java.util.List;
import models.ChiTietSP;
import models.SanPham;

/**
 *
 * @author vieta
 */
public interface IChiTietSPRepository {

    public List<ChiTietSP> getAll();

    int insert(ChiTietSP x);

    int update(ChiTietSP x, String Ma);

    int delete(String ma);

    public List<ChiTietSP> getlistbyTen(String ten);

    public List<ChiTietSP> getSPhet();
    
    public List<ChiTietSP> check(String maSP);
    
    public Date checkngay(String id);
    public Date checkngay2(String id);
    public List<ChiTietSP> GetAll();
    public boolean Update(String ma,String id);
         
}
