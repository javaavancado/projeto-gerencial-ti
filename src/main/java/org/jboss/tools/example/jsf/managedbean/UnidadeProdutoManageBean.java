package org.jboss.tools.example.jsf.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.jboss.tools.example.springmvc.data.IGenericDao;
import org.jboss.tools.example.springmvc.model.rd.UnidadeProduto;
import org.jboss.tools.example.springmvc.relatorio.ReportUtil;
import org.primefaces.model.StreamedContent;

@RequestScoped
@ManagedBean(name = "unidadeProdutoManageBean")
public class UnidadeProdutoManageBean {

	private UnidadeProduto unidadeProduto = new UnidadeProduto();

	@ManagedProperty(name = "iGenericDao", value = "#{iGenericDao}")
	private IGenericDao iGenericDao;

	@ManagedProperty(name = "reportUtil", value = "#{reportUtil}")
	private ReportUtil reportUtil;

	public String salvar() {
		unidadeProduto = (UnidadeProduto) iGenericDao.merge(unidadeProduto);
		ManagedBeanViewUtil.sucesso();
		novo();
		return "";
	}

	public void novo() {
		unidadeProduto = new UnidadeProduto();
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> listaUnidadeProduto() {

		List<UnidadeProduto> list = (List<UnidadeProduto>) iGenericDao.list(UnidadeProduto.class);

		List<SelectItem> retorno = new ArrayList<SelectItem>();
		for (UnidadeProduto unidade : list) {
			retorno.add(new SelectItem(unidade, unidade.getDescricao()));
		}
		return retorno;
	}

	public void setReportUtil(ReportUtil reportUtil) {
		this.reportUtil = reportUtil;
	}

	public ReportUtil getReportUtil() {
		return reportUtil;
	}

	public void setiGenericDao(IGenericDao iGenericDao) {
		this.iGenericDao = iGenericDao;
	}

	public IGenericDao getiGenericDao() {
		return iGenericDao;
	}

	public UnidadeProduto getUnidadeProduto() {
		return unidadeProduto;
	}

	public void setUnidadeProduto(UnidadeProduto unidadeProduto) {
		this.unidadeProduto = unidadeProduto;
	}

	public StreamedContent getFileRelatorio() throws Exception {
		reportUtil.setNomeRelatorioJasper("unidadeProduto");
		return reportUtil.getArquivoReportStreamedContentConnection();
	}

}
