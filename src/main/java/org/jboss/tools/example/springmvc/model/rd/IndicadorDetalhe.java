package org.jboss.tools.example.springmvc.model.rd;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the indicador_detalhe database table.
 * 
 */
@Entity
@Table(name="indicador_detalhe")
@NamedQuery(name="IndicadorDetalhe.findAll", query="SELECT i FROM IndicadorDetalhe i")
public class IndicadorDetalhe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Column(name="indicador_fk")
	private int indicadorFk;

	private BigDecimal indice;

	@Column(name="unidade_fk")
	private int unidadeFk;

	@Column(name="valor_encontrado")
	private BigDecimal valorEncontrado;

	@Column(name="valor_estabelecido")
	private BigDecimal valorEstabelecido;

	public IndicadorDetalhe() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getIndicadorFk() {
		return this.indicadorFk;
	}

	public void setIndicadorFk(int indicadorFk) {
		this.indicadorFk = indicadorFk;
	}

	public BigDecimal getIndice() {
		return this.indice;
	}

	public void setIndice(BigDecimal indice) {
		this.indice = indice;
	}

	public int getUnidadeFk() {
		return this.unidadeFk;
	}

	public void setUnidadeFk(int unidadeFk) {
		this.unidadeFk = unidadeFk;
	}

	public BigDecimal getValorEncontrado() {
		return this.valorEncontrado;
	}

	public void setValorEncontrado(BigDecimal valorEncontrado) {
		this.valorEncontrado = valorEncontrado;
	}

	public BigDecimal getValorEstabelecido() {
		return this.valorEstabelecido;
	}

	public void setValorEstabelecido(BigDecimal valorEstabelecido) {
		this.valorEstabelecido = valorEstabelecido;
	}

}