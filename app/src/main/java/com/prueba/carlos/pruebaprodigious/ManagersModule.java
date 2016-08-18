package com.prueba.carlos.pruebaprodigious;

import android.content.Context;
import android.util.Log;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is the managers module
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
@Module
public class ManagersModule {

    /**
     * Tag for logs
     **/
    private static final String TAG = ManagersModule.class.getName();

    /**
     * Context to be injected into dependencies
     **/
    private final Context mContext;

    /**
     * Managers Module constructor
     *
     * @param context Application context
     */
    public ManagersModule(Context context) {
        this.mContext = context;
    }

    /**
     * Provides with an instance of {@link BaseDeDatosHelper}
     *
     * @return {@link BaseDeDatosHelper} instance
     */
    @Provides
    @Singleton
    public BaseDeDatosHelper databaseHelper() {
        return new BaseDeDatosHelper(mContext);
    }

    /**
     * Bind of the {@link IPostManager} with its implementation
     *
     * @param helper DB Helper
     * @return Implementation of the Posts Manager
     */
    @Provides
    @Singleton
    public IPostManager postManager(BaseDeDatosHelper helper) {
        try {
            return new PostsManager(helper);
        } catch (SQLException e) {
            Log.e(TAG, "An error occurred while creating the instance of the Alarms Manager", e);
        }
        return null;
    }
}

