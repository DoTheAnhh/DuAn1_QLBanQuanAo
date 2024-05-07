/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.util.List;
import models.DanhMucSP;

/**
 *
 * @author vieta
 */
public interface IDanhMucSPRepository {
    public List<DanhMucSP> getAll();
    
    public int insert(DanhMucSP x);
    
    public int update(DanhMucSP dmsp,int id);
    
    public int delete(int id);
    
    public DanhMucSP getdanhmucbyid(int id);
}
