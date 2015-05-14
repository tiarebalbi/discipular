package br.com.discipular.service.impl;

import br.com.discipular.domain.repository.UsuarioRepository;
import br.com.discipular.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Campos
 * @date 13/05/15
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UsuarioRepository getRepository() {
        return this.repository;
    }

}
