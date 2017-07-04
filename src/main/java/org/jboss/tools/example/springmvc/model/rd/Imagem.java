package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the imagem database table.
 * 
 */
@Entity
@NamedQuery(name="Imagem.findAll", query="SELECT i FROM Imagem i")
public class Imagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private byte[] imagem;

	@Temporal(TemporalType.DATE)
	@Column(name="imagem_data")
	private Date imagemData;

	@Column(name="imagem_descricao")
	private String imagemDescricao;

	@Column(name="imagem_nome")
	private String imagemNome;

	@Column(name="tarefa_fk")
	private int tarefaFk;

	public Imagem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImagem() {
		return this.imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Date getImagemData() {
		return this.imagemData;
	}

	public void setImagemData(Date imagemData) {
		this.imagemData = imagemData;
	}

	public String getImagemDescricao() {
		return this.imagemDescricao;
	}

	public void setImagemDescricao(String imagemDescricao) {
		this.imagemDescricao = imagemDescricao;
	}

	public String getImagemNome() {
		return this.imagemNome;
	}

	public void setImagemNome(String imagemNome) {
		this.imagemNome = imagemNome;
	}

	public int getTarefaFk() {
		return this.tarefaFk;
	}

	public void setTarefaFk(int tarefaFk) {
		this.tarefaFk = tarefaFk;
	}

}