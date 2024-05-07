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
import models.KichCo;
import repositorys.IKichCoRepository;

import utilconnext.DBConnection;

/**
 *
 * @author vieta
 */
public class KichCoRepositoryimpl implements IKichCoRepository {

    final String SQL_SELECT_ALL = "SELECT Id,Ten FROM dbo.KichCo";
    final String SQL_SELECT_BY_ID = "SELECT Id,Ten FROM dbo.KichCo WHERE Id = ?";
    final String SQL_INSERT = "INSERT INTO dbo.KichCo(Ten) VALUES(?)";
    final String SQL_UPDATE = "UPDATE dbo.KichCo SET Ten = ? WHERE Id = ?";
    final String SQL_DELETE = "DELETE dbo.KichCo WHERE Id = ?";

    @Override
    public List<KichCo> getAll() {

        return getdataquery(SQL_SELECT_ALL);

    }

    @Override
    public int insert(KichCo x) {

        return DBConnection.ExcuteQuery(SQL_INSERT, x.getTen());

    }

    @Override
    public int update(KichCo x, int id) {
        return DBConnection.ExcuteQuery(SQL_UPDATE, x.getTen(), id);
    }

    @Override
    public int delete(int id) {
        return DBConnection.ExcuteQuery(SQL_DELETE, id);
    }

    @Override
    public KichCo getbyid(int id) {
        if (id == 0) {
            return new KichCo();
        }
        return getdataquery(SQL_SELECT_BY_ID, id).get(0);
    }

    private List<KichCo> getdataquery(String SQL, Object... arvg) {
        List<KichCo> lst = new ArrayList<>();
        try {
            ResultSet rl = DBConnection.getDataFromQuery(SQL, arvg);
            while (rl.next()) {
                lst.add(new KichCo((int) rl.getObject(1), (String) rl.getObject(2)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lst;

    }
}
