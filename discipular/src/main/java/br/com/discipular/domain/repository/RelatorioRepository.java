package br.com.discipular.domain.repository;

import br.com.discipular.domain.model.Relatorio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Repositório do modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	08/09/2014 
 */
public interface RelatorioRepository extends MongoRepository<Relatorio, Long> {

}
