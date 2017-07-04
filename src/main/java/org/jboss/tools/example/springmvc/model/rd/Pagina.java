package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pagina database table.
 * 
 */
@Entity
@NamedQuery(name="Pagina.findAll", query="SELECT p FROM Pagina p")
public class Pagina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descricao;

	private String pagina;

	private String pmi;

	@Column(name="projeto_id")
	private int projetoId;

	private String responsavel;

	private String status;

	public Pagina() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPagina() {
		return this.pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getPmi() {
		return this.pmi;
	}

	public void setPmi(String pmi) {
		this.pmi = pmi;
	}

	public int getProjetoId() {
		return this.projetoId;
	}

	public void setProjetoId(int projetoId) {
		this.projetoId = projetoId;
	}

	public String getResponsavel() {
		return this.responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}