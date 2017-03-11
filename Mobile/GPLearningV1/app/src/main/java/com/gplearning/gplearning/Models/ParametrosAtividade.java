package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;


@Entity(nameInDb = "parametrosAtividade")
public class ParametrosAtividade {
    @Id(autoincrement = true)
    private long _id;

    private String nome;
    private String valor;

    private long atv_id;
    @ToOne(joinProperty = "atv_id")
    private Atividade atividade;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 140681715)
    private transient ParametrosAtividadeDao myDao;

    @Generated(hash = 403996642)
    private transient Long atividade__resolvedKey;


    public ParametrosAtividade(long _id) {
        this._id = _id;
    }

    public ParametrosAtividade(String nome) {
        this.nome = nome;
    }

    @Generated(hash = 170652758)
    public ParametrosAtividade(long _id, String nome, String valor, long atv_id) {
        this._id = _id;
        this.nome = nome;
        this.valor = valor;
        this.atv_id = atv_id;
    }

    @Generated(hash = 1323943023)
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

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1109923230)
    public Atividade getAtividade() {
        long __key = this.atv_id;
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
    @Generated(hash = 1570123940)
    public void setAtividade(@NotNull Atividade atividade) {
        if (atividade == null) {
            throw new DaoException(
                    "To-one property 'atv_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.atividade = atividade;
            atv_id = atividade.get_id();
            atividade__resolvedKey = atv_id;
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
    @Generated(hash = 239183957)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getParametrosAtividadeDao() : null;
    }
}
