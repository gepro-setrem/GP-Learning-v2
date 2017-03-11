package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;


@Entity(nameInDb = "tarefa")
public class Tarefa {

    @Id(autoincrement = true)
    private Long _id;

    private Long id;
    @NotNull
    private String nome;
    private Date termino;

    private Long idEap;
    @ToOne(joinProperty = "idEap")
    private Eap eap;


    @ToMany(referencedJoinProperty = "idTarefa")
    @OrderBy("_id ASC")
    private List<SubTarefa> lsSubTarefas;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1744162860)
    private transient TarefaDao myDao;

    @Generated(hash = 626929379)
    private transient Long eap__resolvedKey;


    public Tarefa() {
    }

    public Tarefa(String nome) {
        this.nome = nome;
    }

    // @Transient = notMappep

    @Generated(hash = 158035812)
    public Tarefa(Long _id, Long id, @NotNull String nome, Date termino, Long idEap) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.termino = termino;
        this.idEap = idEap;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdEap(Long idEap) {
        this.idEap = idEap;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public long getIdEap() {
        return idEap;
    }

    public void setIdEap(long idEap) {
        this.idEap = idEap;
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 863157900)
    public Eap getEap() {
        Long __key = this.idEap;
        if (eap__resolvedKey == null || !eap__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EapDao targetDao = daoSession.getEapDao();
            Eap eapNew = targetDao.load(__key);
            synchronized (this) {
                eap = eapNew;
                eap__resolvedKey = __key;
            }
        }
        return eap;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 605830076)
    public void setEap(Eap eap) {
        synchronized (this) {
            this.eap = eap;
            idEap = eap == null ? null : eap.get_id();
            eap__resolvedKey = idEap;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1843028590)
    public List<SubTarefa> getLsSubTarefas() {
        if (lsSubTarefas == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SubTarefaDao targetDao = daoSession.getSubTarefaDao();
            List<SubTarefa> lsSubTarefasNew = targetDao._queryTarefa_LsSubTarefas(_id);
            synchronized (this) {
                if (lsSubTarefas == null) {
                    lsSubTarefas = lsSubTarefasNew;
                }
            }
        }
        return lsSubTarefas;
    }

    public void setLsSubTarefas(List<SubTarefa> lsSubTarefas) {
        this.lsSubTarefas = lsSubTarefas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 312574848)
    public synchronized void resetLsSubTarefas() {
        lsSubTarefas = null;
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
    @Generated(hash = 139998335)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTarefaDao() : null;
    }
}
