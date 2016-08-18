package com.prueba.carlos.pruebaprodigious;

import java.util.List;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public interface IServicioPosts {

    /**
     * Obtiene los posts
     *
     * @return posts
     */
    List<Post> obtenerPosts();

    /**
     * Obtiene usuario por id
     *
     * @param idUser id usuario
     * @return usuario
     */
    User obtenerUsuario(Integer idUser);
}
