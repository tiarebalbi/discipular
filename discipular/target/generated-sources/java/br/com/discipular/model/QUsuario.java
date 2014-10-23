package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUsuario is a Querydsl query type for Usuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = 1486133770L;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final ListPath<Celula, QCelula> celulas = this.<Celula, QCelula>createList("celulas", Celula.class, QCelula.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath login = createString("login");

    public final StringPath nome = createString("nome");

    public final StringPath senha = createString("senha");

    public final EnumPath<br.com.discipular.enumerator.TipoUsuario> tipo = createEnum("tipo", br.com.discipular.enumerator.TipoUsuario.class);

    public QUsuario(String variable) {
        super(Usuario.class, forVariable(variable));
    }

    public QUsuario(Path<? extends Usuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsuario(PathMetadata<?> metadata) {
        super(Usuario.class, metadata);
    }

}

