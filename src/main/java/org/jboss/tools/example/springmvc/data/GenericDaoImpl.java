package org.jboss.tools.example.springmvc.data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("iGenericDao")
@Transactional
@EnableTransactionManagement
public class GenericDaoImpl extends GenericDAO<Object> implements IGenericDao {

	@Override
	public Object merge(Object object) {
		return super.em.merge(object);
	}

	@Override
	public void remover(Object object) {
		super.em.remove(object);
	}

	@Override
	public Object find(Class<?> classs, Object primarikey) {
		return super.em.find(classs, primarikey);
	}

	public List<?> list(Class<?> classs) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<?> criteria = builder.createQuery(classs);
		criteria.from(classs);
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public int total(String query) {
		return Integer.valueOf(super.em.createQuery("select count(*) " 
				+ query).getSingleResult().toString());
	}

	@Override
	public List<?> finLazy(String query, int first, int pageSize) {
		return (List<?>) super.em.createQuery(query)
				.setFirstResult(first)
				.setMaxResults(pageSize).getResultList();

	}

}
