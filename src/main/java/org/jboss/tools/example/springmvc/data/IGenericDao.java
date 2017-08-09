package org.jboss.tools.example.springmvc.data;

import java.util.List;

public interface IGenericDao {

	Object merge(Object object);

	void remover(Object object);

	Object find(Class<?> classs, Object primarikey);

	public List<?> list(Class<?> classs);

}
