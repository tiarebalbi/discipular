package br.com.discipular.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Lucas Campos
 * @date 12/05/15
 */
@Document(collection = "usuarios")
public class MongoClass {

    @Id
    private String id;

    private String user;

    private String password;

}
