package br.com.discipular.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Lucas Campos
 * @date 05/05/15
 */
//@Entity
public class Lider extends AbstractModel {

    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    @OneToOne
    private Celula celula;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Celula getCelula() {
        return celula;
    }

    public void setCelula(Celula celula) {
        this.celula = celula;
    }
}
