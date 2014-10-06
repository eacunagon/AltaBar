/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanSession;

import HibernateUtil.HibernateUtil;
import classes.Encrypt;
import daos.DaoAltbClientes;
import daos.DaoAltbHsy;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import objects.AltbClientes;
import objects.AltbHsy;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alex
 */
@Named (value ="mbSLogin")
@ManagedBean
@SessionScoped
public class MbSLogin implements Serializable{

    /**
     * Creates a new instance of MbsLogin
     */
   
    private String pss;
    private String EMail;
    private Session session;
    private Transaction transaction;

    public MbSLogin() {
    }
     
    public String logIn()
    {
        this.session=null;
        this.transaction=null;
        
        try
        {
            DaoAltbClientes daoTUsuario=new DaoAltbClientes();
            DaoAltbHsy daoHsyu=new DaoAltbHsy();
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();
            
            AltbClientes cliente=daoTUsuario.getItemByEmail(session, EMail);
            AltbHsy hsy=daoHsyu.getItemById(session, Encrypt.sha512(EMail));
            
            if(cliente!=null& hsy!=null)
            {
                if(hsy.getCu5479().equals(Encrypt.sha512(this.pss)))
                {
                    HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute("correoElectronico", this.EMail);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Sesión iniciada"));
                    return "/index";
                }
            }
            
            transaction.commit();
            
            EMail=null;
            pss=null;
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de acceso:", "Usuario o contraseña incorrecto"));
            
            return "/login";
            
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
            
            return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
    
     public String logOut()
    {
        this.EMail=null;
        this.pss=null;
        
        HttpSession httpSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atención:", "Sesión cerrada"));
        return "/index";
    }
    
    
    
    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getPss() {
        return pss;
    }

    public void setPss(String pss) {
        this.pss = pss;
    }      
        
}
