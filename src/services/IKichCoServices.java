/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import models.KichCo;
import viewmodels.Objecttt;

/**
 *
 * @author vieta
 */
public interface IKichCoServices {
    List<KichCo> getAll();
    
    String Add(Objecttt x);
    
    String Update(Objecttt x,int id);
    
    String Delete(int id);
    
    KichCo getbyid(int id);
}
