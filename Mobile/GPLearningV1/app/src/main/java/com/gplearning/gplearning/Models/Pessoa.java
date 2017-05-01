package com.gplearning.gplearning.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gplearning.gplearning.Converters.StatusPessoaConverter;
import com.gplearning.gplearning.Enums.StatusPessoa;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity(nameInDb = "pessoa")
public class Pessoa {
    @Id
    private Long _id;

    private int id; ///só para API

    private String nome;
    private String email;
    private String telefone;
    @Convert(converter = StatusPessoaConverter.class, columnType = String.class)
    private StatusPessoa status;
    private int karma;


    private long tur_id;
    @ToOne(joinProperty = "tur_id")
    private Turma turma;



    private Date criacao;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1409126797)
    private transient PessoaDao myDao;

    @Generated(hash = 1191549063)
    private transient Long turma__resolvedKey;

    public Pessoa() {
    }

    public Pessoa(Long _id) {
        this._id = _id;
    }

    @Keep
    public Pessoa(Long _id, int id, String nome, String email, String telefone, StatusPessoa status, int karma, long tur_id, Date criacao) {
        this._id = _id;
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
        this.karma = karma;
        this.tur_id = tur_id;
        this.criacao = criacao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public StatusPessoa getStatus() {
        return status;
    }

    public void setStatus(StatusPessoa status) {
        this.status = status;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public long getTur_id() {
        return tur_id;
    }

    public void setTur_id(long tur_id) {
        this.tur_id = tur_id;
    }

    @Keep
    public Turma getTurma() {
        return turma;
    }

    @Keep
    public void setTurma(Turma turma) {
        this.turma = turma;
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
    @Generated(hash = 403681025)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPessoaDao() : null;
    }
}
