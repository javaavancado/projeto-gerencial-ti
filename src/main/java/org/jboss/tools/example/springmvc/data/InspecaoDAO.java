package org.jboss.tools.example.springmvc.data;

import java.io.Serializable;
import java.util.List;

import org.jboss.tools.example.springmvc.model.rd.Inspecao;

public interface InspecaoDAO extends Serializable{

	public Inspecao merge (Inspecao inspecao);
	
	public Inspecao buscar(Long codInspecao);

	public Number[] dadoGraficoPorProduto(Long id);
	
	public List<Inspecao> lista();

	public void removeInspecao(Long id);

	public String queryInspecaoDescricao(String descricaoPesquisa);
}
