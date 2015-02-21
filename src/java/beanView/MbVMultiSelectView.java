/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beanView;

import HibernateUtil.HibernateUtil;
import daos.DaoAltbMesas;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import objects.AltbMesas;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author Alex
 */
@Named (value ="mbVMultiSelectView")
@ManagedBean
@ViewScoped

public class MbVMultiSelectView {
     
    private List<SelectItem> categories;    
    private String selection;
    
    private List <AltbMesas> mesasList;
    private Session session;
    private Transaction transaction;
    
    public MbVMultiSelectView() {
  
     }
 
    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        SelectItemGroup gRes = new SelectItemGroup("Restaurante");   
        SelectItemGroup gDisco = new SelectItemGroup("Disco");
        SelectItemGroup gTerra = new SelectItemGroup("Terraza");
        
        List<AltbMesas> resMesas = this.getMesasAvailable("ALTB_RESTAURANTE");
        List<AltbMesas> discoMesas = this.getMesasAvailable("ALTB_RESTAURANTE");
        List<AltbMesas> clubMesas = this.getMesasAvailable("ALTB_CLUB");
        
        gRes.setSelectItems((SelectItem[])resMesas.toArray());
        gRes.setSelectItems((SelectItem[])discoMesas.toArray());
        gRes.setSelectItems((SelectItem[])clubMesas.toArray());
        
        categories.add(gRes);
        categories.add(gDisco);
        categories.add(gTerra);
     
    }
    
    
   public List<AltbMesas> getMesasAvailable(String area)
    {
        this.session=null;
        this.transaction=null;        
        try
        {
            Date fechaDeHoy= new Date();
            DaoAltbMesas daoAltbMesas=new DaoAltbMesas();            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaction=this.session.beginTransaction();            
            this.mesasList=daoAltbMesas.getMesasAvailables(this.session,fechaDeHoy.toString(),area);            
            this.transaction.commit();            
            return this.mesasList;
        }
        catch(Exception ex)
        {
            if(this.transaction!=null)
            {
                this.transaction.rollback();
            }            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));            return null;
        }
        finally
        {
            if(this.session!=null)
            {
                this.session.close();
            }
        }
    }
         
    public List<SelectItem> getCategories() {
        return categories;
    }    
 
    public String getSelection() {
        return selection;
    }
    public void setSelection(String selection) {
        this.selection = selection;
    }
}