package com.prueba.carlos.pruebaprodigious;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dependency Injection component where all Activities, fragments and adapters are injected
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
@Singleton
@Component(modules = {ManagersModule.class, ServicesModule.class})
public interface DiComponent {

    /**
     * Context injection
     *
     * @return Injected context
     */
    Context context();

    // Common Activities
    void inject(MainActivity activity);

    // Post Activities
    void inject(PrincipalPostActivity activity);
    void inject(PostsAdapter adapter);

    // Detail Activities
    void inject(DetailActivity activity);
}
