/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
/**
 *
 * @author Alex
 */

@FacesValidator("ValidatorEmpty")

public class ValidatorEmpty implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException {
        String label;
       
      
       
         if(component instanceof  HtmlInputTextarea){
         HtmlInputTextarea a = ( HtmlInputTextarea) component;
            if(a.getLabel()==null||a.getLabel().trim().equals("")){
            label = a.getId();
        }
        else{
            label=a.getLabel();        
        }
        if(value.toString().trim().equals("")){
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",label+" es un campo obligatorio"));
        }         
        }
         HtmlInputText  htmlInput = (HtmlInputText) component;
        
         
        if(htmlInput.getLabel()==null||htmlInput.getLabel().trim().equals("")){
            label = htmlInput.getId();
        }
        else{
            label=htmlInput.getLabel();        
        }
        if(value.toString().trim().equals("")){
        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error",label+" es un campo obligatorio"));
        }

    }
    
}
