package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QChamada is a Querydsl query type for Chamada
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChamada extends EntityPathBase<Chamada> {

    private static final long serialVersionUID = -1937080695L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChamada chamada = new QChamada("chamada");

    public final QAbstractModel _super = new QAbstractModel(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nome = createString("nome");

    public final QRelatorio relatorio;

    public final EnumPath<br.com.discipular.enumerator.TipoChamada> tipo = createEnum("tipo", br.com.discipular.enumerator.TipoChamada.class);

    public QChamada(String variable) {
        this(Chamada.class, forVariable(variable), INITS);
    }

    public QChamada(Path<? extends Chamada> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChamada(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QChamada(PathMetadata<?> metadata, PathInits inits) {
        this(Chamada.class, metadata, inits);
    }

    public QChamada(Class<? extends Chamada> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.relatorio = inits.isInitialized("relatorio") ? new QRelatorio(forProperty("relatorio"), inits.get("relatorio")) : null;
    }

}

