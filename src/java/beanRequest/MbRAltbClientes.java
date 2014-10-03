/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanRequest;

import classes.Encrypt;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import objects.AltbClientes;
import daos.DaoAltbClientes;
import daos.DaoAltbHsy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import objects.AltbHsy;
import org.primefaces.context.RequestContext;


/**
 *
 * @author Alex
 */
@ManagedBean
@RequestScoped
public class MbRAltbClientes {

    /**
     * Creates a new instance of MbRAltbClientes
     */
    private AltbClientes cliente;
    private AltbHsy  hsy;
    private List <AltbClientes> listaClientes;
    private String txtContraseñaRepita;
    
    public MbRAltbClientes() {
      this.cliente = new AltbClientes();
      this.hsy = new AltbHsy();
    }
    
    public String addClient() 
    {
        try {
            if(!txtContraseñaRepita.equals(hsy.getCu5479()))
            {
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Las contraseñas no coinciden."));
            return "/cliente/registroCliente";
            }
    
        DaoAltbClientes daoClientes = new DaoAltbClientes();
        daoClientes.insert(this.cliente);    
        hsy.setCu5479(Encrypt.sha512(hsy.getCu5479()));
        hsy.setIu4789(Encrypt.sha512(cliente.getIdCliente()));
        hsy.setUu7879(Encrypt.sha512(cliente.getEMail()));
        DaoAltbHsy daoHsyu = new DaoAltbHsy();    
        daoHsyu.insert(hsy);
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito!","El registro se ha realizado correctamente"));
        
        }
        catch(Exception ex){
           FacesContext.getCurrentInstance().addMessage(null,
           new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error!",ex.getMessage()));
        }
    RequestContext.getCurrentInstance().execute("cleanForm('frmRegistrarCliente')");
    return "/cliente/registroCliente";
    }

    public AltbHsy getHsyu() {
        return hsy;
    }

    public void setHsyu(AltbHsy hsyu) {
        this.hsy = hsyu;
    }

    
    
    public AltbClientes getCliente() {
        return cliente;
    }

    public void setClientes(AltbClientes cliente) {
        this.cliente = cliente;
    }

    public List <AltbClientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List <AltbClientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public String getTxtContraseñaRepita() {
        return txtContraseñaRepita;
    }

    public void setTxtContraseñaRepita(String txtContraseñaRepita) {
        this.txtContraseñaRepita = txtContraseñaRepita;
    }
    
}
