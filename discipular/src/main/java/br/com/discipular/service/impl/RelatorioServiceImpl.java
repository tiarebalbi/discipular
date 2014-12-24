package br.com.discipular.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.discipular.model.Relatorio;
import br.com.discipular.query.RelatorioQuery;
import br.com.discipular.repository.RelatorioRepository;
import br.com.discipular.service.RelatorioService;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Relatorio}
 * 
 * @author Lucas Campos
 * @since 1.0.0
 * @version 1.0.0
 *
 * 	12/09/2014 
 */
@Service
public class RelatorioServiceImpl implements RelatorioService {

	@Autowired
	private RelatorioRepository repository;
	
	@Autowired
	private RelatorioQuery query;
	
	@Override
	public Relatorio salvar(Relatorio relatorio) {
		
		Assert.notNull(relatorio, "Registro nulo, não foi possível salvar este registro.");
		
		if(relatorio.getId() == null) {
			relatorio.setDataCriacao(LocalDate.now());
		}
		
		return repository.save(relatorio);
		
	}

	@Override
	public List<Relatorio> salvar(List<Relatorio> relatorios) {
		Assert.notNull(relatorios, "Registro nulo, não foi possível salvar este registro.");
		
		relatorios.forEach(r -> {
			if(r.getId() == null) {
				r.setDataCriacao(LocalDate.now());
			}
		});
		
		return repository.save(relatorios);
	}

	@Override
	public List<Relatorio> buscarPorSupervisor(String loginSupervisor) {
		return query.buscarPorSupervisor(loginSupervisor);
	}

	@Override
	public RelatorioRepository getRepositorio() {
		return this.repository;
	}
	
	public void setRepositorio(RelatorioRepository repository) {
		this.repository = repository;
	}
	
}
