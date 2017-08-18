package org.jboss.tools.example.springmvc.data.teste;

import java.util.List;

import org.jboss.tools.example.springmvc.model.teste.ArquivoUploadAula;

public interface UploadArquivoAulaInterface {
	
	void salvar(ArquivoUploadAula arquivoUploadAula);

	List<ArquivoUploadAula> lista();

	ArquivoUploadAula buscar(String fileDownloadId);
	
}
