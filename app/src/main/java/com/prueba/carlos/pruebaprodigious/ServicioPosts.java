package com.prueba.carlos.pruebaprodigious;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class ServicioPosts implements IServicioPosts {

    /**
     * Cliente servidor
     **/
    @Inject
    IClientePostsSystem mCliente = new ClientePostsSystem();

    /**
     * Singleton API visitas
     **/
    private IPostsApi mPostsApi;

    /**
     * Application context
     **/
    private Context mContext;

    public ServicioPosts(Context context) {
        this.mContext = context;
    }

    @Override
    public List<Post> obtenerPosts() {
        IPostsApi postsApi = getPostsApi();

        Call<List<Post>> call = postsApi.getPosts();
        Response<List<Post>> response = mCliente.execute(call);
        if (!isSuccessful(response)) {
            return null;
        }
        return response.body();
    }

    @Override
    public User obtenerUsuario(Integer idUser) {
        IPostsApi postsApi = getPostsApi();

        Call<List<User>> call = postsApi.getUsuario(idUser);
        Response<List<User>> response = mCliente.execute(call);
        if (!isSuccessful(response)) {
            return null;
        }
        return response.body().get(0);
    }

    /**
     * Obtiene el API de las visitas del cliente
     *
     * @return La interfaz del API de vencimisntos de producto
     */
    private IPostsApi getPostsApi() {
        if (mPostsApi == null) {
            mPostsApi = mCliente.getApi(IPostsApi.class);
        }
        return mPostsApi;
    }

    /**
     * Este metodo verifica si la respuesta fue exitosa o no
     *
     * @param response La respuesta para ser verificada
     * @return True si fue exitoso. False de lo contrario.
     */
    public static boolean isSuccessful(Response response) {
        return response != null && response.isSuccessful();
    }
}
