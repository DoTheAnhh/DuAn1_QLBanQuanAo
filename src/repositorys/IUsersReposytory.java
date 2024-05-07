/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.util.List;
import models.Users;
import viewmodels.UsersViewmodel;

/**
 *
 * @author hungh
 */
public interface IUsersReposytory {

    public List<Users> getAllNhanVien();

    boolean add(Users nv);

    boolean update(Users us, String id);

    boolean delete(String id);

    List<Users> SearchNVV(String Ten);

    boolean updateMK(Users us, String mail);

    String kiemtra(String mail);
    String kiemtrasdt(String sdt);
    String kiemtratk(String tk);

    Users getUserbytk(String tk);
}
