package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gplearning.gplearning.Converters.PapelUsuarioConverter;
import com.gplearning.gplearning.Enums.PapelUsuario;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "login_role")
public class LoginRole {

    @Id
    private Long _id;

    @Convert(converter = PapelUsuarioConverter.class, columnType = String.class)
    private PapelUsuario role;


    @Transient
    private Login login;

    public LoginRole() {
    }

    @Generated(hash = 795788534)
    public LoginRole(Long _id, PapelUsuario role) {
        this._id = _id;
        this.role = role;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public PapelUsuario getRole() {
        return role;
    }

    public void setRole(PapelUsuario role) {
        this.role = role;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
