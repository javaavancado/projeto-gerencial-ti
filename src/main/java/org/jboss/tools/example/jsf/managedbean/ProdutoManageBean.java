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
import org.jboss.tools.example.springmvc.enums.TipoProduto;
import org.jboss.tools.example.springmvc.model.rd.Produto;
import org.jboss.tools.example.springmvc.model.rd.UnidadeProduto;
import org.jboss.tools.example.springmvc.relatorio.ReportUtil;
import org.primefaces.model.StreamedContent;

@ViewScoped
@ManagedBean(name = "produtoManageBean")
public class ProdutoManageBean {

	private Produto produto = new Produto();

	@ManagedProperty(name = "produtoDAO", value = "#{produtoDAO}")
	private ProdutoDAO produtoDAO;

	@ManagedProperty(name = "iGenericDao", value = "#{iGenericDao}")
	private IGenericDao iGenericDao;

	@ManagedProperty(name = "reportUtil", value = "#{reportUtil}")
	private ReportUtil reportUtil;

	private CarregamentoLazyListForObject<Produto> list = new CarregamentoLazyListForObject<Produto>();

	private String descricaoPesquisa = "";

	public String salvar() {
		produto = produtoDAO.merge(produto);
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
		return "";
	}

	public String novo() {
		produto = new Produto();
		list.clean();
		return "/rd/produto.jsf";
	}

	public void excluir() {
		String produtoId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("produtoId");
		produtoDAO.removeProduto(Long.parseLong(produtoId));
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
	}

	public List<SelectItem> listaTipoProduto() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();
		for (TipoProduto produto : TipoProduto.values()) {
			retorno.add(new SelectItem(produto, produto.getDescricao()));
		}
		return retorno;
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

	public List<SelectItem> listaProduto() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();
		for (Produto produto : produtoDAO.listaProduto()) {
			retorno.add(new SelectItem(produto, produto.getDescricao()));
		}
		return retorno;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoDAO getProdutoDAO() {
		return produtoDAO;
	}

	public void setProdutoDAO(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
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

	public StreamedContent getFileRelatorio() throws Exception {
		reportUtil.setNomeRelatorioJasper("produto");
		return reportUtil.getArquivoReportStreamedContentConnection();
	}

	public void pesquisar() {
		list.clean();
		list.setQuery(produtoDAO.queryProdutoDescricao(descricaoPesquisa));
	}

	public String getDescricaoPesquisa() {
		return descricaoPesquisa;
	}

	public void setDescricaoPesquisa(String descricaoPesquisa) {
		this.descricaoPesquisa = descricaoPesquisa;
	}

	public CarregamentoLazyListForObject<Produto> getList() {
		return list;
	}

}
