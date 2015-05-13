package br.com.discipular.domain.repository;

import br.com.discipular.domain.model.Membro;
import br.com.discipular.domain.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Repositório do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
public interface UsuarioRepository extends MongoRepository<Usuario, Long> {

    Usuario findByLogin(String login);

}
