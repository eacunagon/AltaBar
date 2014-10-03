/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;
import java.util.List;
import objects.AltbClientes;

/**
 *
 * @author Alex
 */
public interface InterfaceAltbClientes {
    public boolean insert(AltbClientes cliente) throws Exception;    
    public List<AltbClientes> getList () throws Exception;
    public AltbClientes getItem (String idCliente) throws Exception;
    public boolean update(AltbClientes cliente) throws Exception;
   
}
