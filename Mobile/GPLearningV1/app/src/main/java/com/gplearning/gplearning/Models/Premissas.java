package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity(nameInDb = "premissas")
public class Premissas {

    @Id
    private Long _id;
    private int id;

    private String descricao;

    private int idTermoAbertura;
    @ToOne(joinProperty = "idTermoAbertura")
    private TermoAbertura termoAbertura;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 81685024)
    private transient PremissasDao myDao;

    @Generated(hash = 1154405152)
    private transient Integer termoAbertura__resolvedKey;


    public Premissas() {
    }

    @Keep
    public Premissas(Long _id, int id, String descricao, int idTermoAbertura) {
        this._id = _id;
        this.id = id;
        this.descricao = descricao;
        this.idTermoAbertura = idTermoAbertura;
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

    public int getIdTermoAbertura() {
        return idTermoAbertura;
    }

    public void setIdTermoAbertura(int idTermoAbertura) {
        this.idTermoAbertura = idTermoAbertura;
    }

    @Keep
    public TermoAbertura getTermoAbertura() {
        return termoAbertura;
    }

    @Keep
    public void setTermoAbertura(TermoAbertura termoAbertura) {
        this.termoAbertura = termoAbertura;
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
    @Generated(hash = 1461463140)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPremissasDao() : null;
    }

}
