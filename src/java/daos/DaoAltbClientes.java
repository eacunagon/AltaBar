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
import org.hibernate.Transaction;
import HibernateUtil.HibernateUtil;
/**
 *
 * @author Alex
 */
public class DaoAltbClientes implements InterfaceAltbClientes{
    private  Session session;

    public boolean insert(AltbClientes cliente) throws Exception {
        try {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cliente);
        transaction.commit();
        session.close();
        } catch (Exception ex){
          throw ex ;
        }
        return true;
    }
    

    @Override
    public List<AltbClientes> getList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AltbClientes getItem(String idCliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AltbClientes cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
