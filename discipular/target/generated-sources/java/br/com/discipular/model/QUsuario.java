package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUsuario is a Querydsl query type for Usuario
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = 1486133770L;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final QAbstractModel _super = new QAbstractModel(this);

    public final StringPath celular = createString("celular");

    public final StringPath email = createString("email");

    public final StringPath endereco = createString("endereco");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nome = createString("nome");

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

