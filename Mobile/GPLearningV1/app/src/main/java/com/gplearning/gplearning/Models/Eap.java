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
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "eap")
public class Eap {
    @Id
    private Long _id;

    private Long id;
    private String nome;
    private String descricao;
    @Transient
    private String position;
    @Transient
    private Boolean eap;
    private Date inicio;
    private Date entrega;
    private double valor;

    private Long idPai;
    @ToOne(joinProperty = "idPai")
    private Eap EapPai;

    @ToMany(referencedJoinProperty = "idPai")
    private List<Eap> eaps;

    @ToMany(referencedJoinProperty = "idEap")
    @OrderBy("_id ASC")
    private List<Tarefa> tarefas;

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
    public Eap(Long _id, Long id, String nome, String descricao, String position, Boolean eap, Date inicio, Date entrega, double valor, Long idPai, long idProjeto) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.position = position;
        this.eap = eap;
        this.inicio = inicio;
        this.entrega = entrega;
        this.valor = valor;
        this.idPai = idPai;
        this.idProjeto = idProjeto;
    }

    @Keep
    public Eap(Long _id, Long id, String nome, String descricao, String position, Boolean eap) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.position = position;
        this.eap = eap;
    }

    @Generated(hash = 1999836360)
    public Eap(Long _id, Long id, String nome, String descricao, Date inicio, Date entrega, double valor, Long idPai, long idProjeto) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.inicio = inicio;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getEap() {
        return eap;
    }

    public void setEap(Boolean eap) {
        this.eap = eap;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
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
    public List<Eap> getEaps() {
        return eaps;
    }

    @Keep
    public void setEaps(List<Eap> eaps) {
        this.eaps = eaps;
    }

    @Keep
    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    @Keep
    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
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

    @Override
    public String toString() {
        return position+"-"+ nome;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 667306427)
    public synchronized void resetEaps() {
        eaps = null;
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
    @Generated(hash = 1976154569)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEapDao() : null;
    }


}
