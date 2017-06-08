package org.jboss.tools.example.springmvc.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GenericDAO<T> {

	@Autowired
	@PersistenceContext
	protected EntityManager em;

}
