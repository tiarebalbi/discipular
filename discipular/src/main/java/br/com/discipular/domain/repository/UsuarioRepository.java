package br.com.discipular.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import br.com.discipular.domain.model.Membro;
import br.com.discipular.domain.model.Usuario;

/**
 * Repositório do modelo {@link Membro}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QueryDslPredicateExecutor<Usuario> {

}
