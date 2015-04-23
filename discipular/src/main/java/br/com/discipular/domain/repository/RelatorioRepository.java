package br.com.discipular.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.discipular.domain.model.Relatorio;

/**
 * Repositório do modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	08/09/2014 
 */
public interface RelatorioRepository extends JpaRepository<Relatorio, Long>, QueryDslPredicateExecutor<Relatorio> {

}
