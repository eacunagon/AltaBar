/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import interfaces.InterfaceAltbReserva;
import java.util.List;
import objects.AltbReserva;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alex
 */
public class DaoAltbReserva implements InterfaceAltbReserva{

    @Override
    public boolean insert(Session session, AltbReserva reserva) throws Exception {
        session.save(reserva);       
        return true;
    }

    @Override
    public List<AltbReserva> getList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkIfAvailable(Session session,AltbReserva reserva ) throws Exception {
        String hql="from AltbReserva where idMesa=:mesa and fhCarga=:fecha";
        Query query=session.createQuery(hql);
        query.setParameter("mesa", reserva.getIdMesa());  
        query.setParameter("fecha", reserva.getFhCarga().toString());       
        AltbReserva hsy=(AltbReserva) query.uniqueResult();        
        return true;
    }

    @Override
    public boolean update(AltbReserva reseva) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

    
}
