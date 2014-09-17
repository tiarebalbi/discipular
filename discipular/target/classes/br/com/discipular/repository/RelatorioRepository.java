package br.com.discipular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.discipular.model.Relatorio;

/**
 * Repositório do modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	08/09/2014 
 */
@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long>, QueryDslPredicateExecutor<Relatorio> {

}
