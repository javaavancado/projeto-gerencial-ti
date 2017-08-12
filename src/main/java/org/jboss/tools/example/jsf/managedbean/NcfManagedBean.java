package org.jboss.tools.example.jsf.managedbean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.tools.example.projeto.listener.CarregamentoLazyListForObject;
import org.jboss.tools.example.springmvc.data.IGenericDao;
import org.jboss.tools.example.springmvc.data.InspecaoDAO;
import org.jboss.tools.example.springmvc.data.NcfDAO;
import org.jboss.tools.example.springmvc.model.rd.Inspecao;
import org.jboss.tools.example.springmvc.model.rd.NaoConformidade;
import org.jboss.tools.example.springmvc.relatorio.ReportUtil;
import org.primefaces.model.StreamedContent;

@SessionScoped
@ManagedBean(name = "ncfManagedBean")
public class NcfManagedBean {

	private NaoConformidade naoConformidade = new NaoConformidade();

	private CarregamentoLazyListForObject<NaoConformidade> list = new CarregamentoLazyListForObject<NaoConformidade>();

	@ManagedProperty(name = "iGenericDao", value = "#{iGenericDao}")
	private IGenericDao iGenericDao;

	@ManagedProperty(name = "ncfDAO", value = "#{ncfDAO}")
	private NcfDAO ncfDAO;

	@ManagedProperty(name = "reportUtil", value = "#{reportUtil}")
	private ReportUtil reportUtil;

	@ManagedProperty(name = "inspecaoDAO", value = "#{inspecaoDAO}")
	private InspecaoDAO inspecaoDAO;

	public String salvar() {
		naoConformidade = ncfDAO.merge(naoConformidade);
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
		return "";
	}

	public void novo() {
		list.clean();
		Inspecao inspecao = naoConformidade.getInspecao();
		naoConformidade = new NaoConformidade();
		naoConformidade.setInspecao(inspecao);
	}

	public void pesquisar() {
		list.clean();
		if (naoConformidade.getInspecao() != null)
			list.setQuery(ncfDAO.queryNcfDescricao(naoConformidade.getInspecao().getId().toString()));
	}

	public StreamedContent getFileRelatorio() throws Exception {
		reportUtil.setNomeRelatorioJasper("naoconformidade");
		return reportUtil.getArquivoReportStreamedContent();
	}

	public NaoConformidade getNaoConformidade() {
		return naoConformidade;
	}

	public void setNaoConformidade(NaoConformidade naoConformidade) {
		this.naoConformidade = naoConformidade;
	}

	public IGenericDao getiGenericDao() {
		return iGenericDao;
	}

	public void setiGenericDao(IGenericDao iGenericDao) {
		this.iGenericDao = iGenericDao;
	}

	public NcfDAO getNcfDAO() {
		return ncfDAO;
	}

	public void setNcfDAO(NcfDAO ncfDAO) {
		this.ncfDAO = ncfDAO;
	}

	public InspecaoDAO getInspecaoDAO() {
		return inspecaoDAO;
	}

	public void setInspecaoDAO(InspecaoDAO inspecaoDAO) {
		this.inspecaoDAO = inspecaoDAO;
	}

	public CarregamentoLazyListForObject<NaoConformidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<NaoConformidade> list) {
		this.list = list;
	}

	public ReportUtil getReportUtil() {
		return reportUtil;
	}

	public void setReportUtil(ReportUtil reportUtil) {
		this.reportUtil = reportUtil;
	}

	public void carregarEdicao() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String inspecaoId = params.get("inspecaoEdicao");
		naoConformidade = new NaoConformidade();
		naoConformidade = ncfDAO.buscar(Long.parseLong(inspecaoId));
		naoConformidade.atualizarVizualizacao();
		naoConformidade = ncfDAO.merge(naoConformidade);
	}

	public void excluir() {
		String ncfID = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ncfID");
		ncfDAO.removeNcf(Long.parseLong(ncfID));
		pesquisar();
		ManagedBeanViewUtil.sucesso();
	}

}
