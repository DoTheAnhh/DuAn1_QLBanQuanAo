/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorys.imp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Chucvu;
import repositorys.IChucvuRepo;
import utilconnext.DBConnection;

/**
 *
 * @author hungh
 */
public class Chucvurepo implements IChucvuRepo {

    @Override
    public List<Chucvu> getAllChucVu() {

        List<Chucvu> cvv = new ArrayList<>();
        String sql = "select * from ChucVu";
        ResultSet rs = null;
        try {
            rs = DBConnection.getDataFromQuery(sql);
            while (rs.next()) {
                cvv.add(new Chucvu(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Chucvurepo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cvv;
    }

}
