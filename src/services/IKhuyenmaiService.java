/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.KhuyenMai;
import viewmodels.KhuyenmaiViewmodel;

/**
 *
 * @author ADMIN
 */
public interface IKhuyenmaiService {

    public List<KhuyenmaiViewmodel> GetALL();

    public boolean Add(KhuyenmaiViewmodel km);

    public boolean Update(KhuyenmaiViewmodel km, String id);

    public boolean Delete(String id);

    public String checktrung(String ten);

    public List<KhuyenmaiViewmodel> GetOnebyBD(String date);

    public List<KhuyenmaiViewmodel> GetOnebyKT(String date);

    public List<KhuyenmaiViewmodel> GetOnebyALL(String datedb, String datekt);

    public List<KhuyenmaiViewmodel> GetOnebyten(String ten);

    public boolean UpdateTT();

    public boolean UpdateTT2();

    public List<KhuyenMai> getlist();
}
