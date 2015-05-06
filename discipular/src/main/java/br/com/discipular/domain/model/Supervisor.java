package br.com.discipular.domain.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Lucas Campos
 * @date 05/05/15
 */
@Entity
public class Supervisor extends AbstractModel {

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @OneToMany(mappedBy = "supervisor")
    private List<Celula> celulas;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Celula> getCelulas() {
        return celulas;
    }

    public void setCelulas(List<Celula> celulas) {
        this.celulas = celulas;
    }
}
