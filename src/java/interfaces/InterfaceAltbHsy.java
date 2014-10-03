/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import objects.AltbHsy;

/**
 *
 * @author Alex
 */
public interface InterfaceAltbHsy {
    public boolean insert(AltbHsy cliente) throws Exception;    
    public List<AltbHsy> getList () throws Exception;
    public AltbHsy getItem (String idCliente) throws Exception;
    public boolean update(AltbHsy cliente) throws Exception;
}
