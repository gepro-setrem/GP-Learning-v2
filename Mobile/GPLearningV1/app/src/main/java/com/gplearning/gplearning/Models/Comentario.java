package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;


@Entity(nameInDb = "comentario", indexes = {
        @Index(value = "texto, criacao DESC")})
public class Comentario {

    @Id
    private Long _id;

    private int id;//id da API

    @NotNull
    private String texto;
    private Date criacao;

    //    private Long atv_id;
//    @ToOne(joinProperty = "atv_id")
//    private Atividade atividade;
//

    private Long use_id;
//    @ToOne(joinProperty = "use_id")
//    private Usuario usuario;


    public Comentario(String texto) {
        this.texto = texto;
    }

    @Generated(hash = 1947443568)
    public Comentario(Long _id, int id, @NotNull String texto, Date criacao,
            Long use_id) {
        this._id = _id;
        this.id = id;
        this.texto = texto;
        this.criacao = criacao;
        this.use_id = use_id;
    }

    @Generated(hash = 751223151)
    public Comentario() {
    }

    public Long get_id() {
        return _id;
    }

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

//    public Long getAtv_id() {
//        return atv_id;
//    }
//
//    public void setAtv_id(Long atv_id) {
//        this.atv_id = atv_id;
//    }

    public void set_id(Long _id) {
        this._id = _id;
    }

 

    public Long getUse_id() {
        return this.use_id;
    }

    public void setUse_id(Long use_id) {
        this.use_id = use_id;
    }

    @Override
    public String toString() {
        return texto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

//    public int getId() {
//        return this.id;
//    }


}
