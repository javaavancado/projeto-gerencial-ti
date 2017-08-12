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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "NaoConformidade.findAll", query = "SELECT i from NaoConformidade i")
@SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = "ncf_sequence", name = "ncf_sequence")
public class NaoConformidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ncf_sequence")
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "inspecao_id", nullable = false)
	@org.hibernate.annotations.ForeignKey(name = "inspecao_id")
	private Inspecao inspecao = new Inspecao();

	@Column(name = "descricao")
	private String descricao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_ncf")
	private Date dataNcf = Calendar.getInstance().getTime();

	@Column(name = "disposicao")
	private String disposicao;

	@Column(name = "responsavel_email")
	private String responsavelEmail;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_eficacia")
	private Date data_eficacia = Calendar.getInstance().getTime();

	@Column(name = "reponsavel_ver")
	private Long reponsavel_ver = 0L;

	@Column(name = "verificacao")
	private Boolean verificacao;

	@Column(name = "comentario")
	private String comentario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataNcf() {
		return dataNcf;
	}

	public void setDataNcf(Date dataNcf) {
		this.dataNcf = dataNcf;
	}

	public String getDisposicao() {
		return disposicao;
	}

	public void setDisposicao(String disposicao) {
		this.disposicao = disposicao;
	}

	public String getResponsavelEmail() {
		return responsavelEmail;
	}

	public void setResponsavelEmail(String responsavelEmail) {
		this.responsavelEmail = responsavelEmail;
	}

	public Date getData_eficacia() {
		return data_eficacia;
	}

	public void setData_eficacia(Date data_eficacia) {
		this.data_eficacia = data_eficacia;
	}

	public Long getReponsavel_ver() {
		return reponsavel_ver;
	}

	public void setReponsavel_ver(Long reponsavel_ver) {
		this.reponsavel_ver = reponsavel_ver;
	}

	public Boolean getVerificacao() {
		return verificacao;
	}

	public void setVerificacao(Boolean verificacao) {
		this.verificacao = verificacao;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void atualizarVizualizacao() {
		this.reponsavel_ver++;

	}

}