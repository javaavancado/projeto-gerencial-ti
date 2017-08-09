package org.jboss.tools.example.jsf.managedbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.jboss.tools.example.projeto.listener.CarregamentoLazyListForObject;
import org.jboss.tools.example.springmvc.data.EmpresaDAO;
import org.jboss.tools.example.springmvc.data.IGenericDao;
import org.jboss.tools.example.springmvc.model.rd.Empresa;
import org.jboss.tools.example.springmvc.relatorio.ReportUtil;
import org.primefaces.model.StreamedContent;

@ViewScoped
@ManagedBean(name = "empresaManageBean")
public class EmpresaManageBean {

	private Empresa empresa = new Empresa();

	private CarregamentoLazyListForObject<Empresa> list = new CarregamentoLazyListForObject<Empresa>();

	@ManagedProperty(name = "reportUtil", value = "#{reportUtil}")
	private ReportUtil reportUtil;

	@ManagedProperty(name = "empresaDAO", value = "#{empresaDAO}")
	private EmpresaDAO empresaDAO;

	@ManagedProperty(name = "iGenericDao", value = "#{iGenericDao}")
	private IGenericDao iGenericDao;

	private String descricaoPesquisa = "";

	public String salvar() {
		iGenericDao.merge(empresa);
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
		return "";
	}

	public void excluir() {
		String empresaId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("empresaId");
		empresaDAO.removeEmpresa(Long.parseLong(empresaId));
		ManagedBeanViewUtil.sucesso();
		novo();
		pesquisar();
	}

	public String novo() {
		empresa = new Empresa();
		list.clean();
		return "/rd/empresa.jsf";
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	public EmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}

	public void setReportUtil(ReportUtil reportUtil) {
		this.reportUtil = reportUtil;
	}

	public ReportUtil getReportUtil() {
		return reportUtil;
	}

	public StreamedContent getFileRelatorio() throws Exception {
		reportUtil.setNomeRelatorioJasper("empresa");
		reportUtil.setListDataBeanCollectionReport(empresaDAO.lista());
		return reportUtil.getArquivoReportStreamedContent();
	}

	public StreamedContent getFileRelatorioUnico() throws Exception {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		String inspecaoId = params.get("empresaId");
		List<Empresa> inspecaos = new ArrayList<Empresa>();
		inspecaos.add(empresaDAO.buscaPorId(Long.parseLong(inspecaoId)));

		reportUtil.setNomeRelatorioJasper("empresa");
		reportUtil.setListDataBeanCollectionReport(inspecaos);
		return reportUtil.getArquivoReportStreamedContent();
	}

	public void pesquisar() {
		list.clean();
		list.setQuery(empresaDAO.queryEmpresaNome(descricaoPesquisa));
	}

	public CarregamentoLazyListForObject<Empresa> getList() {
		return list;
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
