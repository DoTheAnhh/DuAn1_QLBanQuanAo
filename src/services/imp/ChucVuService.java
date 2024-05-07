/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.imp;

import java.util.ArrayList;
import java.util.List;
import models.Chucvu;
import repositorys.IChucvuRepo;
import repositorys.imp.Chucvurepo;
import services.IChucvuService;

/**
 *
 * @author hungh
 */
public class ChucVuService implements IChucvuService {
    IChucvuRepo chucvuRepo;
    List<Chucvu> lstcv ;
    public ChucVuService() {
        lstcv = new ArrayList<>();
        chucvuRepo =  new Chucvurepo();
    }
    @Override
    public List<Chucvu> getAllChucVu() {
        lstcv = new ArrayList<>();
        for (Chucvu cv : chucvuRepo.getAllChucVu()) {
            lstcv.add(new Chucvu(cv.getId(),cv.getTen()));
        }
        return lstcv;
    }

    

}
