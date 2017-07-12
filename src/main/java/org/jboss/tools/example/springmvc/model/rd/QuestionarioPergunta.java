package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the questionario_pergunta database table.
 * 
 */
@Entity
@Table(name="questionario_pergunta")
@NamedQuery(name="QuestionarioPergunta.findAll", query="SELECT q FROM QuestionarioPergunta q")
public class QuestionarioPergunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String a;

	private String b;

	private String c;

	private String d;

	private String f;

	private String g;

	@Column(name="nome_pergunta")
	private String nomePergunta;

	@Column(name="questionario_fk")
	private int questionarioFk;

	public QuestionarioPergunta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getA() {
		return this.a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return this.b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return this.d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getF() {
		return this.f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getG() {
		return this.g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public String getNomePergunta() {
		return this.nomePergunta;
	}

	public void setNomePergunta(String nomePergunta) {
		this.nomePergunta = nomePergunta;
	}

	public int getQuestionarioFk() {
		return this.questionarioFk;
	}

	public void setQuestionarioFk(int questionarioFk) {
		this.questionarioFk = questionarioFk;
	}

}