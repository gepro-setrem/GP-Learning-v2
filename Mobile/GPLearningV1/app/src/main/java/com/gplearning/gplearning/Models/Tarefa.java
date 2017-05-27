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
    private Date inicio;
    private Date termino;
    private Date conclusao;
    private Boolean marco;

    private Long idEap;
    @ToOne(joinProperty = "idEap")
    private Eap eap;

    private Long idPai;
    @ToOne(joinProperty = "idPai")
    private Tarefa pai;

    @ToMany(referencedJoinProperty = "idPai")
    @OrderBy("_id ASC")
    private List<Tarefa> tarefas;

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

    @Generated(hash = 1083567250)
    private transient Long pai__resolvedKey;


    public Tarefa() {
    }

    public Tarefa(String nome) {
        this.nome = nome;
    }

    @Keep
    public Tarefa(Long _id, Long id, String nome, Date inicio, Date termino, Date conclusao, Boolean marco) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.inicio = inicio;
        this.termino = termino;
        this.conclusao = conclusao;
        this.marco = marco;
    }

    @Generated(hash = 2132067516)
    public Tarefa(Long _id, Long id, String nome, Date inicio, Date termino, Date conclusao, Boolean marco,
            Long idEap, Long idPai) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.inicio = inicio;
        this.termino = termino;
        this.conclusao = conclusao;
        this.marco = marco;
        this.idEap = idEap;
        this.idPai = idPai;
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

    public Long getIdPai() {
        return idPai;
    }

    public void setIdPai(Long idPai) {
        this.idPai = idPai;
    }

    @Keep
    public Tarefa getPai() {
        return pai;
    }

    @Keep
    public void setPai(Tarefa pai) {
        this.pai = pai;
    }

    @Keep
    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    @Keep
    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public String toString() {
        return nome;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getConclusao() {
        return conclusao;
    }

    public void setConclusao(Date conclusao) {
        this.conclusao = conclusao;
    }

    public Boolean getMarco() {
        return marco;
    }

    public void setMarco(Boolean marco) {
        this.marco = marco;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1806373937)
    public synchronized void resetTarefas() {
        tarefas = null;
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
