package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "indicadores")
public class Indicadores {

    @Id
    private long _id;
    private String nome;
    private int valor;
    private int tipo;

//    @ToMany
//    @JoinEntity(entity = IndicadoresAtividades.class,
//            sourceProperty = "idIndicador",
//            targetProperty = "idAtividade")
//    private List<IndicadoresAtividades> lsIndicadores;

    @ToMany(referencedJoinProperty = "idIndicador")
    @OrderBy("_id ASC")
    private List<IndicadoresAtividades> lsIndicadoresAtividades;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 2078571539)
    private transient IndicadoresDao myDao;

    public Indicadores(String nome) {
        this.nome = nome;
    }

    @Keep
    public Indicadores(long _id, String nome, int valor, int tipo) {
        this._id = _id;
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Indicadores() {
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @Keep
    public List<IndicadoresAtividades> getLsIndicadoresAtividades() {
        return lsIndicadoresAtividades;
    }

    @Keep
    public void setLsIndicadoresAtividades(List<IndicadoresAtividades> lsIndicadoresAtividades) {
        this.lsIndicadoresAtividades = lsIndicadoresAtividades;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 129847840)
    public synchronized void resetLsIndicadoresAtividades() {
        lsIndicadoresAtividades = null;
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
    @Generated(hash = 2117275616)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getIndicadoresDao() : null;
    }
}
