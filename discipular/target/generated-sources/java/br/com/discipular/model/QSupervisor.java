package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSupervisor is a Querydsl query type for Supervisor
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSupervisor extends EntityPathBase<Supervisor> {

    private static final long serialVersionUID = 1104211724L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSupervisor supervisor = new QSupervisor("supervisor");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nome = createString("nome");

    public final QUsuario usuario;

    public QSupervisor(String variable) {
        this(Supervisor.class, forVariable(variable), INITS);
    }

    public QSupervisor(Path<? extends Supervisor> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSupervisor(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSupervisor(PathMetadata<?> metadata, PathInits inits) {
        this(Supervisor.class, metadata, inits);
    }

    public QSupervisor(Class<? extends Supervisor> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.usuario = inits.isInitialized("usuario") ? new QUsuario(forProperty("usuario")) : null;
    }

}

