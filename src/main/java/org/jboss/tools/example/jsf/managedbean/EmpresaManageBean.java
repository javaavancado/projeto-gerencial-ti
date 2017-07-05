package org.jboss.tools.example.jsf.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.jboss.tools.example.springmvc.data.EmpresaDAO;
import org.jboss.tools.example.springmvc.model.rd.Empresa;

@RequestScoped
@ManagedBean(name = "empresaManageBean")
public class EmpresaManageBean {

	private Empresa empresa = new Empresa();

	@ManagedProperty(name = "empresaDAO", value = "#{empresaDAO}")
	private EmpresaDAO empresaDAO;

	public String salvar() {
		empresa = empresaDAO.merge(empresa);
		novo();
		return "";
	}
	
	
	public void novo(){
		empresa = new Empresa();
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}
	
	public EmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}

}
