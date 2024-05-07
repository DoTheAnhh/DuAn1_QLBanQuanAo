/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.util.List;
import models.MauSac;

/**
 *
 * @author vieta
 */
public interface IMauSacRepository {
    public List<MauSac> getAll();
    
    public int insert(MauSac x);
    
    public int update(MauSac x,int id);
    
    public int delete(int id);
    
    public MauSac getbyid(int id);
}
