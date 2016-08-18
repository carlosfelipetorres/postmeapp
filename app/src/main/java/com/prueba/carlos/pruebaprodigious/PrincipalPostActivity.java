package com.prueba.carlos.pruebaprodigious;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.google.inject.Inject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import roboguice.RoboGuice;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import roboguice.inject.RoboInjector;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
@ContentView(R.layout.activity_principal_post)
public class PrincipalPostActivity extends AppCompatActivity implements ItemClickSupport.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    /**
     * Recycler view de posts
     **/
    @InjectView(R.id.posts_rv)
    private RecyclerView mPostsRv;

    /**
     * Swipe and Refresh layout
     **/
    @InjectView(R.id.refresh_layout)
    private SwipeRefreshLayout mRefreshLayout;

    /**
     * Cargador Progress Wheel
     **/
    @InjectView(R.id.cargador_pw)
    private ProgressWheel mCargadorPw;

    /**
     * Posts mAdapter
     **/
    private PostsAdapter mAdapter;

    /**
     * Servicio posts
     **/
    @Inject
    private IServicioPosts servicioPosts = new ServicioPosts();

    /**
     * Lista de posts
     **/
    private List<Post> mPosts;

    /**
     * Posts favoritos
     **/
    private List<Post> favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_post);

        mPostsRv = (RecyclerView) this.findViewById(R.id.posts_rv);
        mRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.refresh_layout);

        mPostsRv.setLayoutManager(new LinearLayoutManager(this));
        ItemClickSupport.addTo(mPostsRv).setOnItemClickListener(this);

        AppUtils.initSwipeRefreshLayout(mRefreshLayout);
        mRefreshLayout.setOnRefreshListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            favoritos = (List<Post>) extras.get("FAVORITOS");
        }else{
            favoritos = new ArrayList<>();
        }

        new CargaInicialAsyncTask().execute();
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        Intent intent = new Intent(PrincipalPostActivity.this, DetailActivity.class);
        intent.putExtra("POST", mPosts.get(position));
        intent.putExtra("FAVORITOS", (Serializable) favoritos);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
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
            mPosts = servicioPosts.obtenerPosts();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (mPosts == null) {
                AppUtils.crearToast(PrincipalPostActivity.this, "Hubo un error obteniendo los posts", SuperToast.Duration.MEDIUM,
                        TipoNotificacion.ERROR).show();
                return;
            }

            mAdapter = new PostsAdapter(PrincipalPostActivity.this, mPosts, favoritos);
            mPostsRv.setAdapter(mAdapter);

            super.onPostExecute(aVoid);

            if (!mRefreshLayout.isRefreshing()) {
                habilitarCargador(false);
            } else {
                mRefreshLayout.setRefreshing(false);
            }
        }
    }

    /**
     * Habilita el cargador de la vista
     *
     * @param enable Si el cargador se activa o de desactiva
     */
    private void habilitarCargador(boolean enable) {
        AppUtils.enableProgressWheel(mCargadorPw, enable, mPostsRv);
    }
}
