package com.gplearning.gplearning.Models;


import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;

@Entity(nameInDb = "stakeholder")
public class Stakeholder {
    @Id
    private Long _id;

    private int id;
    private String nome;
    private String contribuicao;
    private String papel;

    private int IdProjeto;
    @ToOne(joinProperty = "IdProjeto")
    private Projeto projeto;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 2085601650)
    private transient StakeholderDao myDao;

    @Generated(hash = 415564391)
    private transient Integer projeto__resolvedKey;

    @Keep
    public Stakeholder(Long _id, int id, String nome, String contribuicao, String papel, int idProjeto) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.contribuicao = contribuicao;
        this.papel = papel;
        IdProjeto = idProjeto;
    }

    public Stakeholder() {
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

    public String getContribuicao() {
        return contribuicao;
    }

    public void setContribuicao(String contribuicao) {
        this.contribuicao = contribuicao;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public int getIdProjeto() {
        return IdProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        IdProjeto = idProjeto;
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

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1078832940)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStakeholderDao() : null;
    }
}
