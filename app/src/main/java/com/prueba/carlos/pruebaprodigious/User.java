package com.prueba.carlos.pruebaprodigious;

import com.google.gson.annotations.SerializedName;

/**
 * @author <a href="mailto:carlosfelipetorres75@gmail.com">Carlos Torres</a>
 */
public class User {

    /**id user**/
    @SerializedName("id")
    private Integer id;

    /**name user**/
    @SerializedName("name")
    private String name;

    /**username**/
    @SerializedName("username")
    private String username;

    /**email user**/
    @SerializedName("email")
    private String email;

    /**phone user**/
    @SerializedName("phone")
    private String phone;

    /**website user**/
    @SerializedName("website")
    private String website;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
