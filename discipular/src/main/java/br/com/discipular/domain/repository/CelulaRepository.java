package br.com.discipular.domain.repository;

import br.com.discipular.domain.model.Celula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Repositório do modelo {@link Celula}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
public interface CelulaRepository extends MongoRepository<Celula, Long> {

}
