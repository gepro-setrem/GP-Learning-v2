package com.gplearning.gplearning.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity(nameInDb = "avaliacao")
public class Avaliacao {

    @Id
    private Long _id;

    private int id;

    private int valor;

    private Date criacao;

    private Long idEtapa;
    @ToOne(joinProperty = "idEtapa")
    private Etapa etapa;

    private Long idProjeto;
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
    @Generated(hash = 1845775792)
    private transient AvaliacaoDao myDao;

    @Generated(hash = 298126781)
    private transient Long etapa__resolvedKey;

    @Generated(hash = 1369604525)
    private transient Long projeto__resolvedKey;

    public Avaliacao() {
    }

    @Keep
    public Avaliacao(Long _id, int id, int valor, Date criacao, Long idEtapa, Long idProjeto) {
        this._id = _id;
        this.id = id;
        this.valor = valor;
        this.criacao = criacao;
        this.idEtapa = idEtapa;
        this.idProjeto = idProjeto;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }

    @Keep
    public Etapa getEtapa() {
        return etapa;
    }

    @Keep
    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public Long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Long idProjeto) {
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
    @Generated(hash = 1848823519)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAvaliacaoDao() : null;
    }

}
