package com.prueba.carlos.pruebaprodigious;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.google.inject.Inject;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class DetailActivity extends AppCompatActivity {

    /**
     * Text view de titulo
     **/
    private TextView mTitulo;

    /**
     * Text view de body
     **/
    private TextView mBody;

    /**
     * Text view de titulo
     **/
    private TextView mNombre;

    /**
     * Text view de titulo
     **/
    private TextView mUsername;

    /**
     * Text view de titulo
     **/
    private TextView mTelefono;

    /**
     * Text view de titulo
     **/
    private TextView mCorreo;

    /**
     * Text view de titulo
     **/
    private TextView mWebsite;

    /**
     * FAB
     **/
    private com.github.clans.fab.FloatingActionButton fab;

    /**
     * Servicio posts
     **/
    @Inject
    private IServicioPosts servicioPosts = new ServicioPosts();

    /**
     * Post manager
     **/
    @Inject
    private IPostManager postManager;

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
        mTitulo = (TextView) this.findViewById(R.id.titulo_detalle_tv);
        mBody = (TextView) this.findViewById(R.id.body_detalle_tv);
        mNombre = (TextView) this.findViewById(R.id.nombre_tv);
        mUsername = (TextView) this.findViewById(R.id.username_tv);
        mTelefono = (TextView) this.findViewById(R.id.telefono_tv);
        mCorreo = (TextView) this.findViewById(R.id.correo_tv);
        mWebsite = (TextView) this.findViewById(R.id.website_tv);
        fab = (com.github.clans.fab.FloatingActionButton) this.findViewById(R.id.favorito_fab);

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


        try {
            postManager = new PostsManager(new BaseDeDatosHelper(getBaseContext()),Post.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new CargaInicialAsyncTask().execute();
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
