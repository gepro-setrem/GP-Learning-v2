package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(nameInDb = "requisitoTermoAbertura")
public class RequisitoTermoAbertura {

    @Id
    private int id;
    private String nome;
    private String descricao;

    private int IdTermoAbertura;
    @ToOne(joinProperty = "IdTermoAbertura")
    private TermoAbertura termoAbertura;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 958475097)
    private transient RequisitoTermoAberturaDao myDao;
    @Generated(hash = 1154405152)
    private transient Integer termoAbertura__resolvedKey;

    public RequisitoTermoAbertura() {
    }

    @Keep
    public RequisitoTermoAbertura(int id, String nome, String descricao, int IdTermoAbertura) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.IdTermoAbertura = IdTermoAbertura;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdTermoAbertura() {
        return IdTermoAbertura;
    }

    public void setIdTermoAbertura(int IdTermoAbertura) {
        this.IdTermoAbertura = IdTermoAbertura;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 626913611)
    public TermoAbertura getTermoAbertura() {
        int __key = this.IdTermoAbertura;
        if (termoAbertura__resolvedKey == null || !termoAbertura__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TermoAberturaDao targetDao = daoSession.getTermoAberturaDao();
            TermoAbertura termoAberturaNew = targetDao.load(__key);
            synchronized (this) {
                termoAbertura = termoAberturaNew;
                termoAbertura__resolvedKey = __key;
            }
        }
        return termoAbertura;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 744447317)
    public void setTermoAbertura(@NotNull TermoAbertura termoAbertura) {
        if (termoAbertura == null) {
            throw new DaoException(
                    "To-one property 'IdTermoAbertura' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.termoAbertura = termoAbertura;
            IdTermoAbertura = termoAbertura.getId();
            termoAbertura__resolvedKey = IdTermoAbertura;
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
    @Generated(hash = 1047992156)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRequisitoTermoAberturaDao() : null;
    }
}
