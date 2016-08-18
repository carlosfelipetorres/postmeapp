package com.prueba.carlos.pruebaprodigious;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class DetailActivity extends BaseActivity {

    /**Text view de titulo**/
    @BindView(R.id.titulo_detalle_tv)
    TextView mTitulo;

    /** Text view de body**/
    @BindView(R.id.body_detalle_tv)
    TextView mBody;

    /**
     * Text view de titulo
     **/
    @BindView(R.id.nombre_tv)
    TextView mNombre;

    /**
     * Text view de titulo
     **/
    @BindView(R.id.username_tv)
    TextView mUsername;

    /**
     * Text view de titulo
     **/
    @BindView(R.id.telefono_tv)
    TextView mTelefono;

    /**
     * Text view de titulo
     **/
    @BindView(R.id.correo_tv)
    TextView mCorreo;

    /**
     * Text view de titulo
     **/
    @BindView(R.id.website_tv)
    TextView mWebsite;

    /**
     * FAB
     **/
    @BindView(R.id.favorito_fab)
    com.github.clans.fab.FloatingActionButton fab;

    /**
     * Servicio posts
     **/
    @Inject
    IServicioPosts servicioPosts;

    /**
     * Post manager
     **/
    @Inject
    IPostManager postManager;

    /**
     * Usuario
     **/
    private User mUsuario;

    /**
     * Post
     **/
    private Post mPost;
    private List<Post> favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        mPost = (Post) extras.get("POST");
        if(extras.get("FAVORITOS")!= null) {
            favoritos = (List<Post>) extras.get("FAVORITOS");
        }else{
            favoritos = new ArrayList<>();
        }
//        mTitulo = (TextView) this.findViewById(R.id.titulo_detalle_tv);
//        mBody = (TextView) this.findViewById(R.id.body_detalle_tv);
//        mNombre = (TextView) this.findViewById(R.id.nombre_tv);
//        mUsername = (TextView) this.findViewById(R.id.username_tv);
//        mTelefono = (TextView) this.findViewById(R.id.telefono_tv);
//        mCorreo = (TextView) this.findViewById(R.id.correo_tv);
//        mWebsite = (TextView) this.findViewById(R.id.website_tv);
//        fab = (com.github.clans.fab.FloatingActionButton) this.findViewById(R.id.favorito_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //postManager.crearOActualizar(mPost);
                favoritos.add(mPost);
                Intent intent = new Intent(DetailActivity.this, PrincipalPostActivity.class);
                intent.putExtra("FAVORITOS", (Serializable) favoritos);
                startActivity(intent);
            }
        });
        for(int i = 0 ; i < favoritos.size(); i++){
            if(favoritos.get(i).getIdPost().equals(mPost.getIdPost())){
                fab.setEnabled(false);
            }
        }


//        try {
//            postManager = new PostsManager(new BaseDeDatosHelper(getBaseContext()),Post.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        new CargaInicialAsyncTask().execute();
    }

    /**
     * Injection component. This should be done if there are fields to be injected
     *
     * @param diComponent
     *         Dependency injection
     */
    @Override
    protected void injectComponent(DiComponent diComponent) {
        diComponent.inject(this);
    }

    /**
     * Esta clase realiza la carga inicial de manera asíncrona
     */
    private class CargaInicialAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mUsuario = servicioPosts.obtenerUsuario(mPost.getIdUser());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (mUsuario == null) {
                AppUtils.crearToast(DetailActivity.this, "Hubo un error obteniendo el usuario", SuperToast.Duration.MEDIUM,
                        TipoNotificacion.ERROR).show();
                return;
            }
            mTitulo.setText(mPost.getTituloPost());
            mBody.setText(mPost.getTexto());
            mNombre.setText(mUsuario.getName());
            mCorreo.setText(mUsuario.getEmail());
            mWebsite.setText(mUsuario.getWebsite());
            mTelefono.setText(mUsuario.getPhone());
            mUsername.setText(mUsuario.getUsername());

            super.onPostExecute(aVoid);
        }
    }
}
