package org.jboss.tools.example.jsf.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class ManagedBeanViewUtil { 

	public static void sucesso(){
		FacesContext.getCurrentInstance().
		addMessage(null, new FacesMessage("Sucesso!"));
    }
	
	public static void msg(String msg){
		FacesContext.getCurrentInstance().
		addMessage(null, new FacesMessage(msg + "!"));
    }
}
