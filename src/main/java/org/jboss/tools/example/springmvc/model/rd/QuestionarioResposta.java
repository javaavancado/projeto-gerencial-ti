package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the questionario_resposta database table.
 * 
 */
@Entity
@Table(name="questionario_resposta")
@NamedQuery(name="QuestionarioResposta.findAll", query="SELECT q FROM QuestionarioResposta q")
public class QuestionarioResposta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comentario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column(name="email_respondente")
	private String emailRespondente;

	@Column(name="pergunta_fk")
	private int perguntaFk;

	private String resposta;

	public QuestionarioResposta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getEmailRespondente() {
		return this.emailRespondente;
	}

	public void setEmailRespondente(String emailRespondente) {
		this.emailRespondente = emailRespondente;
	}

	public int getPerguntaFk() {
		return this.perguntaFk;
	}

	public void setPerguntaFk(int perguntaFk) {
		this.perguntaFk = perguntaFk;
	}

	public String getResposta() {
		return this.resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

}