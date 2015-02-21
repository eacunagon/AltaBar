/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import objects.AltbReserva;
import org.hibernate.Session;

/**
 *
 * @author Alex
 */
public interface InterfaceAltbReserva {
    public boolean insert(Session session, AltbReserva reserva) throws Exception;    
    public List<AltbReserva> getList () throws Exception;
    public boolean checkIfAvailable (Session session, AltbReserva reserva ) throws Exception;
    public boolean update(AltbReserva reseva) throws Exception;
}
