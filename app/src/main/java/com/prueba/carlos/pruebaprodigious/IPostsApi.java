package com.prueba.carlos.pruebaprodigious;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api de posts
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos F. Torres J.</a>
 */
public interface IPostsApi {
    /**
     * Obtiene posts
     *
     * @return Respuesta de posts
     */
    @GET("posts")
    Call<List<Post>> getPosts();

    /**
     * Obtiene un usuario especifico
     * @param idUser id de usuario
     * @return usuario
     */
    @GET("users")
    Call<List<User>> getUsuario(@Query("id") Integer idUser);
}
