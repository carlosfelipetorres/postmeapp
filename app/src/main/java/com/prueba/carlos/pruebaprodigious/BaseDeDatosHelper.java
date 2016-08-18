package com.prueba.carlos.pruebaprodigious;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.DatabaseTableConfigUtil;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Esta es la clase Helper para la base de datos SQLite en Android
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
@Singleton
public class BaseDeDatosHelper extends OrmLiteSqliteOpenHelper {

    /**
     * TAG para Logs
     **/
    private static final String TAG_LOG = BaseDeDatosHelper.class.getName();

    /**
     * Fuente de la conexión
     **/
    protected AndroidConnectionSource fuenteConexion = new AndroidConnectionSource(this);

    /**
     * Nombre de la BD
     **/
    private static final String NOMBRE_BD = "dbdplus.db";

    /**
     * Versión de la BD
     **/
    private static final int VERSION_BD = 58;

    /**
     * Inicia el Helper de la Base de Datos
     *
     * @param context El contexto de la app en el que se va a desarrollar la BD
     */
    @Inject
    public BaseDeDatosHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD, R.raw.ormlite_config);
    }

    /**
     * What to do when your database needs to be created. Usually this entails creating the tables and loading any
     * initial data.
     * <p/>
     * <p>
     * <b>NOTE:</b> You should use the connectionSource argument that is passed into this method call or the one
     * returned by getConnectionSource(). If you use your own, a recursive call or other unexpected results may result.
     * </p>
     *
     * @param db               Database being created.
     * @param connectionSource
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        configBd(db);
    }

    /**
     * What to do when your database needs to be updated. This could mean careful migration of old data to new data.
     * Maybe adding or deleting database columns, etc..
     * <p/>
     * <p>
     * <b>NOTE:</b> You should use the connectionSource argument that is passed into this method call or the one
     * returned by getConnectionSource(). If you use your own, a recursive call or other unexpected results may result.
     * </p>
     *
     * @param db               Database being upgraded.
     * @param connectionSource To use get connections to the database to be updated.
     * @param oldVersion       The version of the current database so we can know what to do to the database.
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        configBd(db, oldVersion, newVersion);
    }

    /**
     * Este método se encarga de configurar la BD
     *
     * @param db Conexión a la BD
     */
    private void configBd(SQLiteDatabase db) {
        configBd(db, null, null);
    }

    /**
     * * Este método se encarga de configurar la BD cuando existe una actualización en la versión
     * de
     * la BD. Si alguno (o los dos) de los valores de las versiones de la BD es null, se omitirá el
     * proceso de actualización y se continuará con el proceso normal de construcción
     *
     * @param db         Conexión a la BD
     * @param oldVersion Versión anterior de la BD
     * @param newVersion Versión nueva de la BD
     */
    private void configBd(SQLiteDatabase db, Integer oldVersion, Integer newVersion) {
        DatabaseConnection con = fuenteConexion.getSpecialConnection();
        boolean limpiarEspecial = false;
        if (con == null) {
            con = new AndroidDatabaseConnection(db, true);

            try {
                fuenteConexion.saveSpecialConnection(con);
                limpiarEspecial = true;
            } catch (SQLException e) {
                throw new IllegalStateException("No se pudo guardar la conexión especial", e);
            }
        }

        try {
            if (oldVersion == null || newVersion == null) {
                onCreate();
            } else {
                onUpgrade(oldVersion, newVersion);
            }
        } finally {
            if (limpiarEspecial) {
                fuenteConexion.clearSpecialConnection(con);
            }
        }
    }

    /**
     * Crea el esquema de BD
     */
    private void onCreate() {
        try {
            Log.i(TAG_LOG, "onCreate de BD");
            TableUtils.createTable(fuenteConexion, Post.class);

            Log.i(TAG_LOG, "BD creada con éxito");
        } catch (SQLException e) {
            Log.e(TAG_LOG, "No se pudo crear la BD", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Actualiza la BD dadas la versión anterior y la nueva
     *
     * @param oldVersion Versión anterior de la BD
     * @param newVersion Nueva versión de la BD
     */
    private void onUpgrade(int oldVersion, int newVersion) {
        try {
            Log.i(BaseDeDatosHelper.class.getName(), "onUpgrade");

            TableUtils.dropTable(fuenteConexion, Post.class, true);
            onCreate();
        } catch (SQLException e) {
            Log.e(BaseDeDatosHelper.class.getName(), "No se puede eliminar la BD", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método obtiene un DAO dado
     * <p/>
     * Extraido de: https://goo.gl/6LIYy2
     *
     * @param clazz Instancia de la clase pedida para el DAO
     * @param <D>   Super clase del DAO
     * @param <T>   Clase pedida para el DAO
     * @return El DAO
     * @throws SQLException
     */
    public <D extends Dao<T, ?>, T> D getDao(Class<T> clazz) throws SQLException {
        // lookup the dao, possibly invoking the cached database config
        Dao<T, ?> dao = DaoManager.lookupDao(fuenteConexion, clazz);
        if (dao == null) {
            // try to use our new reflection magic
            DatabaseTableConfig<T>
                    tableConfig = DatabaseTableConfigUtil.fromClass(fuenteConexion, clazz);
            if (tableConfig == null) {
                /**
                 * TODO: we have to do this to get to see if they are using the deprecated
                 * annotations like
                 * {@link DatabaseFieldSimple}.
                 */
                dao = (Dao<T, ?>) DaoManager.createDao(fuenteConexion, clazz);
            } else {
                dao = (Dao<T, ?>) DaoManager.createDao(fuenteConexion, tableConfig);
            }
        }

        @SuppressWarnings("unchecked")
        D castDao = (D) dao;
        return castDao;
    }
}

