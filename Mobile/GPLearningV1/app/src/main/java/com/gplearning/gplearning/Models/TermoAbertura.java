package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

@Entity(nameInDb = "termoAbertura")
public class TermoAbertura {

    @Id
    private int id;

    private String descricao;
    private String justificativa;
    private Date criacao;
    private Date alteracao;

    private int pro_id;
    @ToOne(joinProperty = "pro_id")
    private Projeto projeto;


    @ToMany(referencedJoinProperty = "IdTermoAbertura")
    private List<Restricoes> lsRestricoes;

    @ToMany(referencedJoinProperty = "IdTermoAbertura")
    private List<Premissas> lsPremissas;

    @ToMany(referencedJoinProperty = "IdTermoAbertura")
    private List<Marco> lsMarco;

    @ToMany(referencedJoinProperty = "IdTermoAbertura")
    private List<RequisitoTermoAbertura> lsRequisitoTermoAbertura;


    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1647359195)
    private transient TermoAberturaDao myDao;

    @Generated(hash = 415564391)
    private transient Integer projeto__resolvedKey;

    public TermoAbertura() {
    }

    @Keep
    public TermoAbertura(int id, String descricao, String justificativa, Date criacao, Date alteracao, int pro_id) {
        this.id = id;
        this.descricao = descricao;
        this.justificativa = justificativa;
        this.criacao = criacao;
        this.alteracao = alteracao;
        this.pro_id = pro_id;
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

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    @Keep
    public Projeto getProjeto() {
        return projeto;
    }

    @Keep
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1321194083)
    public List<Restricoes> getLsRestricoes() {
        if (lsRestricoes == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RestricoesDao targetDao = daoSession.getRestricoesDao();
            List<Restricoes> lsRestricoesNew = targetDao._queryTermoAbertura_LsRestricoes(id);
            synchronized (this) {
                if (lsRestricoes == null) {
                    lsRestricoes = lsRestricoesNew;
                }
            }
        }
        return lsRestricoes;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 831465351)
    public synchronized void resetLsRestricoes() {
        lsRestricoes = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1747667354)
    public List<Premissas> getLsPremissas() {
        if (lsPremissas == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PremissasDao targetDao = daoSession.getPremissasDao();
            List<Premissas> lsPremissasNew = targetDao._queryTermoAbertura_LsPremissas(id);
            synchronized (this) {
                if (lsPremissas == null) {
                    lsPremissas = lsPremissasNew;
                }
            }
        }
        return lsPremissas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 718023656)
    public synchronized void resetLsPremissas() {
        lsPremissas = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 111619938)
    public List<Marco> getLsMarco() {
        if (lsMarco == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MarcoDao targetDao = daoSession.getMarcoDao();
            List<Marco> lsMarcoNew = targetDao._queryTermoAbertura_LsMarco(id);
            synchronized (this) {
                if (lsMarco == null) {
                    lsMarco = lsMarcoNew;
                }
            }
        }
        return lsMarco;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 479589052)
    public synchronized void resetLsMarco() {
        lsMarco = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1302167210)
    public List<RequisitoTermoAbertura> getLsRequisitoTermoAbertura() {
        if (lsRequisitoTermoAbertura == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RequisitoTermoAberturaDao targetDao = daoSession.getRequisitoTermoAberturaDao();
            List<RequisitoTermoAbertura> lsRequisitoTermoAberturaNew = targetDao
                    ._queryTermoAbertura_LsRequisitoTermoAbertura(id);
            synchronized (this) {
                if (lsRequisitoTermoAbertura == null) {
                    lsRequisitoTermoAbertura = lsRequisitoTermoAberturaNew;
                }
            }
        }
        return lsRequisitoTermoAbertura;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1369674208)
    public synchronized void resetLsRequisitoTermoAbertura() {
        lsRequisitoTermoAbertura = null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 467792871)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTermoAberturaDao() : null;
    }
}
