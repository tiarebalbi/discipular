package br.com.discipular.service;

import br.com.discipular.domain.model.Celula;
import br.com.discipular.domain.model.Usuario;
import br.com.discipular.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucas Campos
 * @date 13/05/15
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UsuarioRepository getRepositorio() {
        return this.repository;
    }

}
