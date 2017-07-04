package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questionario_categoria database table.
 * 
 */
@Entity
@Table(name="questionario_categoria")
@NamedQuery(name="QuestionarioCategoria.findAll", query="SELECT q FROM QuestionarioCategoria q")
public class QuestionarioCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String categoria;

	public QuestionarioCategoria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}