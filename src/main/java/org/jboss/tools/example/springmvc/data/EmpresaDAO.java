package org.jboss.tools.example.springmvc.data;

import java.io.Serializable;

import org.jboss.tools.example.springmvc.model.rd.Empresa;

public interface EmpresaDAO extends Serializable{

	public Empresa merge (Empresa empres);
}
