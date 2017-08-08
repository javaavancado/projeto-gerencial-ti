package org.jboss.tools.example.springmvc.data.teste;

import org.jboss.tools.example.springmvc.model.teste.ArquivoUploadAula;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("uploadArquivoAula")
@Transactional
@EnableTransactionManagement
public class UploadArquivoAulaDaoImpl extends GenericDAOTest<ArquivoUploadAula> implements UploadArquivoAulaInterface {

	@Override
	public void salvar(ArquivoUploadAula arquivoUploadAula) {
		super.em.persist(arquivoUploadAula);

	}

}
