package org.jboss.tools.example.projeto.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.tools.example.springmvc.data.IGenericDao;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class CarregamentoLazyListForObject<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;

	private List<T> list = new ArrayList<T>();

	private String query = "";

	private IGenericDao iGenericDao = (IGenericDao) ContextLoaderListenerImpl.getBean(IGenericDao.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		if (query != null && !query.isEmpty()) {

			int totalResgistroConsulta = iGenericDao.total(query);

			list = (List<T>) iGenericDao.finLazy(query, first, pageSize);

			setRowCount(totalResgistroConsulta);
			setPageSize(pageSize);
		}

		return list;
	}

	public List<T> getList() {
		return list;
	}

	public void clean() {
		this.list.clear();
	}

	public void remove(T objetoSelecionado) {
		this.list.remove(objetoSelecionado);
	}

	public void add(T objetoSelecioado) {
		this.list.add(objetoSelecioado);
	}

	public void addAll(List<T> collections) {
		this.list.addAll(collections);
	}

	public void setQuery(String query) {
		this.query = query;
	}

}