package br.com.discipular.serializer.test;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import br.com.discipular.domain.model.Membro;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LocalDateSerializerTest {
	
	private DateTimeFormatter formatoBr;

	@Before
	public void init() {
		formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	}
	
	@Test
	public void deveTestarSerializacaoLocalDate() throws Exception {
		Membro m = new Membro();
		m.setDataNascimento(LocalDate.now());

		String esperado = LocalDate.now().format(formatoBr);
		
		ObjectMapper objectMapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, m);
        
        String resultado = writer.getBuffer().substring(41, 51);
        
        assertEquals("A data não foi formatada no padrão brasileiro.", esperado, resultado);
	}

}
