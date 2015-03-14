package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMembro is a Querydsl query type for Membro
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMembro extends EntityPathBase<Membro> {

    private static final long serialVersionUID = 1468307918L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembro membro = new QMembro("membro");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final QCelula celula;

    public final StringPath celular = createString("celular");

    public final DatePath<java.time.LocalDate> dataNascimento = createDate("dataNascimento", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nome = createString("nome");

    public final EnumPath<br.com.discipular.enumerator.TipoMembro> tipo = createEnum("tipo", br.com.discipular.enumerator.TipoMembro.class);

    public QMembro(String variable) {
        this(Membro.class, forVariable(variable), INITS);
    }

    public QMembro(Path<? extends Membro> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembro(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembro(PathMetadata<?> metadata, PathInits inits) {
        this(Membro.class, metadata, inits);
    }

    public QMembro(Class<? extends Membro> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.celula = inits.isInitialized("celula") ? new QCelula(forProperty("celula"), inits.get("celula")) : null;
    }

}

