package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ForeignKey;
import org.jboss.tools.example.springmvc.enums.TipoProduto;

@Entity
@NamedQuery(name = "Produto.findAll", query = "SELECT e FROM Produto e")
@SequenceGenerator(initialValue = 1, allocationSize = 1, sequenceName = "produto_sequence", name = "produto_sequence")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sequence")
	private Long id;

	private String nome_produto;

	private Long criterio_inspecao = 0L;

	private String descricao;

	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="unidade_id")
	@JoinColumn(referencedColumnName = "id", name = "unidade_id", nullable = false)
	private UnidadeProduto unidadeProduto = new UnidadeProduto();

	public void setUnidadeProduto(UnidadeProduto unidadeProduto) {
		this.unidadeProduto = unidadeProduto;
	}

	public UnidadeProduto getUnidadeProduto() {
		return unidadeProduto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public Long getCriterio_inspecao() {
		return criterio_inspecao;
	}

	public void setCriterio_inspecao(Long criterio_inspecao) {
		this.criterio_inspecao = criterio_inspecao;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + "]";
	}

}