package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the anexo database table.
 * 
 */
@Entity
@NamedQuery(name="Anexo.findAll", query="SELECT a FROM Anexo a")
public class Anexo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="an_descricao")
	private String anDescricao;

	@Lob
	private byte[] anexo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="anexo_data")
	private Date anexoData;

	@Column(name="anexo_nome")
	private String anexoNome;

	@Column(name="tarefa_fk")
	private int tarefaFk;

	public Anexo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnDescricao() {
		return this.anDescricao;
	}

	public void setAnDescricao(String anDescricao) {
		this.anDescricao = anDescricao;
	}

	public byte[] getAnexo() {
		return this.anexo;
	}

	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}

	public Date getAnexoData() {
		return this.anexoData;
	}

	public void setAnexoData(Date anexoData) {
		this.anexoData = anexoData;
	}

	public String getAnexoNome() {
		return this.anexoNome;
	}

	public void setAnexoNome(String anexoNome) {
		this.anexoNome = anexoNome;
	}

	public int getTarefaFk() {
		return this.tarefaFk;
	}

	public void setTarefaFk(int tarefaFk) {
		this.tarefaFk = tarefaFk;
	}

}