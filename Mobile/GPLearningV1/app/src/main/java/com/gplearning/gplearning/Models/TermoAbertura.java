package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.DaoException;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity(nameInDb = "termoAbertura")
public class TermoAbertura {

    @Id
    private Long _id;

    private int id;
    private String descricao;
    private String justificativa;
    private Date criacao;
    private Date alteracao;

    private Long idProjeto;
    @ToOne(joinProperty = "idProjeto")
    private Projeto projeto;


    @ToMany(referencedJoinProperty = "idTermoAbertura")
    private List<Restricoes> restricoes;

    @ToMany(referencedJoinProperty = "idTermoAbertura")
    private List<Premissas> premissas;

    @ToMany(referencedJoinProperty = "idTermoAbertura")
    private List<Marco> marcos;

    @ToMany(referencedJoinProperty = "idTermoAbertura")
    private List<RequisitoTermoAbertura> requisitosTermoAberturas;


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

    @Generated(hash = 1369604525)
    private transient Long projeto__resolvedKey;

    public TermoAbertura() {
    }

    @Keep
    public TermoAbertura(Long _id, int id, String descricao, String justificativa, Date criacao, Date alteracao, Long idProjeto) {
        this._id = _id;
        this.id = id;
        this.descricao = descricao;
        this.justificativa = justificativa;
        this.criacao = criacao;
        this.alteracao = alteracao;
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

    @Keep
    public List<Restricoes> getRestricoes() {
        return restricoes;
    }

    @Keep
    public void setRestricoes(List<Restricoes> restricoes) {
        this.restricoes = restricoes;
    }

    @Keep
    public List<Premissas> getPremissas() {
        return premissas;
    }

    @Keep
    public void setPremissas(List<Premissas> premissas) {
        this.premissas = premissas;
    }

    @Keep
    public List<Marco> getMarcos() {
        return marcos;
    }

    @Keep
    public void setMarcos(List<Marco> marcos) {
        this.marcos = marcos;
    }

    @Keep
    public List<RequisitoTermoAbertura> getRequisitosTermoAberturas() {
        return requisitosTermoAberturas;
    }

    @Keep
    public void setRequisitosTermoAberturas(List<RequisitoTermoAbertura> requisitosTermoAberturas) {
        this.requisitosTermoAberturas = requisitosTermoAberturas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1570931294)
    public synchronized void resetRestricoes() {
        restricoes = null;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1685454026)
    public synchronized void resetPremissas() {
        premissas = null;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1063508733)
    public synchronized void resetMarcos() {
        marcos = null;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2036797600)
    public synchronized void resetRequisitosTermoAberturas() {
        requisitosTermoAberturas = null;
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
    @Generated(hash = 467792871)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTermoAberturaDao() : null;
    }
}
