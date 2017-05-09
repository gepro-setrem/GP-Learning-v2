package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.DaoException;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "eap")
public class Eap {
    @Id
    private Long _id;

    private Long id;
    private String nome;
    private Date entrega;
    private double valor;

    private Long idPai;
    @ToOne(joinProperty = "idPai")
    private Eap EapPai;

    @ToMany(referencedJoinProperty = "idPai")
    private List<Eap> lsEapSub;

    @ToMany(referencedJoinProperty = "idEap")
    @OrderBy("_id ASC")
    private List<Tarefa> lsTarefas;

    private long idProjeto;
    @ToOne(joinProperty = "idProjeto")
    private Projeto projeto;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1316680655)
    private transient EapDao myDao;

    @Generated(hash = 886354405)
    private transient Long EapPai__resolvedKey;

    @Generated(hash = 1369604525)
    private transient Long projeto__resolvedKey;

    @Keep
    public Eap() {
    }

    @Keep
    public Eap(Long _id, Long id, String nome, Date entrega, double valor, Long idPai, long idProjeto) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.entrega = entrega;
        this.valor = valor;
        this.idPai = idPai;
        this.idProjeto = idProjeto;
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

    public void setIdPai(Long idPai) {
        this.idPai = idPai;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getEntrega() {
        return entrega;
    }

    public void setEntrega(Date entrega) {
        this.entrega = entrega;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getIdPai() {
        return idPai;
    }

    @Keep
    public Eap getEapPai() {
        return EapPai;
    }

    @Keep
    public void setEapPai(Eap eapPai) {
        EapPai = eapPai;
    }

    @Keep
    public List<Eap> getLsEapSub() {
        return lsEapSub;
    }

    @Keep
    public void setLsEapSub(List<Eap> lsEapSub) {
        this.lsEapSub = lsEapSub;
    }

    @Keep
    public List<Tarefa> getLsTarefas() {
        return lsTarefas;
    }

    @Keep
    public void setLsTarefas(List<Tarefa> lsTarefas) {
        this.lsTarefas = lsTarefas;
    }

    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    @Keep
    public Projeto getProjeto() {
        return projeto;
    }

    @Keep
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2081873823)
    public synchronized void resetLsEapSub() {
        lsEapSub = null;
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
    @Generated(hash = 1976154569)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEapDao() : null;
    }
}
