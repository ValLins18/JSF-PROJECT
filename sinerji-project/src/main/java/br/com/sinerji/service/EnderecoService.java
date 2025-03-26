package br.com.sinerji.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.sinerji.model.Endereco;
import br.com.sinerji.model.Pessoa;
import br.com.sinerji.repository.EnderecoRepository;

public class EnderecoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EnderecoRepository enderecoRepository;

	public void salvar(Endereco endereco) {
		enderecoRepository.salvar(endereco);
	}
	public void remover(Integer id) {
		enderecoRepository.remover(id);
	}
	public List<Endereco> listar(){
		return enderecoRepository.listar();
	}
	public Endereco getById(Integer id) {
		return enderecoRepository.getById(id);
	}
	public List<Endereco> getEnderecosByPessoa(String idParam){
		return enderecoRepository.getEnderecosByPessoa(idParam);
	}
}
