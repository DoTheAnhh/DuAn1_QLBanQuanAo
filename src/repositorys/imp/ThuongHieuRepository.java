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
import models.ThuongHieu;
import repositorys.IThuongHieuRepository;
import utilconnext.DBConnection;

/**
 *
 * @author vieta
 */
public class ThuongHieuRepository implements IThuongHieuRepository {

    final String SQL_SELECT_ALL = "SELECT Id,Ten FROM dbo.ThuongHieu";
    final String SQL_SELECT_BY_ID = "SELECT Id,Ten FROM dbo.ThuongHieu WHERE Id = ?";
    final String SQL_INSERT = "INSERT INTO dbo.ThuongHieu(Ten) VALUES(?)";
    final String SQL_UPDATE = "UPDATE dbo.ThuongHieu SET Ten = ? WHERE Id = ?";
    final String SQL_DELETE = "DELETE dbo.ThuongHieu WHERE Id = ?";

    @Override
    public List<ThuongHieu> getAll() {

        return getdataquery(SQL_SELECT_ALL);

    }

    @Override
    public int insert(ThuongHieu x) {

        return DBConnection.ExcuteQuery(SQL_INSERT, x.getTen());

    }

    @Override
    public int update(ThuongHieu x, int id) {
        return DBConnection.ExcuteQuery(SQL_UPDATE, x.getTen(), id);
    }

    @Override
    public int delete(int id) {
        return DBConnection.ExcuteQuery(SQL_DELETE, id);
    }

    @Override
    public ThuongHieu getbyid(int id) {
        ThuongHieu th = new ThuongHieu();
        if (id == 0) {
            return th;
        }
        th = getdataquery(SQL_SELECT_BY_ID, id).get(0);
        return th;
    }

    private List<ThuongHieu> getdataquery(String SQL, Object... arvg) {
        List<ThuongHieu> lst = new ArrayList<>();
        try {
            ResultSet rl = DBConnection.getDataFromQuery(SQL, arvg);
            while (rl.next()) {
                lst.add(new ThuongHieu((int) rl.getObject(1), (String) rl.getObject(2)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lst;

    }
}
