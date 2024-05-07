/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.util.List;
import models.ThuongHieu;

/**
 *
 * @author vieta
 */
public interface IThuongHieuRepository {

    public List<ThuongHieu> getAll();

    public int insert(ThuongHieu x);

    public int update(ThuongHieu x, int id);

    public int delete(int id);

    public ThuongHieu getbyid(int id);
}
