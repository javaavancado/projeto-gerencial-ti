package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the imagem database table.
 * 
 */
@Entity
@NamedQuery(name = "Imagem.findAll", query = "SELECT i FROM Imagem i")
@SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = "imagem_sequence", name = "imagem_sequence")
public class Imagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagem_sequence")
	private Long id;

	@Lob
	private byte[] imagem;

	@Temporal(TemporalType.DATE)
	@Column(name = "imagem_data")
	private Date imagemData = Calendar.getInstance().getTime();

	@Column(name = "imagem_descricao")
	private String imagemDescricao;

	@Column(columnDefinition = "text", length = 100000)
	private String imagemBase64;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "inspecao_id", nullable = false)
	@org.hibernate.annotations.ForeignKey(name = "inspecao_id")
	private Inspecao inspecao = new Inspecao();

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Date getImagemData() {
		return imagemData;
	}

	public void setImagemData(Date imagemData) {
		this.imagemData = imagemData;
	}

	public String getImagemDescricao() {
		return imagemDescricao;
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

	public String getImagemBase64() {
		return imagemBase64;
	}

	public void setImagemDescricao(String imagemDescricao) {
		this.imagemDescricao = imagemDescricao;
	}

}