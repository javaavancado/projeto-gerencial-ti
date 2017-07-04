package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the indicador_unidade database table.
 * 
 */
@Entity
@Table(name="indicador_unidade")
@NamedQuery(name="IndicadorUnidade.findAll", query="SELECT i FROM IndicadorUnidade i")
public class IndicadorUnidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String unidade;

	public IndicadorUnidade() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

}