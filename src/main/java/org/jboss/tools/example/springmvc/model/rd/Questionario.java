package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the questionario database table.
 * 
 */
@Entity
@NamedQuery(name="Questionario.findAll", query="SELECT q FROM Questionario q")
public class Questionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="categoria_fk")
	private int categoriaFk;

	private String descricao;

	@Column(name="empresa_fk")
	private int empresaFk;

	private String nome;

	public Questionario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoriaFk() {
		return this.categoriaFk;
	}

	public void setCategoriaFk(int categoriaFk) {
		this.categoriaFk = categoriaFk;
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

}