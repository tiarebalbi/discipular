package br.com.discipular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.discipular.model.Celula;

/**
 * Repositório do modelo {@link Celula}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
@Repository
public interface CelulaRepository extends JpaRepository<Celula, Long>, QueryDslPredicateExecutor<Celula> {

}
