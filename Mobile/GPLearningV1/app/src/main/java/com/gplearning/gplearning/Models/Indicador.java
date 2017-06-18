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

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(nameInDb = "indicador")
public class Indicador {

    @Id
    private Long _id;

    private int id;

    private String nome;
    private int valor;
    private int tipo;

    private Long idTurma;
    @ToOne(joinProperty = "idTurma")
    private Turma turma;

    @ToMany(referencedJoinProperty = "idIndicador")
    @OrderBy("_id ASC")
    private List<IndicadoresAtividades> lsIndicadoresAtividades;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 818989729)
    private transient IndicadorDao myDao;

    @Generated(hash = 1191549063)
    private transient Long turma__resolvedKey;

    public Indicador(String nome) {
        this.nome = nome;
    }

    @Keep
    public Indicador(Long _id, int id, String nome, int valor, int tipo) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Indicador() {
    }

    @Generated(hash = 2046173908)
    public Indicador(Long _id, int id, String nome, int valor, int tipo, Long idTurma) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
        this.idTurma = idTurma;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    @Keep
    public Turma getTurma() {
        return turma;
    }

    @Keep
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Keep
    public List<IndicadoresAtividades> getLsIndicadoresAtividades() {
        return lsIndicadoresAtividades;
    }

    @Keep
    public void setLsIndicadoresAtividades(List<IndicadoresAtividades> lsIndicadoresAtividades) {
        this.lsIndicadoresAtividades = lsIndicadoresAtividades;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 129847840)
    public synchronized void resetLsIndicadoresAtividades() {
        lsIndicadoresAtividades = null;
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
    @Generated(hash = 1650979189)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getIndicadorDao() : null;
    }

}
