package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QRelatorio is a Querydsl query type for Relatorio
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRelatorio extends EntityPathBase<Relatorio> {

    private static final long serialVersionUID = 1052732081L;

    public static final QRelatorio relatorio = new QRelatorio("relatorio");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final NumberPath<Integer> ask1 = createNumber("ask1", Integer.class);

    public final NumberPath<Integer> ask2 = createNumber("ask2", Integer.class);

    public final NumberPath<Integer> ask3 = createNumber("ask3", Integer.class);

    public final NumberPath<Integer> ask4 = createNumber("ask4", Integer.class);

    public final NumberPath<Integer> ask5 = createNumber("ask5", Integer.class);

    public final ListPath<Chamada, QChamada> chamadas = this.<Chamada, QChamada>createList("chamadas", Chamada.class, QChamada.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> data = createDate("data", java.time.LocalDate.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath observacao = createString("observacao");

    public final StringPath tema = createString("tema");

    public QRelatorio(String variable) {
        super(Relatorio.class, forVariable(variable));
    }

    public QRelatorio(Path<? extends Relatorio> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRelatorio(PathMetadata<?> metadata) {
        super(Relatorio.class, metadata);
    }

}

