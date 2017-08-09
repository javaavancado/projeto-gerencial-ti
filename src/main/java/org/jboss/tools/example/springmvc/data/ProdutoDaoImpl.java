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

import org.jboss.tools.example.springmvc.model.rd.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("produtoDAO")
@Transactional
@EnableTransactionManagement
public class ProdutoDaoImpl extends GenericDAO<Produto> implements ProdutoDAO {

	private static final long serialVersionUID = 582521384015595582L;

	@Override
	public Produto merge(Produto produto) {
		return super.em.merge(produto);
	}

	@Override
	public Produto buscaPorId(Long codigoProduto) {
		return super.em.find(Produto.class, codigoProduto);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listaProduto() {
		return super.em.createQuery("from Produto").getResultList();
	}

	@Override
	public String queryUnidadeDescricao(String descricao) {
		if (descricao != null && !descricao.trim().isEmpty()) {
			return " from UnidadeProduto where descricao like'%" + descricao + "%'";
		}
		return " from UnidadeProduto ";
	}

	@Override
	public void removeUnidade(Long id) {
		super.em.createQuery("delete from  UnidadeProduto where id = " + id).executeUpdate();
	}

}
