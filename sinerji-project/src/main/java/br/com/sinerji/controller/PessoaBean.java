package br.com.sinerji.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


import br.com.sinerji.model.Pessoa;
import br.com.sinerji.service.PessoaService;

@Named("pessoaBean")
@SessionScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoa pessoa;
	@Inject
	private PessoaService pessoaService;

	private List<Pessoa> pessoas;
	
	public void adicionar() {
		try {
			pessoaService.salvar(pessoa);
			dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remover(Integer id) {
		try {
			pessoaService.remover(id);
			dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editar(Integer id) {
		try {
			pessoa = pessoaService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init() {
		pessoas = pessoaService.listar();
	}
	
	
	private void dispose() {
		pessoa = new Pessoa();
		init();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}
