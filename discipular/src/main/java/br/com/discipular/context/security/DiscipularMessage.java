package br.com.discipular.context.security;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class DiscipularMessage {

	@Autowired
	private MessageSource message;
	
	/**
	 * @param chave chave de acesso a mensagem
	 * @param params parametros para complemento da mensagem
	 * @return String mensagem de retorno
	 */
	public String getMessage(String chave, Object... params) {
		String sigla = "pt-BR";
		
		Locale defaultLocale = new Locale(sigla);
		return message.getMessage(chave, params, defaultLocale);
	}
	
}
