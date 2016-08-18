package com.prueba.carlos.pruebaprodigious;

import android.app.Application;

/**
 * This is the WakeMeApp Application where Dependency Injection is set up
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class PostMeAppApplication extends Application {

    DiComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerDiComponent.builder().servicesModule(new ServicesModule(this))
                .managersModule(new ManagersModule(this)).build();
    }

    public DiComponent getComponent() {
        return component;
    }

}


