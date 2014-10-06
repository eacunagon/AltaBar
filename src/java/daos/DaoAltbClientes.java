/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;
import interfaces.*;
import java.util.List;
import objects.AltbClientes;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;
/**
 *
 * @author Alex
 */
public class DaoAltbClientes implements InterfaceAltbClientes{
    private  Session session;
    
    @Override
    public boolean insert(Session session,AltbClientes cliente) throws Exception, ConstraintViolationException {
        session.save(cliente);             
        return true;
    }
    
    @Override
    public List<AltbClientes> getAll(Session session) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AltbClientes getItem(Session session,String idCliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    @Override
    public boolean update(Session session, AltbClientes cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public AltbClientes getItemByEmail(Session session, String eMail) throws Exception {
        String hql="from AltbClientes where EMail=:eMail";
        Query query=session.createQuery(hql);
        query.setParameter("eMail", eMail);        
        AltbClientes cliente=(AltbClientes) query.uniqueResult();        
        return cliente;
    }
     
}
