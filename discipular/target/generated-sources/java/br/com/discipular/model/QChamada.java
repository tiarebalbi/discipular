package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QChamada is a Querydsl query type for Chamada
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QChamada extends EntityPathBase<Chamada> {

    private static final long serialVersionUID = -1937080695L;

    public static final QChamada chamada = new QChamada("chamada");

    public final QAbstractModel _super = new QAbstractModel(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nome = createString("nome");

    public final StringPath observacao = createString("observacao");

    public final EnumPath<br.com.discipular.enumerator.TipoChamada> tipo = createEnum("tipo", br.com.discipular.enumerator.TipoChamada.class);

    public QChamada(String variable) {
        super(Chamada.class, forVariable(variable));
    }

    public QChamada(Path<? extends Chamada> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChamada(PathMetadata<?> metadata) {
        super(Chamada.class, metadata);
    }

}

