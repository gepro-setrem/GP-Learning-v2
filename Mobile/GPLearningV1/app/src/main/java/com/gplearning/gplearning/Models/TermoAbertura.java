package com.gplearning.gplearning.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;

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

    private int idProjeto;
    @ToOne(joinProperty = "idProjeto")
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
    public TermoAbertura(Long _id, int id, String descricao, String justificativa, Date criacao, Date alteracao, int idProjeto) {
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

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
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
    public List<Restricoes> getLsRestricoes() {
        return lsRestricoes;
    }

    @Keep
    public void setLsRestricoes(List<Restricoes> lsRestricoes) {
        this.lsRestricoes = lsRestricoes;
    }

    @Keep
    public List<Premissas> getLsPremissas() {
        return lsPremissas;
    }

    @Keep
    public void setLsPremissas(List<Premissas> lsPremissas) {
        this.lsPremissas = lsPremissas;
    }

    @Keep
    public List<Marco> getLsMarco() {
        return lsMarco;
    }

    @Keep
    public void setLsMarco(List<Marco> lsMarco) {
        this.lsMarco = lsMarco;
    }

    @Keep
    public List<RequisitoTermoAbertura> getLsRequisitoTermoAbertura() {
        return lsRequisitoTermoAbertura;
    }

    @Keep
    public void setLsRequisitoTermoAbertura(List<RequisitoTermoAbertura> lsRequisitoTermoAbertura) {
        this.lsRequisitoTermoAbertura = lsRequisitoTermoAbertura;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 831465351)
    public synchronized void resetLsRestricoes() {
        lsRestricoes = null;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 718023656)
    public synchronized void resetLsPremissas() {
        lsPremissas = null;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 479589052)
    public synchronized void resetLsMarco() {
        lsMarco = null;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1369674208)
    public synchronized void resetLsRequisitoTermoAbertura() {
        lsRequisitoTermoAbertura = null;
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
