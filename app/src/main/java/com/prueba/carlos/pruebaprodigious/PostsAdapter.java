package com.prueba.carlos.pruebaprodigious;

import java.util.List;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Esta clase es utilizada para adaptar los posts en un ListView
 *
 * @author <a href="mailto:carlos-torres@accionplus.com">Carlos Torres</a>
 */
public class PostsAdapter extends RecyclerView.Adapter {

    /**
     * Contexto actual
     **/
    private Context mContext;

    /**
     * Inflater
     **/
    private LayoutInflater mInflater;

    /**
     * Los despachos a ser mostrados
     **/
    private List<Post> mPosts;
    private List<Post> favoritos;

    public PostsAdapter(Context context, List<Post> posts, List<Post> favoritos) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mPosts = posts;
        this.favoritos = favoritos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vistaDespacho = mInflater.inflate(R.layout.post_row, parent, false);
        return new ViewHolder(vistaDespacho);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Post post = mPosts.get(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.titulo.setText(post.getTituloPost().toUpperCase());
        for(int i = 0; i < favoritos.size();i++){
            if(favoritos.get(i).getIdPost().equals(post.getIdPost())){
                vh.star.setVisibility(View.VISIBLE);
                break;
            }else {
                vh.star.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    @Override
    public long getItemId(int position) {
        return mPosts.get(position).getIdPost();
    }

    /**
     * Clase que contiene los elementos de la vista
     */
    private static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         *Titulo
         **/
        public TextView titulo;
        /**
         * Estrella
         **/
        public ImageView star;

        public ViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titulo_rtv);
            star = (ImageView) itemView.findViewById(R.id.favorito_star);
        }
    }
}
