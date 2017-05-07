package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity(nameInDb = "parametrosAtividade")
public class ParametrosAtividade {
    @Id(autoincrement = true)
    private long _id;

    private String nome;
    private String valor;

    private long atv_id;
    @ToOne(joinProperty = "atv_id")
    private Etapa etapa;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 140681715)
    private transient ParametrosAtividadeDao myDao;

    @Generated(hash = 298126781)
    private transient Long etapa__resolvedKey;


    public ParametrosAtividade(long _id) {
        this._id = _id;
    }

    public ParametrosAtividade(String nome) {
        this.nome = nome;
    }

    @Keep
    public ParametrosAtividade(long _id, String nome, String valor, long atv_id) {
        this._id = _id;
        this.nome = nome;
        this.valor = valor;
        this.atv_id = atv_id;
    }

    @Keep
    public ParametrosAtividade() {
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public long getAtv_id() {
        return atv_id;
    }

    public void setAtv_id(long atv_id) {
        this.atv_id = atv_id;
    }

    @Keep
    public Etapa getEtapa() {
        return etapa;
    }

    @Keep
    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
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
    @Generated(hash = 239183957)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getParametrosAtividadeDao() : null;
    }
}
