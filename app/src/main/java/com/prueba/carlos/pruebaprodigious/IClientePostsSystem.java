package com.prueba.carlos.pruebaprodigious;


import retrofit2.Call;
import retrofit2.Response;

/**
 * Esta es la versión 2 del cliente de DBD+ basado en el framework Retrofi2. Esta interface ofrece
 * los métodos básicos utilizados para la comunicación con el servidor
 *
 * @author <a href="mailto:antonio-jimenez@accionplus.com">Antonio A. Jimenez N.</a>
 */
public interface IClientePostsSystem {

    /**
     * This method returns the API of the selected class
     *
     * @param clazz Class of the desired API
     * @param <T>   API class
     * @return the instance API
     */
    <T> T getApi(Class<T> clazz);

    /**
     * This method executes a call to the server and returns the result. If an error occurs, then
     * null is returned
     *
     * @param call Call to be executed
     * @param <T>  Response type
     * @return The response. Returns null if an error occurs
     */
    <T> Response<T> execute(Call<T> call);

}
