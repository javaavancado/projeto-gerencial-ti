package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import java.util.Date;

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
@NamedQuery(name = "Inspecao.findAll", query = "SELECT e FROM Inspecao e")
@SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = "inspecao_sequence", name = "inspecao_sequence")
public class Inspecao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inspecao_sequence")
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "empresa_id", nullable = false)
	private Empresa empresa = new Empresa();

	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name = "produto_id", nullable = false)
	private Produto produto = new Produto();

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInspecao;

	private String local;

	private String descricao;

	private String responsavel_email;

	private Integer reprovado = 0;

	private Integer aprovado = 0;

	private Integer liberadoCondicional = 0;

	private Integer naoInspecionado = 0;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResponsavel_email() {
		return responsavel_email;
	}

	public void setResponsavel_email(String responsavel_email) {
		this.responsavel_email = responsavel_email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Date getDataInspecao() {
		return dataInspecao;
	}

	public void setDataInspecao(Date dataInspecao) {
		this.dataInspecao = dataInspecao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Integer getReprovado() {
		return reprovado;
	}

	public void setReprovado(Integer reprovado) {
		this.reprovado = reprovado;
	}

	public Integer getAprovado() {
		return aprovado;
	}

	public void setAprovado(Integer aprovado) {
		this.aprovado = aprovado;
	}

	public Integer getLiberadoCondicional() {
		return liberadoCondicional;
	}

	public void setLiberadoCondicional(Integer liberadoCondicional) {
		this.liberadoCondicional = liberadoCondicional;
	}

	public Integer getNaoInspecionado() {
		return naoInspecionado;
	}

	public void setNaoInspecionado(Integer naoInspecionado) {
		this.naoInspecionado = naoInspecionado;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Produto getProduto() {
		return produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inspecao other = (Inspecao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inspecao [empresa=" + empresa + ", produto=" + produto + "]";
	}

}