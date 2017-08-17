package org.jboss.tools.example.jsf.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jboss.tools.example.projeto.listener.CarregamentoLazyListForObject;
import org.jboss.tools.example.springmvc.data.IGenericDao;
import org.jboss.tools.example.springmvc.data.ProdutoDAO;
import org.jboss.tools.example.springmvc.model.rd.UnidadeProduto;
import org.jboss.tools.example.springmvc.relatorio.ReportUtil;
import org.primefaces.model.StreamedContent;

@ViewScoped
@ManagedBean(name = "unidadeProdutoManageBean")
public class UnidadeProdutoManageBean {

	private UnidadeProduto unidadeProduto = new UnidadeProduto();

	private CarregamentoLazyListForObject<UnidadeProduto> list = new CarregamentoLazyListForObject<UnidadeProduto>();

	@ManagedProperty(name = "iGenericDao", value = "#{iGenericDao}")
	private IGenericDao iGenericDao;

	@ManagedProperty(name = "reportUtil", value = "#{reportUtil}")
	private ReportUtil reportUtil;

	@ManagedProperty(name = "produtoDAO", value = "#{produtoDAO}")
	private ProdutoDAO produtoDAO;

	private String descricaoPesquisa = "";

	public String salvar() {
		iGenericDao.merge(unidadeProduto);
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
		return "";
	}

	public void excluir() {
		String unidadeProdutoId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("unidadeProdutoId");
		produtoDAO.removeUnidade(Long.parseLong(unidadeProdutoId));
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
	}

	public String novo() {
		unidadeProduto = new UnidadeProduto();
		list.clean();
		return "/rd/unidadeproduto.jsf";
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
		reportUtil.setListDataBeanCollectionReport(iGenericDao.list(UnidadeProduto.class));
		reportUtil.setNomeRelatorioJasper("unidadeProduto");
		return reportUtil.getArquivoReportStreamedContent();
	}

	public CarregamentoLazyListForObject<UnidadeProduto> getList() {
		return list;
	}

	public void pesquisar() {
		list.clean();
		list.setQuery(produtoDAO.queryUnidadeDescricao(descricaoPesquisa));
	}

	public void setDescricaoPesquisa(String descricaoPesquisa) {
		this.descricaoPesquisa = descricaoPesquisa;
	}

	public String getDescricaoPesquisa() {
		return descricaoPesquisa;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
	}

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

}
