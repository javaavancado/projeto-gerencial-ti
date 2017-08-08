package org.jboss.tools.example.springmvc.data.teste;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
abstract class GenericDAOTest<T> {

	@Autowired
	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected DriverManagerDataSource dataSource;

	@Autowired
	protected LocalContainerEntityManagerFactoryBean myEmf;

	@Autowired
	protected org.springframework.orm.jpa.JpaTransactionManager transactionManager;

}
