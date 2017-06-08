package org.jboss.tools.example.jsf.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "userManageBean")
public class UserManageBean {

	
	 public void salvar (){
		 System.out.println("Salvar");
	 }
}
