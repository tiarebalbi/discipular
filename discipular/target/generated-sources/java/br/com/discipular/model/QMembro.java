package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMembro is a Querydsl query type for Membro
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMembro extends EntityPathBase<Membro> {

    private static final long serialVersionUID = 1468307918L;

    public static final QMembro membro = new QMembro("membro");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final StringPath celular = createString("celular");

    public final StringPath email = createString("email");

    public final StringPath endereco = createString("endereco");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DatePath<java.time.LocalDate> nascimento = createDate("nascimento", java.time.LocalDate.class);

    public final StringPath nome = createString("nome");

    public final EnumPath<br.com.discipular.enumerator.TipoMembro> tipo = createEnum("tipo", br.com.discipular.enumerator.TipoMembro.class);

    public QMembro(String variable) {
        super(Membro.class, forVariable(variable));
    }

    public QMembro(Path<? extends Membro> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMembro(PathMetadata<?> metadata) {
        super(Membro.class, metadata);
    }

}

