package com.gplearning.gplearning.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

@JsonIgnoreProperties(ignoreUnknown = true)
@Keep
@Entity(nameInDb = "usuario")
public class Usuario {
    @Id(autoincrement = true)
    private long _id;

    private long id;
    private String nome;
    private String email;
    private String telefone;
    private int karma;
//    @Convert(converter = PapelUsuarioConverter.class, columnType = String.class)
//    private PapelUsuario papel;

    public Usuario(long _id) {
        this._id = _id;
    }



    public Usuario(long _id, long id, String nome, String email, String telefone, int karma) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.karma = karma;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

}
