package org.jboss.tools.example.springmvc.data;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.tools.example.springmvc.model.rd.Empresa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("empresaDAO")
@Transactional
@EnableTransactionManagement
public class EmpresaDaoImpl extends GenericDAO<Empresa> implements EmpresaDAO {

	private static final long serialVersionUID = 582521384015595582L;

	@Override
	public Empresa merge(Empresa empresa) {
		return super.em.merge(empresa);
	}

	@Override
	public Empresa buscaPorId(Long codEmpresa) {
		return super.em.find(Empresa.class, codEmpresa);
	}

	@Override
	public List<Empresa> lista() {
		return super.em.createQuery(" from Empresa ").getResultList();
	}

	@Override
	public List<SelectItem> listaComboBox() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();

		List<Empresa> lista = this.lista();

		for (Empresa empresa : lista) {
			retorno.add(new SelectItem(empresa, empresa.getNome()));
		}

		return retorno;
	}

	

}
