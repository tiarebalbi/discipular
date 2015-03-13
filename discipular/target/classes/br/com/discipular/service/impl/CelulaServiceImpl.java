package br.com.discipular.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.discipular.model.Celula;
import br.com.discipular.repository.CelulaRepository;
import br.com.discipular.service.CelulaService;

/**
 * Implementação dos métodos de consulta e manipulação do modelo {@link Celula}
 * 
 * @author Lucas Campos
 * @version 1.0.0
 * @since 1.0.0
 *
 * 	10/09/2014 
 */
@Service
public class CelulaServiceImpl implements CelulaService {

	@Autowired
	private CelulaRepository repository;
	
	@Override
	public CelulaRepository getRepositorio() {
		return repository;
	}
	
	@Override
	public Celula salvar(Celula celula) throws Exception {

		if(celula.getSupervisor() == null) {
			celula.setArea(0);
		} else {
			celula.setArea(celula.getSupervisor().getArea());
		}
		
		return this.repository.save(celula);
	}

	@Override
	public void excluir(Long id) throws Exception {
		Celula celula = this.repository.findOne(id);
		celula.setSupervisor(null);
		celula.setUsuario(null);
		celula.setApagada(true);
		this.salvar(celula);
	}

	public void setRepositorio(CelulaRepository repository) {
		this.repository = repository;
	}

}