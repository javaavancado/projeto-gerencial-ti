package org.jboss.tools.example.jsf.managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.jboss.tools.example.projeto.listener.CarregamentoLazyListForObject;
import org.jboss.tools.example.springmvc.data.EmpresaDAO;
import org.jboss.tools.example.springmvc.data.IGenericDao;
import org.jboss.tools.example.springmvc.data.InspecaoDAO;
import org.jboss.tools.example.springmvc.model.rd.Inspecao;
import org.jboss.tools.example.springmvc.model.rd.Produto;
import org.jboss.tools.example.springmvc.relatorio.ReportUtil;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ViewScoped
@ManagedBean(name = "inspecaoManageBean")
public class InspecaoManageBean {

	@ManagedProperty(name = "reportUtil", value = "#{reportUtil}")
	private ReportUtil reportUtil;

	private BarChartModel barModel = new BarChartModel();

	private Inspecao inspecao = new Inspecao();

	private Produto produtoSelecionadoGrafico = new Produto();

	private CarregamentoLazyListForObject<Inspecao> list = new CarregamentoLazyListForObject<Inspecao>();

	@ManagedProperty(name = "inspecaoDAO", value = "#{inspecaoDAO}")
	private InspecaoDAO inspecaoDAO;

	@ManagedProperty(name = "empresaDAO", value = "#{empresaDAO}")
	private EmpresaDAO empresaDAO;

	@ManagedProperty(name = "iGenericDao", value = "#{iGenericDao}")
	private IGenericDao iGenericDao;

	private String descricaoPesquisa = "";

	public String salvar() {
		inspecao = inspecaoDAO.merge(inspecao);
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
		return "";
	}

	public List<SelectItem> listaComboBox() {
		return empresaDAO.listaComboBox();
	}

	@PostConstruct
	public String novo() {
		inspecao = new Inspecao();
		iniciarGrafico();
		list.clean();
		return "/rd/inspecao.jsf";
	}

	public void excluir() {
		String unidadeProdutoId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("inspecaoId");
		inspecaoDAO.removeInspecao(Long.parseLong(unidadeProdutoId));
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
	}

	private void iniciarGrafico() {
		ChartSeries dadoGrafico = new ChartSeries();
		dadoGrafico.setLabel("Materiais");
		dadoGrafico.set("Aprovado", 0);
		dadoGrafico.set("Reprovado", 0);
		dadoGrafico.set("Liberado Condicional", 0);
		dadoGrafico.set("Não Inspecionado", 0);
		barModel.addSeries(dadoGrafico);

	}

	public Inspecao getInspecao() {
		return inspecao;
	}

	public void setInspecao(Inspecao inspecao) {
		this.inspecao = inspecao;
	}

	public InspecaoDAO getInspecaoDAO() {
		return inspecaoDAO;
	}

	public void setInspecaoDAO(InspecaoDAO inspecaoDAO) {
		this.inspecaoDAO = inspecaoDAO;
	}

	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	public EmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}

	public void setProdutoSelecionadoGrafico(Produto produtoSelecionadoGrafico) {
		this.produtoSelecionadoGrafico = produtoSelecionadoGrafico;
	}

	public Produto getProdutoSelecionadoGrafico() {
		return produtoSelecionadoGrafico;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void processarGraficoProduto() {
		if (produtoSelecionadoGrafico != null && produtoSelecionadoGrafico.getId() != null) {
			Number[] dados = inspecaoDAO.dadoGraficoPorProduto(produtoSelecionadoGrafico.getId());
			if (dados != null) {
				barModel = new BarChartModel();
				ChartSeries dadoGrafico = new ChartSeries();
				dadoGrafico.setLabel("Materiais");
				dadoGrafico.set("Aprovado", dados[0].doubleValue());
				dadoGrafico.set("Reprovado", dados[3].doubleValue());
				dadoGrafico.set("Liberado Condicional", dados[1].doubleValue());
				dadoGrafico.set("Não Inspecionado", dados[2].doubleValue());
				barModel.addSeries(dadoGrafico);
			} else {
				iniciarGrafico();
			}
		}
	}

	public StreamedContent getFileRelatorio() throws Exception {
		reportUtil.setNomeRelatorioJasper("inspecao");
		reportUtil.setListDataBeanCollectionReport(inspecaoDAO.lista());
		return reportUtil.getArquivoReportStreamedContent();
	}

	public StreamedContent getFileRelatorioUnico() throws Exception {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		String inspecaoId = params.get("inspecaoId");
		List<Inspecao> inspecaos = new ArrayList<Inspecao>();
		inspecaos.add(inspecaoDAO.buscar(Long.parseLong(inspecaoId)));

		reportUtil.setNomeRelatorioJasper("inspecao");
		reportUtil.setListDataBeanCollectionReport(inspecaos);
		return reportUtil.getArquivoReportStreamedContent();
	}

	public CarregamentoLazyListForObject<Inspecao> getList() {
		return list;
	}

	public void setReportUtil(ReportUtil reportUtil) {
		this.reportUtil = reportUtil;
	}

	public ReportUtil getReportUtil() {
		return reportUtil;
	}

	public void pesquisar() {
		list.clean();
		list.setQuery(inspecaoDAO.queryInspecaoDescricao(descricaoPesquisa));
	}

	public IGenericDao getiGenericDao() {
		return iGenericDao;
	}

	public void setiGenericDao(IGenericDao iGenericDao) {
		this.iGenericDao = iGenericDao;
	}

	public String getDescricaoPesquisa() {
		return descricaoPesquisa;
	}

	public void setDescricaoPesquisa(String descricaoPesquisa) {
		this.descricaoPesquisa = descricaoPesquisa;
	}

}
