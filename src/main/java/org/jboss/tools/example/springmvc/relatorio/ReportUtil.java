package org.jboss.tools.example.springmvc.relatorio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

@SuppressWarnings({ "unused", "deprecation", "rawtypes" })
@Repository("reportUtil")
@Transactional
@EnableTransactionManagement
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	protected DriverManagerDataSource dataSource;

	@Autowired
	protected LocalContainerEntityManagerFactoryBean myEmf;

	@Autowired
	protected JpaTransactionManager transactionManager;

	private static final String FOLDER_RELATORIOS = "/relatorios";
	private String SEPARATOR = File.separator;
	private String caminhoSubreport_dir = "";
	private int tipoRelatorio;
	private List<?> listDataBeanCollectionReport = new ArrayList();
	private HashMap<String, Object> parametrosRelatorio = new HashMap<String, Object>();
	private String nomeRelatorioJasper = "default";

	private byte[] geraRelatorioByte(List<?> listDataBeanColletionReport, HashMap<String, Object> parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRelatorio, boolean usaConnection)
			throws Exception {

		String caminhoRelatorio = getContext().getRealPath(FOLDER_RELATORIOS);
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanColletionReport);

		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");

		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}

		caminhoSubreport_dir = caminhoRelatorio + SEPARATOR;

		/* caminho para imagens */
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		parametrosRelatorio.put("SUBREPORT_DIR", caminhoSubreport_dir);

		/* Caminho relatorio exportado */
		String caminhoArquivoJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";

		byte[] byteRetorno = null;
		JasperPrint jasperPrint = null;

		if (tipoRelatorio == 1) {// Gera PDF

			if (usaConnection) {
				jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametrosRelatorio, getConnection());
			} else {
				jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametrosRelatorio, jrbcds);
			}

			byteRetorno = JasperExportManager.exportReportToPdf(jasperPrint);

		} else if (tipoRelatorio == 2) {// Gera Excel

			String caminhoArquivoXls = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".xls";

			if (usaConnection) {
				jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametrosRelatorio, getConnection());
			} else {
				jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametrosRelatorio, jrbcds);
			}
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, caminhoArquivoXls);
			exporter.exportReport();

			InputStream conteudoRelatorio = new FileInputStream(caminhoArquivoXls);
			byteRetorno = getBytes(conteudoRelatorio);
		}

		return byteRetorno;

	}

	/**
	 * Retorna o relatorio em byte[] para ser suado em qualquer aplicação
	 * 
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] getArquivoReportByte() throws Exception {
		return geraRelatorioByte(getListDataBeanCollectionReport(), getParametrosRelatorio(), getNomeRelatorioJasper(),
				getNomeRelatorioJasper(), getTipoRelatorio(), false);
	}
	
	/**
	 * Retorna o relatorio em byte[] para ser suado em qualquer aplicação
	 * 
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] getArquivoReportByteConnection() throws Exception {
		return geraRelatorioByte(getListDataBeanCollectionReport(), getParametrosRelatorio(), getNomeRelatorioJasper(),
				getNomeRelatorioJasper(), getTipoRelatorio(), true);
	}

	/**
	 * Retorna o relatorio pronto para o download em PrimeFaces
	 * 
	 * @return StreamedContent
	 * @throws Exception
	 */
	public StreamedContent getArquivoReportStreamedContent() throws Exception {
		byte[] relatorio = getArquivoReportByte();
		InputStream stream = new ByteArrayInputStream(relatorio);
		return new DefaultStreamedContent(stream, "aplication/" + (tipoRelatorio == 1 ? "pdf" : "xls"),
				getNomeRelatorioJasper() + "." + (tipoRelatorio == 1 ? "pdf" : "xls"));
	}
	
	/**
	 * Retorna o relatorio pronto para o download em PrimeFaces
	 * 
	 * @return StreamedContent
	 * @throws Exception
	 */
	public StreamedContent getArquivoReportStreamedContentConnection() throws Exception {
		byte[] relatorio = getArquivoReportByteConnection();
		InputStream stream = new ByteArrayInputStream(relatorio);
		return new DefaultStreamedContent(stream, "aplication/" + (tipoRelatorio == 1 ? "pdf" : "xls"),
				getNomeRelatorioJasper() + "." + (tipoRelatorio == 1 ? "pdf" : "xls"));
	}

	private int getTipoRelatorio() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String tipoRelatorio = params.get("tipoRelatorio");
		if (tipoRelatorio == null || (tipoRelatorio != null && tipoRelatorio.isEmpty())) {
			tipoRelatorio = "1";
		}
		this.tipoRelatorio = Integer.parseInt(tipoRelatorio);

		return this.tipoRelatorio;
	}

	private List<?> getListDataBeanCollectionReport() {
		return listDataBeanCollectionReport;
	}

	public void setListDataBeanCollectionReport(List<?> listDataBeanCollectionReport) {
		if (listDataBeanCollectionReport != null) {
			this.listDataBeanCollectionReport = listDataBeanCollectionReport;
		}
	}

	private HashMap<String, Object> getParametrosRelatorio() {
		return parametrosRelatorio;
	}

	public void setParametrosRelatorio(HashMap<String, Object> parametrosRelatorio) {
		if (parametrosRelatorio != null) {
			this.parametrosRelatorio = parametrosRelatorio;
		}
	}

	private String getNomeRelatorioJasper() {
		return nomeRelatorioJasper;
	}

	public void setNomeRelatorioJasper(String nomeRelatorioJasper) {

		if (nomeRelatorioJasper == null || nomeRelatorioJasper.isEmpty()) {
			nomeRelatorioJasper = "default";
		}

		this.nomeRelatorioJasper = nomeRelatorioJasper;
	}

	private ServletContext getContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	/***
	 * 
	 * @param is
	 *            InputStream
	 * @return byte[] do relatório em excel
	 * @throws IOException
	 */
	private byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}

	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	private Connection getConnectionMyEmf() throws SQLException {
		return myEmf.getDataSource().getConnection();
	}

	private Connection getConnectionTM() throws SQLException {
		return transactionManager.getDataSource().getConnection();
	}

	private DataSource getDataSourceEmf() throws SQLException {
		return myEmf.getDataSource();
	}

	private DataSource getDataSourceTM() throws SQLException {
		return transactionManager.getDataSource();
	}
}
