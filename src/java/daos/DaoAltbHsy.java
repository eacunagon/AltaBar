/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import interfaces.InterfaceAltbHsy;
import java.util.List;
import objects.AltbHsy;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alex
 */
public class DaoAltbHsy implements InterfaceAltbHsy{
 private  Session session;
    @Override
    public boolean insert(Session session, AltbHsy hsy) throws Exception {
       session.save(hsy);       
       return true;
    }

    @Override
    public List<AltbHsy> getList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AltbHsy getItemById(Session session, String id) throws Exception {
        String hql="from AltbHsy where uu7879=:id";
        Query query=session.createQuery(hql);
        query.setParameter("id", id);        
        AltbHsy hsy=(AltbHsy) query.uniqueResult();        
        return hsy;
    }

    @Override
    public boolean update(AltbHsy cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
