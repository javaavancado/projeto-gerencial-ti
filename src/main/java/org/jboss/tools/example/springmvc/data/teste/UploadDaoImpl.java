/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.tools.example.springmvc.data.teste;

import java.util.List;

import org.jboss.tools.example.springmvc.model.teste.ArquivoUpload;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("uploadDao")
@Transactional
@EnableTransactionManagement
public class UploadDaoImpl extends GenericDAOTest<ArquivoUpload> implements UploadDao {

	@Override
	public void salvar(ArquivoUpload arquivoUpload) {
		super.em.persist(arquivoUpload);
	}

	@Override
	public List<ArquivoUpload> lista() {
		return super.em.createQuery("from ArquivoUpload").getResultList();
	}

	@Override
	public ArquivoUpload busca(Long id) {
		return super.em.find(ArquivoUpload.class, id);
	}

}
