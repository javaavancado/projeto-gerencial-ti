package org.jboss.tools.example.springmvc.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.jboss.tools.example.projeto.listener.ContextLoaderListenerImpl;
import org.jboss.tools.example.springmvc.data.ProdutoDAO;
import org.jboss.tools.example.springmvc.model.rd.Produto;

@FacesConverter(forClass = ProdutoConverter.class, value = "produtoConverter")
public class ProdutoConverter implements Converter {

	private ProdutoDAO produtoDAO = (ProdutoDAO) ContextLoaderListenerImpl.getBean(ProdutoDAO.class);

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codProduto) {
		return produtoDAO.buscaPorId(Long.parseLong(codProduto));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object produto) {

		if (produto == null || produto.equals("0") || (produto != null && produto.toString().isEmpty()))
			return "";

		if (((Produto) produto).getId() == null)
			return "";

		return ((Produto) produto).getId().toString();
	}

}
