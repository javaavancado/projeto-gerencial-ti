package org.jboss.tools.example.jsf.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.jboss.tools.example.springmvc.data.EmpresaDAO;
import org.jboss.tools.example.springmvc.data.InspecaoDAO;
import org.jboss.tools.example.springmvc.model.rd.Inspecao;

@RequestScoped
@ManagedBean(name = "inspecaoManageBean")
public class InspecaoManageBean {

	private Inspecao inspecao = new Inspecao();

	@ManagedProperty(name = "inspecaoDAO", value = "#{inspecaoDAO}")
	private InspecaoDAO inspecaoDAO;

	@ManagedProperty(name = "empresaDAO", value = "#{empresaDAO}")
	private EmpresaDAO empresaDAO;

	public String salvar() {
		inspecao = inspecaoDAO.merge(inspecao);
		novo();
		return "";
	}

	public List<SelectItem> listaComboBox() {
		return empresaDAO.listaComboBox();
	}

	public void novo() {
		inspecao = new Inspecao();
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public InspecaoDAO getInspecaoDAO() {
		return inspecaoDAO;
	}

	public void setInspecaoDAO(InspecaoDAO inspecaoDAO) {
		this.inspecaoDAO = inspecaoDAO;
	}

	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	public EmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}

}
