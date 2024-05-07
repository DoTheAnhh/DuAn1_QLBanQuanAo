/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services.imp;

import java.util.List;
import models.DanhMucSP;
import repositorys.IDanhMucSPRepository;
import repositorys.imp.DanhMucSPRepository;
import services.IDanhMucSPServices;
import viewmodels.Objecttt;

/**
 *
 * @author vieta
 */
public class DanhMucSPServices implements IDanhMucSPServices{
     private IDanhMucSPRepository danhMucSPRepository = new DanhMucSPRepository();
    
     @Override
    public List<DanhMucSP> getAll() {
        return danhMucSPRepository.getAll();
    }

    @Override
    public String Add(Objecttt x) {
        DanhMucSP danhmuc = new DanhMucSP(x.getId(), x.getTen());
        int them = danhMucSPRepository.insert(danhmuc);
        if (them == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public String Update(Objecttt x, int id) {
        DanhMucSP danhmuc = new DanhMucSP(x.getId(), x.getTen());
        int sua = danhMucSPRepository.update(danhmuc,id);
        if (sua == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public String Delete(int id) {
        if (danhMucSPRepository.delete(id) == 1) {
            return "Thành công";
        }
        return "Thất bại";
    }

    @Override
    public DanhMucSP getbyid(int id) {
        return danhMucSPRepository.getdanhmucbyid(id);
    }

}
