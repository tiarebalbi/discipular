package br.com.discipular.config.support.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@PreAuthorize ("hasRole('ROLE_SUPERVISOR') or hasRole('ROLE_ADMINISTRADOR')")
public @interface SupervisorRoles {

}