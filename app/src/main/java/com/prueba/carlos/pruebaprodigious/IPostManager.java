package com.prueba.carlos.pruebaprodigious;

import java.util.List;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public interface IPostManager {
    /**
     * Este método guarda unpost en la base de datos
     *
     * @param post Post a ser persistido en la BD
     * @return True si fue persistida exitosamente. False de lo contrario
     */
    boolean crearOActualizar(Post post);

    /**
     * Trae todos los post guardados
     *
     * @return lista de posts
     */
    List<Post> allPosts();

}
