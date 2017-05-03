package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;


@Entity(nameInDb = "comentario", indexes = {
        @Index(value = "descricao, criacao DESC")})
public class Comentario {

    @Id
    private Long _id;

    private int id;//id da API

    @NotNull
    private String descricao;
    private Date criacao;

    private Long IdAtividade;
    @ToOne(joinProperty = "IdAtividade")
    private Atividade atividade;
//

    private Long IdRemetente;
    @ToOne(joinProperty = "IdRemetente")
    private Pessoa remetente;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 978109081)
    private transient ComentarioDao myDao;

    @Generated(hash = 403996642)
    private transient Long atividade__resolvedKey;

    @Generated(hash = 603090513)
    private transient Long remetente__resolvedKey;


    public Comentario() {
    }

    public Comentario(String descricao) {
        this.descricao = descricao;
    }

    @Keep
    public Comentario(Long _id, int id, @NotNull String descricao, Date criacao,
                      Long IdRemetente) {
        this._id = _id;
        this.id = id;
        this.descricao = descricao;
        this.criacao = criacao;
        this.IdRemetente = IdRemetente;
    }

    @Generated(hash = 809527407)
    public Comentario(Long _id, int id, @NotNull String descricao, Date criacao,
            Long IdAtividade, Long IdRemetente) {
        this._id = _id;
        this.id = id;
        this.descricao = descricao;
        this.criacao = criacao;
        this.IdAtividade = IdAtividade;
        this.IdRemetente = IdRemetente;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Long getIdAtividade() {
        return IdAtividade;
    }

    public void setIdAtividade(Long idAtividade) {
        IdAtividade = idAtividade;
    }

    @Keep
    public Atividade getAtividade() {
        return atividade;
    }

    @Keep
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public Long getIdRemetente() {
        return IdRemetente;
    }

    public void setIdRemetente(Long idRemetente) {
        IdRemetente = idRemetente;
    }

    @Keep
    public Pessoa getRemetente() {
        return remetente;
    }

    @Keep
    public void setRemetente(Pessoa remetente) {
        this.remetente = remetente;
    }

    @Override
    public String toString() {
        return descricao;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1701566171)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getComentarioDao() : null;
    }
}
