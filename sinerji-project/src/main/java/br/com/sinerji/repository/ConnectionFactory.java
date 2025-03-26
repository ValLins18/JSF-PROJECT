package br.com.sinerji.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
