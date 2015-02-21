/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;
import java.util.List;
import objects.AltbMesas;
import org.hibernate.Session;

/**
 *
 * @author Alex
 */
public interface InterfaceAltbMesas {
    public boolean insert(Session session, AltbMesas mesa) throws Exception;    
    public List<AltbMesas> getList () throws Exception;
    public AltbMesas getItemById (Session session,String id ) throws Exception;
    public boolean update(AltbMesas cliente) throws Exception;    
    public List<AltbMesas> getMesasAvailables(Session session, String fecha,String idMesa )throws Exception;
}
