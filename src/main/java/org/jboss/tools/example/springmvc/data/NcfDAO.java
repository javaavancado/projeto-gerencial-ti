package org.jboss.tools.example.springmvc.data;

import java.io.Serializable;
import java.util.List;

import org.jboss.tools.example.springmvc.model.rd.NaoConformidade;

public interface NcfDAO extends Serializable{

	public NaoConformidade merge (NaoConformidade ncf);
	
	public NaoConformidade buscar(Long codNcf);

	public List<NaoConformidade> lista();

	public String queryNcfDescricao(String descricaoPesquisa);

	public void removeNcf(Long id);
	
	public void removeNcfs(Long idInspecao);

	public List<NaoConformidade> carregaNcfs(Long idInspecao);

	public void atualizarVisualizacao(Long idNcf);	
}
