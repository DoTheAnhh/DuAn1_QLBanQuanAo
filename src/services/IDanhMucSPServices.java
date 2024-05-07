/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.DanhMucSP;
import viewmodels.Objecttt;

/**
 *
 * @author vieta
 */
public interface IDanhMucSPServices {
    List<DanhMucSP> getAll();
    
    String Add(Objecttt x);
    
    String Update(Objecttt x,int id);
    
    String Delete(int id);
    
    DanhMucSP getbyid(int id);
}
