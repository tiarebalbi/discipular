package br.com.discipular.web.api;

import br.com.discipular.domain.model.Membro;
import br.com.discipular.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas Campos
 * @date 13/05/15
 */
@RestController
public class MembroRestAPI {

    @Autowired
    private MembroService service;

    @RequestMapping(value = "membros")
    public List<Membro> listar() {
        return this.service.getRepository().findAll();
    }

}
