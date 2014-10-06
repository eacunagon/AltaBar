/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;
import java.util.List;
import objects.AltbClientes;
import org.hibernate.Session;

/**
 *
 * @author Alex
 */
public interface InterfaceAltbClientes {
    public boolean insert(Session session, AltbClientes cliente) throws Exception;    
    public List<AltbClientes> getAll (Session session) throws Exception;
    public AltbClientes getItem (Session session,String idCliente) throws Exception;
    public boolean update(Session session, AltbClientes cliente) throws Exception;
    public AltbClientes getItemByEmail(Session session, String eMail) throws Exception;
   
}
