package org.jboss.tools.example.jsf.managedbean.teste;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import org.jboss.tools.example.springmvc.data.teste.UploadDao;
import org.jboss.tools.example.springmvc.model.teste.Aluno;
import org.jboss.tools.example.springmvc.model.teste.ArquivoUpload;

@RequestScoped
@ManagedBean(name = "uploadJsf")
public class UploadJsf {

	private ArquivoUpload arquivoUpload = new ArquivoUpload();

	private Part arquivo;

	@ManagedProperty(name = "uploadDao", value = "#{uploadDao}")
	private UploadDao uploadDao;

	public void upload() throws IOException {
		
		
		//-------LENDO ARQUIVO CVS---------
		
		 Scanner scanner = new Scanner(arquivo.getInputStream(), "UTF-8");
		 scanner.useDelimiter(",");
		
		 while (scanner.hasNext()){
			 String linha = scanner.nextLine();
			 
			 if (linha != null && !linha.trim().isEmpty()){
				 linha = linha.replaceAll("\"", "");
				
				 String[] dados = linha.split("\\,");
				 
				 Aluno aluno = new Aluno();
				 aluno.setNome(dados[0]);
				 aluno.setEmail(dados[1]);
				 
				// alunoDao.merge(aluno);
				 
			 }
		 }
		//-------LENDO ARQUIVO CVS---------

		InputStream inputStreamFile = arquivo.getInputStream();
		
		
		Scanner conteudo = new Scanner(inputStreamFile, "UTF-8");
		conteudo.useDelimiter(",");
		
		while (conteudo.hasNext()) {
			System.out.println(conteudo.nextLine());
		}

		byte[] byteFile = toByteArrayUsingJava(inputStreamFile);
		arquivoUpload.setArquivo(byteFile);

		uploadDao.salvar(arquivoUpload);

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

}
