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
import models.NSX;
import repositorys.INSXRepository;
import utilconnext.DBConnection;

/**
 *
 * @author vieta
 */
public class NSXRepository implements INSXRepository{
     final String SQL_SELECT_ALL = "SELECT Id,Ten FROM dbo.NSX";
    final String SQL_SELECT_BY_ID = "SELECT Id,Ten FROM dbo.NSX WHERE Id = ?";
    final String SQL_INSERT = "INSERT INTO dbo.NSX( Ten ) VALUES ( ? )";
    final String SQL_UPDATE = "UPDATE dbo.NSX SET Ten = ? WHERE Id = ?";
    final String SQL_DELETE = "DELETE dbo.NSX WHERE Id = ?";

    @Override
    public List<NSX> getAll() {

        return getdataquery(SQL_SELECT_ALL);

    }

    @Override
    public int insert(NSX x) {

        return DBConnection.ExcuteQuery(SQL_INSERT, x.getTen());

    }

    @Override
    public int update(NSX x, int id) {
        return DBConnection.ExcuteQuery(SQL_UPDATE, x.getTen(), id);
    }

    @Override
    public int delete(int id) {
        return DBConnection.ExcuteQuery(SQL_DELETE, id);
    }

    @Override
    public NSX getbyid(int id) {
        if (id == 0) {
            return new NSX();
        }
        return getdataquery(SQL_SELECT_BY_ID, id).get(0);
    }

    private List<NSX> getdataquery(String SQL, Object... arvg) {
        List<NSX> lst = new ArrayList<>();
        try {
            ResultSet rl = DBConnection.getDataFromQuery(SQL, arvg);
            while (rl.next()) {
                lst.add(new NSX((int) rl.getObject(1), (String) rl.getObject(2)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lst;

    }
}
