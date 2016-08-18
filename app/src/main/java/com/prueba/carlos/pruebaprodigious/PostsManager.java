package com.prueba.carlos.pruebaprodigious;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class PostsManager extends CrudManager<Post, Integer> implements IPostManager {

    /**
     * Constructor para el CRUD Manager
     *  @param helper Helper de la BD*/
    protected PostsManager(BaseDeDatosHelper helper) throws SQLException {
        super(helper, Post.class);
    }

    /**
     * Este método guarda una categoría en la base de datos
     *
     * @param post
     *         Categoría a ser persistida en la BD
     *
     * @return True si fue persistida exitosamente. False de lo contrario
     */
    @Override
    public boolean crearOActualizar(Post post) {
        return crearOActualizar(post);
    }

    /**
     * Este método retorna todas los posts persistidos
     *
     * @return Lista de todos los posts
     */
    @Override
    public List<Post> allPosts() {
        List<Post> dtos = todos();
        List<Post> posts = new ArrayList<Post>(dtos.size());
        for (Post dto : dtos) {
            posts.add(dto);
        }
        return posts;
    }
}
