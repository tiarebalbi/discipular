package br.com.discipular.domain.bo;

import br.com.discipular.context.security.DiscipularPasswordEncoder;
import br.com.discipular.model.Usuario;

public class UsuarioBO {
	
	public static String retirarEspacoBrancoDoInicio(String string) {
		if(string.startsWith(" ")) {
			return string.substring(1, string.length());
		}
		
		return string;
	}
	
	public static Usuario criptografarSenha(Usuario usuario) {
		if(usuario.getSenha() != null && usuario.getId() == null) {
			usuario.setSenha(new DiscipularPasswordEncoder().encode(usuario.getSenha()));
		}

		return usuario;
	}
	
}
