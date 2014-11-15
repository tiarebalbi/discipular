package br.com.discipular.predicate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import br.com.discipular.model.QSupervisor;
import br.com.discipular.model.Supervisor;
import br.com.discipular.model.Usuario;

import com.mysema.query.types.Predicate;

public class SupervisorPredicate {

	public static Pageable buscarPaginacao(int pagina,int quantidade) {
		return new PageRequest(pagina, quantidade, Direction.ASC, "area");
	}

	public static Predicate buscarPorNomeComFiltro(String nome) {
		QSupervisor condicao = QSupervisor.supervisor;
		return condicao.usuario.nome.startsWithIgnoreCase(nome).and(condicao.usuario.nome.containsIgnoreCase(nome));
	}

	public static Predicate buscarPorNome(String nome) {
		QSupervisor condicao = QSupervisor.supervisor;
		return condicao.usuario.nome.eq(nome);
	}

	public static Predicate buscarPorSupervisoresDiferente(Supervisor supervisor) {
		QSupervisor condicao = QSupervisor.supervisor;
		return condicao.id.ne(supervisor.getId());
	}

	public static Predicate buscarPorArea(int area) {
		QSupervisor condicao = QSupervisor.supervisor;
		return condicao.area.eq(area);
	}

	public static Predicate buscarPorAreaRepitida(Supervisor supervisor) {
		QSupervisor condicao = QSupervisor.supervisor;
		return condicao.area.eq(supervisor.getArea()).and(condicao.id.ne(supervisor.getId()));
	}

	public static Predicate buscarPor(Usuario usuario) {
		QSupervisor condicao = QSupervisor.supervisor;
		return condicao.usuario.login.eq(usuario.getLogin());
	}

}
