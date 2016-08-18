package com.prueba.carlos.pruebaprodigious;


import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public interface ICrudManager<T, Id> {

    /**
     * Creación de la entidad T. En caso de que la entidad utilice Id autogenerado, éste debe ser
     * cargado en la instancia
     *
     * @param entidad Instancia de la entidad T a ser creada en la BD
     * @return True si la entidad pudo ser creada, False de lo contrario
     */
    boolean crearOActualizar(T entidad);

    /**
     * Busca una entidad por su Id
     *
     * @param id Id de la entidad a buscar
     * @return Retorna la instancia si existe una coincidencia. De lo contrario, retorna null
     */
    T buscarPorId(Id id);

    /**
     * Retorna una lista de todas las entidades T almacenadas
     *
     * @return Lista de las entidades almacenadas
     */
    List<T> todos();

    /**
     * Elimina una entidad T dado su Id. Retorna la entidad eliminada
     *
     * @param id Id de la entidad a eliminar
     * @return El elemento T eliminado
     */
    T eliminar(Id id);

    /**
     * Elimina todos los registros de la tabla
     **/
    void eliminarTodo();

    /**
     * Retorna el DAO de la entidad
     *
     * @return DAO de la entidad
     */
    Dao<T, Id> getDao();

}
