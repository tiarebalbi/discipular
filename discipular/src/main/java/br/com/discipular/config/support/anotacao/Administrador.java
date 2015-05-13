package br.com.discipular.config.support.anotacao;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@PreAuthorize ("hasRole('ROLE_ADMINISTRADOR')")
public @interface Administrador {

}
