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
import models.ChatLieu;
import repositorys.IChatLieuRepository;
import utilconnext.DBConnection;

/**
 *
 * @author vieta
 */
public class ChatLieuRepositoryimpl implements IChatLieuRepository{
    final String SQL_SELECT_ALL = "SELECT Id,Ten FROM dbo.ChatLieu";
    final String SQL_SELECT_BY_ID = "SELECT Id,Ten FROM dbo.ChatLieu WHERE Id = ?";
    final String SQL_INSERT = "INSERT INTO dbo.ChatLieu (Ten) VALUES(?)";
    final String SQL_UPDATE = "UPDATE dbo.ChatLieu SET Ten = ? WHERE Id = ?";
    final String SQL_DELETE = "DELETE  dbo.ChatLieu WHERE Id = ?";

    @Override
    public List<ChatLieu> getAll() {

        return getdataquery(SQL_SELECT_ALL);

    }

    @Override
    public int insert(ChatLieu x) {

        return DBConnection.ExcuteQuery(SQL_INSERT, x.getTen());

    }

    @Override
    public int update(ChatLieu x, int id) {
        return DBConnection.ExcuteQuery(SQL_UPDATE, x.getTen(), id);
    }

    @Override
    public int delete(int id) {
        return DBConnection.ExcuteQuery(SQL_DELETE, id);
    }

    @Override
    public ChatLieu getbyid(int id) {
        return getdataquery(SQL_SELECT_BY_ID, id).get(0);
    }

    private List<ChatLieu> getdataquery(String SQL, Object... arvg) {
        List<ChatLieu> lst = new ArrayList<>();
        try {
            ResultSet rl = DBConnection.getDataFromQuery(SQL, arvg);
            while (rl.next()) {
                lst.add(new ChatLieu((int) rl.getObject(1), (String) rl.getObject(2)));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lst;

    }
     
    
}