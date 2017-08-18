package org.jboss.tools.example.jsf.managedbean.teste;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.jboss.tools.example.springmvc.data.teste.UploadDao;
import org.jboss.tools.example.springmvc.model.teste.Aluno;
import org.jboss.tools.example.springmvc.model.teste.ArquivoUpload;
import org.primefaces.event.FileUploadEvent;

@RequestScoped
@ManagedBean(name = "uploadJsf")
public class UploadJsf {

	private ArquivoUpload arquivoUpload = new ArquivoUpload();

	private List<ArquivoUpload> list = new ArrayList<ArquivoUpload>();

	private Part arquivo;

	@ManagedProperty(name = "uploadDao", value = "#{uploadDao}")
	private UploadDao uploadDao;

	public void upload() throws IOException {

		
		//-------LENDO ARQUIVO CSV---------
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		scanner.useDelimiter(",");

		while (scanner.hasNext()) {
			String linha = scanner.nextLine();
			if (linha != null && !linha.trim().isEmpty()) {
				linha = linha.replaceAll("\"", "");

				String[] dados = linha.split("\\,");

				Aluno aluno = new Aluno();
				aluno.setNome(dados[0]);
				aluno.setEmail(dados[1]);
				//uploadDao.merge(aluno);
			}

		}		 
		
		
		//-------LENDO ARQUIVO CSV---------
		
		byte[] arquivoByte = toByteArrayUsingJava(arquivo.getInputStream());  
		arquivoUpload.setArquivo(arquivoByte);
		uploadDao.salvar(arquivoUpload);
		
		carregarList();
	}

	@PostConstruct
	private void carregarList() {
		list = uploadDao.lista();

	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivoUpload(ArquivoUpload arquivoUpload) {
		this.arquivoUpload = arquivoUpload;
	}

	public ArquivoUpload getArquivoUpload() {
		return arquivoUpload;
	}

	public UploadDao getUploadDao() {
		return uploadDao;
	}

	public void setUploadDao(UploadDao uploadDao) {
		this.uploadDao = uploadDao;
	}

	public byte[] toByteArrayUsingJava(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = is.read();
		while (reads != -1) {
			baos.write(reads);
			reads = is.read();
		}
		return baos.toByteArray();
	}

	public void download() {
		try {

			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			String fileDownloadId = params.get("fileDownloadId");
			
			arquivoUpload = uploadDao.busca(Long.parseLong(fileDownloadId));

			HttpServletResponse response = (HttpServletResponse) FacesContext.
					getCurrentInstance().getExternalContext()
					.getResponse();

			response.addHeader("Content-Disposition", "attachment; filename=download.csv");
			response.setContentType("application/octet-stream");

			response.setContentLength(arquivoUpload.getArquivo().length);
			response.getOutputStream().write(arquivoUpload.getArquivo());
			
			response.getOutputStream().flush();

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ArquivoUpload> getList() {
		return list;
	}

	public void handleFileUpload(FileUploadEvent event) throws Exception {
		String miniImgBase64 = DatatypeConverter.printBase64Binary(event.getFile().getContents());
		System.out.println(miniImgBase64);
	}

}
