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
@Entity(nameInDb = "turma")
public class Turma {

    @Id
    private Long _id;

    private int id;

    private String nome;
    private int ano;

    private Date criacao;
    private Date alteracao;

    private long pro_id;
    @ToOne(joinProperty = "pro_id")
    private Pessoa professor;

    @ToMany(referencedJoinProperty = "idTurma")
    @OrderBy("_id ASC")
    private List<Etapa> etapas;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1752625201)
    private transient TurmaDao myDao;

    @Generated(hash = 502892095)
    private transient Long professor__resolvedKey;

    @Keep
    public Turma() {
    }

    @Keep
    public Turma(Long _id, int id, String nome, int ano, Date criacao, Date alteracao, long pro_id) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.criacao = criacao;
        this.alteracao = alteracao;
        this.pro_id = pro_id;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
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

    public long getPro_id() {
        return pro_id;
    }

    public void setPro_id(long pro_id) {
        this.pro_id = pro_id;
    }

    @Keep
    public Pessoa getProfessor() {
        return professor;
    }

    @Keep
    public void setProfessor(Pessoa professor) {
        this.professor = professor;
    }

    @Keep
    public List<Etapa> getEtapas() {
        return etapas;
    }
    
    @Keep
    public void setEtapas(List<Etapa> etapas) {
        this.etapas = etapas;
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

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1925714574)
    public synchronized void resetEtapas() {
        etapas = null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 844828238)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTurmaDao() : null;
    }
}
