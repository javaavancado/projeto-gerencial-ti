package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the indicador database table.
 * 
 */
@Entity
@NamedQuery(name="Indicador.findAll", query="SELECT i FROM Indicador i")
public class Indicador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private String nome;

	private String objetivo;

	@Column(name="processo_fk")
	private int processoFk;

	@Column(name="tarefa_fk")
	private int tarefaFk;

	public Indicador() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getProcessoFk() {
		return this.processoFk;
	}

	public void setProcessoFk(int processoFk) {
		this.processoFk = processoFk;
	}

	public int getTarefaFk() {
		return this.tarefaFk;
	}

	public void setTarefaFk(int tarefaFk) {
		this.tarefaFk = tarefaFk;
	}

}