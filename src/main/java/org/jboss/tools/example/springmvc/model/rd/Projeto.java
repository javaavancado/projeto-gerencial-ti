package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the projeto database table.
 * 
 */
@Entity
@NamedQuery(name="Projeto.findAll", query="SELECT p FROM Projeto p")
public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_final")
	private Date dataFinal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_inicial")
	private Date dataInicial;

	private String descricao;

	@Column(name="empresa_fk")
	private int empresaFk;

	private String nome;

	@Column(name="`ramo de atividade`")
	private String ramo_de_atividade;

	private String responsavel;

	private String status;

	public Projeto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getDataInicial() {
		return this.dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getEmpresaFk() {
		return this.empresaFk;
	}

	public void setEmpresaFk(int empresaFk) {
		this.empresaFk = empresaFk;
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

}