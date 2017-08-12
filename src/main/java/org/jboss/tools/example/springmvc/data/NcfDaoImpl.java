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
package org.jboss.tools.example.springmvc.data;

import java.util.List;

import org.jboss.tools.example.springmvc.model.rd.NaoConformidade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("ncfDAO")
@Transactional
@EnableTransactionManagement
public class NcfDaoImpl extends GenericDAO<NaoConformidade> implements NcfDAO {

	private static final long serialVersionUID = 1L;

	@Override
	public NaoConformidade merge(NaoConformidade ncf) {
		return super.em.merge(ncf);
	}

	@Override
	public NaoConformidade buscar(Long codNcf) {
		return (NaoConformidade) super.em.createQuery("from NaoConformidade where id = " + codNcf).getSingleResult();
	}

	@Override
	public List<NaoConformidade> lista() {
		return super.em.createQuery("from NaoConformidade ").getResultList();
	}

	@Override
	public String queryNcfDescricao(String descricaoPesquisa) {
		if (descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty()) {
			return " from NaoConformidade where inspecao.id = " + descricaoPesquisa + " ";
		}
		return "";
	}

	@Override
	public void removeNcf(Long id) {
		super.em.createQuery("delete from  NaoConformidade where id = " + id).executeUpdate();

	}

	@Override
	public void removeNcfs(Long idInspecao) {
		super.em.createQuery("delete from  NaoConformidade where inspecao.id = " + idInspecao).executeUpdate();

	}

	@Override
	public List<NaoConformidade> carregaNcfs(Long idInspecao) {
		return super.em.createQuery("from NaoConformidade where inspecao.id = " + idInspecao).getResultList();
	}

	@Override
	public void atualizarVisualizacao(Long idNcf) {
		super.em.createNativeQuery("update naoconformidade set reponsavel_ver = reponsavel_ver + 1 where id = " + idNcf ).executeUpdate();
		super.em.flush();
	}

}
