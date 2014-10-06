/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanRequest;

import HibernateUtil.HibernateUtil;
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
import org.hibernate.Session;
import org.hibernate.Transaction;


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
    private Session session;
    private Transaction transaction;
    
    public MbRAltbClientes() {
      this.cliente = new AltbClientes();
      this.hsy = new AltbHsy();
    }
    
    public void addClient() throws Exception 
    {
        session=null;
        transaction=null;
        try {
            session =HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if(!txtContraseñaRepita.equals(hsy.getCu5479()))
            {
                FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Las contraseñas no coinciden."));
                return ;
            }    
            DaoAltbClientes daoClientes = new DaoAltbClientes();
            if(daoClientes.getItemByEmail(session, cliente.getEMail())!=null)
            {
                FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","El usuario ya se encuentra registrado en el sistema"));
                return ;
            } 
        //insertamos el cliente    
        daoClientes.insert(session,cliente);    
        //Encriptamos los campos para guardar en la tabla de seguridad
        hsy.setCu5479(Encrypt.sha512(hsy.getCu5479()));
        hsy.setIu4789(Encrypt.sha512(cliente.getIdCliente()));
        hsy.setUu7879(Encrypt.sha512(cliente.getEMail()));
        //se instancia el dao para la tabla de seguridad y se hace el insert
        DaoAltbHsy daoHsyu = new DaoAltbHsy();    
        daoHsyu.insert(session,hsy);
        //se hace commit de la transaction
        transaction.commit();
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito!","El registro se ha realizado correctamente"));
        
        }
        catch(ExceptionInInitializerError ex){
           if(transaction!=null)transaction.rollback();
           
           FacesContext.getCurrentInstance().addMessage(null,
           new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error!","Porfavor contacte al administrador "+ex.getMessage()));
        }
        finally{
        if(session!=null)session.close();
        }
    //RequestContext.getCurrentInstance().execute("cleanForm('frmRegistrarCliente')");
        cliente = new AltbClientes();
        hsy = new AltbHsy();
        cliente.setSexo("M");
   
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
