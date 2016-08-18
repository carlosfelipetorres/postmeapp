package com.prueba.carlos.pruebaprodigious;
import com.google.inject.AbstractModule;

/**
 * This is the RoboGuice configuration class
 *
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class ModuloConfiguracion extends AbstractModule {

    @Override
    protected void configure() {
        bindServices();
        bindManagers();
        bindOthers();
    }

    /**
     * Este método ata otras clases como clientes y clases personalizadas
     */
    private void bindOthers() {
        bind(IClientePostsSystem.class).to(ClientePostsSystem.class);
    }

    /**
     * Este método ata los managers
     */
    private void bindManagers() {
        bind(IPostManager.class).to(PostsManager.class);
    }

    /**
     * Este método ata los servicios
     */
    private void bindServices() {
        bind(IServicioPosts.class).to(ServicioPosts.class);
    }
}
