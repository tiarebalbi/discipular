package br.com.discipular.domain.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.discipular.domain.model.Celula;
import br.com.discipular.domain.model.QCelula;
import br.com.discipular.domain.model.QRelatorio;
import br.com.discipular.domain.model.QUsuario;
import br.com.discipular.domain.model.Relatorio;

import com.mysema.query.jpa.impl.JPAQuery;
@Repository
public class RelatorioQuery {

	@PersistenceContext
	private EntityManager em;
	
	public List<Relatorio> buscarPorSupervisor(String loginSupervisor) {
		QUsuario usuario = QUsuario.usuario;
		JPAQuery query1 = new JPAQuery(em);
		int area = query1.from(usuario).where(usuario.login.eq(loginSupervisor)).uniqueResult(usuario.area);
		
		QCelula celula = QCelula.celula;
		JPAQuery query2 = new JPAQuery(em);
		List<Celula> celulas = query2.from(celula).where(celula.area.eq(area)).list(celula);
		
		QRelatorio relatorio = QRelatorio.relatorio;
		JPAQuery query3 = new JPAQuery(em);
		List<Relatorio> resultados = query3.from(relatorio).where(relatorio.celula.in(celulas)).list(relatorio);
		
		return resultados;
	}

}
