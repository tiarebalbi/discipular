package br.com.discipular.domain.repository;

import br.com.discipular.domain.model.Chamada;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Repositório do modelo {@link Chamada}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	06/10/2014 
 */
public interface ChamadaRepository extends MongoRepository<Chamada, Long> {

}
