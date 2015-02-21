/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanView;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Alex
 */
@Named (value ="mbVListaEmail")
@ManagedBean
@ViewScoped
public class MbVListaEmail implements Serializable{

    
private String eMail;
private String nombre;
private String message;





    public void sendMail() {
        String subject= nombre+ " " +eMail;             
        try {
            
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("altabar.listas", "Nivde017"));
            email.setSSLOnConnect(true);
            email.isStartTLSEnabled();
            email.setFrom("altabar.listas@gmail.com");
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo("eacunagon@gmail.com");
            email.send();
                 FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Listo!", "Lista enviada"));
        } catch (Exception ex) {
               FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",ex.getMessage()));
               eMail="";
               nombre="";
               message="";
          
        }
        
    }   

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}