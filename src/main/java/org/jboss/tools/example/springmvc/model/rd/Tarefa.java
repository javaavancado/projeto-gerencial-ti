package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tarefa database table.
 * 
 */
@Entity
@NamedQuery(name="Tarefa.findAll", query="SELECT t FROM Tarefa t")
public class Tarefa implements Serializable {
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

	@Column(name="hora_final")
	private Time horaFinal;

	@Column(name="hora_inicial")
	private Time horaInicial;

	private String nome;

	@Column(name="pagina_fk")
	private int paginaFk;

	private String percentual;

	private String status;

	public Tarefa() {
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

	public Time getHoraFinal() {
		return this.horaFinal;
	}

	public void setHoraFinal(Time horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Time getHoraInicial() {
		return this.horaInicial;
	}

	public void setHoraInicial(Time horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPaginaFk() {
		return this.paginaFk;
	}

	public void setPaginaFk(int paginaFk) {
		this.paginaFk = paginaFk;
	}

	public String getPercentual() {
		return this.percentual;
	}

	public void setPercentual(String percentual) {
		this.percentual = percentual;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}