package br.com.discipular.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QAbstractModel is a Querydsl query type for AbstractModel
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QAbstractModel extends EntityPathBase<AbstractModel> {

    private static final long serialVersionUID = 224915971L;

    public static final QAbstractModel abstractModel = new QAbstractModel("abstractModel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAbstractModel(String variable) {
        super(AbstractModel.class, forVariable(variable));
    }

    public QAbstractModel(Path<? extends AbstractModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAbstractModel(PathMetadata<?> metadata) {
        super(AbstractModel.class, metadata);
    }

}

