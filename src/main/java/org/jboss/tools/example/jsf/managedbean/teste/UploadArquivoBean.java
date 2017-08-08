package org.jboss.tools.example.jsf.managedbean.teste;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;

import org.jboss.tools.example.springmvc.data.teste.AlunoDao;
import org.jboss.tools.example.springmvc.data.teste.UploadArquivoAulaInterface;
import org.jboss.tools.example.springmvc.model.teste.Aluno;
import org.jboss.tools.example.springmvc.model.teste.ArquivoUploadAula;

@RequestScoped
@ManagedBean(name = "uploadArquivoBean")
public class UploadArquivoBean {

	private ArquivoUploadAula arquivoUploadAula = new ArquivoUploadAula();

	@ManagedProperty(name = "uploadArquivoAula", value = "#{uploadArquivoAula}")
	private UploadArquivoAulaInterface uploadArquivoAula;
	
	
	@ManagedProperty(name = "alunoDao", value = "#{alunoDao}")
	private AlunoDao alunoDao;

	private Part arquivo;
	
	
	public void upload () throws IOException{
		
		//-------LENDO ARQUIVO CSV---------
		
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
				alunoDao.merge(aluno);
			}

		}		 
		
		
		//-------LENDO ARQUIVO CSV---------
		
		byte[] arquivoByte = toByteArrayUsingJava(arquivo.getInputStream());  
		arquivoUploadAula.setArquivo(arquivoByte);
		uploadArquivoAula.salvar(arquivoUploadAula);
	}
	

	public ArquivoUploadAula getArquivoUploadAula() {
		return arquivoUploadAula;
	}

	public void setArquivoUploadAula(ArquivoUploadAula arquivoUploadAula) {
		this.arquivoUploadAula = arquivoUploadAula;
	}

	public UploadArquivoAulaInterface getUploadArquivoAula() {
		return uploadArquivoAula;
	}

	public void setUploadArquivoAula(UploadArquivoAulaInterface uploadArquivoAula) {
		this.uploadArquivoAula = uploadArquivoAula;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
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
	
	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}
	
	public AlunoDao getAlunoDao() {
		return alunoDao;
	}


}
