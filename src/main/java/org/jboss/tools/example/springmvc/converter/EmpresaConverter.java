package org.jboss.tools.example.springmvc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.jboss.tools.example.projeto.listener.ContextLoaderListenerImpl;
import org.jboss.tools.example.springmvc.data.EmpresaDAO;
import org.jboss.tools.example.springmvc.model.rd.Empresa;

@FacesConverter(forClass = Empresa.class, value = "empresaConverter")
public class EmpresaConverter implements Converter {

	private EmpresaDAO empresaDAO = (EmpresaDAO) ContextLoaderListenerImpl.getBean(EmpresaDAO.class);

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codEmpresa) {
		return empresaDAO.buscaPorId(Long.parseLong(codEmpresa));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object empresa) {
		
		if (empresa == null || (empresa != null && empresa.toString().isEmpty()))
			return "";
		
		if (((Empresa) empresa).getId() == null)
			return "";
		
		return ((Empresa) empresa).getId().toString();
	}

}
