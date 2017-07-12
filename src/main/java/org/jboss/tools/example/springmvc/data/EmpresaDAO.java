package org.jboss.tools.example.springmvc.data;

import java.io.Serializable;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.tools.example.springmvc.model.rd.Empresa;

public interface EmpresaDAO extends Serializable{

	public Empresa merge (Empresa empres);
	
	Empresa buscaPorId(Long codEmpresa);
	
	List<Empresa> lista();
	
	List<SelectItem> listaComboBox();

}
