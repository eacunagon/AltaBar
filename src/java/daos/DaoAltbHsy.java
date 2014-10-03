/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import HibernateUtil.HibernateUtil;
import interfaces.InterfaceAltbHsy;
import java.util.List;
import objects.AltbHsy;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alex
 */
public class DaoAltbHsy implements InterfaceAltbHsy{
 private  Session session;
    @Override
    public boolean insert(AltbHsy hsy) throws Exception {
         try {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(hsy);
        transaction.commit();
        session.close();
        } catch (Exception ex){
        String error = ex.getMessage();
        }
       return true;
    }

    @Override
    public List<AltbHsy> getList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AltbHsy getItem(String idCliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AltbHsy cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
