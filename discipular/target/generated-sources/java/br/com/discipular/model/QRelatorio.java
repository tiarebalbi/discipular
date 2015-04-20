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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRelatorio relatorio = new QRelatorio("relatorio");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final QCelula celula;

    public final ListPath<Chamada, QChamada> chamada = this.<Chamada, QChamada>createList("chamada", Chamada.class, QChamada.class, PathInits.DIRECT2);

    public final NumberPath<Integer> conteudo = createNumber("conteudo", Integer.class);

    public final DatePath<java.time.LocalDate> data = createDate("data", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> dataCriacao = createDate("dataCriacao", java.time.LocalDate.class);

    public final NumberPath<Integer> e5 = createNumber("e5", Integer.class);

    public final NumberPath<Integer> geral = createNumber("geral", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath observacao = createString("observacao");

    public final NumberPath<Integer> participacao = createNumber("participacao", Integer.class);

    public final StringPath tema = createString("tema");

    public final NumberPath<Integer> tempo = createNumber("tempo", Integer.class);

    public final QUsuario usuario;

    public QRelatorio(String variable) {
        this(Relatorio.class, forVariable(variable), INITS);
    }

    public QRelatorio(Path<? extends Relatorio> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRelatorio(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QRelatorio(PathMetadata<?> metadata, PathInits inits) {
        this(Relatorio.class, metadata, inits);
    }

    public QRelatorio(Class<? extends Relatorio> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.celula = inits.isInitialized("celula") ? new QCelula(forProperty("celula"), inits.get("celula")) : null;
        this.usuario = inits.isInitialized("usuario") ? new QUsuario(forProperty("usuario")) : null;
    }

}

