package br.com.sinerji.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sinerji.model.Endereco;
import br.com.sinerji.model.Pessoa;

public class EnderecoRepository {

private static EntityManager manager = ConnectionFactory.getEntityManager();
	
	public Endereco getById(Integer id) {
		return manager.find(Endereco.class, id);
	}
	
	public void salvar(Endereco endereco) {
		try {
			manager.getTransaction().begin();
			if(endereco.getId() == null) {
				manager.persist(endereco);
				manager.getTransaction().commit();
				System.out.println("Novo Registro Criado com Sucesso");
				return;
			} 
			manager.merge(endereco);
			manager.getTransaction().commit();
			System.out.println("Registro Alterado com Sucesso");
			return;
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println("Erro ao inserir Registro");
			e.printStackTrace();
		}
	}
	public void remover(Integer id) {
		try {
			Endereco Endereco = getById(id);
			manager.getTransaction().begin();
			manager.remove(Endereco);
			manager.getTransaction().commit();
			System.out.println("Registro deletado com Sucesso");
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println("Erro ao deletar Registro");
			e.printStackTrace();
		}
	}
	public List<Endereco> listar(){
		return manager.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
	}

	public List<Endereco> getEnderecosByPessoa(String idParam) {
		return manager.createQuery("SELECT e FROM Endereco e WHERE e.Pessoa.id = :pessoa_id", Endereco.class)
				.setParameter("pessoa_id", Integer.parseInt(idParam))
				.getResultList();
	}
}
