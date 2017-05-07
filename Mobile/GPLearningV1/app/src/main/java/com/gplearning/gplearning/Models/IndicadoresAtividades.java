package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity(nameInDb = "indicadores_atividades")
public class IndicadoresAtividades {

    @Id(autoincrement = true)
    private long _id;

    private Long idIndicador;
    @ToOne(joinProperty = "idIndicador")
    private Indicadores indicador;

    private Long idAtividade;
    @ToOne(joinProperty = "idAtividade")
    private Etapa etapa;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1007608956)
    private transient IndicadoresAtividadesDao myDao;

    @Generated(hash = 806692465)
    private transient Long indicador__resolvedKey;

    @Generated(hash = 298126781)
    private transient Long etapa__resolvedKey;

    public IndicadoresAtividades(long _id) {
        this._id = _id;
    }

    @Generated(hash = 2061885427)
    public IndicadoresAtividades(long _id, Long idIndicador, Long idAtividade) {
        this._id = _id;
        this.idIndicador = idIndicador;
        this.idAtividade = idAtividade;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }


    public IndicadoresAtividades(Long idIndicador, Long idAtividade) {
        this.idIndicador = idIndicador;
        this.idAtividade = idAtividade;
    }

    @Generated(hash = 1394050800)
    public IndicadoresAtividades() {
    }

    @Keep
    public Long getIdIndicador() {
        return idIndicador;
    }

    @Keep
    public void setIdIndicador(Long idIndicador) {
        this.idIndicador = idIndicador;
    }

    @Keep
    public Indicadores getIndicador() {
        return indicador;
    }

    @Keep
    public void setIndicador(Indicadores indicador) {
        this.indicador = indicador;
    }

    public Long getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Long idAtividade) {
        this.idAtividade = idAtividade;
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
    @Generated(hash = 191286534)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getIndicadoresAtividadesDao() : null;
    }
}
