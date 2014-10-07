package br.com.discipular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.discipular.model.Supervisor;

/**
 * Repositório do modelo {@link Supervisor}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 *        Oct 7, 2014
 */
@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long>, QueryDslPredicateExecutor<Supervisor> {

}
