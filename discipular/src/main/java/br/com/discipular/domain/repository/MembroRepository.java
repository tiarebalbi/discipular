package br.com.discipular.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.discipular.domain.model.Membro;

/**
 * Repositório do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
public interface MembroRepository extends JpaRepository<Membro, Long>, QueryDslPredicateExecutor<Membro> {

}
