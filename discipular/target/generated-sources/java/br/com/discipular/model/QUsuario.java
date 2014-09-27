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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final QCelula celula;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath login = createString("login");

    public final StringPath senha = createString("senha");

    public final EnumPath<br.com.discipular.enumerator.TipoUsuario> tipo = createEnum("tipo", br.com.discipular.enumerator.TipoUsuario.class);

    public QUsuario(String variable) {
        this(Usuario.class, forVariable(variable), INITS);
    }

    public QUsuario(Path<? extends Usuario> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUsuario(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUsuario(PathMetadata<?> metadata, PathInits inits) {
        this(Usuario.class, metadata, inits);
    }

    public QUsuario(Class<? extends Usuario> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.celula = inits.isInitialized("celula") ? new QCelula(forProperty("celula"), inits.get("celula")) : null;
    }

}

