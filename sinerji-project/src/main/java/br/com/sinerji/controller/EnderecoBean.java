package br.com.sinerji.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.sinerji.model.Endereco;
import br.com.sinerji.model.Pessoa;
import br.com.sinerji.service.EnderecoService;
import br.com.sinerji.service.PessoaService;

@Named("enderecoBean")
@ViewScoped
public class EnderecoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pessoaId;
	
	@Inject
	private Endereco endereco;
	@Inject
	private Pessoa pessoa;
	@Inject
	private EnderecoService enderecoService;
	@Inject
	private PessoaService pessoaService;

	private List<Endereco> enderecos;
	
	public void adicionar() {
		try {
			endereco.setPessoa(pessoa);
			enderecoService.salvar(endereco);
			dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void remover(Integer id) {
		try {
			enderecoService.remover(id);
			dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editar(Integer id) {
		try {
			endereco = enderecoService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init() {
		try {
			 
			if(pessoaId == null) {
				String pId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("parametro");
				
				if(pId == null) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("CadastroPessoa.xhtml");
					return;
				}
				pessoaId = pId;
				pessoa = pessoaService.getById(Integer.parseInt(pessoaId));
				enderecos = enderecoService.getEnderecosByPessoa(pessoaId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dispose() {
		endereco = new Endereco();
		enderecos = enderecoService.getEnderecosByPessoa(pessoaId);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
}
