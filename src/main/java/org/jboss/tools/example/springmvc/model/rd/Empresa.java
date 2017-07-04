package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO) 
	private int id;

	private String nome;

	@Column(name="`ramo de atividade`")
	private String ramo_de_atividade;

	private String responsavel;

	private String status;

	@Column(name="tipo_fiscal")
	private String tipoFiscal;

	public Empresa() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRamo_de_atividade() {
		return this.ramo_de_atividade;
	}

	public void setRamo_de_atividade(String ramo_de_atividade) {
		this.ramo_de_atividade = ramo_de_atividade;
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

	public String getTipoFiscal() {
		return this.tipoFiscal;
	}

	public void setTipoFiscal(String tipoFiscal) {
		this.tipoFiscal = tipoFiscal;
	}

}