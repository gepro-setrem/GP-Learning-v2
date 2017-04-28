package com.gplearning.gplearning.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Keep
@Entity(nameInDb = "pessoa")
public class Pessoa {
    @Id(autoincrement = true)
    private Long _id;

    private int id; ///só para API

    private String nome;
    private String email;
    private String telefone;
    private int karma;

    private Date criacao;


//    @Convert(converter = PapelUsuarioConverter.class, columnType = String.class)
//    private PapelUsuario papel;


    public Pessoa() {
    }

    public Pessoa(Long _id) {
        this._id = _id;
    }


    public Pessoa(Long _id, int id, String nome, String email, String telefone, int karma, Date criacao) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.karma = karma;
        this.criacao = criacao;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }


}
