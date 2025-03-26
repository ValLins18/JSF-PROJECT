package br.com.sinerji.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.sinerji.model.Pessoa;

public class PessoaRepository {

	private static EntityManager manager = ConnectionFactory.getEntityManager();
	
	public Pessoa getById(Integer id) {
		return manager.find(Pessoa.class, id);
	}
	
	public void salvar(Pessoa pessoa) {
		try {
			manager.getTransaction().begin();
			if(pessoa.getId() == null) {
				manager.persist(pessoa);
				manager.getTransaction().commit();
				System.out.println("Novo Registro Criado com Sucesso");
				return;
			} 
			manager.merge(pessoa);
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
			Pessoa pessoa = getById(id);
			manager.getTransaction().begin();
			manager.remove(pessoa);
			manager.getTransaction().commit();
			System.out.println("Registro deletado com Sucesso");
		} catch (Exception e) {
			manager.getTransaction().rollback();
			System.out.println("Erro ao deletar Registro");
			e.printStackTrace();
		}
	}
	public List<Pessoa> listar(){
		return manager.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
	}
}
