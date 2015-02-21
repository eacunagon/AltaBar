/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import interfaces.InterfaceAltbMesas;
import java.util.Date;
import java.util.List;
import objects.AltbMesas;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Alex
 */
public class DaoAltbMesas implements InterfaceAltbMesas{

    @Override
    public boolean insert(Session session, AltbMesas mesa) throws Exception {
       session.save(mesa);       
       return true;
    }

    @Override
    public List<AltbMesas> getList() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AltbMesas getItemById(Session session, String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AltbMesas cliente) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AltbMesas> getMesasAvailables(Session session, String fecha ,String idArea ) throws Exception {
       String sql = "select * from altb_mesas_area where  ID_MESA not in (SELECT altb_mesas_area.ID_MESA FROM altb_mesas_area"
               + "   left join altb_reserva ON altb_mesas_area.ID_MESA = altb_reserva.ID_MESA  where altb_reserva.FH_CARGA ='date' "
               + "and altb_mesas_area.ID_AREA = 'idArea') ";
       Query query =  session.createSQLQuery(sql);
       query.setParameter("idArea", idArea);
       query.setParameter("date", fecha);
       List <AltbMesas> mesas=query.list();        
    return mesas;
    }
    
}
