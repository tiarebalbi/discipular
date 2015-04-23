package br.com.discipular.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.discipular.domain.model.Chamada;

/**
 * Repositório do modelo {@link Chamada}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	06/10/2014 
 */
public interface ChamadaRepository extends JpaRepository<Chamada, Long>, QueryDslPredicateExecutor<Chamada> {

}
