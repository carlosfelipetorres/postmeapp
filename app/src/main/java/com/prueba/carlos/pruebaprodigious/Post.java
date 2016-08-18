package com.prueba.carlos.pruebaprodigious;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class Post implements Serializable {

    /**
     * id user
     **/
    @SerializedName("userId")
    @DatabaseField(canBeNull = false)
    private Integer idUser;

    /**
     * Id post
     **/
    @SerializedName("id")
    @DatabaseField(canBeNull = false, index = true)
    private Integer idPost;

    /**
     * Titulo Post
     **/
    @SerializedName("title")
    @DatabaseField(canBeNull = false)
    private String tituloPost;

    /**
     * Body del post
     **/
    @SerializedName("body")
    @DatabaseField(canBeNull = false)
    private String texto;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getTituloPost() {
        return tituloPost;
    }

    public void setTituloPost(String tituloPost) {
        this.tituloPost = tituloPost;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
