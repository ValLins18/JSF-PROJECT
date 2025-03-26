package br.com.sinerji.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sinerji.model.Pessoa;
import br.com.sinerji.repository.PessoaRepository;

public class PessoaService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaRepository pessoaRepository;

	public void salvar(Pessoa pessoa) {
		pessoaRepository.salvar(pessoa);
	}
	public void remover(Integer id) {
		pessoaRepository.remover(id);
	}
	public List<Pessoa> listar(){
		return pessoaRepository.listar();
	}
	public Pessoa getById(Integer id) {
		return pessoaRepository.getById(id);
	}
}
