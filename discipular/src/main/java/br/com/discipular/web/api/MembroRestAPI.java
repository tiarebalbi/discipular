package br.com.discipular.web.api;

import br.com.discipular.domain.model.Membro;
import br.com.discipular.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Lucas Campos
 * @date 13/05/15
 */
@RestController
@RequestMapping(value = "/membros")
public class MembroRestAPI {

    @Autowired
    private MembroService service;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Membro> listar(@PageableDefault(size = 25, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return this.service.getRepository().findAll(pageable);
    }

}
