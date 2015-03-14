package br.com.discipular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.discipular.model.Chamada;

/**
 * Repositório do modelo {@link Chamada}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	06/10/2014 
 */
@Repository
public interface ChamadaRepository extends JpaRepository<Chamada, Long>, QueryDslPredicateExecutor<Chamada> {

}
