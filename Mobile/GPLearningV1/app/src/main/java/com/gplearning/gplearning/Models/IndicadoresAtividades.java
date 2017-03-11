package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;


@Entity(nameInDb = "indicadores_atividades")
public class IndicadoresAtividades {

    @Id(autoincrement = true)
    private long _id;

    private Long idIndicador;
    @ToOne(joinProperty = "idIndicador")
    private Indicadores indicador;

    private Long idAtividade;
    @ToOne(joinProperty = "idAtividade")
    private Atividade atividade;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1007608956)
    private transient IndicadoresAtividadesDao myDao;

    @Generated(hash = 806692465)
    private transient Long indicador__resolvedKey;

    @Generated(hash = 403996642)
    private transient Long atividade__resolvedKey;

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

    public Long getIdIndicador() {
        return idIndicador;
    }

    public void setIdIndicador(Long idIndicador) {
        this.idIndicador = idIndicador;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 289010054)
    public Indicadores getIndicador() {
        Long __key = this.idIndicador;
        if (indicador__resolvedKey == null
                || !indicador__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IndicadoresDao targetDao = daoSession.getIndicadoresDao();
            Indicadores indicadorNew = targetDao.load(__key);
            synchronized (this) {
                indicador = indicadorNew;
                indicador__resolvedKey = __key;
            }
        }
        return indicador;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1611246607)
    public void setIndicador(Indicadores indicador) {
        synchronized (this) {
            this.indicador = indicador;
            idIndicador = indicador == null ? null : indicador.getId();
            indicador__resolvedKey = idIndicador;
        }
    }

    public Long getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Long idAtividade) {
        this.idAtividade = idAtividade;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1873052142)
    public Atividade getAtividade() {
        Long __key = this.idAtividade;
        if (atividade__resolvedKey == null
                || !atividade__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AtividadeDao targetDao = daoSession.getAtividadeDao();
            Atividade atividadeNew = targetDao.load(__key);
            synchronized (this) {
                atividade = atividadeNew;
                atividade__resolvedKey = __key;
            }
        }
        return atividade;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 354233269)
    public void setAtividade(Atividade atividade) {
        synchronized (this) {
            this.atividade = atividade;
            idAtividade = atividade == null ? null : atividade.get_id();
            atividade__resolvedKey = idAtividade;
        }
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
