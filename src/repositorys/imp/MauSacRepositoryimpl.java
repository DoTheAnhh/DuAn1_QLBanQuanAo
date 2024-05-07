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
import models.MauSac;
import repositorys.IMauSacRepository;
import utilconnext.DBConnection;

/**
 *
 * @author vieta
 */
public class MauSacRepositoryimpl implements IMauSacRepository{
    final String SQL_SELECT_ALL = "SELECT Id,Ten FROM dbo.MauSac";
    final String SQL_SELECT_BY_ID = "SELECT Id,Ten FROM dbo.MauSac WHERE Id = ?";
    final String SQL_INSERT = "INSERT INTO dbo.MauSac(Ten) VALUES(?)";
    final String SQL_UPDATE = "UPDATE dbo.MauSac SET Ten = ? WHERE Id = ?";
    final String SQL_DELETE = "DELETE dbo.MauSac WHERE Id = ?";

    @Override
    public List<MauSac> getAll() {

        return getdataquery(SQL_SELECT_ALL);

    }

    @Override
    public int insert(MauSac x) {

        return DBConnection.ExcuteQuery(SQL_INSERT, x.getTen());

    }

    @Override
    public int update(MauSac x, int id) {
        return DBConnection.ExcuteQuery(SQL_UPDATE, x.getTen(), id);
    }

    @Override
    public int delete(int id) {
        return DBConnection.ExcuteQuery(SQL_DELETE, id);
    }

    @Override
    public MauSac getbyid(int id) {
        if (id == 0) {
            return new MauSac();
        }  
        return getdataquery(SQL_SELECT_BY_ID, id).get(0);
    }

    private List<MauSac> getdataquery(String SQL, Object... arvg) {
        List<MauSac> lst = new ArrayList<>();
        try {
            ResultSet rl = DBConnection.getDataFromQuery(SQL, arvg);
            while (rl.next()) {
                lst.add(new MauSac((int) rl.getObject(1), (String) rl.getObject(2)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lst;

    }
}
