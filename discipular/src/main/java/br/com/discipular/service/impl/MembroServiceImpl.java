package br.com.discipular.service.impl;

import br.com.discipular.domain.repository.MembroRepository;
import br.com.discipular.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Campos
 * @date 13/05/15
 */
@Service
public class MembroServiceImpl implements MembroService {

    @Autowired
    private MembroRepository repository;

    @Override
    public MembroRepository getRepository() {
        return repository;
    }
}
