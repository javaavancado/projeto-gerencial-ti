package org.jboss.tools.example.springmvc.relatorio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class ReportUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
	private String SEPARATOR = File.separator;
	private static final String PONTO = ".";
	private String caminhoSubreport_dir = "";
	private int tipoRelatorio;
	private List<?> listDataBeanCollectionReport = new ArrayList();
	private HashMap<String, Object> parametrosRelatorio = new HashMap<String, Object>();
	private String nomeRelatorioJasper = "default";
	private String nomeRelatorioSaida = "default";
	private ServletContext context;

	public byte[] geraRelatorioByte(List<?> listDataBeanColletionReport, HashMap<String, Object> parametrosRelatorio,
			String nomeRelatorioJasper, String nomeRelatorioSaida, int tipoRelatorio) throws Exception {

		String caminhoRelatorio = context.getRealPath(FOLDER_RELATORIOS);
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listDataBeanColletionReport);

		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "jasper");

		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
			SEPARATOR = "";
		}

		caminhoSubreport_dir = caminhoRelatorio + SEPARATOR;

		/* caminho para imagens */
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubreport_dir);

		/* Caminho relatorio exportado */
		String caminhoArquivoJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "jasper";

		byte[] byteRetorno = null;

		if (tipoRelatorio == 1) {// Gera PDF

			JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoArquivoJasper, parametrosRelatorio, jrbcds);
			byteRetorno = JasperExportManager.exportReportToPdf(jasperPrint);

		} else if (tipoRelatorio == 2) {// Gera Excel
			
			String caminhoArquivoXls = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + PONTO + "xls";

			JasperPrint printFileName = JasperFillManager.
					    fillReport(caminhoArquivoJasper, parametrosRelatorio,
					    jrbcds);
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, printFileName);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, caminhoArquivoXls);
			exporter.exportReport();

			InputStream conteudoRelatorio = new FileInputStream(caminhoArquivoXls);
			byteRetorno = getBytes(conteudoRelatorio);
		}

		return byteRetorno;

	}

	public byte[] getArquivoReportByte() throws Exception {
		return geraRelatorioByte(getListDataBeanCollectionReport(), getParametrosRelatorio(), getNomeRelatorioJasper(),
				getNomeRelatorioSaida(), getTipoRelatorio());
	}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public List<?> getListDataBeanCollectionReport() {
		return listDataBeanCollectionReport;
	}

	public void setListDataBeanCollectionReport(List<?> listDataBeanCollectionReport) {
		this.listDataBeanCollectionReport = listDataBeanCollectionReport;
	}

	public HashMap<String, Object> getParametrosRelatorio() {
		return parametrosRelatorio;
	}

	public void setParametrosRelatorio(HashMap<String, Object> parametrosRelatorio) {
		this.parametrosRelatorio = parametrosRelatorio;
	}

	public String getNomeRelatorioJasper() {
		return nomeRelatorioJasper;
	}

	public void setNomeRelatorioJasper(String nomeRelatorioJasper) {

		if (nomeRelatorioJasper == null || nomeRelatorioJasper.isEmpty()) {
			nomeRelatorioJasper = "default";
		}

		this.nomeRelatorioJasper = nomeRelatorioJasper;
	}

	public String getNomeRelatorioSaida() {
		return nomeRelatorioSaida;
	}

	public void setNomeRelatorioSaida(String nomeRelatorioSaida) {

		if (nomeRelatorioSaida == null || nomeRelatorioSaida.isEmpty()) {
			nomeRelatorioSaida = "default";
		}
		this.nomeRelatorioSaida = nomeRelatorioSaida;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	/***
	 * 
	 * @param is InputStream
	 * @return byte[] do relatório em excel
	 * @throws IOException
	 */
	public byte[] getBytes(InputStream is) throws IOException {

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

	public StreamedContent getArquivoReportStreamedContent() throws Exception {
		byte[] relatorio = getArquivoReportByte();
		InputStream stream = new ByteArrayInputStream(relatorio);
		return new DefaultStreamedContent(stream, "aplication/" + (tipoRelatorio == 1 ? "pdf" : "xls"),
				"analise." + (tipoRelatorio == 1 ? "pdf" : "xls"));
	}

}
