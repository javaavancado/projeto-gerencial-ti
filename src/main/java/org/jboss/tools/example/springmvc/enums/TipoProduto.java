package org.jboss.tools.example.springmvc.enums;

public enum TipoProduto {

	MATERIA_PRIMA("Matéria prima"), PRODUTO_DE_PROCESSO("Produto de Processo"), PRODUTO_ACABADO(
			"Produto acabado"), PRODUTO_POS_ACABADO("Produto pós acabado");

	private String descricao;

	private TipoProduto(String descricao) {
		this.descricao = descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
