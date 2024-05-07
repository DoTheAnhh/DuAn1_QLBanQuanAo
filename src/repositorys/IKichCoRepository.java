/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorys;

import java.util.List;
import models.KichCo;

/**
 *
 * @author vieta
 */
public interface IKichCoRepository {
    public List<KichCo> getAll();
    
    public int insert(KichCo x);
    
    public int update(KichCo x,int id);
    
    public int delete(int id);
    
    public KichCo getbyid(int id);
}
