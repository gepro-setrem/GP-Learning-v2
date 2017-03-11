package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

@Entity(nameInDb = "indicadores")
public class Indicadores {

    @Id
    private long id;
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
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 2078571539)
    private transient IndicadoresDao myDao;

    public Indicadores(String nome) {
        this.nome = nome;
    }

    @Generated(hash = 1706605952)
    public Indicadores(long id, String nome, int valor, int tipo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    @Generated(hash = 1820549493)
    public Indicadores() {
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 713559389)
    public List<IndicadoresAtividades> getLsIndicadoresAtividades() {
        if (lsIndicadoresAtividades == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IndicadoresAtividadesDao targetDao = daoSession
                    .getIndicadoresAtividadesDao();
            List<IndicadoresAtividades> lsIndicadoresAtividadesNew = targetDao
                    ._queryIndicadores_LsIndicadoresAtividades(id);
            synchronized (this) {
                if (lsIndicadoresAtividades == null) {
                    lsIndicadoresAtividades = lsIndicadoresAtividadesNew;
                }
            }
        }
        return lsIndicadoresAtividades;
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
