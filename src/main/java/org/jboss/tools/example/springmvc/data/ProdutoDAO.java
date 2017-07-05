package org.jboss.tools.example.springmvc.data;

import java.io.Serializable;
import java.util.List;

import org.jboss.tools.example.springmvc.model.rd.Produto;

public interface ProdutoDAO extends Serializable{

	public Produto merge (Produto produto);

	public Produto buscaPorId(Long codigoProduto);
	
	public List<Produto> listaProduto();
}
