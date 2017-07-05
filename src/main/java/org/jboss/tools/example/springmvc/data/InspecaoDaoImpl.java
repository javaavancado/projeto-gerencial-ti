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

import org.jboss.tools.example.springmvc.model.rd.Inspecao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("inspecaoDAO")
@Transactional
@EnableTransactionManagement
public class InspecaoDaoImpl extends GenericDAO<Inspecao> implements InspecaoDAO {

	private static final long serialVersionUID = 582521384015595582L;

	@Override
	public Inspecao merge(Inspecao inspecao) {
		return super.em.merge(inspecao);
	}

	@Override
	public Number[] dadoGraficoPorProduto(Long codigoProduto) {
		StringBuilder sql = new StringBuilder();
		sql.append("select coalesce(sum(aprovado), 0) as aprovado, ");
		sql.append("coalesce(sum(liberadocondicional), 0) as liberadocondicional, ");
		sql.append("coalesce(sum(naoinspecionado), 0) as naoinspecionado, ");
		sql.append("coalesce(sum(reprovado), 0) as reprovado ");
		sql.append(" from   inspecao where  produto_id in (");
		sql.append(codigoProduto);
		sql.append(");");

		Object[] resultado = (Object[]) super.em.createNativeQuery(sql.toString()).getSingleResult();
		if (resultado != null && resultado.length > 0) {
			Number[] numbers = new Number[resultado.length];
			numbers[0] = Integer.valueOf(resultado[0].toString());
			numbers[1] = Integer.valueOf(resultado[1].toString());
			numbers[2] = Integer.valueOf(resultado[3].toString());
			numbers[3] = Integer.valueOf(resultado[3].toString());
			return numbers;
		}
		return null;

	}

}
