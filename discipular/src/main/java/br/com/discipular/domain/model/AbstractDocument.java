package br.com.discipular.domain.model;

import javax.persistence.MappedSuperclass;

/**
 * @author Lucas Campos
 * @date 13/05/15
 */
@MappedSuperclass
public class AbstractDocument {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
