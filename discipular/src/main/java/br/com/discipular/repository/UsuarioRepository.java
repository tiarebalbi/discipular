package br.com.discipular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import br.com.discipular.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QueryDslPredicateExecutor<Usuario> {

}
