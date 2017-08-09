package org.jboss.tools.example.springmvc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.jboss.tools.example.projeto.listener.ContextLoaderListenerImpl;
import org.jboss.tools.example.springmvc.data.IGenericDao;
import org.jboss.tools.example.springmvc.model.rd.UnidadeProduto;

@FacesConverter(forClass = UnidadeProduto.class, value = "unidadeProdutoConverter")
public class UnidadeProdutoConverter implements Converter {

	private IGenericDao idGenericDao = (IGenericDao) ContextLoaderListenerImpl.getBean(IGenericDao.class);

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String unidade) {
		return idGenericDao.find(UnidadeProduto.class, Long.parseLong(unidade));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object unidade) {

		if (unidade == null || (unidade != null && unidade.toString().isEmpty()))
			return "";

		if (((UnidadeProduto) unidade).getId() == null)
			return "";

		return ((UnidadeProduto) unidade).getId().toString();
	}

}
