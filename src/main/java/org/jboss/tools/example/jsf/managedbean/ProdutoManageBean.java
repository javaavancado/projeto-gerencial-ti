package org.jboss.tools.example.jsf.managedbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.jboss.tools.example.springmvc.data.ProdutoDAO;
import org.jboss.tools.example.springmvc.enums.TipoProduto;
import org.jboss.tools.example.springmvc.model.rd.Produto;

@RequestScoped
@ManagedBean(name = "produtoManageBean")
public class ProdutoManageBean {

	private Produto produto = new Produto();

	@ManagedProperty(name = "produtoDAO", value = "#{produtoDAO}")
	private ProdutoDAO produtoDAO;

	public String salvar() {
		produto = produtoDAO.merge(produto);
		novo();
		return "";
	}

	public void novo() {
		produto = new Produto();
	}

	public List<SelectItem> listaTipoProduto() {
		List<SelectItem> retorno = new ArrayList<SelectItem>();
		for (TipoProduto produto : TipoProduto.values()) {
			retorno.add(new SelectItem(produto, produto.getDescricao()));
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

}
