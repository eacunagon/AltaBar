/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alex
 */
@WebFilter("*.xhtml")
public class SessionUrlFilter implements Filter{
    FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res =(HttpServletResponse) response;
        HttpSession session= req.getSession(true);
        String requestUrl=req.getRequestURL().toString();
        String[] urlNotAllowed=new String[]
        {
            //"faces/index.xhtml",
            "faces/cliente/reserva.xhtml",
           // "faces/cliente/listaEmail.xhtml"
        };
        
        boolean redireccionar=false;
        if(session.getAttribute("eMail")==null){
        for(String item : urlNotAllowed)
            {
            if(requestUrl.contains(item)){
            redireccionar=true;
            }
            }
        
        }
        if(redireccionar)
        {
            res.sendRedirect(req.getContextPath()+"/faces/index.xhtml");
        }
        else
        {
            chain.doFilter(request, response);
        }
     
    }

    @Override
    public void destroy() {
       this.filterConfig = null;
    }
    
}
