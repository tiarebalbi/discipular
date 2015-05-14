package br.com.discipular.domain.repository;

import br.com.discipular.domain.model.Membro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Repositório do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	08/09/2014 
 */
public interface MembroRepository extends MongoRepository<Membro, Long> {

}
