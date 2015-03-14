package br.com.discipular.query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class UsuarioQuery {

	@PersistenceContext
	private EntityManager em;
	
}
