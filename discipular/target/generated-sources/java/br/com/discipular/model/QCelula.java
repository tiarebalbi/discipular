package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCelula is a Querydsl query type for Celula
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCelula extends EntityPathBase<Celula> {

    private static final long serialVersionUID = 1182004676L;

    public static final QCelula celula = new QCelula("celula");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final EnumPath<br.com.discipular.enumerator.DiaSemana> dia = createEnum("dia", br.com.discipular.enumerator.DiaSemana.class);

    public final StringPath endereco = createString("endereco");

    public final EnumPath<br.com.discipular.enumerator.Horario> horario = createEnum("horario", br.com.discipular.enumerator.Horario.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<Membro, QMembro> membros = this.<Membro, QMembro>createList("membros", Membro.class, QMembro.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> nascimento = createDate("nascimento", java.time.LocalDate.class);

    public final StringPath nome = createString("nome");

    public QCelula(String variable) {
        super(Celula.class, forVariable(variable));
    }

    public QCelula(Path<? extends Celula> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCelula(PathMetadata<?> metadata) {
        super(Celula.class, metadata);
    }

}

