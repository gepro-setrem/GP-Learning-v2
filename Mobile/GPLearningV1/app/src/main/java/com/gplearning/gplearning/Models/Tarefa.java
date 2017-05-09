package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity(nameInDb = "tarefa")
public class Tarefa {

    @Id
    private Long _id;

    private Long id;
    private String nome;
    private Date termino;

    private Long idEap;
    @ToOne(joinProperty = "idEap")
    private Eap eap;

    private Long idTarefa;
    @ToOne(joinProperty = "idTarefa")
    private Tarefa TarefaPai;

    @ToMany(referencedJoinProperty = "idTarefa")
    @OrderBy("_id ASC")
    private List<Tarefa> lsTarefas;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1744162860)
    private transient TarefaDao myDao;

    @Generated(hash = 626929379)
    private transient Long eap__resolvedKey;

    @Generated(hash = 1823071567)
    private transient Long TarefaPai__resolvedKey;


    public Tarefa() {
    }

    public Tarefa(String nome) {
        this.nome = nome;
    }

    // @Transient = notMappep

    @Generated(hash = 350609841)
    public Tarefa(Long _id, Long id, String nome, Date termino, Long idEap, Long idTarefa) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.termino = termino;
        this.idEap = idEap;
        this.idTarefa = idTarefa;
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

    public Long getIdEap() {
        return idEap;
    }

    public void setIdEap(Long idEap) {
        this.idEap = idEap;
    }

    @Keep
    public Eap getEap() {
        return eap;
    }

    @Keep
    public void setEap(Eap eap) {
        this.eap = eap;
    }

    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    @Keep
    public Tarefa getTarefaPai() {
        return TarefaPai;
    }

    @Keep
    public void setTarefaPai(Tarefa tarefaPai) {
        TarefaPai = tarefaPai;
    }

    @Keep
    public List<Tarefa> getLsTarefas() {
        return lsTarefas;
    }

    @Keep
    public void setLsTarefas(List<Tarefa> lsTarefas) {
        this.lsTarefas = lsTarefas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 997083221)
    public synchronized void resetLsTarefas() {
        lsTarefas = null;
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
