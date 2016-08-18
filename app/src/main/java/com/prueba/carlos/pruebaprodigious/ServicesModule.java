package com.prueba.carlos.pruebaprodigious;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
@Module
public class ServicesModule {

    /**
     * Context to be injected into dependencies
     **/
    private final Context mContext;

    /**
     * Services Module constructor
     *
     * @param context Application context
     */
    public ServicesModule(Context context) {
        mContext = context;
    }

    /**
     * Bind of the {@link IServicioPosts} with its implementation
     *
     * @return Posts Service implementation
     */
    @Provides
    @Singleton
    public IServicioPosts servicioPosts() {
        return new ServicioPosts(mContext);
    }

    /**
     * Bind of the {@link IServicioPosts} with its implementation
     *
     * @return Posts Service implementation
     */
    @Provides
    @Singleton
    public IClientePostsSystem clientePostsSystem() {
        return new ClientePostsSystem();
    }

    /**
     * Injection of the application context
     *
     * @return Application context
     */
    @Provides
    public Context context() {
        return mContext;
    }

}

